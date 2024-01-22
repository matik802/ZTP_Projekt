package Models;

import java.util.List;

public abstract class Question {
    private int id;
    private int correctAnswerId;
    private String questionToAnswer;
    private Word correctAnswer;
    private String language;

    public Question(int correctAnswerId, String questionToAnswer, String language) {
        id = 1;
        this.correctAnswerId = correctAnswerId;
        this.questionToAnswer = questionToAnswer;
        //this.correctAnswer = correctAnswer;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(int correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
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

    public abstract List<Word> getAnswers();

    public abstract void setAnswers(List<Word> answers);
}
