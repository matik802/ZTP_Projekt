package Controllers;

public interface IQuizDifficultyManager {
    String getDifficulty(Boolean... correctAnswer);
    void setDifficulty(String difficulty);
}
