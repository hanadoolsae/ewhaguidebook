package GuideBook.GuideBook.repository;

import GuideBook.GuideBook.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    GenreEntity findByGenreName(String genreName);
}