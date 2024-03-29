package Controllers;

import Models.Question;
import Models.Word;
import Views.QuizView;

import javax.swing.table.DefaultTableModel;
import java.util.List;

// Kontroler odpowiedzialny za zarządzanie stanem quizu oraz dostępem do bazy danych
public class QuizController {

    // Aktualny stan quizu
    private QuizState quizState;

    // Obiekt dostępu do bazy danych
    private DatabaseAccess databaseAccess;

    // Metoda do pobierania danych
    public void fetchData() {
        quizState.fetchData(this);
    }

    // Metoda do rozpoczęcia quizu
    public void startQuiz() {
        quizState.startQuiz();
    }

    // Metoda do ustawiania aktualnego stanu quizu
    public void setQuizState(QuizState quizState) {
        this.quizState = quizState;
    }

    // Metoda do ustawiania języka pytań
    public void setQuestionsLanguage(String questionsLanguage) {
        quizState.setQuestionsLanguage(questionsLanguage);
    }

    // Metoda do ustawiania konfiguracji quizu
    public void setQuizConfiguration(QuizConfiguration quizConfiguration) {
        quizState.setQuizConfiguration(quizConfiguration);
    }

    // Metoda do ustawiania menedżera trudności quizu
    public void setQuizDifficultyManager(IQuizDifficultyManager quizDifficultyManager) {
        quizState.setQuizDifficultyManager(quizDifficultyManager);
    }

    // Metoda do ustawiania widoku quizu
    public void setQuizView(QuizView quizView) {
        quizState.setQuizView(quizView);
    }

    // Metoda do inicjalizacji pytań
    public List<Question> initQuestions() {
    DatabaseAccess databaseAccess = new DatabaseAccess(quizState.questionsLanguage);

        return databaseAccess.initQuestions();
    }

    // Metoda do dodawania słowa do pliku JSON
    public void addWordToJsonFile(String question, String translation) {    DatabaseAccess databaseAccess = new DatabaseAccess(quizState.questionsLanguage);

        databaseAccess.addWordToJsonFile(question, translation);
    }

    // Metoda do odświeżania tabeli w interfejsie graficznym
    public void refreshTable(DefaultTableModel tableModel, List<Question> questionList) {    DatabaseAccess databaseAccess = new DatabaseAccess(quizState.questionsLanguage);

        databaseAccess.refreshTable(tableModel, questionList);
    }

    // Metoda do usuwania słowa z pliku JSON
    public void deleteWordFromJsonFile(String question) {    DatabaseAccess databaseAccess = new DatabaseAccess(quizState.questionsLanguage);

        databaseAccess.deleteWordFromJsonFile(question);
    }

    // Metoda do edycji słowa w pliku JSON
    public void editWordInJsonFile(String oldQuestion, String newQuestion, String newTranslation) {    DatabaseAccess databaseAccess = new DatabaseAccess(quizState.questionsLanguage);

        databaseAccess.editWordInJsonFile(oldQuestion, newQuestion, newTranslation);
    }

    // Metoda do pobierania listy słów
    public List<Word> getWords() {    DatabaseAccess databaseAccess = new DatabaseAccess(quizState.questionsLanguage);

        return databaseAccess.getWords();
    }

    // Metoda do pobierania listy pytań
    public List<Question> getQuestions() {    DatabaseAccess databaseAccess = new DatabaseAccess(quizState.questionsLanguage);

        return databaseAccess.getQuestions();
    }
}
