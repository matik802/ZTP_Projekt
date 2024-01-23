package Controllers;

import java.util.ArrayList;
import java.util.List;

import Models.HardQuestion;
import Models.Question;
import Models.Word;
import Utils.Constants;
import Views.QuizView;

public abstract class QuizState {
    protected QuizController quizController;
    protected QuizView quizView;
    protected IQuizDifficultyManager quizDifficultyManager;
    protected QuizConfiguration quizConfiguration;
    protected Question currentQuestion;
    protected List<Question> questionsPool;
    protected List<Word> wordsPool;
    protected String questionsLanguage;
    protected int questionsCount = 0;

    public void fetchData() {
        //pobieranie z bazy
        if (questionsLanguage.equals(Constants.languagePl)) {
            wordsPool = new ArrayList<>();
            wordsPool.add(new Word("Dog", Constants.languageEng));
            wordsPool.add(new Word("Cat", Constants.languageEng));
            wordsPool.add(new Word("Horse", Constants.languageEng));
            wordsPool.add(new Word("House", Constants.languageEng));
            wordsPool.add(new Word("Pizza", Constants.languageEng));
            wordsPool.add(new Word("Human", Constants.languageEng));
            wordsPool.add(new Word("Earth", Constants.languageEng));

            questionsPool = new ArrayList<>();
            HardQuestion[] hardQuestions = new HardQuestion[10];
            hardQuestions[0] = new HardQuestion(1,"Pies",Constants.languagePl);
            hardQuestions[0].setCorrectAnswer(wordsPool.get(0));
            questionsPool.add(hardQuestions[0]);
            hardQuestions[1] = new HardQuestion(1,"Kot",Constants.languagePl);
            hardQuestions[1].setCorrectAnswer(wordsPool.get(1));
            questionsPool.add(hardQuestions[1]);
            hardQuestions[2] = new HardQuestion(1,"Koń",Constants.languagePl);
            hardQuestions[2].setCorrectAnswer(wordsPool.get(2));
            questionsPool.add(hardQuestions[2]);
            hardQuestions[3] = new HardQuestion(1,"Dom",Constants.languagePl);
            hardQuestions[3].setCorrectAnswer(wordsPool.get(3));
            questionsPool.add(hardQuestions[3]);
            
        }
    }

    public abstract void startQuiz();

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

    public void setQuizConfiguration(QuizConfiguration quizConfiguration) {
        this.quizConfiguration = quizConfiguration;
    }
}
