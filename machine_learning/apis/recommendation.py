from flask import Flask, request, jsonify, Blueprint, current_app, g
from flask_restx import Api, Resource
from db import db

import numpy as np
import pandas as pd
from sklearn.metrics.pairwise import cosine_similarity
from sqlalchemy.exc import SQLAlchemyError

from models.Book import Book
from models.Member import Member
from models.Genre import Genre
from models.MemberGenre import MemberGenre
from models.MemberMyBook import MemberMyBook
from models.MemberMyLike import MemberMyLike

from random import sample
from urllib.parse import unquote


import logging


# 로거 설정
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

recommendation_blueprint = Blueprint('recommendation', __name__)
api = Api(recommendation_blueprint)



# 전처리된 책 데이터 불러오기
books = pd.read_pickle("data/preprocessed_book_introduce.pkl")
# 책 데이터 임베딩 처리한 데이터 불러오기
book_embeddings = np.load("data/book_embeddings.npy", allow_pickle=True)


# 전처리된 전공 데이터 불러오기
majors = pd.read_pickle("data/preprocessed_major_introduce.pkl")
# 전공 데이터랑 책 데이터 임베딩 처리한 데이터 불러오기
book_major_embedding = np.load("data/major_book_matrix.npy")

def calculate_score(similarity):
    ''' 유사도를 점수로 반환
    - paramter
        - similarity : 두 책간의 유사도 (0.0 ~ 1.0)

    - return
        - 환산 점수
    '''
    score = 1
    if similarity >= 0.9:
        score = 1.5
    elif similarity >= 0.7:
        score = 1
    elif similarity >= 0.6:
        score = 0.8
    else:
        score = 0.5

    return score * similarity


vcalculate_score = np.vectorize(calculate_score)

def find_similar_books(book_id, top_n=10):
    # 주어진 책의 임베딩을 찾기
    book_index = books.index[books['id'] == book_id].tolist()[0]  # 책의 인덱스를 찾기
    target_book_embedding = book_embeddings[book_index].reshape(1, -1)

    # 모든 책과의 코사인 유사도를 계산
    cos_similarities = cosine_similarity(target_book_embedding, book_embeddings)

    # 유사도가 높은 상위 N개 책의 인덱스 가져오기
    similar_indices = cos_similarities.argsort()[0][-top_n-1:-1][::-1]

    # 해당 인덱스의 책 정보를 반환
    similar_books = books.iloc[similar_indices]
    return similar_books

def get_interruption_reasons(member_id):
    # 사용자가 중단한 도서 목록을 가져오기
    interrupted_books = MemberMyBook.query.filter_by(
        member_entity_id=member_id, status=2  # status 2가 중단임
    ).all()

    interruption_reasons = {
        'high_difficulty': [],
        'low_difficulty': [],
        'many_pages': [],
        'few_pages': [],
        'disliked_genre': []
    }

    # 중단 사유별로 도서의 특성을 분류
    for book in interrupted_books:
        if book.status_detail == '1':
            interruption_reasons['high_difficulty'].append(book.book_entity_id)
        elif book.status_detail == '2':
            interruption_reasons['low_difficulty'].append(book.book_entity_id)
        elif book.status_detail == '3':
            interruption_reasons['many_pages'].append(book.book_entity_id)
        elif book.status_detail == '4':
            interruption_reasons['few_pages'].append(book.book_entity_id)
        elif book.status_detail == '5':
            interruption_reasons['disliked_genre'].append(book.book_entity_id)
            logger.info(f"Book with ID {book.book_entity_id} added to disliked_genre for member {member_id}")

    # 모든 책을 확인한 후에 사유별 분류를 반환
    return interruption_reasons


def update_disliked_genre_with_genre_numbers(member_id, interruption_reasons):
    # 장르를 싫어하는 책의 ID
    disliked_book_ids = interruption_reasons['disliked_genre']

    # 각 책의 장르 번호를 찾기
    genre_numbers = []
    for book_id in disliked_book_ids:
        book_genre = Book.query.get(book_id).bookGenre
        genre_numbers.append(book_genre)

        logger.info(f"Genre number {book_genre} found for book ID {book_id}")

    interruption_reasons['disliked_genre'] = genre_numbers

    # 업데이트된 사유별 분류를 반환
    return interruption_reasons


# 난이도를 숫자로 변환하는 딕셔너리를 전역 변수로 선언
difficulty_levels = {'0': 0, '1': 1, '2': 2, '3': 3, '4': 4, '5': 5}


