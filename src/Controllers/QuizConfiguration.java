package Controllers;
public class QuizConfiguration {
    private static QuizConfiguration configuration = null;
    private int easyDiffultyQuestions = 3;
    private int quizLength = 20;
    private int toEasyInLeariningMode = 5;
    private int toHardInLeariningMode = 5;
    private int toEasyInTestMode = 5;
    private int toHardInTestMode = 5;

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
