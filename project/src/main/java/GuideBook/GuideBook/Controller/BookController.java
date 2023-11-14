package GuideBook.GuideBook.Controller;

import GuideBook.GuideBook.dto.MemberDTO;
import GuideBook.GuideBook.dto.BookDTO;
import GuideBook.GuideBook.dto.MemberMyBookDTO;
import GuideBook.GuideBook.dto.MemberMyLikeDTO;
import GuideBook.GuideBook.entity.BookEntity;
import GuideBook.GuideBook.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final MemberMyBookService memberMyBookService;
    private final MemberMyLikeService memberMyLikeService;
    private final GenreService genreService;


    @PostMapping("/book/search")
    public String search(@RequestParam("bookTitle") String bookTitle, Model model) {
        List<BookDTO> bookDTOList = bookService.search(bookTitle);
        model.addAttribute("bookList", bookDTOList);
        return "search";
    }

    @GetMapping("/book/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, HttpSession session) {
        BookDTO bookDTO = bookService.findById(id);
        session.setAttribute("book", bookDTO);
        model.addAttribute("book", bookDTO);
        session.setAttribute("bookId", id);

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");
        if (memberDTO != null) {
            model.addAttribute("memberDTO", memberDTO);
            System.out.println(memberDTO.getMemberName());
        } else {
            System.out.println("로그인이 되어있지 않습니다.");
        }
        return "detail";
    }


    @GetMapping("/book/library")
    public String addLibraryForm(Model model, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");
        BookDTO bookDTO = (BookDTO) session.getAttribute("book");
        model.addAttribute("book", bookDTO);
        model.addAttribute("memberDTO", memberDTO);
        System.out.println(memberDTO.getMemberName());
        return "detail";
    }

    @PostMapping("/book/library")
    public String addLibrary(HttpSession session) {
        Long id = (Long) session.getAttribute("bookId");
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");

        System.out.println(id);
        System.out.println(memberDTO.getId());

        if (memberMyBookService.existsByMemberIdAndBookId(memberDTO.getId(), id)) {
            return "redirect:/recommendationsuccess";
        } else {
            MemberMyBookDTO memberMyBookDTO = new MemberMyBookDTO();
            memberMyBookDTO.setMemberEntityId(memberDTO.getId());
            memberMyBookDTO.setBookEntityId(id);
            memberMyBookService.save(memberMyBookDTO);
            return "redirect:/recommendationsuccess";
        }
    }

    @GetMapping("/book/like")
    public String addLikeForm(Model model, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");
        BookDTO bookDTO = (BookDTO) session.getAttribute("book");
        model.addAttribute("book", bookDTO);
        model.addAttribute("memberDTO", memberDTO);
        System.out.println(memberDTO.getMemberName());
        return "detail";
    }

    @PostMapping("/book/like")
    public String addLike(HttpSession session) {
        Long id = (Long) session.getAttribute("bookId");
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");

        System.out.println(id);
        System.out.println(memberDTO.getId());

        if (memberMyLikeService.existsByMemberIdAndBookId(memberDTO.getId(), id)) {
            return "redirect:/recommendationsuccess";
        } else {
            MemberMyLikeDTO memberMyLikeDTO = new MemberMyLikeDTO();
            memberMyLikeDTO.setMemberEntityId(memberDTO.getId());
            memberMyLikeDTO.setBookEntityId(id);
            memberMyLikeService.save(memberMyLikeDTO);
            return "redirect:/recommendationsuccess";
        }
    }

    @GetMapping("/book/check")
    @ResponseBody
    public Map<String, Boolean> checkBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        Map<String, Boolean> response = new HashMap<>();
        boolean exists = memberMyBookService.existsByMemberIdAndBookId(memberId, bookId);
        response.put("exists", exists);
        return response;
    }

    @GetMapping("/book/mybook/{id}")
    public ResponseEntity<BookDTO> getMyBookById(@PathVariable Long id) {
        BookDTO bookDTO = bookService.findById(id);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/book/status/{id}")
    public ResponseEntity<MemberMyBookDTO> statusBook (@PathVariable Long id, HttpSession session) {
        // bookId로 bookDTO 찾기
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");
        Optional<MemberMyBookDTO> optionalMyBookDTO = memberMyBookService.findByMemberIdAndBookId(memberDTO.getId(), id);

        if (optionalMyBookDTO.isPresent()) {
            MemberMyBookDTO myBookDTO = optionalMyBookDTO.get();
            System.out.println("Received data in server: " + myBookDTO.getId()); // 데이터 콘솔 출력
            return ResponseEntity.ok(myBookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/book/complete")
    public ResponseEntity<MemberMyBookDTO> completeBook(@RequestBody Map<String, Object> requestData, HttpSession session) {
        // bookId로 bookDTO 찾기
        Long bookId = Long.parseLong(requestData.get("bookId").toString());
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");
        Optional<MemberMyBookDTO> optionalMyBookDTO = memberMyBookService.findByMemberIdAndBookId(memberDTO.getId(), bookId);

        if (optionalMyBookDTO.isPresent()) {
            MemberMyBookDTO myBookDTO = optionalMyBookDTO.get();
            System.out.println("Received data in server: " + myBookDTO.getId()); // 데이터 콘솔 출력
            myBookDTO.setStatus(1);
            myBookDTO.setStatusDetail("100");
            memberMyBookService.save(myBookDTO);
            return ResponseEntity.ok(myBookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/book/pause")
    public ResponseEntity<MemberMyBookDTO> pauseBook(@RequestBody Map<String, Object> requestData, HttpSession session) {
        // bookId로 bookDTO 찾기
        Long bookId = Long.parseLong(requestData.get("bookId").toString());
        String reason = (String) requestData.get("reason");
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");
        Optional<MemberMyBookDTO> optionalMyBookDTO = memberMyBookService.findByMemberIdAndBookId(memberDTO.getId(), bookId);

        if (optionalMyBookDTO.isPresent()) {
            MemberMyBookDTO myBookDTO = optionalMyBookDTO.get();
            System.out.println("Received data in server: " + myBookDTO.getId()); // 데이터 콘솔 출력
            myBookDTO.setStatus(2);
            myBookDTO.setStatusDetail(reason);
            memberMyBookService.save(myBookDTO);
            return ResponseEntity.ok(myBookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/book/progress")
    public ResponseEntity<MemberMyBookDTO> progressBook(@RequestBody Map<String, Object> requestData, HttpSession session) {
        // bookId로 bookDTO 찾기
        Long bookId = Long.parseLong(requestData.get("bookId").toString());
        BookDTO bookDTO = bookService.findById(bookId);
        Long progress = Long.parseLong(requestData.get("progress").toString());
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");
        Optional<MemberMyBookDTO> optionalMyBookDTO = memberMyBookService.findByMemberIdAndBookId(memberDTO.getId(), bookId);

        if (optionalMyBookDTO.isPresent()) {
            MemberMyBookDTO myBookDTO = optionalMyBookDTO.get();
            System.out.println("Received data in server: " + myBookDTO.getId()); // 데이터 콘솔 출력
            myBookDTO.setStatus(3);
            Long bookPage = bookDTO.getBookPage();
            double myprogress = (double) progress / bookPage * 100;
            String result = String.format("%.2f", myprogress);
            myBookDTO.setStatusDetail(result);
            memberMyBookService.save(myBookDTO);
            return ResponseEntity.ok(myBookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/book/delete/{id}")
    public ResponseEntity<MemberMyBookDTO> deleteBookForm (@PathVariable Long id, HttpSession session) {
        // bookId로 bookDTO 찾기
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");
        Optional<MemberMyBookDTO> optionalMyBookDTO = memberMyBookService.findByMemberIdAndBookId(memberDTO.getId(), id);

        if (optionalMyBookDTO.isPresent()) {
            MemberMyBookDTO myBookDTO = optionalMyBookDTO.get();
            System.out.println("Received data in server: " + myBookDTO.getId()); // 데이터 콘솔 출력
            return ResponseEntity.ok(myBookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/book/delete")
    public ResponseEntity<MemberMyBookDTO> deleteBook (@RequestBody Map<String, Object> requestData, HttpSession session) {
        // bookId로 bookDTO 찾기
        Long bookId = Long.parseLong(requestData.get("bookId").toString());
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");
        Optional<MemberMyBookDTO> optionalMyBookDTO = memberMyBookService.findByMemberIdAndBookId(memberDTO.getId(), bookId);

        if (optionalMyBookDTO.isPresent()) {
            MemberMyBookDTO myBookDTO = optionalMyBookDTO.get();
            Long id = myBookDTO.getId();
            System.out.println("Received data in server: " + myBookDTO.getId()); // 데이터 콘솔 출력
            memberMyBookService.deleteById(id);
            return ResponseEntity.ok(myBookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/book/mylike/{id}")
    public ResponseEntity<MemberMyLikeDTO> likeToLibraryForm (@PathVariable Long id, HttpSession session) {
        // bookId로 bookDTO 찾기
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");
        Optional<MemberMyLikeDTO> optionalMyLikeDTO = memberMyLikeService.findByMemberIdAndBookId(memberDTO.getId(), id);
        System.out.println(memberDTO.getId());
        System.out.println(id);

        if (optionalMyLikeDTO.isPresent()) {
            MemberMyLikeDTO myLikeDTO = optionalMyLikeDTO.get();
            System.out.println("Received data in server: " + myLikeDTO.getId()); // 데이터 콘솔 출력
            return ResponseEntity.ok(myLikeDTO);
        } else {
            System.out.println("Cannot found"); // 데이터 콘솔 출력
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/book/mylike")
    public ResponseEntity<MemberMyBookDTO> likeToLibrary(@RequestBody Map<String, Object> requestData, HttpSession session) {
        // bookId로 bookDTO 찾기
        Long bookId = Long.parseLong(requestData.get("bookId").toString());
        System.out.println(bookId);
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");

        Optional<MemberMyLikeDTO> optionalMyLikeDTO = memberMyLikeService.findByMemberIdAndBookId(memberDTO.getId(), bookId);

        if (optionalMyLikeDTO.isPresent()) {
            if (memberMyBookService.existsByMemberIdAndBookId(memberDTO.getId(), bookId)) {
                return ResponseEntity.notFound().build();
            } else {
                memberMyLikeService.deleteById(optionalMyLikeDTO.get().getId());

                MemberMyBookDTO memberMyBookDTO = new MemberMyBookDTO();
                memberMyBookDTO.setMemberEntityId(memberDTO.getId());
                memberMyBookDTO.setBookEntityId(bookId);
                memberMyBookService.save(memberMyBookDTO);
                return ResponseEntity.ok(memberMyBookDTO);
            }
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/book/mystatus/{id}")
    public ResponseEntity<MemberMyBookDTO> myStatusForm(@PathVariable Long id, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");
        Optional<MemberMyBookDTO> optionalMyBookDTO = memberMyBookService.findByMemberIdAndBookId(memberDTO.getId(), id);

        if (optionalMyBookDTO.isPresent()) {
            MemberMyBookDTO myBookDTO = optionalMyBookDTO.get();
            System.out.println(myBookDTO.getStatus());
            System.out.println(myBookDTO.getStatusDetail());
            return ResponseEntity.ok(myBookDTO);
        } else {
            System.out.println("Cannot found"); // 데이터 콘솔 출력
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/book/memo/{id}")
    public ResponseEntity<MemberMyBookDTO> addMemoForm(@PathVariable Long id, Model model, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");
        Optional<MemberMyBookDTO> optionalMyBookDTO = memberMyBookService.findByMemberIdAndBookId(memberDTO.getId(), id);

        if (optionalMyBookDTO.isPresent()) {
            MemberMyBookDTO myBookDTO = optionalMyBookDTO.get();
            return ResponseEntity.ok(myBookDTO);
        } else {
            System.out.println("Cannot found"); // 데이터 콘솔 출력
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/book/memo")
    public ResponseEntity<MemberMyBookDTO> addMemo(@RequestBody Map<String, Object> requestData, HttpSession session) {
        // bookId로 bookDTO 찾기
        Long bookId = Long.parseLong(requestData.get("bookId").toString());
        String memo = requestData.get("memo").toString();
        System.out.println(bookId);
        System.out.println(memo);
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginDTO");
        System.out.println(memberDTO.getId());

        Optional<MemberMyBookDTO> optionalMyBookDTO = memberMyBookService.findByMemberIdAndBookId(memberDTO.getId(), bookId);

        if (optionalMyBookDTO.isPresent()) {
            MemberMyBookDTO memberMyBookDTO = optionalMyBookDTO.get();
            System.out.println(memberMyBookDTO.getId());
            memberMyBookDTO.setMemo(memo);
            System.out.println(memo);
            memberMyBookService.save(memberMyBookDTO);
            return ResponseEntity.ok(memberMyBookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}






