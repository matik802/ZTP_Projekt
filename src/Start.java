import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controllers.AdaptiveQuiz;
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

		//1
		//z pl na ang, tryb testu, stały poziom trudności, wysoki poziom trudności 
		QuizController quizController = new QuizController();
		quizController.setQuizState(new TestQuizState());
		quizController.setQuestionsLanguage(Constants.languagePl);
		quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		StaticQuiz staticQuiz = new StaticQuiz();
		staticQuiz.setDifficulty(Constants.hardDifficultyLevel);
		quizController.setQuizDifficultyManager(staticQuiz);
		quizController.setQuizView(quizView);

		//2
		//z ang na pl, tryb testu, stały poziom trudności, wysoki poziom trudności BRAK PYTAŃ
		// QuizController quizController = new QuizController();
		// quizController.setQuizState(new TestQuizState());
		// quizController.setQuestionsLanguage(Constants.languageEng);
		// quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		// StaticQuiz staticQuiz = new StaticQuiz();
		// staticQuiz.setDifficulty(Constants.hardDifficultyLevel);
		// quizController.setQuizDifficultyManager(staticQuiz);
		// quizController.setQuizView(quizView);

		//3
		//z pl na ang, tryb nauki, stały poziom trudności, wysoki poziom trudności 
		// QuizController quizController = new QuizController();
		// quizController.setQuizState(new LearningQuizState());
		// quizController.setQuestionsLanguage(Constants.languagePl);
		// quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		// StaticQuiz staticQuiz = new StaticQuiz();
		// staticQuiz.setDifficulty(Constants.hardDifficultyLevel);
		// quizController.setQuizDifficultyManager(staticQuiz);
		// quizController.setQuizView(quizView);

		//4
		//z ang na pl, tryb nauki, stały poziom trudności, wysoki poziom trudności BRAK PYTAŃ
		// QuizController quizController = new QuizController();
		// quizController.setQuizState(new LearningQuizState());
		// quizController.setQuestionsLanguage(Constants.languageEng);
		// quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		// StaticQuiz staticQuiz = new StaticQuiz();
		// staticQuiz.setDifficulty(Constants.hardDifficultyLevel);
		// quizController.setQuizDifficultyManager(staticQuiz);
		// quizController.setQuizView(quizView);

		//5
		//z pl na ang, tryb nauki, stały poziom trudności, niski poziom trudności TODO
		// QuizController quizController = new QuizController();
		// quizController.setQuizState(new LearningQuizState());
		// quizController.setQuestionsLanguage(Constants.languagePl);
		// quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		// StaticQuiz staticQuiz = new StaticQuiz();
		// staticQuiz.setDifficulty(Constants.easyDifficultyLevel);
		// quizController.setQuizDifficultyManager(staticQuiz);
		// quizController.setQuizView(quizView);

		//6
		//z ang na pl, tryb nauki, stały poziom trudności, niski poziom trudności TODO
		// QuizController quizController = new QuizController();
		// quizController.setQuizState(new LearningQuizState());
		// quizController.setQuestionsLanguage(Constants.languageEng);
		// quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		// StaticQuiz staticQuiz = new StaticQuiz();
		// staticQuiz.setDifficulty(Constants.easyDifficultyLevel);
		// quizController.setQuizDifficultyManager(staticQuiz);
		// quizController.setQuizView(quizView);

		//7
		//z pl na ang, tryb testu, stały poziom trudności, niski poziom trudności TODO
		// QuizController quizController = new QuizController();
		// quizController.setQuizState(new TestQuizState());
		// quizController.setQuestionsLanguage(Constants.languagePl);
		// quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		// StaticQuiz staticQuiz = new StaticQuiz();
		// staticQuiz.setDifficulty(Constants.easyDifficultyLevel);
		// quizController.setQuizDifficultyManager(staticQuiz);
		// quizController.setQuizView(quizView);

		//8
		//z ang na pl, tryb nauki, stały poziom trudności, niski poziom trudności TODO
		// QuizController quizController = new QuizController();
		// quizController.setQuizState(new TestQuizState());
		// quizController.setQuestionsLanguage(Constants.languageEng);
		// quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		// StaticQuiz staticQuiz = new StaticQuiz();
		// staticQuiz.setDifficulty(Constants.easyDifficultyLevel);
		// quizController.setQuizDifficultyManager(staticQuiz);
		// quizController.setQuizView(quizView);

		//9
		//z pl na ang, tryb testu, adaptatywny poziom trudności (zaczyna się na łatwym) TODO
		// QuizController quizController = new QuizController();
		// quizController.setQuizState(new TestQuizState());
		// quizController.setQuestionsLanguage(Constants.languagePl);
		// quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		// AdaptiveQuiz staticQuiz = new AdaptiveQuiz();
		// staticQuiz.setDifficulty(Constants.easyDifficultyLevel);
		// quizController.setQuizDifficultyManager(staticQuiz);
		// quizController.setQuizView(quizView);

		//10
		//z ang na pl, tryb testu, adaptatywny poziom trudności (zaczyna się na łatwym) TODO
		// QuizController quizController = new QuizController();
		// quizController.setQuizState(new TestQuizState());
		// quizController.setQuestionsLanguage(Constants.languageEng);
		// quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		// AdaptiveQuiz staticQuiz = new AdaptiveQuiz();
		// staticQuiz.setDifficulty(Constants.easyDifficultyLevel);
		// quizController.setQuizDifficultyManager(staticQuiz);
		// quizController.setQuizView(quizView);

		//11
		//z pl na ang, tryb nauki, adaptatywny poziom trudności (zaczyna się na łatwym) TODO
		// QuizController quizController = new QuizController();
		// quizController.setQuizState(new LearningQuizState());
		// quizController.setQuestionsLanguage(Constants.languagePl);
		// quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		// AdaptiveQuiz staticQuiz = new AdaptiveQuiz();
		// staticQuiz.setDifficulty(Constants.easyDifficultyLevel);
		// quizController.setQuizDifficultyManager(staticQuiz);
		// quizController.setQuizView(quizView);

		//12
		//z ang na pl, tryb nauki, adaptatywny poziom trudności (zaczyna się na łatwym) TODO
		// QuizController quizController = new QuizController();
		// quizController.setQuizState(new LearningQuizState());
		// quizController.setQuestionsLanguage(Constants.languageEng);
		// quizController.setQuizConfiguration(QuizConfiguration.getInstance());
		// AdaptiveQuiz staticQuiz = new AdaptiveQuiz();
		// staticQuiz.setDifficulty(Constants.easyDifficultyLevel);
		// quizController.setQuizDifficultyManager(staticQuiz);
		// quizController.setQuizView(quizView);
		
		quizController.fetchData();
		quizController.startQuiz();
	}
}