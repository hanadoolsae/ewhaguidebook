package GuideBook.GuideBook.Controller;

import GuideBook.GuideBook.dto.*;
import GuideBook.GuideBook.entity.BookEntity;
import GuideBook.GuideBook.entity.GenreEntity;
import GuideBook.GuideBook.entity.MemberEntity;
import GuideBook.GuideBook.entity.MemberGenreEntity;
import GuideBook.GuideBook.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;
    private final MajorService majorService;
    private final MemberGenreService memberGenreService;
    private final MemberMyBookService memberMyBookService;
    private final GenreService genreService;
    private final BookService bookService;
    private final MemberMyLikeService memberMyLikeService;
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @PersistenceContext
    private EntityManager entityManager;


    @GetMapping("/")
    public String showGuideBook (){
        return "index";
    }


    @GetMapping("/about")
    public String introduce() {
        return "about";
    }

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm(HttpSession session) {
        session.invalidate();
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);

        // MemberService의 save 메소드를 호출하여 데이터베이스에 저장하고, 저장된 MemberEntity를 반환받음
        MemberEntity savedMember = memberService.save(memberDTO);

        // 생성된 MemberEntity에서 id를 가져와 세션에 저장
        if (savedMember != null && savedMember.getId() != null) {
            session.setAttribute("saveDTO", savedMember.getId());
        } else {
            // 회원 정보 저장 실패 처리
            // 적절한 예외 처리 혹은 오류 메시지를 반환할 수 있습니다.
            return "error";
        }

        return "genre";
    }


    @GetMapping("/member/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "아이디나 비밀번호가 틀렸습니다.");
        }
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            session.setAttribute("loginDTO", loginResult);
            session.setAttribute("loginName", loginResult.getMemberName());
            session.setAttribute("major", loginResult.getMemberMajor());
            session.setAttribute("grade", loginResult.getMemberGrade());
            session.setAttribute("memberEntityId", loginResult.getId()); // 멤버 ID를 세션에 저장합니다.


            // 사용자가 좋아하는 도서나 완독한 도서 중 랜덤으로 하나의 도서 제목을 세션에 저장합니다.
            BookDTO randomBook = bookService.getRandomLikedOrCompletedBook(loginResult.getId());
            if (randomBook != null) {
                session.setAttribute("randomLikedOrCompletedBookTitle", randomBook.getBookName());
            } else {
                session.setAttribute("randomLikedOrCompletedBookTitle", "No favorite or completed books found.");
            }

            System.out.println("로그인 성공. 이메일: " + loginResult.getMemberEmail());
            return "redirect:/recommendationsuccess";
        } else {
            // login 실패
            System.out.println("로그인 실패. 이메일: " + memberDTO.getMemberEmail());
            redirectAttributes.addFlashAttribute("errorMessage", "이메일이나 패스워드가 틀립니다.");
            return "redirect:/member/login";
        }

    }

    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model사용
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    @GetMapping("/member/mypage")
    public String mypage(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByEmail(myEmail);
        Long memberId = memberDTO.getId();
        Long memberLevel = memberDTO.getMemberLevel();  // memberLevel을 가져옵니다.

        System.out.println(memberId);

        List<MemberMyBookDTO> memberMyBookDTOList = memberMyBookService.findByMemberEntityId(memberId);
        List<MemberMyLikeDTO> memberMyLikeDTOList = memberMyLikeService.findByMemberEntityId(memberId);

        List<BookDTO> mybookDTOList = new ArrayList<>();
        for (MemberMyBookDTO memberMyBookDTO : memberMyBookDTOList) {
            Long bookId = memberMyBookDTO.getBookEntityId();
            BookDTO bookDTO = bookService.findById(bookId);
            System.out.println(bookDTO.getBookName());
            mybookDTOList.add(bookDTO);
        }

        List<BookDTO> mylikeDTOList = new ArrayList<>();
        for (MemberMyLikeDTO memberMyLikeDTO : memberMyLikeDTOList) {
            Long bookId = memberMyLikeDTO.getBookEntityId();
            BookDTO bookDTO = bookService.findById(bookId);
            System.out.println(bookDTO.getBookName());
            mylikeDTOList.add(bookDTO);
        }

        model.addAttribute("member", memberDTO);
        model.addAttribute("memberLevel", memberLevel);  // model에 memberLevel을 추가합니다.
        model.addAttribute("mybookList", mybookDTOList);
        model.addAttribute("mylikeList", mylikeDTOList);
        return "mypage";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        session.setAttribute("loginName", memberDTO.getMemberName());
        return "update";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO, RedirectAttributes redirectAttributes) {
        memberService.update(memberDTO);
        redirectAttributes.addFlashAttribute("message", "회원 정보가 성공적으로 업데이트되었습니다.");
        return "mypage";
    }


    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
        return "redirect:/member/";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        System.out.println("로그아웃: " + session.getAttribute("loginEmail"));
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }

    @Transactional
    @PostMapping("/member/genre")
    public String register(@RequestParam("selectedGenres") List<Long> selectedGenres, HttpSession session) {
        // 세션에서 회원 ID를 가져옵니다.
        Long memberId = (Long) session.getAttribute("saveDTO");
        logger.info("Session Member ID: {}", memberId);

        // 데이터베이스에서 기존의 회원 정보를 조회합니다.
        MemberDTO memberDTO = memberService.findById(memberId);
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        for (Long genreId : selectedGenres) {
            GenreDTO genreDTO = genreService.findById(genreId);
            if (genreDTO != null) {
                GenreEntity genreEntity = GenreEntity.toGenreEntity(genreDTO);
                MemberGenreDTO memberGenreDTO = new MemberGenreDTO();

                // MemberEntity를 다시 연결하여 영속 상태로 만듭니다.
                if (!entityManager.contains(memberEntity)) {
                    memberEntity = entityManager.merge(memberEntity);
                }

                memberGenreDTO.setMemberEntity(memberEntity);
                memberGenreDTO.setGenreEntity(genreEntity);

                memberGenreService.save(memberGenreDTO);
            }
        }


        // 다음 단계로 이동합니다.
        return "major";
    }

    @PostMapping("/member/major-search")
    @ResponseBody
    public List<String> search(@RequestBody Map<String, String> requestBody) {
        String keyword = requestBody.get("keyword");
        System.out.println(keyword);
        return majorService.search(keyword);
    }

    @PostMapping("/member/major")
    public String majorSearch(@RequestParam(value = "selectedMajor", required = false) String selectedMajor, HttpSession session) {
        if (selectedMajor == null) {
            selectedMajor = " "; // 여기에 원하는 기본값을 설정하세요.
        }
        Long id = (Long) session.getAttribute("saveDTO");
        MemberDTO memberDTO = memberService.findById(id);
        memberService.updateMajor(memberDTO.getMemberEmail(), selectedMajor);
        return "test";
    }

    @PostMapping("/save-answer")
    public ResponseEntity<Map<String, Object>> submitAnswer(@RequestBody QuizAnswerDTO answerDTO, HttpSession session) {

        Map<String, Object> response = new HashMap<>();
        Long id = (Long) session.getAttribute("saveDTO");
        MemberDTO memberDuringSignup = memberService.findById(id);
        String myEmail = memberDuringSignup.getMemberEmail();

        if (memberDuringSignup == null) {
            response.put("error", "User is not in the signup process or session data is missing");
            System.out.println("에러!!!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        System.out.println(answerDTO.getQuizId() + " and " + answerDTO.getMemberAnswer());

        boolean isCorrect = memberService.checkAnswerAndIncrementScore(
                answerDTO.getQuizId(), answerDTO.getMemberAnswer(), myEmail);

        // 답변이 제출된 후, 사용자의 레벨 업데이트
        memberService.updateMemberLevel(myEmail);

        response.put("isCorrect", isCorrect);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/resave-answer")
    public ResponseEntity<Map<String, Object>> reSubmitAnswer(@RequestBody QuizAnswerDTO answerDTO, HttpSession session) {

        Map<String, Object> response = new HashMap<>();
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDuringSignup = memberService.findByEmail(myEmail);

        if (memberDuringSignup == null) {
            response.put("error", "User is not in the signup process or session data is missing");
            System.out.println("에러!!!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        System.out.println(answerDTO.getQuizId() + " and " + answerDTO.getMemberAnswer());

        boolean isCorrect = memberService.checkAnswerAndIncrementScore(
                answerDTO.getQuizId(), answerDTO.getMemberAnswer(), myEmail);

        // 답변이 제출된 후, 사용자의 레벨 업데이트
        memberService.updateMemberLevel(myEmail);

        response.put("isCorrect", isCorrect);
        return ResponseEntity.ok(response);
    }


    // 테스트
    @GetMapping("test/start")
    public String showQuizPage(HttpSession session) { return "test"; }
    @GetMapping("test/1")
    public String showQuizPage1() {
        return "test-1";
    }
    @GetMapping("test/2")
    public String showQuizPage2() {
        return "test-2";
    }
    @GetMapping("test/3")
    public String showQuizPage3() {
        return "test-3";
    }
    @GetMapping("test/4")
    public String showQuizPage4() {
        return "test-4";
    }
    @GetMapping("test/5")
    public String showQuizPage5() {
        return "test-5";
    }
    @GetMapping("test/6")
    public String showQuizPage6() {
        return "test-6";
    }
    @GetMapping("test/7")
    public String showQuizPage7() {
        return "test-7";
    }
    @GetMapping("test/8")
    public String showQuizPage8() {
        return "test-8";
    }
    @GetMapping("test/9")
    public String showQuizPage9() {
        return "test-9";
    }
    @GetMapping("test/10")
    public String showQuizPage10() {
        return "test-10";
    }

    // 재시험
    @GetMapping("test/restart")
    public String showReQuizPage(HttpSession session) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.findByEmail(myEmail);

        memberDTO.setMemberScore(0L);
        memberDTO.setMemberLevel(0L);
        memberService.update(memberDTO);

        System.out.println(memberDTO.getMemberEmail());
        System.out.println(memberDTO.getMemberScore());
        System.out.println(memberDTO.getMemberLevel());
        return "retest";
    }

    @GetMapping("test/11")
    public String showReQuizPage1() {
        return "test-11";
    }
    @GetMapping("test/12")
    public String showReQuizPage2() {
        return "test-12";
    }
    @GetMapping("test/13")
    public String showReQuizPage3() {
        return "test-13";
    }
    @GetMapping("test/14")
    public String showReQuizPage4() {
        return "test-14";
    }
    @GetMapping("test/15")
    public String showReQuizPage5() {
        return "test-15";
    }
    @GetMapping("test/16")
    public String showReQuizPage6() {
        return "test-16";
    }
    @GetMapping("test/17")
    public String showReQuizPage7() {
        return "test-17";
    }
    @GetMapping("test/18")
    public String showReQuizPage8() {
        return "test-18";
    }
    @GetMapping("test/19")
    public String showReQuizPage9() {
        return "test-19";
    }
    @GetMapping("test/20")
    public String showReQuizPage10() { return "test-20"; }

    @GetMapping("/test/pass")
    public String testPassForm() {
        return "passtest";
    }

    @PostMapping("/test/pass")
    public String testPass(@RequestParam String difficulty, HttpSession session) {
        Long id = (Long) session.getAttribute("saveDTO");
        MemberDTO saveDTO = memberService.findById(id);

        saveDTO.setMemberScore(1L);
        if (difficulty.equals("하")) {
            saveDTO.setMemberLevel(6L);
        } else if (difficulty.equals("중")) {
            saveDTO.setMemberLevel(7L);
        } else if (difficulty.equals("상")) {
            saveDTO.setMemberLevel(8L);
        }

        memberService.save(saveDTO);

        return "login";
    }

    @GetMapping("retest/result")
    public String retestResult(HttpSession session) {
        String myEmail = (String) session.getAttribute("loginEmail");

        // memberService를 사용하여 member_level을 가져오기
        Long memberLevelLong = memberService.getMemberLevelByEmail(myEmail);
        int memberLevel = memberLevelLong.intValue();

        // "result-<memberlevel>.html"으로 리다이렉트
        return "redirect:/rtresult-" + memberLevel;
    }

    @GetMapping("test/result")
    public String signupResult(HttpSession session, RedirectAttributes attributes) {
        Long id = (Long) session.getAttribute("saveDTO");
        MemberDTO saveDTO = memberService.findById(id);

        if (saveDTO == null) {
            // 세션에 saveDTO 정보가 없는 경우
            return "error";
        }

        String memberEmail = saveDTO.getMemberEmail();

        if (memberEmail == null || memberEmail.isEmpty()) {
            // 이메일 주소가 비어 있을 경우의 처리...
            return "error";
        }

        // memberService를 사용하여 member_level을 가져오기
        Long memberLevelLong = memberService.getMemberLevelByEmail(memberEmail);
        int memberLevel = memberLevelLong.intValue();

        // "result-<memberlevel>.html"으로 리다이렉트
        return "redirect:/result-" + memberLevel;
    }

    @GetMapping("/result-{memberLevel}")
    public String showResultPage(@PathVariable int memberLevel, Model model) {
        // 로직 (예: memberLevel에 따른 데이터를 모델에 추가하는 코드)
        return "result-" + memberLevel; // 실제로 반환되는 뷰 이름
    }

    @GetMapping("/rtresult-{memberLevel}")
    public String showRTresultPage(@PathVariable int memberLevel, Model model) {
        // 로직 (예: memberLevel에 따른 데이터를 모델에 추가하는 코드)
        return "rtresult-" + memberLevel; // 실제로 반환되는 뷰 이름
    }




}