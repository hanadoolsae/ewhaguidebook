package GuideBook.GuideBook.service;

import GuideBook.GuideBook.dto.GenreDTO;
import GuideBook.GuideBook.dto.MemberDTO;
import GuideBook.GuideBook.entity.GenreEntity;
import GuideBook.GuideBook.entity.MemberEntity;
import GuideBook.GuideBook.repository.MemberRepository;
import GuideBook.GuideBook.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final QuizService quizService;

    public MemberEntity save(MemberDTO memberDTO) {
        // 비밀번호 해시 생성
        String hashedPassword = SecurityUtils.hashPassword(memberDTO.getMemberPassword());
        memberDTO.setMemberPassword(hashedPassword);

        // MemberEntity로 변환 후 저장
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        // 저장된 MemberEntity를 반환
        return memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> memberOptional = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (memberOptional.isPresent()) {
            MemberEntity memberEntity = memberOptional.get();
            String storedHashedPassword = memberEntity.getMemberPassword(); // 데이터베이스에 저장된 해시된 비밀번호 가져오기
            String hashedInputPassword = SecurityUtils.hashPassword(memberDTO.getMemberPassword()); // 사용자가 제출한 비밀번호를 해싱

            if (storedHashedPassword.equals(hashedInputPassword)) {
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                return MemberDTO.toMemberDTO(memberEntity);
            } else {
                // 비밀번호 불일치 (로그인 실패)
                return null;
            }
        } else {
            // 조회 결과가 없다 -> 해당 이메일을 가진 회원이 없다
            return null;
        }
    }


    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();

        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            memberDTOList.add(memberDTO);
        }

        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toMemberEntity(memberDTO));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public String emailCheck(String memberEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
        if (byMemberEmail.isPresent()) {
            // 조회결과가 있다 -> 사용할 수 없다.
            return null;
        } else {
            // 조회결과가 없다 -> 사용할 수 있다.
            return "ok";
        }
    }

    public MemberDTO findByEmail(String email) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(email);
        return optionalMemberEntity.map(MemberDTO::toMemberDTO).orElse(null);
    }

    public void updateMember(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public boolean checkAnswerAndIncrementScore(Long quizId, String memberAnswer, String memberEmail) {
        String correctAnswer = quizService.findAnswerByQuizId(quizId);

        if (correctAnswer.equals(memberAnswer)) {
            incrementScoreByEmail(memberEmail);
            return true;
        }
        return false;
    }


    private void incrementScoreByEmail(String email) {
        MemberDTO member = findByEmail(email);
        if (member != null) {
            member.setMemberScore(member.getMemberScore() + 1);
            updateMember(member);
        }
    }

    public void updateMemberLevel(String memberEmail) {
        // 1. 회원 정보 조회
        Optional<MemberEntity> memberOpt = memberRepository.findByMemberEmail(memberEmail);

        if (memberOpt.isPresent()) {
            MemberEntity member = memberOpt.get();

            // 2. 점수를 기반으로 레벨 결정
            Long level = determineLevelBasedOnScore(member.getMemberScore());

            // 3. 회원 정보에 레벨 값 업데이트
            member.setMemberLevel(level);
            memberRepository.save(member); // 회원 정보 업데이트
        }
    }


    private Long determineLevelBasedOnScore(Long score) {
        if (score <= 2) {
            return 1L;
        } else if (score <= 4) {
            return 2L;
        } else if (score <= 6) {
            return 3L;
        } else if (score <= 8) {
            return 4L;
        } else if (score <= 10) {
            return 5L;
        }
        return 1L; // default value, you can adjust this
    }

    public Long getMemberLevelByEmail(String memberEmail) {
        MemberEntity member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> new IllegalArgumentException("No member found with email: " + memberEmail));
        return member.getMemberLevel();
    }

    public void updateMajor(String myEmail, String newMajor) {
        // 이메일을 기반으로 회원 정보를 조회
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);

        if (optionalMemberEntity.isPresent()) {
            // 회원 정보가 존재할 때만 업데이트 진행
            MemberEntity memberEntity = optionalMemberEntity.get();
            memberEntity.setMemberMajor(newMajor); // 새로운 전공 정보로 업데이트
            memberRepository.save(memberEntity); // 업데이트된 회원 정보 저장
        }
    }

}