def get_average_difficulty_and_page_count(interruption_reasons):
    # 난이도와 페이지 수의 평균을 계산
    difficulties = [
        Book.query.get(book_id).bookDifficulty
        for book_id in interruption_reasons['high_difficulty']
        if Book.query.get(book_id).bookDifficulty != '0'  # 난이도 0인 책은 배제
    ]
    page_counts = [
        Book.query.get(book_id).bookPage
        for book_id in interruption_reasons['many_pages']
    ]

    # 문자열 난이도를 숫자로 변환
    numeric_difficulties = [
        difficulty_levels.get(difficulty, 3)  # 기본값 중간 난이도
        for difficulty in difficulties
    ]

    avg_difficulty = sum(numeric_difficulties) / len(numeric_difficulties) if numeric_difficulties else None
    avg_page_count = sum(page_counts) / len(page_counts) if page_counts else None

    return avg_difficulty, avg_page_count


def adjust_recommendations(member_id, recommendations):
    # 중단한 도서의 사유 가져오기
    interruption_reasons = get_interruption_reasons(member_id)
    # 싫어하는 장르의 장르 번호 업데이트
    interruption_reasons = update_disliked_genre_with_genre_numbers(member_id, interruption_reasons)

    # 평균 난이도와 페이지 수 계산
    avg_difficulty, avg_page_count = get_average_difficulty_and_page_count(interruption_reasons)

    # 추천 도서 목록 조정
    adjusted_recommendations = []
    for rec in recommendations:
        book_id = rec['id']
        book_difficulty = rec['bookDifficulty']
        book_page_count = rec['bookPage']
        book_genre_id = rec['bookGenre']

        # 난이도가 0인 책은 난이도 관련 필터링에서 제외
        if book_difficulty != '0':
            # 사용자가 난이도 때문에 중단한 책 제외
            if avg_difficulty is not None and difficulty_levels.get(book_difficulty, 3) > avg_difficulty:
                logger.info(f'Book with ID {book_id} excluded due to higher difficulty than preferred.')
                continue

        # 페이지 수에 따른 조정을 추가
        if avg_page_count is not None:
            # 사용자가 페이지 수가 많은 책을 중단한 경우, 평균보다 많은 페이지 수를 가진 책 제외
            if book_page_count > avg_page_count and book_id in interruption_reasons['many_pages']:
                logger.info(f'Book with ID {book_id} excluded due to having more pages than preferred.')
                continue
            # 사용자가 페이지 수가 적은 책을 중단한 경우, 평균보다 적은 페이지 수를 가진 책 제외
            if book_page_count < avg_page_count and book_id in interruption_reasons['few_pages']:
                logger.info(f'Book with ID {book_id} excluded due to having fewer pages than preferred.')
                continue

            # 중단한 장르와 같은 장르의 책 제외
        if book_genre_id in interruption_reasons['disliked_genre']:
            logger.info(
                f'Book with ID {book_id} excluded due to being of a disliked genre with genre number {book_genre_id}.')
            continue

            # 조정된 추천 도서를 목록에 추가
        adjusted_recommendations.append(rec)

    logger.info(f'Returning {len(adjusted_recommendations)} adjusted recommendations for member ID {member_id}')
    return adjusted_recommendations



def get_difficulty_preference(member_grade, member_level):
    # 난이도 선호도 설정 로직
    if member_grade in ['초등학생', '중1', '중2', '중3']:
        preference = [member_level, member_level - 1, member_level + 1]
    else:
        preference = [member_level, member_level + 1, member_level - 1]

    # 0을 난이도 선호 리스트에 추가하되, 중복을 피하기 위해 set을 사용
    preference_with_zero = list(set(preference + [0]))

    # 난이도 리스트를 정렬 (0은 순서 상관없이 추가되므로, 나머지 숫자들을 정렬)
    preference_with_zero.sort(key=lambda x: (x != 0, x))

    return preference_with_zero

