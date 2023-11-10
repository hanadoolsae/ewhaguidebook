package GuideBook.GuideBook.entity;

import GuideBook.GuideBook.dto.MemberDTO;
import GuideBook.GuideBook.dto.MemberGenreDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "member_genre_table")
public class MemberGenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn
    private GenreEntity genreEntity;

    public static MemberGenreEntity toMemberGenreEntity(MemberGenreDTO memberGenreDTO) {
        MemberGenreEntity memberGenreEntity = new MemberGenreEntity();
        memberGenreEntity.setId(memberGenreDTO.getId());
        memberGenreEntity.setMemberEntity(memberGenreDTO.getMemberEntity());
        memberGenreEntity.setGenreEntity(memberGenreDTO.getGenreEntity());
        return memberGenreEntity;
    }

}
