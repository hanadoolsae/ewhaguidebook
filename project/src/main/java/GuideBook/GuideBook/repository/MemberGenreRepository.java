package GuideBook.GuideBook.repository;

import GuideBook.GuideBook.entity.BookEntity;
import GuideBook.GuideBook.entity.MajorEntity;
import GuideBook.GuideBook.entity.MemberGenreEntity;
import GuideBook.GuideBook.entity.MemberMyBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberGenreRepository extends JpaRepository<MemberGenreEntity, Long> {
    List<MemberGenreEntity> findByMemberEntityId(Long memberId);

    Optional<MemberGenreEntity> findByMemberEntityIdAndGenreEntityId(Long memberId, Long GenreId);

}