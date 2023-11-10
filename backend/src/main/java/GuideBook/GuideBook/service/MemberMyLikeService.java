package GuideBook.GuideBook.service;

import GuideBook.GuideBook.dto.MemberMyBookDTO;
import GuideBook.GuideBook.dto.MemberMyLikeDTO;
import GuideBook.GuideBook.entity.MemberMyBookEntity;
import GuideBook.GuideBook.entity.MemberMyLikeEntity;
import GuideBook.GuideBook.repository.MemberMyLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberMyLikeService {
    private final MemberMyLikeRepository memberMyLikeRepository;

    public void save(MemberMyLikeDTO memberMyLikeDTO) {
        MemberMyLikeEntity memberMyLikeEntity = MemberMyLikeEntity.toMemberMyLikeEntity(memberMyLikeDTO);
        memberMyLikeRepository.save(memberMyLikeEntity);
    }

    public List<MemberMyLikeDTO> findByMemberEntityId(Long id) {
        List<MemberMyLikeEntity> memberMyLikeEntityList = memberMyLikeRepository.findByMemberEntityId(id);
        List<MemberMyLikeDTO> memberMyLikeDTOList = new ArrayList<>();

        for (MemberMyLikeEntity memberMyLikeEntity : memberMyLikeEntityList) {
            MemberMyLikeDTO memberMyLikeDTO = MemberMyLikeDTO.tomemberMyLikeDTO(memberMyLikeEntity);
            memberMyLikeDTOList.add(memberMyLikeDTO);
        }

        return memberMyLikeDTOList;
    }

    public boolean existsByMemberIdAndBookId(Long memberId, Long bookId) {
        return memberMyLikeRepository.existsByMemberEntityIdAndBookEntityId(memberId, bookId);
    }

    public Optional<MemberMyLikeDTO> findByMemberIdAndBookId(Long memberId, Long bookId) {
        Optional<MemberMyLikeEntity> memberMyLikeEntityOptional = memberMyLikeRepository.findByMemberEntityIdAndBookEntityId(memberId, bookId);

        if (memberMyLikeEntityOptional.isPresent()) {
            MemberMyLikeEntity memberMyLikeEntity = memberMyLikeEntityOptional.get();
            return Optional.of(MemberMyLikeDTO.tomemberMyLikeDTO(memberMyLikeEntity));
        }
        else {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        memberMyLikeRepository.deleteById(id);
    }
}
