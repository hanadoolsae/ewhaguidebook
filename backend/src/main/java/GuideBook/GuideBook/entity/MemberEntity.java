package GuideBook.GuideBook.entity;

import GuideBook.GuideBook.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)  // unique 제약조건 추가
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    @Column
    private Long memberLevel;

    @Column
    private LocalDate memberBirth;

    @Column
    private String memberGrade;

    @Column
    private String memberMajor;

    @Column
    private Long memberScore=1L;

    @Column
    private String memberCharacter;


    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberLevel(memberDTO.getMemberLevel());
        memberEntity.setMemberBirth(memberDTO.getMemberBirth());
        memberEntity.setMemberGrade(memberDTO.getMemberGrade());
        memberEntity.setMemberScore(memberDTO.getMemberScore());
        memberEntity.setMemberMajor(memberDTO.getMemberMajor());
        memberEntity.setMemberCharacter(memberDTO.getMemberCharacter());
        return memberEntity;
    }


}