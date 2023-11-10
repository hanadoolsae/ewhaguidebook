from app import db  # SQLAlchemy 인스턴스를 app.py로부터 임포트


class Book(db.Model):
    __tablename__ = "book_table"

    id = db.Column('id', db.Integer, primary_key=True, autoincrement=True)
    bookName = db.Column('book_name', db.String, unique=True, nullable=False)
    bookAuthor = db.Column('book_author', db.String, nullable=False)
    bookPublisher = db.Column('book_publisher', db.String, nullable=False)
    bookDifficulty = db.Column('book_difficulty', db.String)
    bookGenre = db.Column('book_genre', db.String)  # 숫자
    bookGenres = db.Column('book_genres', db.String)  # 찐 전공명
    bookIsbn = db.Column('book_isbn', db.String)
    bookintroduce = db.Column('book_introduce', db.Text)
    bookDate = db.Column('book_date', db.String)
    bookImage = db.Column('book_image', db.String(255))
    bookPage = db.Column('book_page', db.Integer)

    # MemberMyLike 모델과의 관계 설정 추가
    memberMyLikes = db.relationship('MemberMyLike', back_populates='book')

    def __repr__(self):
        """객체의 문자열 표현을 반환합니다."""
        return f"<Book {self.id}, '{self.bookName}', '{self.bookAuthor}', '{self.bookPublisher}', '{self.bookDate}', '{self.bookImage}', {self.bookPage}>"

    def as_dict(self):
        """모든 컬럼 값을 딕셔너리 형태로 반환합니다."""
        # 컬럼 이름을 모델에 정의된 속성 이름으로 매핑합니다.
        return {
            'id': self.id,
            'bookName': self.bookName,
            'bookAuthor': self.bookAuthor,
            'bookPublisher': self.bookPublisher,
            'bookDifficulty': self.bookDifficulty,
            'bookGenre': self.bookGenre,
            'bookGenres': self.bookGenres,
            'bookIsbn': self.bookIsbn,
            'bookintroduce': self.bookintroduce,
            'bookDate': self.bookDate,
            'bookImage': self.bookImage,
            'bookPage': self.bookPage
        }

    def as_simple_dict(self, idx):
        rank = {"rank": idx}
        return dict({x.name: getattr(self, x.name) for x in self.__table__.columns if
                     x.name not in ['bookPage', 'bookIntroduce', 'bookDate', 'bookGenre']}, **rank)
