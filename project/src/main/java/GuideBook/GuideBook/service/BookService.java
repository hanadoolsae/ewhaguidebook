package GuideBook.GuideBook.service;

import GuideBook.GuideBook.dto.ApiBookDTO;
import GuideBook.GuideBook.dto.BookDTO;
import GuideBook.GuideBook.entity.BookEntity;
import GuideBook.GuideBook.repository.BookRepository;
import GuideBook.GuideBook.entity.MemberMyBookEntity;
import GuideBook.GuideBook.entity.MemberMyLikeEntity;
import GuideBook.GuideBook.repository.BookRepository;
import GuideBook.GuideBook.repository.MemberMyBookRepository;
import GuideBook.GuideBook.repository.MemberMyLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final MemberMyLikeRepository memberMyLikeRepository;
    private final MemberMyBookRepository memberMyBookRepository;
    private final String API_URL = "http://data4library.kr/api/loanItemSrch?authKey=bae8f3e6a6c795092d5f4d9be0ce476f1779b249e92f51c4a0e3afa1b2d06c89&startDt=2023-01-01&endDt=2023-10-18";

    public List<BookDTO> search(String indtroduce) {
        List<BookEntity> bookEntityList = bookRepository.findByBookNameContaining(indtroduce);
        List<BookDTO> bookDTOList = new ArrayList<>();

        for (BookEntity bookEntity : bookEntityList) {
            BookDTO bookDTO = BookDTO.toBookDTO(bookEntity);
            bookDTOList.add(bookDTO);
        }

        return bookDTOList;
    }

    public BookDTO findById(Long id) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);
        if (optionalBookEntity.isPresent()) {
            return BookDTO.toBookDTO(optionalBookEntity.get());
        } else {
            return null;
        }
    }


    public BookDTO getRandomLikedOrCompletedBook(Long memberId) {
        List<MemberMyLikeEntity> likedEntities = memberMyLikeRepository.findByMemberEntityId(memberId);
        List<MemberMyBookEntity> completedEntities = memberMyBookRepository.findByMemberEntityIdAndStatus(memberId, 1);

        List<Long> likedBookIds = likedEntities.stream()
                .map(MemberMyLikeEntity::getBookEntityId)
                .collect(Collectors.toList());

        List<Long> completedBookIds = completedEntities.stream()
                .map(MemberMyBookEntity::getBookEntityId)
                .collect(Collectors.toList());

        // likedBookIds와 completedBookIds를 결합
        List<Long> combinedBookIds = Stream.concat(likedBookIds.stream(), completedBookIds.stream())
                .distinct()
                .collect(Collectors.toList());

        if (!combinedBookIds.isEmpty()) {
            Collections.shuffle(combinedBookIds);
            Optional<BookEntity> book = bookRepository.findById(combinedBookIds.get(0));
            return book.map(BookDTO::toBookDTO).orElse(null);
        } else {
            return null;
        }
    }

}
