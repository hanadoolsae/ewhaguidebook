package GuideBook.GuideBook.dto;

import GuideBook.GuideBook.entity.MajorEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MajorDTO {
    private Long id;
    private String majorName;

    public static MajorDTO toMajorDTO(MajorEntity majorEntity) {
        MajorDTO majorDTO = new MajorDTO();
        majorDTO.setId(majorEntity.getId());
        majorDTO.setMajorName(majorEntity.getMajorName());
        return majorDTO;
    }

}