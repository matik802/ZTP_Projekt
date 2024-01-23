package Controllers;

import Utils.Constants;

public class AdaptiveQuiz implements IQuizDifficultyManager{
    private String difficulty;
    private int badAnswerStreak;
    private int goodAnswerStreak;
    private int streakToEasy;
    private int streakToHard;

    public AdaptiveQuiz(int streakToEasy, int streakToHard) {
        super();
        this.streakToEasy = streakToEasy;
        this.streakToHard = streakToHard;
    }
    @Override
    public String getDifficulty(Boolean... correctAnswer) {
        if (correctAnswer.length > 0) {
            if (correctAnswer[0]) {
                goodAnswerStreak++;
                badAnswerStreak = 0;
                if (goodAnswerStreak == streakToEasy) {
                    difficulty = Constants.hardDifficultyLevel;
                }
            }
            else if (!correctAnswer[0]) {
                badAnswerStreak++;
                goodAnswerStreak = 0;
                if (badAnswerStreak == streakToHard) {
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
