package Controllers;
public class QuizConfiguration {
    private static QuizConfiguration configuration = null;
    private final int easyDiffultyQuestions = 3;
    private final int quizLength = 20;
    private final int toEasyInLeariningMode = 5;
    private final int toHardInLeariningMode = 5;
    private final int toEasyInTestMode = 5;
    private final int toHardInTestMode = 5;

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

    public int getToEasyInLeariningMode() {
        return toEasyInLeariningMode;
    }

    public int getToEasyInTestMode() {
        return toEasyInTestMode;
    }

    public int getToHardInLeariningMode() {
        return toHardInLeariningMode;
    }

    public int getToHardInTestMode() {
        return toHardInTestMode;
    }
}
