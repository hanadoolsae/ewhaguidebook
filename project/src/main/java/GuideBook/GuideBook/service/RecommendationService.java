package GuideBook.GuideBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RecommendationService {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RecommendationService.class);

    @Value("${flask.recommendation.url}") // application.properties에서 설정값 주입
    private String flaskApiUrl;

    @Autowired
    public RecommendationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getRecommendations(Long memberId) {
        // memberId로 추천을 요청하는 URL을 생성합니다.
        String url = flaskApiUrl + "/similar_books_by_member/" + memberId;

        // 로그를 출력합니다.
        logger.info("Requesting recommendations for member ID: {}", memberId);

        try {
            // REST 호출을 통해 Flask 서비스로부터 추천 받아옵니다.
            String recommendations = restTemplate.getForObject(url, String.class);

            // 성공적으로 추천을 받아온 경우, 로그를 출력합니다.
            logger.info("Received recommendations for member ID: {}", memberId);

            return recommendations;
        } catch (Exception e) {
            // 예외 발생 시, 에러 로그를 출력합니다.
            logger.error("Error occurred while requesting recommendations for member ID: {}", memberId, e);

            // 예외를 던져 다른 곳에서 처리하게 합니다.
            throw e;
        }
    }
}
