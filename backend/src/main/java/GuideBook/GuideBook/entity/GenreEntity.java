package GuideBook.GuideBook.entity;

import GuideBook.GuideBook.dto.GenreDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "genre_table")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false)
    private String genreName;

    public static GenreEntity toGenreEntity(GenreDTO genreDTO) {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(genreDTO.getId());
        genreEntity.setGenreName(genreDTO.getGenreName());
        return genreEntity;
    }


}