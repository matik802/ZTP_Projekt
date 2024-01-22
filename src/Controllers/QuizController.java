package Controllers;
import Views.QuizView;

public class QuizController {
    private QuizState quizState; 
    
    public QuizController(QuizView quizView) {
        quizState.setQuizView(quizView);
    }

    public void getNextQuestion() {
        quizState.getNextQuestion();
    }

    public void setQuizState(QuizState quizState) {
        this.quizState = quizState;
    }

    public void setQuestionsLanguage(String questionsLanguage) {
        quizState.setQuestionsLanguage(questionsLanguage);
    }
}
