package Controllers;

import java.util.List;

import Models.Question;
import Models.Word;
import Views.QuizView;

public abstract class QuizState {
    protected QuizController quizController;
    protected QuizView quizView;
    protected IQuizDifficultyManager quizDifficultyManager;
    protected Question currentQuestion;
    protected List<Question> questionsPool;
    protected List<Word> wordsPool;
    protected String questionsLanguage;
    protected int questionsCount = 1;

    public QuizState(QuizView quizView) {
        this.quizView = quizView;
        //pobieranie z bazy
    }

    public abstract void getNextQuestion();

    public void setQuizContoller(QuizController quizController) {
        this.quizController = quizController;
    }

    public void setQuizView(QuizView quizView) {
        this.quizView = quizView;
    }

    public void setQuizDifficultyManager(IQuizDifficultyManager quizDifficultyManager) {
        this.quizDifficultyManager = quizDifficultyManager;
    }

    public void setQuestionsLanguage(String questionsLanguage) {
        this.questionsLanguage = questionsLanguage;
    }
}
