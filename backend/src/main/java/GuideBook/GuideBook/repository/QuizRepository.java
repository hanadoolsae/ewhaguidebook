package GuideBook.GuideBook.repository;

import GuideBook.GuideBook.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
    // 필요한 쿼리 메서드들을 여기에 추가할 수 있습니다.
}
