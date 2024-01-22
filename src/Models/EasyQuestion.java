package Models;

import java.util.List;

public class EasyQuestion extends Question {
    private List<Word> answers;

    public EasyQuestion(int correctAnswerId, String questionToAnswer, String language, List<Word> answers) {
        super(correctAnswerId, questionToAnswer, language);
        this.answers = answers;
    }
    
    @Override
    public List<Word> getAnswers() {
        return answers;
    }

    @Override
    public void setAnswers(List<Word> answers) {
        this.answers = answers;
    }
}
