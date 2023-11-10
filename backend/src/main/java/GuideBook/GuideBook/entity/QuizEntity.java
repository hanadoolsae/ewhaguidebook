package GuideBook.GuideBook.entity;

import lombok.Getter;

import javax.persistence.*;



@Entity
@Table(name = "quiz_table")
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long quizId;

    @Getter
    @Column(name = "quiz_answer")
    private String quizAnswer;


    // Getter, Setter, Constructor 등...
}
