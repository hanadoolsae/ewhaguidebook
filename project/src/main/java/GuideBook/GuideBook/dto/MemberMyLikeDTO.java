package GuideBook.GuideBook.dto;

import GuideBook.GuideBook.entity.MemberMyLikeEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  // No args constructor 추가
@ToString
public class MemberMyLikeDTO {
    private Long id;
    private Long memberEntityId;
    private Long bookEntityId;

    public static MemberMyLikeDTO tomemberMyLikeDTO(MemberMyLikeEntity memberMyLikeEntity) {
        MemberMyLikeDTO memberMyLikeDTO = new MemberMyLikeDTO();
        memberMyLikeDTO.setId(memberMyLikeEntity.getId());
        memberMyLikeDTO.setMemberEntityId(memberMyLikeEntity.getMemberEntityId());
        memberMyLikeDTO.setBookEntityId(memberMyLikeEntity.getBookEntityId());
        return memberMyLikeDTO;
    }
}
