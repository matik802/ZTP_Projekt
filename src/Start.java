import Controllers.*;
import Utils.Constants;
import Views.QuizView;
import Views.AddWordsDialog;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame implements ActionListener {

	private static JButton learnButton, testButton, easyButton, hardButton, adaptiveButton, polishButton, englishButton, startButton, playButton, exitButton, addWordsButton, backButton;
	private static IQuizDifficultyManager quizDifficultyManager;
	private static QuizConfiguration quizConfiguration;
	private static QuizState quizState;
	private static String startingDifficulty;
	private static String startingLanguage;
	private QuizView quizView;
	private JLabel quizMenu;
	private JLabel mainPanel;

	public static void setQuiz(QuizState quizState, String language, QuizConfiguration quizConfiguration, IQuizDifficultyManager quizDifficultyManager, QuizView quizView) {
		QuizController quizController = new QuizController();
		quizController.setQuizState(quizState);
		quizController.setQuestionsLanguage(language);
		quizController.setQuizConfiguration(quizConfiguration);
		quizController.setQuizDifficultyManager(quizDifficultyManager);
		quizController.setQuizView(quizView);
		quizController.fetchData();
		quizController.startQuiz();
		setDefaults();
	}

	public Start() {
		setTitle("Quiz App");
		setSize(1300, 830);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		mainPanel = new JLabel(new ImageIcon("src/menuBackground.jpg"));
		mainPanel.setLayout(null);

		startButton = new JButton("Start");
		startButton.setBounds(517,260,240,115);
		startButton.setBorderPainted(false);
		startButton.setFocusPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFont(new Font("Arial",Font.BOLD,30));
		startButton.setForeground(new Color(215,102,156));
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setQuizMenu();
				mainPanel.removeAll();
				mainPanel.setIcon(new ImageIcon("src/quizMenuBackground.jpg"));
				mainPanel.add(quizMenu);
				repaint();
				revalidate();
			}
		});
		mainPanel.add(startButton);

		addWordsButton = new JButton("Edit words");
		addWordsButton.setBounds(517,442,240,115);
		addWordsButton.setBorderPainted(false);
		addWordsButton.setFocusPainted(false);
		addWordsButton.setContentAreaFilled(false);
		addWordsButton.setFont(new Font("Arial",Font.BOLD,30));
		addWordsButton.setForeground(new Color(85,182,214));
		addWordsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddWordsDialog addWordsDialog = new AddWordsDialog(Start.this);
				addWordsDialog.setVisible(true);
			}
		});
		mainPanel.add(addWordsButton);

		exitButton = new JButton("Exit");
		exitButton.setBounds(517,625,240,115);
		exitButton.setBorderPainted(false);
		exitButton.setFocusPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFont(new Font("Arial",Font.BOLD,30));
		exitButton.setForeground(new Color(181,69,195));
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mainPanel.add(exitButton);

		setVisible(true);
		add(mainPanel,BorderLayout.NORTH);
	}

	void setQuizMenu() {
		quizMenu = new JLabel();
		quizMenu.setBounds(0, 0, 450, 540);
		quizMenu.setOpaque(false);
		quizMenu.setLayout(null);


		int menuWidth = 400;
		int menuHeight = 500;
		int centerX = (getWidth() - menuWidth) / 2;
		int centerY = (getHeight() - menuHeight) / 2;

		quizMenu.setBounds(centerX-15, centerY+40, menuWidth, menuHeight);

		JPanel languagePanel = new JPanel();
		languagePanel.setLayout(new GridLayout(1, 2));
		JLabel languageText = new JLabel("Choose language");
		languageText.setForeground(Color.WHITE);
		languageText.setHorizontalAlignment(SwingConstants.CENTER);
		languageText.setBounds(50, 30, 300, 20);
		polishButton = createButton("Polish", languagePanel);
		englishButton = createButton("English", languagePanel);
		languagePanel.setBounds(50, 50, 300, 50);


		JPanel quizTypePanel = new JPanel();
		quizTypePanel.setLayout(new GridLayout(1, 2));
		JLabel quizTypeText = new JLabel("Choose quiz type");
		quizTypeText.setForeground(Color.WHITE);
		quizTypeText.setHorizontalAlignment(SwingConstants.CENTER);
		quizTypeText.setBounds(50, 130, 300, 20);
		learnButton = createButton("Learn", quizTypePanel);
		testButton = createButton("Test", quizTypePanel);
		quizTypePanel.setBounds(50, 150, 300, 50);

		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new GridLayout(1, 3));
		JLabel difficultyText = new JLabel("Choose quiz difficulty");
		difficultyText.setForeground(Color.WHITE);
		difficultyText.setHorizontalAlignment(SwingConstants.CENTER);
		difficultyText.setBounds(50, 230, 300, 20);
		easyButton = createButton("Easy", difficultyPanel);
		hardButton = createButton("Hard", difficultyPanel);
		adaptiveButton = createButton("Adaptive", difficultyPanel);
		difficultyPanel.setBounds(50, 250, 300, 50);

		JPanel startPanel = new JPanel();
		startPanel.setLayout(new GridLayout(1, 1));
		playButton = createButton("Start", startPanel);
		startPanel.setBounds(50, 350, 300, 50);


		JPanel backButtonPanel = new JPanel();
		backButtonPanel.setLayout(new GridLayout(1, 1));
		backButton = createButton("Go back", backButtonPanel);
		backButtonPanel.setBounds(50, 450, 300, 50);
		

		setDefaults();

		quizMenu.add(languageText);
		quizMenu.add(languagePanel);
		quizMenu.add(quizTypeText);
		quizMenu.add(quizTypePanel);
		quizMenu.add(difficultyText);
		quizMenu.add(difficultyPanel);
		quizMenu.add(startPanel);
		quizMenu.add(backButtonPanel);
	}
	static void setDefaults() {
		startingDifficulty = Constants.easyDifficultyLevel;
		startingLanguage = Constants.languagePl;
		quizDifficultyManager = new StaticQuiz();
		quizDifficultyManager.setDifficulty(startingDifficulty);
		quizConfiguration = QuizConfiguration.getInstance();
		quizState = new LearningQuizState();
		setdDefaultButtons();
	}

	static void setdDefaultButtons(){
		polishButton.setEnabled(false);
		englishButton.setEnabled(true);
		learnButton.setEnabled(false);
		testButton.setEnabled(true);
		easyButton.setEnabled(false);
		hardButton.setEnabled(true);
		adaptiveButton.setEnabled(true);
	}

	private JButton createButton(String label, JPanel panel) {
		JButton button = new JButton(label);
		button.addActionListener(this);
		button.setContentAreaFilled(false);
		panel.setOpaque(false);
		button.setBorderPainted(true);
		button.setFocusPainted(false);

		if(label.equals("Start")){
			button.setForeground(new Color(142,253,148));
			button.setBorder(new LineBorder(new Color(142,253,148), 2));
		}else if(label.equals("Go back")){
			button.setForeground(new Color(253,240,144));
			button.setBorder(new LineBorder(new Color(253,240,144), 2));
		} else {
			button.setForeground(new Color(165,248,254));
			button.setBorder(new LineBorder(new Color(165,248,254), 2));
		}

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
		} else if (sourceButton == playButton) {
			quizView = new QuizView();
			setQuiz(quizState, startingLanguage, quizConfiguration, quizDifficultyManager, quizView);
		} else {
			mainPanel.removeAll();
			mainPanel.setIcon(new ImageIcon("src/menuBackground.jpg"));
			mainPanel.add(startButton);
			mainPanel.add(addWordsButton);
			mainPanel.add(exitButton);
			repaint();
			revalidate();
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