@recommendation_blueprint.route('/recommendations/similar_books/<int:member_id>', methods=['GET'])
def similar_books(member_id):
    try:
        random_book_title_encoded = request.args.get('randomLikedOrCompletedBookTitle')

        # random_book_title_encoded가 None인 경우를 처리
        if random_book_title_encoded is not None:
            random_book_title = unquote(random_book_title_encoded)
        else:
            random_book_title = ""  # 또는 다른 적절한 기본값 설정

        logging.info(f"Received request for member_id: {member_id} with book title: {random_book_title}")

        recommendations = []

        if random_book_title:
            try:
                # 데이터프레임에서 해당 책의 인덱스를 찾기
                filtered_books = books[books['book_title'] == random_book_title]
                if filtered_books.empty:
                    logging.warning(f"Book not found: {random_book_title}")
                    return jsonify({"error": "Book not found"}), 404

                book = filtered_books.iloc[0]
                book_id = book['id']
                logging.info(f"Found book: {book['book_title']} with ID: {book_id}")

                # id를 사용하여 해당 책의 임베딩 인덱스를 찾기
                book_index = np.where(book_embeddings['id'] == book_id)[0]
                if not book_index.size:
                    logging.warning(f"Book embedding not found for ID: {book_id}")
                    return jsonify({"error": "Book embedding not found"}), 404

                # 임베딩 추출 부분 수정
                book_embedding = book_embeddings['embedding'][book_index][0]

                # NumPy 배열로 변환
                all_embeddings = np.array([emb for emb in book_embeddings['embedding']])

                similarity_scores = cosine_similarity([book_embedding], all_embeddings)[0]

                similar_indices = similarity_scores.argsort()[-7:-1][::-1]  # 자기 자신을 제외하고 상위 6개
                for i in similar_indices:
                    similar_book_id = book_embeddings['id'][i]

                    # book 모델에서 정보 가져오기
                    similar_book_model = Book.query.get(similar_book_id)

                    # similar_book 딕셔너리 생성
                    similar_book = {
                        'id': similar_book_model.id,
                        'bookName': similar_book_model.bookName,
                        'bookAuthor': similar_book_model.bookAuthor,
                        'bookPublisher': similar_book_model.bookPublisher,
                        'bookDifficulty': similar_book_model.bookDifficulty,
                        'bookGenre': similar_book_model.bookGenre,
                        'bookGenres': similar_book_model.bookGenres,
                        'bookIsbn': similar_book_model.bookIsbn,
                        'bookDate': similar_book_model.bookDate,
                        'bookImage': similar_book_model.bookImage,
                        'bookPage': similar_book_model.bookPage,
                        'similarity_score': float(similarity_scores[i])  # float32를 float로 변환
                    }

                    recommendations.append(similar_book)
                    logging.info(
                        f"Adding recommendation: {similar_book['bookName']} with score: {similarity_scores[i]}")


            except Exception as e:
                logging.error(f"Error finding or processing the book: {e}", exc_info=True)
                return jsonify({"error": str(e)}), 500

        # 결과 반환
        logging.info("Returning recommendations.")
        return jsonify(recommendations)

    except Exception as e:
        logging.error(f"Unhandled exception in similar_books: {e}", exc_info=True)
        return jsonify({"error": "An unexpected error occurred"}), 500



@recommendation_blueprint.route('/recommendations/similar_major_and_grade/<int:member_id>')
def similar_major_and_grade(member_id):
    current_app.logger.info(f"Fetching similar major and grade recommendations for member ID: {member_id}")

    # 사용자 정보 가져오기
    member = Member.query.get(member_id)
    if not member:
        current_app.logger.error(f"Member with ID {member_id} not found.")
        return jsonify({"message": "Member not found"}), 404

    # 같은 학년과 전공을 가진 다른 사용자 찾기
    current_app.logger.info(f"Looking for members with similar major and grade as member ID: {member_id}")
    similar_members = Member.query.filter(
        Member.member_grade == member.member_grade,
        Member.member_major == member.member_major,
        Member.id != member_id
    ).all()

    if not similar_members:
        current_app.logger.info(f"No similar members found for member ID: {member_id}")
        return jsonify({"message": "No similar members found"}), 404

    similar_members_ids = [u.id for u in similar_members]
    current_app.logger.info(f"Found {len(similar_members)} similar members.")

    # 현재 사용자의 완독 및 좋아요 도서 목록 가져오기
    current_user_books = set()
    current_user_completed_books = MemberMyBook.query.filter(
        MemberMyBook.member_entity_id == member_id,
        MemberMyBook.status == 1
    ).all()
    current_user_liked_books = MemberMyLike.query.filter(
        MemberMyLike.member_entity_id == member_id
    ).all()

    for book in current_user_completed_books + current_user_liked_books:
        current_user_books.add(book.book_entity_id)

    # 모든 비슷한 사용자의 도서 목록과 중복 횟수 계산
    all_user_books = {}
    for user_id in similar_members_ids:
        user_books = MemberMyBook.query.filter(
            MemberMyBook.member_entity_id == user_id,
            MemberMyBook.status == 1
        ).all()
        user_liked_books = MemberMyLike.query.filter(
            MemberMyLike.member_entity_id == user_id
        ).all()

        for book in user_books + user_liked_books:
            book_id = book.book_entity_id
            all_user_books[book_id] = all_user_books.get(book_id, 0) + 1

    # 협업 필터링을 통해 찾은 도서 중 현재 사용자의 도서 목록과 중복되지 않는 것들만 추천 목록에 추가
    collaborative_book_ids = set(all_user_books.keys()) - current_user_books
    collaborative_recommendations = Book.query.filter(Book.id.in_(collaborative_book_ids)).all()
    recommended_books_dicts = [book.as_dict() for book in collaborative_recommendations]

    # 책 정보에 중복 횟수 추가
    for book in recommended_books_dicts:
        book_id = book['id']
        book['duplicate_count'] = all_user_books.get(book_id, 0)

    # 도서 목록을 중복 횟수와 전공 유사도에 따라 정렬
    final_recommendations = sorted(recommended_books_dicts,
                                   key=lambda x: (-x['duplicate_count'], -x.get('major_similarity', 0)))

    # 로깅 및 상위 6권 추천 도서 반환
    top_six_recommendations = final_recommendations[:6]
    current_app.logger.info("Top 6 recommendations (based on collaborative filtering and major similarity):")
    for book in top_six_recommendations:
        current_app.logger.info(
            f"Book ID: {book['id']}, Duplicate Count: {book['duplicate_count']}, Major Similarity: {book.get('major_similarity', 0)}")

    return jsonify(top_six_recommendations)


