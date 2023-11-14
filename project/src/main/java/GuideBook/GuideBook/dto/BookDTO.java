package GuideBook.GuideBook.dto;

import GuideBook.GuideBook.entity.BookEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDTO {
    private Long id;
    private String bookName;
    private String bookPublisher;
    private String bookAuthor;
    private String bookDifficulty;
    private String bookGenre;
    private String bookGenres;
    private String bookIsbn;
    private String bookIntroduce;
    private String bookDate;
    private String bookImage;
    private Long bookPage;

    public static BookDTO toBookDTO(BookEntity bookEntity) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(bookEntity.getId());
        bookDTO.setBookName(bookEntity.getBookName());
        bookDTO.setBookPublisher(bookEntity.getBookPublisher());
        bookDTO.setBookAuthor(bookEntity.getBookAuthor());
        bookDTO.setBookDifficulty(bookEntity.getBookDifficulty());
        bookDTO.setBookGenre(bookEntity.getBookGenre());
        bookDTO.setBookGenres(bookEntity.getBookGenres());
        bookDTO.setBookIsbn(bookEntity.getBookIsbn());
        bookDTO.setBookIntroduce(bookEntity.getBookIntroduce());
        bookDTO.setBookDate(bookEntity.getBookDate());
        bookDTO.setBookImage(bookEntity.getBookImage());
        bookDTO.setBookPage(bookEntity.getBookPage());
        return bookDTO;
    }
}
