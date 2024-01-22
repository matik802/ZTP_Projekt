package Views;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class QuizView extends JFrame {
    private JLabel questionLabel;
	private JTextField userAnswerTextField;
	private JButton nextButton;

	public QuizView() {
		questionLabel = new JLabel("Question");  
		questionLabel.setBounds(10,10,400,30);
        this.setQuestionLabel(questionLabel);

		userAnswerTextField = new JTextField("Answer...");
        userAnswerTextField.setBounds(10,40,200,30);
        this.setUserAnswerTextField(userAnswerTextField);
		                                
        nextButton = new JButton("Next question");
        nextButton.setBounds(10,100,150,30);
		this.setNextButton(nextButton);
	}

	public void setQuestion(String question) {
		questionLabel.setText(question);
		this.add(questionLabel);
	}

	public void showQuizOverScreen(int points) {
		if (points >= 0) {
			this.setQuestion("You scored " + points + " points!");
			this.getNextButton().setVisible(false);
			this.getUserAnswerTextField().setVisible(false);
		}
		else {
			this.setQuestion("You have finished the test!");
			this.getNextButton().setVisible(false);
			this.getUserAnswerTextField().setVisible(false);
		}
	}
	
	public JLabel getQuestionLabel() {
		return questionLabel;
	}

	public void setQuestionLabel(JLabel questionLabel) {
		this.questionLabel = questionLabel;
		this.add(questionLabel);
	}

	public JTextField getUserAnswerTextField() {
		return userAnswerTextField;
	}

	public void setUserAnswerTextField(JTextField userAnswerTextField) {
		this.userAnswerTextField = userAnswerTextField;
		this.add(userAnswerTextField);
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public void setNextButton(JButton nextButton) {
		this.nextButton = nextButton;
		this.add(nextButton);
	}
}
