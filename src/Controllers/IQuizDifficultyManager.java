package Controllers;

public interface IQuizDifficultyManager {

    // Metoda do pobierania aktualnej trudności quizu na podstawie poprawnych/ niepoprawnych odpowiedzi
    // Parametr "correctAnswer" określa, czy odpowiedź jest poprawna (true) czy niepoprawna (false)
    String getDifficulty(Boolean... correctAnswer);

    // Metoda do ustawiania trudności quizu na zadaną wartość
    // Parametr "difficulty" określa poziom trudności
    void setDifficulty(String difficulty);
}
