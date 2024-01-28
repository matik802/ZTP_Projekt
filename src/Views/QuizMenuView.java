package Views;

import Controllers.*;
import Utils.Constants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Klasa reprezentująca widok menu quizu
public class QuizMenuView implements ActionListener {
    // Komponenty interfejsu użytkownika
    private final JButton learnButton, testButton, easyButton, hardButton, adaptiveButton, polishButton, englishButton, startButton, playButton, exitButton, addWordsButton, backButton;
    private static IQuizDifficultyManager quizDifficultyManager;
    private static QuizConfiguration quizConfiguration;
    private static QuizState quizState;
    private static String startingDifficulty;
    private static String startingLanguage;
    private QuizView quizView;
    private final JLabel quizMenu;
    private final JLabel mainPanel;
    private QuizController quizController;

    // Konstruktor widoku menu quizu
    public QuizMenuView(QuizController quizController, JLabel mainPanel, JButton startButton, JButton addWordsButton, JButton exitButton) {
        // Inicjalizacja komponentów
        this.mainPanel = mainPanel;
        this.startButton = startButton;
        this.addWordsButton = addWordsButton;
        this.exitButton = exitButton;

        this.quizController = quizController;

        quizMenu = new JLabel();
        quizMenu.setBounds(0, 0, 450, 540);
        quizMenu.setOpaque(false);
        quizMenu.setLayout(null);

        // Ustawienia pozycji i rozmiarów menu
        int menuWidth = 400;
        int menuHeight = 500;
        int centerX = (mainPanel.getWidth() - menuWidth) / 2;
        int centerY = (mainPanel.getHeight() - menuHeight) / 2;
        quizMenu.setBounds(centerX - 15, centerY + 40, menuWidth, menuHeight);

        // Inicjalizacja panelu wyboru języka
        JPanel languagePanel = new JPanel();
        languagePanel.setLayout(new GridLayout(1, 2));
        JLabel languageText = new JLabel("Choose language");
        languageText.setForeground(Color.WHITE);
        languageText.setHorizontalAlignment(SwingConstants.CENTER);
        languageText.setBounds(50, 30, 300, 20);
        polishButton = createButton("Polish", languagePanel);
        englishButton = createButton("English", languagePanel);
        languagePanel.setBounds(50, 50, 300, 50);

        // Inicjalizacja panelu wyboru rodzaju quizu
        JPanel quizTypePanel = new JPanel();
        quizTypePanel.setLayout(new GridLayout(1, 2));
        JLabel quizTypeText = new JLabel("Choose quiz type");
        quizTypeText.setForeground(Color.WHITE);
        quizTypeText.setHorizontalAlignment(SwingConstants.CENTER);
        quizTypeText.setBounds(50, 130, 300, 20);
        learnButton = createButton("Learn", quizTypePanel);
        testButton = createButton("Test", quizTypePanel);
        quizTypePanel.setBounds(50, 150, 300, 50);

        // Inicjalizacja panelu wyboru trudności quizu
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

        // Inicjalizacja panelu rozpoczęcia quizu
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new GridLayout(1, 1));
        playButton = createButton("Start", startPanel);
        startPanel.setBounds(50, 350, 300, 50);

