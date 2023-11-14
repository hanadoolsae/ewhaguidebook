package GuideBook.GuideBook.repository;

import GuideBook.GuideBook.entity.MajorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MajorRepository extends JpaRepository<MajorEntity, Long> {
    List<MajorEntity> findByMajorNameContaining(String majorName);
}
