import Controllers.*;
import Utils.Constants;
import Views.QuizMenuView;
import Views.QuizView;
import Views.AddWordsView;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start extends JFrame{

	private static JButton learnButton, testButton, easyButton, hardButton, adaptiveButton, polishButton, englishButton, startButton, playButton, exitButton, addWordsButton, backButton;
	private static IQuizDifficultyManager quizDifficultyManager;
	private static QuizConfiguration quizConfiguration;
	private static QuizState quizState;
	private static String startingDifficulty;
	private static String startingLanguage;
	private QuizView quizView;
	private JLabel quizMenu;
	private final JLabel mainPanel;

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
				new QuizMenuView(mainPanel,startButton,addWordsButton,exitButton);
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
				AddWordsView addWordsDialog = new AddWordsView(Start.this);
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


	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Start());
	}
}