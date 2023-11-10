package GuideBook.GuideBook.dto.response;

import GuideBook.GuideBook.entity.GenreEntity;
import GuideBook.GuideBook.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  // No args constructor 추가
@ToString
public class MemberDTO {
    private Long id;
    private String memberEmail;

    private String memberPassword;
    private String memberName;
    private Long memberLevel;
    private Long memberScore = 1L;  // Score 필드 추가

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate memberBirth;

    private String memberGrade;
    private String memberMajor;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberLevel(memberEntity.getMemberLevel());
        memberDTO.setMemberScore(memberEntity.getMemberScore());  // Score 필드의 설정 추가
        memberDTO.setMemberBirth(memberEntity.getMemberBirth());
        memberDTO.setMemberGrade(memberEntity.getMemberGrade());
        memberDTO.setMemberMajor(memberEntity.getMemberMajor());
        return memberDTO;
    }
}
