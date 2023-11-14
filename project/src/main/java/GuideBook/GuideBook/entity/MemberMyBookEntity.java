package GuideBook.GuideBook.entity;

import GuideBook.GuideBook.dto.MemberGenreDTO;
import GuideBook.GuideBook.dto.MemberMyBookDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "member_mybook_table")
public class MemberMyBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_entity_id")
    private Long memberEntityId;

    @Column(name = "book_entity_id")
    private Long bookEntityId;

    @Column(name = "status")
    private int status=0;
    // 1 완독, 2 중단, 3 독서 중

    @Column(name = "status_detail")
    private String statusDetail="독서 상태를 입력하세요.";

    @Lob
    @Column(name = "memo")
    private String memo;


    public static MemberMyBookEntity toMemberMyBookEntity(MemberMyBookDTO memberMyBookDTO) {
        MemberMyBookEntity memberMyBookEntity = new MemberMyBookEntity();
        memberMyBookEntity.setId(memberMyBookDTO.getId());
        memberMyBookEntity.setMemberEntityId(memberMyBookDTO.getMemberEntityId());
        memberMyBookEntity.setBookEntityId(memberMyBookDTO.getBookEntityId());
        memberMyBookEntity.setStatus(memberMyBookDTO.getStatus());
        memberMyBookEntity.setStatusDetail(memberMyBookDTO.getStatusDetail());
        memberMyBookEntity.setMemo(memberMyBookDTO.getMemo());
        return memberMyBookEntity;
    }
}
