package GuideBook.GuideBook.service;

import GuideBook.GuideBook.entity.QuizEntity;
import GuideBook.GuideBook.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;

    public String findAnswerByQuizId(Long quizId) {
        return quizRepository.findById(quizId)
                .map(QuizEntity::getQuizAnswer)
                .orElseThrow(() -> new IllegalArgumentException("Invalid quiz id: " + quizId));
    }
}
