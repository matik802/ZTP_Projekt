package Controllers;

import Utils.Constants;

public class AdaptiveQuiz implements IQuizDifficultyManager {
    private String difficulty;
    private int badAnswerStreak;
    private int goodAnswerStreak;
    private final int streakToEasy;
    private final int streakToHard;

    // Konstruktor klasy
    public AdaptiveQuiz(int streakToEasy, int streakToHard) {
        super();
        this.streakToEasy = streakToEasy;
        this.streakToHard = streakToHard;
    }

    // Metoda do pobierania trudności quizu na podstawie poprawnych/ niepoprawnych odpowiedzi
    @Override
    public String getDifficulty(Boolean... correctAnswer) {
        if (correctAnswer.length > 0) {
            // Jeśli odpowiedź jest poprawna
            if (correctAnswer[0]) {
                goodAnswerStreak++;
                badAnswerStreak = 0;
                // Sprawdź, czy osiągnięto wymaganą liczbę poprawnych odpowiedzi dla zwiększenia trudności
                if (goodAnswerStreak == streakToEasy) {
                    difficulty = Constants.hardDifficultyLevel;
                }
            }
            // Jeśli odpowiedź jest niepoprawna
            else if (!correctAnswer[0]) {
                badAnswerStreak++;
                goodAnswerStreak = 0;
                // Sprawdź, czy osiągnięto wymaganą liczbę niepoprawnych odpowiedzi dla zmniejszenia trudności
                if (badAnswerStreak == streakToHard) {
                    difficulty = Constants.easyDifficultyLevel;
                }
            }
        }
        return difficulty;
    }

    // Metoda do ustawiania trudności quizu
    @Override
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
