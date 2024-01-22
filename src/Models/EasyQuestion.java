package Models;

import java.util.List;

public class EasyQuestion extends Question {
    private List<Word> answers;
    
    @Override
    public List<Word> getAnswers() {
        return answers;
    }

    @Override
    public void setAnswers(List<Word> answers) {
        this.answers = answers;
    }
}
