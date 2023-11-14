package GuideBook.GuideBook.Controller;

import GuideBook.GuideBook.dto.MemberDTO;
import GuideBook.GuideBook.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map; // Importing Map class

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiMemberController {

    private final MemberService memberService;

    @PostMapping("/get_member_data")
    public MemberDTO getMemberData(@RequestBody Map<String, String> requestBody, HttpSession session) {
        // 예시로 requestBody에서 email 값을 받아오는 코드. 실제 로직에 맞게 수정이 필요합니다.
        String email = requestBody.get("email");

        // 세션에서 이메일을 가져옵니다.
        String sessionEmail = (String) session.getAttribute("loginEmail");

        // 만약 세션 이메일이 null이거나, 세션 이메일과 요청 이메일이 다르다면, 권한이 없다는 응답을 줍니다.
        if (sessionEmail == null || !sessionEmail.equals(email)) {
            throw new RuntimeException("Unauthorized request"); // 실제 사용 시에는 적절한 예외 처리 및 응답 로직이 필요합니다.
        }

        // 이메일에 해당하는 멤버의 정보를 가져옵니다.
        return memberService.findByEmail(email); // 이 메서드는 이메일을 파라미터로 받아, 이에 해당하는 MemberDTO를 반환해야 합니다.
    }
}
