package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Models.EasyQuestion;
import Models.HardQuestion;
import Models.Word;
import Utils.Constants;

public class TestQuizState extends QuizState{
    private int points = 0;

    @Override
    public void startQuiz() {
        Random rand = new Random();
        int n;
        if (currentQuestion == null) {
            n = rand.nextInt(questionsPool.size());
            currentQuestion = questionsPool.get(n);

            quizView.setQuestion(currentQuestion.getQuestionToAnswer());
            quizView.getNextButton().addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    getNextQuestion();
                }  
            });  	

            return;
        }
    }

    @Override
    public void getNextQuestion() {
        String userAnswer = null;
        if (quizDifficultyManager.getDifficulty().equals(Constants.easyDifficultyLevel)) {
            //userAnswer = quizView.getCheckBoxAnswer().getText();
        }
        else if (quizDifficultyManager.getDifficulty().equals(Constants.hardDifficultyLevel)) {
            userAnswer = quizView.getUserAnswerTextField().getText();
        }

        String difficulty = null;
        if (currentQuestion.getCorrectAnswer().getName().equals(userAnswer)) {
            points++;
            difficulty = quizDifficultyManager.getDifficulty(true);
        }
        else {
            difficulty = quizDifficultyManager.getDifficulty(false);
        }

        if (++questionsCount >= quizConfiguration.getQuizLength()) {
            //summary screen
            quizView.showQuizOverScreen(points);
            return;
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
            for (int i = 0; i < quizConfiguration.getEasyDiffultyQuestions() + x; i++) {
                n = rand.nextInt(wordsPool.size());
                if (!wordsPool.get(n).getName().equals(currentQuestion.getCorrectAnswer().getName())) {
                    answers.add(wordsPool.get(n));
                }
                else x++;
            }
            n = rand.nextInt(quizConfiguration.getEasyDiffultyQuestions());
            Word temp1 = answers.get(0);
            Word temp2 = answers.get(n);
            answers.add(n, temp1);
            answers.add(0, temp2);

            //TODO
        }
        else {
            currentQuestion = (HardQuestion) currentQuestion;

            quizView.setQuestion(currentQuestion.getQuestionToAnswer());
        }
    }
    
}
