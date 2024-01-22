package Models;

public abstract class Question {
    private int id;
    private String questionToAnswer;
    private Word correctAnswer;
    private String language;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionToAnswer() {
        return questionToAnswer;
    }

    public void setQuestionToAnswer(String questionToAnswer) {
        this.questionToAnswer = questionToAnswer;
    }

    public Word getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Word correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
