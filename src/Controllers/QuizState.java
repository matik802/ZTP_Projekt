package Controllers;

import java.util.List;

import Models.Question;
import Models.Word;
import Views.QuizView;

// Abstrakcyjna klasa stanu quizu, która definiuje wspólne funkcjonalności dla różnych stanów
public abstract class QuizState {
    protected QuizView quizView;
    protected IQuizDifficultyManager quizDifficultyManager;
    protected QuizConfiguration quizConfiguration;
    protected QuizController quizController;
    protected Question currentQuestion;
    protected List<Question> questionsPool;
    protected List<Word> wordsPool;
    protected String questionsLanguage;
    protected int questionsCount = 0;

    // Metoda do pobierania danych potrzebnych do przeprowadzenia quizu
    public void fetchData(QuizController quizController) {
        //pobranie danych z bazy

        wordsPool = quizController.getWords();
        questionsPool = quizController.getQuestions();
    }

    // Metoda abstrakcyjna rozpoczynająca quiz (implementacja zależy od konkretnej klasy stanu)
    public abstract void startQuiz();

    // Metoda abstrakcyjna przechodząca do kolejnego pytania (implementacja zależy od konkretnej klasy stanu)
    public abstract void getNextQuestion();

    // Metoda do ustawiania widoku quizu
    public void setQuizView(QuizView quizView) {
        this.quizView = quizView;
    }

    // Metoda do ustawiania menedżera trudności quizu
    public void setQuizDifficultyManager(IQuizDifficultyManager quizDifficultyManager) {
        this.quizDifficultyManager = quizDifficultyManager;
    }

    // Metoda do ustawiania języka pytań
    public void setQuestionsLanguage(String questionsLanguage) {
        this.questionsLanguage = questionsLanguage;
    }

    // Metoda do ustawiania konfiguracji quizu
    public void setQuizConfiguration(QuizConfiguration quizConfiguration) {
        this.quizConfiguration = quizConfiguration;
    }
}
