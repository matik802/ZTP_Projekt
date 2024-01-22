package Controllers;

public class AdaptiveQuiz implements IQuizDifficultyManager{
    private String difficulty;
    @Override
    public String getDifficulty() {
       return difficulty;
    }

    @Override
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
}
