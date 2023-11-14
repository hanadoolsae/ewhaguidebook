package GuideBook.GuideBook.repository;

import GuideBook.GuideBook.entity.MemberMyBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberMyBookRepository extends JpaRepository<MemberMyBookEntity, Long> {
    List<MemberMyBookEntity> findByMemberEntityId(Long id);
    boolean existsByMemberEntityIdAndBookEntityId(Long memberId, Long bookId);
    Optional<MemberMyBookEntity> findByMemberEntityIdAndBookEntityId(Long memberId, Long bookId);
    List<MemberMyBookEntity> findByMemberEntityIdAndStatus(Long memberEntityId, Integer status);

}
