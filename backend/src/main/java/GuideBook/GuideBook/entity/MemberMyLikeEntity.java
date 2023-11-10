package GuideBook.GuideBook.entity;

import GuideBook.GuideBook.dto.MemberMyLikeDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "member_mylike_table")
public class MemberMyLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_entity_id")
    private Long memberEntityId;

    @Column(name = "book_entity_id")
    private Long bookEntityId;

    public static MemberMyLikeEntity toMemberMyLikeEntity(MemberMyLikeDTO memberMyLikeDTO) {
        MemberMyLikeEntity memberMyLikeEntity = new MemberMyLikeEntity();
        memberMyLikeEntity.setId(memberMyLikeDTO.getId());
        memberMyLikeEntity.setMemberEntityId(memberMyLikeDTO.getMemberEntityId());
        memberMyLikeEntity.setBookEntityId(memberMyLikeDTO.getBookEntityId());
        return memberMyLikeEntity;
    }
}
