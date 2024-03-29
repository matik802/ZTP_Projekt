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
	private final JLabel backgroundLabel = new JLabel();
	private List<Word> answers;
	private String selectAnswer;

	public QuizView() {
		setSize(563, 450);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		setVisible(true);
	}

	// Metoda do czyszczenia zawartości panelu tła
	public void clear() {
		if (backgroundLabel != null) {
			backgroundLabel.removeAll();
			backgroundLabel.revalidate();
			backgroundLabel.repaint();
		}
	}

	// Metoda do tworzenia interfejsu dla trudnego pytania
	public void buildHardQuestionUI() {
		clear();

		backgroundLabel.setIcon(new ImageIcon("src/hardQuestionBackground.jpg"));
		backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
		backgroundLabel.setLayout(null);

		// Ustawienia etykiety pytania
		questionLabel = new JLabel("No questions");
		questionLabel.setBounds(64, 113, 451, 63);
		questionLabel.setForeground(Color.WHITE);
		questionLabel.setFont(new Font("Arial", Font.BOLD, 30));
		questionLabel.setHorizontalAlignment(JLabel.CENTER);
		backgroundLabel.add(questionLabel);

		// Ustawienia pola tekstowego odpowiedzi użytkownika
		userAnswerTextField = new JTextField("Write your answer:");
		userAnswerTextField.setBounds(83, 205, 414, 45);
		userAnswerTextField.setHorizontalAlignment(JTextField.CENTER);
		userAnswerTextField.setForeground(Color.gray);
		userAnswerTextField.setFont(new Font("Arial", Font.BOLD, 20));
		userAnswerTextField.setBorder(null);
		userAnswerTextField.setVisible(true);
		userAnswerTextField.setOpaque(false);
		userAnswerTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userAnswerTextField.setForeground(Color.WHITE);
				userAnswerTextField.setText("");
			}
		});
		backgroundLabel.add(userAnswerTextField);

		// Ustawienia przycisku "Next question"
		nextButton = new JButton("Next question");
		nextButton.setBounds(197, 270, 187, 44);
		nextButton.setContentAreaFilled(false);
		nextButton.setFocusPainted(false);
		nextButton.setBorderPainted(false);
		nextButton.setForeground(Color.WHITE);
		backgroundLabel.add(nextButton);

		add(backgroundLabel);
		setLayout(null);
	}

	// Metoda do tworzenia interfejsu dla łatwego pytania
	public void buildEasyQuestionUI() {
		clear();

		backgroundLabel.setIcon(new ImageIcon("src/easyQuestionBackground.jpg"));
		backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
		backgroundLabel.setLayout(null);

		// Ustawienia etykiety pytania
		questionLabel = new JLabel("No questions");
		questionLabel.setBounds(64, 113, 451, 63);
		questionLabel.setForeground(Color.WHITE);
		questionLabel.setFont(new Font("Arial", Font.BOLD, 30));
		questionLabel.setHorizontalAlignment(JLabel.CENTER);
		backgroundLabel.add(questionLabel);

		// Ustawienia przycisku "Next question"
		nextButton = new JButton("Next question");
		nextButton.setBounds(194, 339, 187, 44);
		nextButton.setContentAreaFilled(false);
		nextButton.setFocusPainted(false);
		nextButton.setBorderPainted(false);
		nextButton.setForeground(Color.WHITE);
		backgroundLabel.add(nextButton);

		// Ustawienia panelu z przyciskami odpowiedzi
		selectAnswerPanel = new JPanel();
		selectAnswerPanel.setLayout(new GridLayout(2, 2, 40, 21));
		selectAnswerPanel.setBounds(83, 205, 414, 109);
		selectAnswerPanel.setOpaque(false);

		setAnswersButtons(answers);

		backgroundLabel.add(selectAnswerPanel);

		add(backgroundLabel);
		setLayout(null);
	}

	// Metoda ustawiająca odpowiedzi na pytania
	public void setAnswers(List<Word> answers) {
		this.answers = answers;
		selectAnswerPanel.removeAll();
		setAnswersButtons(answers);
		selectAnswerPanel.repaint();
		selectAnswerPanel.revalidate();
	}

	// Metoda tworząca przyciski z odpowiedziami
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

					// Wyłączenie innych przycisków po wybraniu odpowiedzi
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

	// Metoda zwracająca wybraną odpowiedź
	public String getSelectAnswer() {
		return selectAnswer;
	}

	// Metoda ustawiająca treść pytania
	public void setQuestion(String question) {
		questionLabel.setText(question);
		backgroundLabel.add(questionLabel);
	}

	// Metoda zwracająca pole tekstowe odpowiedzi użytkownika
	public JTextField getUserAnswerTextField() {
		return userAnswerTextField;
	}

	// Metoda ustawiająca akcję przycisku "Next question"
	public void setNextButtonAction(QuizState quizState) {
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quizState.getNextQuestion();
			}
		});
		backgroundLabel.add(nextButton);
	}
}
