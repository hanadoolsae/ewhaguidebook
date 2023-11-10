package GuideBook.GuideBook.service;

import GuideBook.GuideBook.dto.MemberGenreDTO;
import GuideBook.GuideBook.dto.MemberMyBookDTO;
import GuideBook.GuideBook.dto.MemberMyLikeDTO;
import GuideBook.GuideBook.entity.MemberGenreEntity;
import GuideBook.GuideBook.entity.MemberMyBookEntity;
import GuideBook.GuideBook.entity.MemberMyLikeEntity;
import GuideBook.GuideBook.repository.MemberGenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberGenreService {
    private final MemberGenreRepository memberGenreRepository;

    public void save(MemberGenreDTO memberGenreDTO) {
        MemberGenreEntity memberGenreEntity = MemberGenreEntity.toMemberGenreEntity(memberGenreDTO);
        memberGenreRepository.save(memberGenreEntity);
    }

    public void deleteById(Long id) {
        memberGenreRepository.deleteById(id);
    }

    public Optional<MemberGenreDTO> findByMemberIdAndGenreId(Long memberId, Long genreId) {
        Optional<MemberGenreEntity> memberGenreEntityOptional = memberGenreRepository.findByMemberEntityIdAndGenreEntityId(memberId, genreId);

        if (memberGenreEntityOptional.isPresent()) {
            MemberGenreEntity memberGenreEntity = memberGenreEntityOptional.get();
            return Optional.of(MemberGenreDTO.toMemberGenreDTO(memberGenreEntity));
        }
        else {
            return Optional.empty();
        }
    }

    public List<MemberGenreDTO> findByMemberEntityId(Long id) {
        List<MemberGenreEntity> memberGenreEntityList = memberGenreRepository.findByMemberEntityId(id);
        List<MemberGenreDTO> memberGenreDTOList = new ArrayList<>();

        for (MemberGenreEntity memberGenreEntity : memberGenreEntityList) {
            MemberGenreDTO memberGenreDTO = MemberGenreDTO.toMemberGenreDTO(memberGenreEntity);
            memberGenreDTOList.add(memberGenreDTO);
        }

        return memberGenreDTOList;
    }

}
