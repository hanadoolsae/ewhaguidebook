package GuideBook.GuideBook.service;

import GuideBook.GuideBook.dto.BookDTO;
import GuideBook.GuideBook.dto.GenreDTO;
import GuideBook.GuideBook.entity.BookEntity;
import GuideBook.GuideBook.entity.GenreEntity;
import GuideBook.GuideBook.entity.MemberEntity;
import GuideBook.GuideBook.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public void save(GenreDTO genreDTO) {
        /*
            1. dto -> entity 변환
            2. repository의 save 메서드 호출
         */
        GenreEntity genreEntity = GenreEntity.toGenreEntity(genreDTO);
        genreRepository.save(genreEntity);
        // repository의 save 메서드 호출 (조건 - entity 객체를 넘겨줘야 함)
    }

    public GenreDTO findById(Long id) {
        Optional<GenreEntity> optionalGenreEntity = genreRepository.findById(id);
        if (optionalGenreEntity.isPresent()) {
            return GenreDTO.toGenreDTO(optionalGenreEntity.get());
        } else {
            return null;
        }
    }
}

