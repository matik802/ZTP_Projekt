package Controllers;
import java.util.List;

import Models.Question;
import Views.QuizView;

public class QuizController {
    private QuizState quizState;
    private List<Question> questionsPool;
    private QuizView quizView;
    private String language;
    
    public QuizController(QuizView quizView) {
        this.quizView = quizView;
        //pobieranie z bazy
    }

    public void getNextQuestion() {
           
    }

    public void showResult() {

    }


}
