package Views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controllers.QuizState;

public class QuizView extends JFrame {
    private JLabel questionLabel;
	private JTextField userAnswerTextField;
	private JButton nextButton;

	public QuizView() {
		questionLabel = new JLabel("No questions");  
		questionLabel.setBounds(10,10,400,30);
        this.add(questionLabel);

		userAnswerTextField = new JTextField("Answer...");
        userAnswerTextField.setBounds(10,40,200,30);
        this.add(userAnswerTextField);
		                                
        nextButton = new JButton("Next question");
        nextButton.setBounds(10,100,150,30);
		this.add(nextButton);
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

	public void setNextButtonAction(QuizState quizState) {
		nextButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				quizState.getNextQuestion();
			}  
		});  
		this.add(nextButton);
	}
}