        // Inicjalizacja panelu przycisku powrotu
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new GridLayout(1, 1));
        backButton = createButton("Go back", backButtonPanel);
        backButtonPanel.setBounds(50, 450, 300, 50);

        // Ustawienia domyślne
        setDefaults();

        // Dodanie komponentów do panelu menu
        mainPanel.removeAll();
        mainPanel.setIcon(new ImageIcon("src/quizMenuBackground.jpg"));
        mainPanel.add(quizMenu);
        mainPanel.repaint();
        mainPanel.revalidate();

        quizMenu.add(languageText);
        quizMenu.add(languagePanel);
        quizMenu.add(quizTypeText);
        quizMenu.add(quizTypePanel);
        quizMenu.add(difficultyText);
        quizMenu.add(difficultyPanel);
        quizMenu.add(startPanel);
        quizMenu.add(backButtonPanel);
        mainPanel.add(quizMenu);
    }

    // Metoda ustawiająca quiz z wybranymi opcjami
    public void setQuiz(QuizController quizController, QuizState quizState, String language, QuizConfiguration quizConfiguration, IQuizDifficultyManager quizDifficultyManager, QuizView quizView) {
        quizController.setQuizState(quizState);
        quizController.setQuestionsLanguage(language);
        quizController.setQuizConfiguration(quizConfiguration);
        quizController.setQuizDifficultyManager(quizDifficultyManager);
        quizController.setQuizView(quizView);
        quizController.fetchData();
        quizController.startQuiz();
        setDefaults();
    }

    // Metoda ustawiająca wartości domyślne
    void setDefaults() {
        startingDifficulty = Constants.easyDifficultyLevel;
        startingLanguage = Constants.languagePl;
        quizDifficultyManager = new StaticQuiz();
        quizDifficultyManager.setDifficulty(startingDifficulty);
        quizConfiguration = QuizConfiguration.getInstance();
        quizState = new LearningQuizState();
        setDefaultButtons();
    }

    // Metoda ustawiająca domyślne stany przycisków
    void setDefaultButtons() {
        polishButton.setEnabled(false);
        englishButton.setEnabled(true);
        learnButton.setEnabled(false);
        testButton.setEnabled(true);
        easyButton.setEnabled(false);
        hardButton.setEnabled(true);
        adaptiveButton.setEnabled(true);
    }

    // Metoda tworząca przycisk z określonym stylem
    private JButton createButton(String label, JPanel panel) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        button.setContentAreaFilled(false);
        panel.setOpaque(false);
        button.setBorderPainted(true);
        button.setFocusPainted(false);

        // Ustawienie koloru przycisku w zależności od etykiety
        if (label.equals("Start")) {
            button.setForeground(new Color(142, 253, 148));
            button.setBorder(new LineBorder(new Color(142, 253, 148), 2));
        } else if (label.equals("Go back")) {
            button.setForeground(new Color(253, 240, 144));
            button.setBorder(new LineBorder(new Color(253, 240, 144), 2));
        } else {
            button.setForeground(new Color(165, 248, 254));
            button.setBorder(new LineBorder(new Color(165, 248, 254), 2));
        }

        panel.add(button);
        return button;
    }

    // Obsługa zdarzeń przycisków
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();

        // Obsługa wyboru języka
        if (sourceButton == polishButton || sourceButton == englishButton) {
            polishButton.setEnabled(sourceButton != polishButton);
            englishButton.setEnabled(sourceButton != englishButton);
        }
        // Obsługa wyboru rodzaju quizu
        else if (sourceButton == learnButton || sourceButton == testButton) {
            learnButton.setEnabled(sourceButton != learnButton);
            testButton.setEnabled(sourceButton != testButton);
        }
        // Obsługa wyboru trudności quizu
        else if (sourceButton == easyButton || sourceButton == hardButton || sourceButton == adaptiveButton) {
            easyButton.setEnabled(sourceButton != easyButton);
            hardButton.setEnabled(sourceButton != hardButton);
            adaptiveButton.setEnabled(sourceButton != adaptiveButton);
            if (sourceButton == adaptiveButton) {
                showAdaptiveOptionsDialog();
            }
        }
        // Obsługa rozpoczęcia quizu
        else if (sourceButton == playButton) {
            quizView = new QuizView();
            setQuiz(quizController, quizState, startingLanguage, quizConfiguration, quizDifficultyManager, quizView);
        }
        // Obsługa powrotu do głównego menu
        else {
            mainPanel.removeAll();
            mainPanel.setIcon(new ImageIcon("src/menuBackground.jpg"));
            mainPanel.add(startButton);
            mainPanel.add(addWordsButton);
            mainPanel.add(exitButton);
            mainPanel.repaint();
            mainPanel.revalidate();
        }

        // Ustawienia wartości w zależności od naciśniętego przycisku
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

    // Metoda wyświetlająca okno dialogowe z opcjami adaptacyjnymi
    private void showAdaptiveOptionsDialog() {
        String[] options = {"Easy -> Hard", "Hard -> Easy"};
        int choice = JOptionPane.showOptionDialog(null, "Choose Adaptive Options", "Adaptive Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        // Ustawienia wartości w zależności od wybranej opcji
        if (choice == 0) {
            startingDifficulty = Constants.easyDifficultyLevel;
        } else if (choice == 1) {
            startingDifficulty = Constants.hardDifficultyLevel;
        }
    }
}
