package GuideBook.GuideBook.Controller;

import GuideBook.GuideBook.dto.ApiBookDTO;
import GuideBook.GuideBook.dto.MemberDTO;
import GuideBook.GuideBook.service.BookService;
import GuideBook.GuideBook.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class RecommendationController {

    private static final Logger logger = LoggerFactory.getLogger(RecommendationController.class);

    @Value("${flask.recommendation.url}")
    private String flaskRecommendationUrl;

    private final RestTemplate restTemplate;
    private final MemberService memberService;
    private final BookService bookService;

    @Autowired
    public RecommendationController(RestTemplate restTemplate, MemberService memberService, BookService bookService) {
        this.restTemplate = restTemplate;
        this.memberService = memberService; // MemberService 초기화
        this.bookService = bookService; // BookService도 초기화

    }

    // 멤버 ID를 세션에서 가져오는 메서드
    private Long getMemberIdFromSession(HttpSession session) {
        String email = (String) session.getAttribute("loginEmail");
        MemberDTO member = memberService.findByEmail(email);
        return (member != null) ? member.getId() : null;
    }


    @GetMapping("/recommendationsuccess")
    public String getRecommendationBook(HttpSession session, Model model) {
        // 세션에서 회원 ID를 가져옵니다.
        Long memberId = getMemberIdFromSession(session);
        // 회원 ID가 없으면 로그인 페이지로 리다이렉트합니다.
        if (memberId == null) {
            return "redirect:/login";
        }

        // 사용자 맞춤 도서 추천 - Flask 서비스 URL
        String customBooksUrl = flaskRecommendationUrl + "/recommendations/similar_books_by_member/" + memberId;
        Object[] customBooksRecommendations = restTemplate.getForObject(customBooksUrl, Object[].class);
        model.addAttribute("customBooksRecommendations", customBooksRecommendations);
        logger.debug("Custom Books Recommendations: {}", customBooksRecommendations); // 로그 추가

        // 전공 및 학년 기반 추천 - Flask 서비스 URL
        String majorAndGradeUrl = flaskRecommendationUrl + "/recommendations/similar_major_and_grade/" + memberId;
        Object[] majorAndGradeRecommendations = restTemplate.getForObject(majorAndGradeUrl, Object[].class);
        model.addAttribute("majorAndGradeRecommendations", majorAndGradeRecommendations);
        logger.debug("Major and Grade Recommendations: {}", majorAndGradeRecommendations); // 로그 추가

        // 내서재 기준 추천 - Flask 서비스 URL
        String randomBookTitle = (String) session.getAttribute("randomLikedOrCompletedBookTitle");
        String similarBooksUrl = flaskRecommendationUrl + "/recommendations/similar_books/" + memberId;

        // URL에 쿼리 파라미터 추가
        try {
            if(randomBookTitle != null && !randomBookTitle.isEmpty() && !randomBookTitle.equals("No favorite or completed books found.")) {
                // "No favorite or completed books found."가 아닌 경우에만 인코딩하고 URL에 추가
                randomBookTitle = URLEncoder.encode(randomBookTitle, StandardCharsets.UTF_8.toString());
                similarBooksUrl += "?randomLikedOrCompletedBookTitle=" + randomBookTitle;
            }
        } catch (UnsupportedEncodingException e) {
            // Exception handling logic here
            System.out.println("Error encoding the URL: " + e.getMessage());
        }

        Object[] similarBookRecommendations = restTemplate.getForObject(similarBooksUrl, Object[].class);
        model.addAttribute("similarBookRecommendations", similarBookRecommendations);



        // index2 템플릿을 반환하여 현재 홈페이지 템플릿으로 변경합니다.
        return "index2";
    }
}


