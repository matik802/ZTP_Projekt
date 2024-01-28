package Controllers;


import java.util.List;

import Models.Question;
import Models.Word;

import Views.QuizView;

public abstract class QuizState {
    protected QuizView quizView;
    protected IQuizDifficultyManager quizDifficultyManager;
    protected QuizConfiguration quizConfiguration;
    protected Question currentQuestion;
    protected List<Question> questionsPool;
    protected List<Word> wordsPool;
    protected String questionsLanguage;
    protected int questionsCount = 0;



    public void fetchData() {

        DatabaseAccess database = new DatabaseAccess(questionsLanguage);
        wordsPool = database.getWords();
        questionsPool = database.getQuestions();

    }

    public abstract void startQuiz();

    public abstract void getNextQuestion();

    public void setQuizView(QuizView quizView) {
        this.quizView = quizView;
    }

    public void setQuizDifficultyManager(IQuizDifficultyManager quizDifficultyManager) {
        this.quizDifficultyManager = quizDifficultyManager;
    }

    public void setQuestionsLanguage(String questionsLanguage) {
        this.questionsLanguage = questionsLanguage;
    }

    public void setQuizConfiguration(QuizConfiguration quizConfiguration) {
        this.quizConfiguration = quizConfiguration;
    }
}