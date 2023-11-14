package GuideBook.GuideBook.entity;

import GuideBook.GuideBook.dto.MajorDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "major_table")
public class MajorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String majorName;

    public static MajorEntity toMajorEntity(MajorDTO majorDTO) {
        MajorEntity majorEntity = new MajorEntity();
        majorEntity.setId(majorDTO.getId());
        majorEntity.setMajorName(majorDTO.getMajorName());
        return majorEntity;
    }

    public static MajorEntity toUpdateMajorEntity(MajorDTO majorDTO) {
        MajorEntity majorEntity = new MajorEntity();
        majorEntity.setId(majorDTO.getId());
        majorEntity.setMajorName(majorDTO.getMajorName());
        return majorEntity;
    }
}