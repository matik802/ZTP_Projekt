package Controllers;

import Utils.Constants;

public class AdaptiveQuiz implements IQuizDifficultyManager{
    private String difficulty;
    private int badAnswerStreak;
    private int goodAnswerStreak;
    private int streak;
    public AdaptiveQuiz(int streak) {
        super();
        this.streak = streak;
    }
    @Override
    public String getDifficulty(Boolean... correctAnswer) {
        if (correctAnswer.length > 0) {
            if (correctAnswer[0]) {
                goodAnswerStreak++;
                badAnswerStreak = 0;
                if (goodAnswerStreak == streak) {
                    difficulty = Constants.hardDifficultyLevel;
                }
            }
            else if (!correctAnswer[0]) {
                badAnswerStreak++;
                goodAnswerStreak = 0;
                if (badAnswerStreak == streak) {
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
