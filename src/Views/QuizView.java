package Views;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import Controllers.QuizState;
import Models.Word;

public class QuizView extends JFrame {
	private JLabel questionLabel = new JLabel();
	private JTextField userAnswerTextField = new JTextField();
	private JButton nextButton = new JButton();

	private JPanel selectAnswerPanel = new JPanel();
	private JLabel backgroundLabel;
	private List<Word> answers;
	private String selectAnswer;

	public QuizView() {
		setSize(563, 450);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		setVisible(true);
	}

	public void clear() {
		if (backgroundLabel != null) {
			backgroundLabel.removeAll();
			backgroundLabel.revalidate();
			backgroundLabel.repaint();
		}
	}

	public void buildHardQuestionUI() {
		clear();

		backgroundLabel = new JLabel(new ImageIcon("src/hardQuestionBackground.jpg"));
		backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
		backgroundLabel.setLayout(null);

		questionLabel = new JLabel("No questions");
		questionLabel.setBounds(64, 113, 451, 63);
		questionLabel.setForeground(Color.WHITE);
		questionLabel.setFont(new Font("Arial",Font.BOLD,30));
		questionLabel.setHorizontalAlignment(JLabel.CENTER);
		backgroundLabel.add(questionLabel);

		userAnswerTextField = new JTextField("Write your answer:");
		userAnswerTextField.setBounds(83, 205, 414, 45);
		userAnswerTextField.setHorizontalAlignment(JTextField.CENTER);
		userAnswerTextField.setForeground(Color.gray);
		userAnswerTextField.setFont(new Font("Arial",Font.BOLD,20));
		userAnswerTextField.setBorder(null);
		userAnswerTextField.setOpaque(false);
		userAnswerTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userAnswerTextField.setForeground(Color.WHITE);
				userAnswerTextField.setText("");
			}
		});
		backgroundLabel.add(userAnswerTextField);

		nextButton = new JButton("Next question");
		nextButton.setBounds(197, 270, 187, 44);
		nextButton.setContentAreaFilled(false);
		nextButton.setFocusPainted(false);
		nextButton.setBorderPainted(false);
		nextButton.setForeground(Color.WHITE);
		backgroundLabel.add(nextButton);

		userAnswerTextField.setVisible(true);
		add(backgroundLabel);
		setLayout(null);
	}

	public void buildEasyQuestionUI() {
		clear();

		backgroundLabel = new JLabel(new ImageIcon("src/easyQuestionBackground.jpg"));
		backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
		backgroundLabel.setLayout(null);

		questionLabel = new JLabel("No questions");
		questionLabel.setBounds(64, 113, 451, 63);
		questionLabel.setForeground(Color.WHITE);
		questionLabel.setFont(new Font("Arial",Font.BOLD,30));
		questionLabel.setHorizontalAlignment(JLabel.CENTER);
		backgroundLabel.add(questionLabel);

		nextButton = new JButton("Next question");
		nextButton.setBounds(194, 339, 187, 44);
		nextButton.setContentAreaFilled(false);
		nextButton.setFocusPainted(false);
		nextButton.setBorderPainted(false);
		nextButton.setForeground(Color.WHITE);
		backgroundLabel.add(nextButton);

		selectAnswerPanel = new JPanel();
		selectAnswerPanel.setLayout(new GridLayout(2, 2, 40, 21));
		selectAnswerPanel.setBounds(83, 205, 414, 109);
		selectAnswerPanel.setOpaque(false);

		setAnswersButtons(answers);

		backgroundLabel.add(selectAnswerPanel);

		userAnswerTextField.setVisible(true);
		add(backgroundLabel);
		setLayout(null);
	}

	public void setAnswers(List<Word> answers) {
		this.answers=answers;
		selectAnswerPanel.removeAll();
		setAnswersButtons(answers);
		selectAnswerPanel.repaint();
		selectAnswerPanel.revalidate();
	}

	private void setAnswersButtons(List<Word> answers) {
		for (Word w : answers) {
			JButton answerButton = new JButton(w.getName());
			answerButton.setContentAreaFilled(false);
			answerButton.setFocusPainted(false);
			answerButton.setBorderPainted(false);
			answerButton.setForeground(Color.WHITE);

			answerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton temp = (JButton) e.getSource();
					selectAnswer = temp.getText();

					for (Component c : selectAnswerPanel.getComponents()) {
						if (c instanceof JButton) {
							JButton button = (JButton) c;
							button.setEnabled(true);
						}
					}

					temp.setEnabled(false);
				}
			});

			selectAnswerPanel.add(answerButton);
		}
	}
	public String getSelectAnswer() {
		return selectAnswer;
	}

	public void setQuestion(String question) {
		questionLabel.setText(question);
		backgroundLabel.add(questionLabel);
	}

	public void showQuizOverScreen(int points) {
		clear();
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
