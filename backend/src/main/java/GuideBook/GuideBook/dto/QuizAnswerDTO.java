package GuideBook.GuideBook.dto;

public class QuizAnswerDTO {

    private Long memberId;      // The member's ID
    private Long quizId;        // The ID of the quiz
    private String memberAnswer;  // The answer provided by the member
    private String answer;      // The actual answer to the quiz question

    // Standard getters and setters

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getMemberAnswer() {
        return memberAnswer;
    }

    public void setMemberAnswer(String memberAnswer) {
        this.memberAnswer = memberAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
