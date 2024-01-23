package Controllers;
public class QuizConfiguration {
    private static QuizConfiguration configuration = null;
    private int easyDiffultyQuestions = 4;
    private int quizLength = 4;
    private int streakToDifficultyChange = 2;

    public static QuizConfiguration getInstance() {
        if (configuration == null) configuration = new QuizConfiguration();
        return configuration;
    }

    public int getEasyDiffultyQuestions() {
        return easyDiffultyQuestions;
    }

    public int getQuizLength() {
        return quizLength;
    }

    public int getStreakToDifficultyChange() {
        return streakToDifficultyChange;
    }
}
