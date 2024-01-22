package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Models.EasyQuestion;
import Models.HardQuestion;
import Models.Word;
import Utils.Constants;
import Views.QuizView;

public class TestQuizState extends QuizState{
    private int points = 0;

    public TestQuizState(QuizView quizView) {
        super(quizView);
    }

    @Override
    public void getNextQuestion() {
        if (++questionsCount == 10) { //singleton
            //summary screen
            return;
        }
        String userAnswer = null;
        if (quizDifficultyManager.getDifficulty().equals(Constants.easyDifficultyLevel)) {
            //userAnswer = quizView.getCheckBoxAnswer().getText();
        }
        else if (quizDifficultyManager.getDifficulty().equals(Constants.hardDifficultyLevel)) {
            //userAnswer = quizView.getAnswerField().getText();
        }

        String difficulty = null;
        if (currentQuestion.getAnswers().equals(userAnswer)) {
            points++;
            difficulty = quizDifficultyManager.getDifficulty(true);
        }
        else {
            difficulty = quizDifficultyManager.getDifficulty(false);
        }

        questionsPool.remove(currentQuestion);

        Random rand = new Random();
        int n = rand.nextInt(questionsPool.size());
        currentQuestion = questionsPool.get(n);
        int x = 0;
        if (difficulty.equals(Constants.easyDifficultyLevel)) {
            currentQuestion = (EasyQuestion) currentQuestion;
            List<Word> answers = new ArrayList<>();
            answers.add(currentQuestion.getCorrectAnswer());
            for (int i = 0; i < 5 + x; i++) { //singleton
                n = rand.nextInt(wordsPool.size());
                if (!wordsPool.get(n).getName().equals(currentQuestion.getCorrectAnswer())) {
                    answers.add(wordsPool.get(n));
                }
                else x++;
            }
            n = rand.nextInt(5); //singleton
            Word temp1 = answers.get(0);
            Word temp2 = answers.get(n);
            answers.add(n, temp1);
            answers.add(0, temp2);
            //ustawić kontrolki do następnego pytania
            //onClick() {getNextQuestion()}
        }
        else {
            currentQuestion = (HardQuestion) currentQuestion;
            //ustawić kontrolki do następnego pytania
            //onClick() {getNextQuestion()}
        }
    }
    
}
