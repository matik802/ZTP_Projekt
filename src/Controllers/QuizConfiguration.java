package Controllers;

// Klasa reprezentująca konfigurację quizu
public class QuizConfiguration {

    // Jedyna instancja konfiguracji (Singleton)
    private static QuizConfiguration configuration = null;

    // Stałe dotyczące konfiguracji quizu
    private final int easyDiffultyQuestions = 3;
    private final int quizLength = 20;
    private final int toEasyInLeariningMode = 5;
    private final int toHardInLeariningMode = 5;
    private final int toEasyInTestMode = 5;
    private final int toHardInTestMode = 5;

    // Prywatny konstruktor zapobiegający tworzeniu wielu instancji
    private QuizConfiguration() {
    }

    // Metoda do uzyskania instancji konfiguracji (Singleton)
    public static QuizConfiguration getInstance() {
        if (configuration == null) {
            configuration = new QuizConfiguration();
        }
        return configuration;
    }

    // Metoda zwracająca liczbę pytań łatwych
    public int getEasyDiffultyQuestions() {
        return easyDiffultyQuestions;
    }

    // Metoda zwracająca długość quizu (liczbę pytań)
    public int getQuizLength() {
        return quizLength;
    }

    // Metoda zwracająca liczbę punktów do osiągnięcia, aby przejść do łatwiejszego poziomu w trybie nauki
    public int getToEasyInLeariningMode() {
        return toEasyInLeariningMode;
    }

    // Metoda zwracająca liczbę punktów do osiągnięcia, aby przejść do łatwiejszego poziomu w trybie testowym
    public int getToEasyInTestMode() {
        return toEasyInTestMode;
    }

    // Metoda zwracająca liczbę punktów do osiągnięcia, aby przejść do trudniejszego poziomu w trybie nauki
    public int getToHardInLeariningMode() {
        return toHardInLeariningMode;
    }

    // Metoda zwracająca liczbę punktów do osiągnięcia, aby przejść do trudniejszego poziomu w trybie testowym
    public int getToHardInTestMode() {
        return toHardInTestMode;
    }
}
