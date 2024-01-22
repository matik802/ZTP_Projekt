package Controllers;

public abstract class QuizState {
    private QuizController quizController;
    private IQuizDifficultyManager quizDifficultyManager;

    public abstract void getNextQuestion();

    public void setQuizContoller(QuizController quizController) {
        this.quizController = quizController;
    }

    public void setQuizDifficultyManager(IQuizDifficultyManager quizDifficultyManager) {
        this.quizDifficultyManager = quizDifficultyManager;
    }
}
