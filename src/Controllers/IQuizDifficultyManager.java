package Controllers;

public interface IQuizDifficultyManager {
    public String getDifficulty(Boolean... correctAnswer);
    public void setDifficulty(String difficulty);
}