@recommendation_blueprint.route('/recommendations/similar_books_by_member/<int:member_id>')
def recommend_books_for_member(member_id):
    try:
        # 멤버 정보 가져오기
        logger.info(f"Attempting to recommend books for member ID: {member_id}")
        member = Member.query.get(member_id)
        if not member:
            logger.warning(f"Member not found for ID: {member_id}")
            return jsonify({"error": "Member not found"}), 404

        member_major = member.member_major  # 멤버의 전공
        member_grade = member.member_grade  # 멤버의 학년
        member_level = member.member_level  # 멤버의 레벨

        # 멤버의 선호하는 장르 가져오기
        preferred_genres = MemberGenre.query.filter_by(member_entity_id=member_id).all()
        preferred_genre_ids = [genre.genre_entity_id for genre in preferred_genres]
        logger.info(f"Member {member_id} prefers genres with IDs: {preferred_genre_ids}")

        # 멤버의 선호 장르에 따라 도서 간추리기
        genre_filtered_books = Book.query.filter(Book.bookGenre.in_(preferred_genre_ids)).all()

        # 멤버의 학년과 레벨을 기반으로 난이도 선호도 계산
        difficulty_preference = get_difficulty_preference(member_grade, member_level)

        # 중단 사유를 반영하여 추천 목록 조정
        interruption_reasons_adjusted_books = adjust_recommendations(member_id,
                                                                     [book.as_dict() for book in genre_filtered_books])

        # 조정된 추천 목록에서 랜덤으로 6개 선택
        random_selected_books = sample(interruption_reasons_adjusted_books,
                                       min(len(interruption_reasons_adjusted_books), 6))

        # 멤버의 전공에 해당하는 임베딩 인덱스 찾기
        major_index = np.where(majors == member_major)[0][0] if member_major in majors else None

        # 각 책의 전공과의 유사도 계산
        if major_index is not None:
            for book in random_selected_books:
                book_id_index = book['id'] - 1  # 책의 ID가 1부터 시작한다고 가정
                book['major_similarity'] = book_major_embedding[book_id_index, major_index]
        else:
            for book in random_selected_books:
                book['major_similarity'] = 0  # 전공 정보가 없는 경우 유사도를 0으로 설정

        # 난이도 선호도를 확인하고, 리스트에 없는 값이면 최소 우선순위를 부여
        max_difficulty_index = len(difficulty_preference)
        sorted_books = sorted(random_selected_books, key=lambda x: (
            difficulty_preference.index(x['bookDifficulty']) if x['bookDifficulty'] in difficulty_preference else max_difficulty_index,
            x['major_similarity']
        ), reverse=True)

        # 조정된 추천 도서 정보를 JSON 형태로 변환하여 반환
        return jsonify(sorted_books)

    except SQLAlchemyError as e:
        logger.error(f"Database error while recommending books for member ID {member_id}: {e}", exc_info=True)
        return jsonify({"error": "Database error"}), 500
    except Exception as e:
        logger.error(f"An unexpected error occurred while recommending books for member ID {member_id}: {e}",
                     exc_info=True)
        return jsonify({"error": "An unexpected error occurred"}), 500
