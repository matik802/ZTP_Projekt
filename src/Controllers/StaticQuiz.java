package Controllers;

public class StaticQuiz implements IQuizDifficultyManager{
    private String difficulty;
    @Override
    public String getDifficulty(Boolean... correctAnswer) {
        return difficulty;
    }

    @Override
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
}
