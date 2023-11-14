package GuideBook.GuideBook.repository;

import GuideBook.GuideBook.entity.MemberMyBookEntity;
import GuideBook.GuideBook.entity.MemberMyLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberMyLikeRepository extends JpaRepository<MemberMyLikeEntity, Long> {

    List<MemberMyLikeEntity> findByMemberEntityId(Long id);
    boolean existsByMemberEntityIdAndBookEntityId(Long memberId, Long bookId);

    Optional<MemberMyLikeEntity> findByMemberEntityIdAndBookEntityId(Long memberId, Long bookId);

}
