package GuideBook.GuideBook.entity;

import GuideBook.GuideBook.dto.BookDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "book_table")
public class BookEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)  // unique 제약조건 추가
    private String bookName;

    @Column
    private String bookPublisher;

    @Column
    private String bookAuthor;

    @Column
    private String bookDifficulty;

    @Column
    private String bookGenre;

    @Column
    private String bookGenres;

    @Column
    private String bookIsbn;

    @Column
    private String bookIntroduce;

    @Column
    private String bookDate;

    @Column
    private String bookImage;

    @Column
    private Long bookPage;


    public static BookEntity toBookEntity(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookDTO.getId());
        bookEntity.setBookName(bookDTO.getBookName());
        bookEntity.setBookPublisher(bookDTO.getBookPublisher());
        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
        bookEntity.setBookDifficulty(bookDTO.getBookDifficulty());
        bookEntity.setBookGenre(bookDTO.getBookGenre());
        bookEntity.setBookGenres(bookDTO.getBookGenres());
        bookEntity.setBookIsbn(bookDTO.getBookIsbn());
        bookEntity.setBookIntroduce(bookDTO.getBookIntroduce());
        bookEntity.setBookDate(bookDTO.getBookDate());
        bookEntity.setBookImage(bookDTO.getBookImage());
        bookEntity.setBookPage(bookDTO.getBookPage());
        return bookEntity;
    }

    public static BookEntity toUpdateBookEntity(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookDTO.getId());
        bookEntity.setBookName(bookDTO.getBookName());
        bookEntity.setBookPublisher(bookDTO.getBookPublisher());
        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
        bookEntity.setBookDifficulty(bookDTO.getBookDifficulty());
        bookEntity.setBookGenre(bookDTO.getBookGenre());
        bookEntity.setBookGenres(bookDTO.getBookGenres());
        bookEntity.setBookIsbn(bookDTO.getBookIsbn());
        bookEntity.setBookIntroduce(bookDTO.getBookIntroduce());
        bookEntity.setBookDate(bookDTO.getBookDate());
        bookEntity.setBookImage(bookDTO.getBookImage());
        bookEntity.setBookPage(bookDTO.getBookPage());
        return bookEntity;
    }
}
