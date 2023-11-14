package GuideBook.GuideBook.dto;

import GuideBook.GuideBook.entity.MemberGenreEntity;
import GuideBook.GuideBook.entity.MemberMyBookEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  // No args constructor 추가
@ToString
public class MemberMyBookDTO {
    private Long id;
    private Long memberEntityId;
    private Long bookEntityId;

    private int status;
    private String statusDetail;

    private String memo;


    public static MemberMyBookDTO tomemberMyBookDTO(MemberMyBookEntity memberMyBookEntity) {
        MemberMyBookDTO memberMyBookDTO = new MemberMyBookDTO();
        memberMyBookDTO.setId(memberMyBookEntity.getId());
        memberMyBookDTO.setMemberEntityId(memberMyBookEntity.getMemberEntityId());
        memberMyBookDTO.setBookEntityId(memberMyBookEntity.getBookEntityId());
        memberMyBookDTO.setStatus(memberMyBookEntity.getStatus());
        memberMyBookDTO.setStatusDetail(memberMyBookEntity.getStatusDetail());
        memberMyBookDTO.setMemo(memberMyBookEntity.getMemo());
        return memberMyBookDTO;
    }
}
