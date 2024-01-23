package Views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controllers.QuizState;
import Models.Word;

public class QuizView extends JFrame {
    private JLabel questionLabel = new JLabel();
	private JTextField userAnswerTextField = new JTextField();
	private JButton nextButton = new JButton();

	private JList<String> selectAnswerList = new JList<>();
	private JPanel selectAnswerPanel = new JPanel();

	public QuizView() {
		
	}

	public void clear() {
		this.remove(questionLabel);
		this.remove(userAnswerTextField);
		this.remove(nextButton);
		selectAnswerPanel.remove(selectAnswerList);
		this.remove(selectAnswerPanel);
		this.revalidate();
		this.repaint();
	}

	public void buildHardQuestionUI() {
		questionLabel = new JLabel("No questions");  
		questionLabel.setBounds(10,10,400,30);
        this.add(questionLabel);

		userAnswerTextField = new JTextField("Answer...");
        userAnswerTextField.setBounds(10,40,200,30);
        this.add(userAnswerTextField);
		                                
        nextButton = new JButton("Next question");
        nextButton.setBounds(10,100,150,30);
		this.add(nextButton);
		selectAnswerPanel.setVisible(false);
		selectAnswerList.setVisible(false);
		userAnswerTextField.setVisible(true);
	}

	public void buildEasyQuestionUI() {
		questionLabel = new JLabel("No questions");  
		questionLabel.setBounds(10,10,400,30);
        this.add(questionLabel);
		                                
        nextButton = new JButton("Next question");
        nextButton.setBounds(10,140,150,30);
		this.add(nextButton);

		selectAnswerList = new JList<>();
		selectAnswerList.setModel(new AbstractListModel() {

            String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4"};

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public Object getElementAt(int i) {
                return strings[i];
            }
        });

		selectAnswerPanel = new JPanel();
        selectAnswerPanel.add(selectAnswerList);
		selectAnswerPanel.setBounds(10,40,200,300);
		this.add(selectAnswerPanel);
		selectAnswerPanel.setVisible(true);
		selectAnswerList.setVisible(true);
		userAnswerTextField.setVisible(true);
	}

	public void setAnswers(List<Word> answers) {
		List<String> tempAnswers = new ArrayList<>();
		for (Word w : answers) {
			tempAnswers.add(w.getName());
		}
		selectAnswerList.setModel(new AbstractListModel() {

            List<String> strings = tempAnswers;

            @Override
            public int getSize() {
                return strings.size();
            }

            @Override
            public Object getElementAt(int i) {
                return strings.get(i);
            }
        });
	}

	public JList getSelectAnswerList() {
		return selectAnswerList;
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
