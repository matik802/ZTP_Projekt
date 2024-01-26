package Controllers;
import Views.QuizView;

public class QuizController {
    private QuizState quizState; 
    
    public void fetchData() {
        quizState.fetchData();
    }

    public void startQuiz() {
        quizState.startQuiz();
    }

    public void setQuizState(QuizState quizState) {
        this.quizState = quizState;
    }

    public void setQuestionsLanguage(String questionsLanguage) {
        quizState.setQuestionsLanguage(questionsLanguage);
    }

    public void setQuizConfiguration(QuizConfiguration quizConfiguration) {
        quizState.setQuizConfiguration(quizConfiguration);
    }

    public void setQuizDifficultyManager(IQuizDifficultyManager quizDifficultyManager) {
        quizState.setQuizDifficultyManager(quizDifficultyManager);
    }

    public void setQuizView(QuizView quizView) {
        quizState.setQuizView(quizView);
    }
}
