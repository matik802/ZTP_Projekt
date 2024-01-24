package Views;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import Controllers.QuizState;
import Models.Word;

public class QuizView extends JFrame {
	private JLabel questionLabel = new JLabel();
	private JTextField userAnswerTextField = new JTextField();
	private JButton nextButton = new JButton();

	private JList<String> selectAnswerList = new JList<>();
	private JPanel selectAnswerPanel = new JPanel();
	private JLabel backgroundLabel;

	public QuizView() {
		setSize(563, 383);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setVisible(true);
	}

	public void clear() {
		backgroundLabel.remove(questionLabel);
		backgroundLabel.remove(userAnswerTextField);
		backgroundLabel.remove(nextButton);
		selectAnswerPanel.remove(selectAnswerList);
		backgroundLabel.remove(selectAnswerPanel);
		backgroundLabel.revalidate();
		backgroundLabel.repaint();
	}

	public void buildHardQuestionUI() {

		questionLabel = new JLabel("No questions");
		questionLabel.setBounds(10, 10, 400, 30);
		backgroundLabel.add(questionLabel);

		userAnswerTextField = new JTextField("Answer...");
		userAnswerTextField.setBounds(10, 40, 200, 30);
		backgroundLabel.add(userAnswerTextField);

		nextButton = new JButton("Next question");
		nextButton.setBounds(10, 100, 150, 30);
		backgroundLabel.add(nextButton);
		selectAnswerPanel.setVisible(false);
		selectAnswerList.setVisible(false);
		userAnswerTextField.setVisible(true);
	}

	public void buildEasyQuestionUI() {

		backgroundLabel = new JLabel(new ImageIcon("ZTP_Projekt/src/questionBackground.jpg"));
		backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
		backgroundLabel.setLayout(null);

		questionLabel = new JLabel("No questions");
		questionLabel.setBounds(10, 10, 400, 30);
		backgroundLabel.add(questionLabel);

		nextButton = new JButton("Next question");
		nextButton.setBounds(10, 140, 150, 30);
		backgroundLabel.add(nextButton);

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
		selectAnswerPanel.setBounds(10, 40, 200, 50);
		backgroundLabel.add(selectAnswerPanel);

		selectAnswerPanel.setVisible(true);
		selectAnswerList.setVisible(true);
		userAnswerTextField.setVisible(true);

		add(backgroundLabel);
		setLayout(null);
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
		backgroundLabel.add(questionLabel);
	}

	public void showQuizOverScreen(int points) {
		if (points >= 0) {
			setQuestion("You scored " + points + " points!");
			getNextButton().setVisible(false);
			getUserAnswerTextField().setVisible(false);
		} else {
			setQuestion("You have finished the test!");
			getNextButton().setVisible(false);
			getUserAnswerTextField().setVisible(false);
		}
	}

	public JLabel getQuestionLabel() {
		return questionLabel;
	}

	public void setQuestionLabel(JLabel questionLabel) {
		this.questionLabel = questionLabel;
		backgroundLabel.add(questionLabel);
	}

	public JTextField getUserAnswerTextField() {
		return userAnswerTextField;
	}

	public void setUserAnswerTextField(JTextField userAnswerTextField) {
		this.userAnswerTextField = userAnswerTextField;
		backgroundLabel.add(userAnswerTextField);
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public void setNextButtonAction(QuizState quizState) {
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quizState.getNextQuestion();
			}
		});
		backgroundLabel.add(nextButton);
	}
}
