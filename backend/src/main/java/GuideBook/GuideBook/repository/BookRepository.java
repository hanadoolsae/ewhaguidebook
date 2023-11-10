package GuideBook.GuideBook.repository;


import GuideBook.GuideBook.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import GuideBook.GuideBook.entity.MajorEntity;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByBookNameContaining(String bookName);
    List<BookEntity> findByBookGenreInAndBookDifficulty(List<String> genres, String difficulty);

}
