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

public class Start {
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

	public static void main(String[] args) {
		QuizView quizView = new QuizView();
		quizView.setLayout(null);
		quizView.setSize(500,500);
		quizView.setVisible(true);

		//1
		//z pl na ang, tryb testu, stały poziom trudności, wysoki poziom trudności 
		// IQuizDifficultyManager quizDifficultyManager = new StaticQuiz();
		// quizDifficultyManager.setDifficulty(Constants.hardDifficultyLevel);
		// setQuiz(new TestQuizState(), Constants.languagePl, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//2
		//z ang na pl, tryb testu, stały poziom trudności, wysoki poziom trudności
		//IQuizDifficultyManager quizDifficultyManager = new StaticQuiz();
		// quizDifficultyManager.setDifficulty(Constants.hardDifficultyLevel);
		// setQuiz(new TestQuizState(), Constants.languageEng, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//3
		//z pl na ang, tryb nauki, stały poziom trudności, wysoki poziom trudności 
		//IQuizDifficultyManager quizDifficultyManager = new StaticQuiz();
		// quizDifficultyManager.setDifficulty(Constants.hardDifficultyLevel);
		// setQuiz(new LearningQuizState(), Constants.languagePl, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//4
		//z ang na pl, tryb nauki, stały poziom trudności, wysoki poziom trudności
		//IQuizDifficultyManager quizDifficultyManager = new StaticQuiz();
		// quizDifficultyManager.setDifficulty(Constants.hardDifficultyLevel);
		// setQuiz(new LearningQuizState(), Constants.languageEng, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//5
		//z pl na ang, tryb nauki, stały poziom trudności, niski poziom trudności
		// IQuizDifficultyManager quizDifficultyManager = new StaticQuiz();
		// quizDifficultyManager.setDifficulty(Constants.easyDifficultyLevel);
		// setQuiz(new LearningQuizState(), Constants.languagePl, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//6
		//z ang na pl, tryb nauki, stały poziom trudności, niski poziom trudności
		//IQuizDifficultyManager quizDifficultyManager = new StaticQuiz();
		// quizDifficultyManager.setDifficulty(Constants.easyDifficultyLevel);
		// setQuiz(new LearningQuizState(), Constants.languageEng, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//7
		//z pl na ang, tryb testu, stały poziom trudności, niski poziom trudności
		//IQuizDifficultyManager quizDifficultyManager = new StaticQuiz();
		// quizDifficultyManager.setDifficulty(Constants.easyDifficultyLevel);
		// setQuiz(new TestQuizState(), Constants.languagePl, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//8
		//z ang na pl, tryb nauki, stały poziom trudności, niski poziom trudności
		//IQuizDifficultyManager quizDifficultyManager = new StaticQuiz();
		// quizDifficultyManager.setDifficulty(Constants.easyDifficultyLevel);
		// setQuiz(new TestQuizState(), Constants.languageEng, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//9
		//z pl na ang, tryb testu, adaptatywny poziom trudności (zaczyna się na łatwym)
		//IQuizDifficultyManager quizDifficultyManager = new AdaptiveQuiz(QuizConfiguration.getInstance().getStreakToDifficultyChange());
		// quizDifficultyManager.setDifficulty(Constants.easyDifficultyLevel);
		// setQuiz(new TestQuizState(), Constants.languagePl, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//10
		//z ang na pl, tryb testu, adaptatywny poziom trudności (zaczyna się na łatwym)
		// IQuizDifficultyManager quizDifficultyManager = new AdaptiveQuiz(QuizConfiguration.getInstance().getStreakToDifficultyChange());
		// quizDifficultyManager.setDifficulty(Constants.easyDifficultyLevel);
		// setQuiz(new TestQuizState(), Constants.languageEng, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//11
		//z pl na ang, tryb testu, adaptatywny poziom trudności (zaczyna się na trudnym)
		//IQuizDifficultyManager quizDifficultyManager = new AdaptiveQuiz(QuizConfiguration.getInstance().getStreakToDifficultyChange());
		// quizDifficultyManager.setDifficulty(Constants.hardDifficultyLevel);
		// setQuiz(new TestQuizState(), Constants.languagePl, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//12
		//z ang na pl, tryb testu, adaptatywny poziom trudności (zaczyna się na trudnym)
		//IQuizDifficultyManager quizDifficultyManager = new AdaptiveQuiz(QuizConfiguration.getInstance().getStreakToDifficultyChange());
		// quizDifficultyManager.setDifficulty(Constants.hardDifficultyLevel);
		// setQuiz(new TestQuizState(), Constants.languageEng, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//13
		//z pl na ang, tryb nauki, adaptatywny poziom trudności (zaczyna się na łatwym)
		// IQuizDifficultyManager quizDifficultyManager = new AdaptiveQuiz(QuizConfiguration.getInstance().getStreakToDifficultyChange());
		// quizDifficultyManager.setDifficulty(Constants.easyDifficultyLevel);
		// setQuiz(new LearningQuizState(), Constants.languagePl, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//14
		//z ang na pl, tryb nauki, adaptatywny poziom trudności (zaczyna się na łatwym)
		// IQuizDifficultyManager quizDifficultyManager = new AdaptiveQuiz(QuizConfiguration.getInstance().getStreakToDifficultyChange());
		// quizDifficultyManager.setDifficulty(Constants.easyDifficultyLevel);
		// setQuiz(new LearningQuizState(), Constants.languageEng, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//15
		//z pl na ang, tryb nauki, adaptatywny poziom trudności (zaczyna się na trudnym)
		// IQuizDifficultyManager quizDifficultyManager = new AdaptiveQuiz(QuizConfiguration.getInstance().getStreakToDifficultyChange());
		// quizDifficultyManager.setDifficulty(Constants.hardDifficultyLevel);
		// setQuiz(new LearningQuizState(), Constants.languagePl, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);

		//16
		//z ang na pl, tryb nauki, adaptatywny poziom trudności (zaczyna się na trudnym)
		IQuizDifficultyManager quizDifficultyManager = new AdaptiveQuiz(QuizConfiguration.getInstance().getStreakToDifficultyChange());
		quizDifficultyManager.setDifficulty(Constants.hardDifficultyLevel);
		setQuiz(new LearningQuizState(), Constants.languageEng, QuizConfiguration.getInstance(), quizDifficultyManager, quizView);
	}
}