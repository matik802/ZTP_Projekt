import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controllers.LearningQuizState;
import Controllers.QuizConfiguration;
import Controllers.QuizController;
import Controllers.StaticQuiz;
import Controllers.TestQuizState;
import Utils.Constants;
import Views.QuizView;

public class Start {

	public static void main(String[] args) {
		QuizView quizView = new QuizView();
		quizView.setLayout(null);
		quizView.setSize(500,500);
		quizView.setVisible(true);

		//z pl na ang, tryb testu, stały poziom trudności, wysoki poziom trudności 
		// QuizController quizController = new QuizController();
		// quizController.setQuizState(new TestQuizState());
		// quizController.setQuestionsLanguage(Constants.languagePl);
		// quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		// StaticQuiz staticQuiz = new StaticQuiz();
		// staticQuiz.setDifficulty(Constants.hardDifficultyLevel);
		// quizController.setQuizDifficultyManager(staticQuiz);
		// quizController.setQuizView(quizView);

		//z pl na ang, tryb nauki, stały poziom trudności, wysoki poziom trudności 
		QuizController quizController = new QuizController();
		quizController.setQuizState(new LearningQuizState());
		quizController.setQuestionsLanguage(Constants.languagePl);
		quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		StaticQuiz staticQuiz = new StaticQuiz();
		staticQuiz.setDifficulty(Constants.hardDifficultyLevel);
		quizController.setQuizDifficultyManager(staticQuiz);
		quizController.setQuizView(quizView);
		
		quizController.fetchData();
		quizController.startQuiz();
	}
}