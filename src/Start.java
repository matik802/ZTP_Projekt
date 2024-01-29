import Controllers.LearningQuizState;
import Controllers.QuizController;
import Controllers.QuizState;
import Controllers.TestQuizState;
import Utils.Constants;
import Views.QuizMenuView;
import Views.AddWordsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame {

	private static JButton startButton, exitButton, addWordsButton;
	private final JLabel mainPanel;
	private QuizController quizController;

	public Start() {
		setTitle("Quiz App");
		setSize(1300, 830);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		quizController = new QuizController();

		// Utworzenie głównego panelu z tłem
		mainPanel = new JLabel(new ImageIcon("src/menuBackground.jpg"));
		mainPanel.setLayout(null);

		// Przycisk uruchamiający quiz
		startButton = new JButton("Start");
		startButton.setBounds(517, 260, 240, 115);
		startButton.setBorderPainted(false);
		startButton.setFocusPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFont(new Font("Arial", Font.BOLD, 30));
		startButton.setForeground(new Color(215, 102, 156));
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new QuizMenuView(quizController, mainPanel, startButton, addWordsButton, exitButton);
			}
		});
		mainPanel.add(startButton);

		// Przycisk umożliwiający edycję słów
		addWordsButton = new JButton("Edit words");
		addWordsButton.setBounds(517, 442, 240, 115);
		addWordsButton.setBorderPainted(false);
		addWordsButton.setFocusPainted(false);
		addWordsButton.setContentAreaFilled(false);
		addWordsButton.setFont(new Font("Arial", Font.BOLD, 30));
		addWordsButton.setForeground(new Color(85, 182, 214));
		addWordsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TestQuizState tempQuizState = new TestQuizState();
				tempQuizState.setQuestionsLanguage(Constants.languageEng);
				quizController.setQuizState(tempQuizState);
				AddWordsView addWordsDialog = new AddWordsView(Start.this, quizController);
				addWordsDialog.setVisible(true);
			}
		});
		mainPanel.add(addWordsButton);

		// Przycisk zamykający aplikację
		exitButton = new JButton("Exit");
		exitButton.setBounds(517, 625, 240, 115);
		exitButton.setBorderPainted(false);
		exitButton.setFocusPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFont(new Font("Arial", Font.BOLD, 30));
		exitButton.setForeground(new Color(181, 69, 195));
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mainPanel.add(exitButton);

		// Wyświetlenie głównego okna
		setVisible(true);
		add(mainPanel, BorderLayout.NORTH);
	}

	public static void main(String[] args) {
		// Uruchomienie aplikacji w wątku GUI
		SwingUtilities.invokeLater(() -> new Start());
	}
}
