package GuideBook.GuideBook.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiBookDTO {
    private String bookName;
    private String bookPublisher;
    private String bookAuthor;
    private String bookImage;

    // 필요한 추가 메서드나 로직들
}
