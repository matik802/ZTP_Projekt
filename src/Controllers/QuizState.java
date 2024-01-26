package Controllers;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.Question;
import Models.Word;
import Utils.Constants;
import Views.QuizView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class QuizState {
    protected QuizController quizController;
    protected QuizView quizView;
    protected IQuizDifficultyManager quizDifficultyManager;
    protected QuizConfiguration quizConfiguration;
    protected Question currentQuestion;
    protected List<Question> questionsPool;
    protected List<Word> wordsPool;
    protected String questionsLanguage;
    protected int questionsCount = 0;



    public void fetchData() {

            wordsPool = new ArrayList<>();
            questionsPool = new ArrayList<>();

            JSONParser parser = new JSONParser();
            try (FileReader reader = new FileReader("src/words.json")) {
                JSONArray jsonArray = (JSONArray) parser.parse(reader);
                for (Object obj : jsonArray) {
                    JSONObject jsonObject = (JSONObject) obj;
                    Long id = (Long) jsonObject.get("id");
                    String word = (String) jsonObject.get("word");
                    String translation = (String) jsonObject.get("translation");

                    if (questionsLanguage.equals(Constants.languageEng)) {
                        Word tempWord = new Word(word,Constants.languageEng);
                        wordsPool.add(tempWord);
                        Question tempQuestion = new Question(translation, Constants.languagePl);
                        tempQuestion.setCorrectAnswer(tempWord);
                        questionsPool.add(tempQuestion);
                    } else {
                        Word tempWord = new Word(translation,Constants.languagePl);
                        wordsPool.add(tempWord);
                        Question tempQuestion = new Question(word, Constants.languagePl);
                        tempQuestion.setCorrectAnswer(tempWord);
                        questionsPool.add(tempQuestion);
                    }
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
    }

    public abstract void startQuiz();

    public abstract void getNextQuestion();

    public void setQuizView(QuizView quizView) {
        this.quizView = quizView;
    }

    public void setQuizDifficultyManager(IQuizDifficultyManager quizDifficultyManager) {
        this.quizDifficultyManager = quizDifficultyManager;
    }

    public void setQuestionsLanguage(String questionsLanguage) {
        this.questionsLanguage = questionsLanguage;
    }

    public void setQuizConfiguration(QuizConfiguration quizConfiguration) {
        this.quizConfiguration = quizConfiguration;
    }
}