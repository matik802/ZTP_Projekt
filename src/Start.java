import Controllers.AdaptiveQuiz;
import Controllers.IQuizDifficultyManager;
import Controllers.LearningQuizState;
import Controllers.QuizConfiguration;
import Controllers.QuizController;
import Controllers.QuizState;
import Controllers.StaticQuiz;
import Controllers.TestQuizState;
import Utils.Constants;
import Views.QuizView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame implements ActionListener {

	private static JButton learnButton, testButton, easyButton, hardButton, adaptiveButton, polishButton, englishButton, startButton;
	IQuizDifficultyManager quizDifficultyManager;
	QuizConfiguration quizConfiguration;
	QuizState quizState;
	String startingDifficulty, startingLanguage;
	QuizView quizView;

	public static void setQuiz(QuizState quizState, String language, QuizConfiguration quizConfiguration, IQuizDifficultyManager quizDifficultyManager, QuizView quizView) {
		QuizController quizController = new QuizController();
		quizController.setQuizState(quizState);
		quizController.setQuestionsLanguage(language);
		quizController.setQuizConfiguration(quizConfiguration);
		quizController.setQuizDifficultyManager(quizDifficultyManager);
		quizController.setQuizView(quizView);
		quizController.fetchData();
		quizController.startQuiz();
	}

	public Start() {

		setTitle("Quiz App");
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel quizMenu = new JPanel();
		quizMenu.setSize(500, 300);
		setDefaults();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(4, 2));

		JPanel languagePanel = new JPanel();
		languagePanel.setBorder(BorderFactory.createTitledBorder("Choose Language"));
		languagePanel.setLayout(new GridLayout(1, 2));
		polishButton = createButton("Polish", languagePanel);
		polishButton.setEnabled(false);
		englishButton = createButton("English", languagePanel);

		JPanel quizTypePanel = new JPanel();
		quizTypePanel.setBorder(BorderFactory.createTitledBorder("Choose Quiz Type"));
		quizTypePanel.setLayout(new GridLayout(1, 2));
		learnButton = createButton("Learn", quizTypePanel);
		learnButton.setEnabled(false);
		testButton = createButton("Test", quizTypePanel);

		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setBorder(BorderFactory.createTitledBorder("Choose Difficulty"));
		difficultyPanel.setLayout(new GridLayout(1, 3));
		easyButton = createButton("Easy", difficultyPanel);
		easyButton.setEnabled(false);
		hardButton = createButton("Hard", difficultyPanel);
		adaptiveButton = createButton("Adaptive", difficultyPanel);

		JPanel startPanel = new JPanel();
		startPanel.setBorder(BorderFactory.createTitledBorder("Start Quiz"));
		startButton = createButton("Start", startPanel);

		mainPanel.add(languagePanel);
		mainPanel.add(quizTypePanel);
		mainPanel.add(difficultyPanel);
		mainPanel.add(startPanel);

		quizMenu.add(mainPanel);

		setVisible(true);

		add(quizMenu);

	}

	void setDefaults() {
		startingDifficulty = Constants.easyDifficultyLevel;
		startingLanguage = Constants.languagePl;
		quizDifficultyManager = new StaticQuiz();
		quizDifficultyManager.setDifficulty(startingDifficulty);
		quizConfiguration = QuizConfiguration.getInstance();
		quizState = new LearningQuizState();
	}

	private JButton createButton(String label, JPanel panel) {
		JButton button = new JButton(label);
		button.addActionListener(this);
		panel.add(button);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton sourceButton = (JButton) e.getSource();

		if (sourceButton == polishButton || sourceButton == englishButton) {
			polishButton.setEnabled(sourceButton != polishButton);
			englishButton.setEnabled(sourceButton != englishButton);
		} else if (sourceButton == learnButton || sourceButton == testButton) {
			learnButton.setEnabled(sourceButton != learnButton);
			testButton.setEnabled(sourceButton != testButton);
		} else if (sourceButton == easyButton || sourceButton == hardButton || sourceButton == adaptiveButton) {
			easyButton.setEnabled(sourceButton != easyButton);
			hardButton.setEnabled(sourceButton != hardButton);
			adaptiveButton.setEnabled(sourceButton != adaptiveButton);
			if (sourceButton == adaptiveButton) {
				showAdaptiveOptionsDialog();
			}
		} else if (sourceButton == startButton) {
			quizView = new QuizView();
			setQuiz(quizState, startingLanguage, quizConfiguration, quizDifficultyManager, quizView);
		}

		switch (sourceButton.getText()) {
			case "Learn" -> {
				quizState = new LearningQuizState();
			}
			case "Test" -> {
				quizState = new TestQuizState();
			}
			case "Easy" -> {
				quizDifficultyManager = new StaticQuiz();
				quizDifficultyManager.setDifficulty(Constants.easyDifficultyLevel);
			}
			case "Hard" -> {
				quizDifficultyManager = new StaticQuiz();
				quizDifficultyManager.setDifficulty(Constants.hardDifficultyLevel);
			}
			case "Adaptive" -> {
				if (quizState.toString().equals("class Controllers.LearningQuizState")) {
					quizDifficultyManager = new AdaptiveQuiz(quizConfiguration.getToEasyInLeariningMode(), quizConfiguration.getToHardInLeariningMode());
				} else {
					quizDifficultyManager = new AdaptiveQuiz(quizConfiguration.getToEasyInTestMode(), quizConfiguration.getToHardInTestMode());
				}
				quizDifficultyManager.setDifficulty(startingDifficulty);
			}
			case "Polish" -> {
				startingLanguage = Constants.languagePl;
			}
			case "English" -> {
				startingLanguage = Constants.languageEng;
			}
		}
	}

	private void showAdaptiveOptionsDialog() {
		String[] options = {"Easy -> Hard", "Hard -> Easy"};
		int choice = JOptionPane.showOptionDialog(this, "Choose Adaptive Options", "Adaptive Options",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (choice == 0) {
			startingDifficulty = Constants.easyDifficultyLevel;
		} else if (choice == 1) {
			startingDifficulty = Constants.hardDifficultyLevel;
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Start());
	}
}
