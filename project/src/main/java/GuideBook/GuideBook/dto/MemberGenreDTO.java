package GuideBook.GuideBook.dto;

import GuideBook.GuideBook.entity.GenreEntity;
import GuideBook.GuideBook.entity.MemberEntity;
import GuideBook.GuideBook.entity.MemberGenreEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor  // No args constructor 추가
@ToString
public class MemberGenreDTO {
    private Long id;
    private MemberEntity memberEntity;
    private GenreEntity genreEntity;

    public static MemberGenreDTO toMemberGenreDTO(MemberGenreEntity memberGenreEntity) {
        MemberGenreDTO memberGenreDTO = new MemberGenreDTO();
        memberGenreDTO.setId(memberGenreEntity.getId());
        memberGenreDTO.setMemberEntity(memberGenreEntity.getMemberEntity());
        memberGenreDTO.setGenreEntity(memberGenreEntity.getGenreEntity());
        return memberGenreDTO;
    }
}
