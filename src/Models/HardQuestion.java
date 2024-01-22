package Models;

import java.util.List;

public class HardQuestion extends Question {

    public HardQuestion(int correctAnswerId, String questionToAnswer, String language) {
        super(correctAnswerId, questionToAnswer, language);
    }

    @Override
    public List<Word> getAnswers() {
        throw new UnsupportedOperationException("Unimplemented method 'getAnswers'");
    }

    @Override
    public void setAnswers(List<Word> answers) {
        throw new UnsupportedOperationException("Unimplemented method 'setAnswers'");
    }
    
}
