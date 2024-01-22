package Controllers;

import Utils.Constants;

public class AdaptiveQuiz implements IQuizDifficultyManager{
    private String difficulty;
    private int badAnswerStreak;
    private int goodAnswerStreak;
    @Override
    public String getDifficulty(Boolean... correctAnswer) {
        if (correctAnswer[0] != null) {
            if (correctAnswer[0]) {
                goodAnswerStreak++;
                badAnswerStreak = 0;
                if (goodAnswerStreak == 3) {
                    difficulty = Constants.hardDifficultyLevel;
                }
            }
            else if (!correctAnswer[0]) {
                badAnswerStreak++;
                goodAnswerStreak = 0;
                if (badAnswerStreak == 3) {
                    difficulty = Constants.easyDifficultyLevel;
                }
            }
        }
        return difficulty;
    }

    @Override
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    
}
