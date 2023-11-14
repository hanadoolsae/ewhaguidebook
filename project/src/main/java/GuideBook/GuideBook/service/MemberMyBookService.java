package GuideBook.GuideBook.service;


import GuideBook.GuideBook.dto.BookDTO;
import GuideBook.GuideBook.dto.MemberDTO;
import GuideBook.GuideBook.dto.MemberGenreDTO;
import GuideBook.GuideBook.dto.MemberMyBookDTO;
import GuideBook.GuideBook.entity.MemberGenreEntity;
import GuideBook.GuideBook.entity.MemberMyBookEntity;
import GuideBook.GuideBook.repository.MemberMyBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberMyBookService {
    private final MemberMyBookRepository memberMyBookRepository;

    public void save(MemberMyBookDTO memberMyBookDTO) {
        MemberMyBookEntity memberMyBookEntity = MemberMyBookEntity.toMemberMyBookEntity(memberMyBookDTO);
        memberMyBookRepository.save(memberMyBookEntity);
    }


    public List<MemberMyBookDTO> findByMemberEntityId(Long id) {
        List<MemberMyBookEntity> memberMyBookEntityList = memberMyBookRepository.findByMemberEntityId(id);
        List<MemberMyBookDTO> memberMyBookDTOList = new ArrayList<>();
        for (MemberMyBookEntity memberMyBookEntity : memberMyBookEntityList) {
            MemberMyBookDTO memberMyBookDTO = MemberMyBookDTO.tomemberMyBookDTO(memberMyBookEntity);
            memberMyBookDTOList.add(memberMyBookDTO);
        }
        return memberMyBookDTOList;
    }

    public boolean existsByMemberIdAndBookId(Long memberId, Long bookId) {
        return memberMyBookRepository.existsByMemberEntityIdAndBookEntityId(memberId, bookId);
    }

    public Optional<MemberMyBookDTO> findByMemberIdAndBookId(Long memberId, Long bookId) {
        Optional<MemberMyBookEntity> memberMyBookEntityOptional = memberMyBookRepository.findByMemberEntityIdAndBookEntityId(memberId, bookId);

        if (memberMyBookEntityOptional.isPresent()) {
            MemberMyBookEntity memberMyBookEntity = memberMyBookEntityOptional.get();
            return Optional.of(MemberMyBookDTO.tomemberMyBookDTO(memberMyBookEntity));
        }
        else {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        memberMyBookRepository.deleteById(id);
    }

}
