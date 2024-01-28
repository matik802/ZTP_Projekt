package Controllers;

// Klasa reprezentująca statyczny quiz, w którym poziom trudności nie zmienia się dynamicznie
public class StaticQuiz implements IQuizDifficultyManager {
    private String difficulty;

    // Metoda do pobierania aktualnego poziomu trudności (zawsze taki sam w przypadku statycznego quizu)
    @Override
    public String getDifficulty(Boolean... correctAnswer) {
        return difficulty;
    }

    // Metoda do ustawiania poziomu trudności
    @Override
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
