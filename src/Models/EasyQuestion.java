package Models;

import java.util.List;

public class EasyQuestion extends Question {
    private List<Word> answers;
    
    public List<Word> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Word> answers) {
        this.answers = answers;
    }
}
