package Controllers;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Models.Word;
import Utils.Constants;
import Views.QuizSummaryWindow;

public class LearningQuizState extends QuizState {
    private Boolean answeredCorrectly = false;
    private String oldDifficulty;

    @Override
    public void startQuiz() {
        oldDifficulty = quizDifficultyManager.getDifficulty();
        Random rand = new Random();
        int n;
        if (currentQuestion == null) {
            n = rand.nextInt(questionsPool.size());
            currentQuestion = questionsPool.get(n);

            if (quizDifficultyManager.getDifficulty().equals(Constants.hardDifficultyLevel)) {
                quizView.buildHardQuestionUI();
            }
            else {
                preBuildEasy(rand);
            }
            quizView.setQuestion(currentQuestion.getQuestionToAnswer());
            quizView.setNextButtonAction(this);
        }
    }

    public void preBuildEasy(Random rand){
        int x = 0, n;

        List<Word> answers = new ArrayList<>();
        answers.add(currentQuestion.getCorrectAnswer());
        for (int i = 0; i < quizConfiguration.getEasyDiffultyQuestions() + x; i++) {
            n = rand.nextInt(wordsPool.size());
            if (!wordsPool.get(n).getName().equals(currentQuestion.getCorrectAnswer().getName())) {
                Boolean checkIfOnList = false;
                for (Word a : answers) {
                    if (a.getName().equals(wordsPool.get(n).getName())) {
                        x++;
                        checkIfOnList = true;
                    }
                }
                if (!checkIfOnList) answers.add(wordsPool.get(n));
            }
            else x++;
        }
        n = rand.nextInt(quizConfiguration.getEasyDiffultyQuestions());
        if (n > 0) {
            Word temp1 = answers.get(0);
            Word temp2 = answers.get(n);
            answers.remove(n);
            answers.remove(0);
            answers.add(0, temp2);
            answers.add(n, temp1);
        }
        quizView.setAnswers(answers);
        quizView.buildEasyQuestionUI();
    }

    @Override
    public void getNextQuestion() {

        String userAnswer = null;
        if (oldDifficulty.equals(Constants.easyDifficultyLevel)) {
            userAnswer = quizView.getSelectAnswer();
        }
        else if (oldDifficulty.equals(Constants.hardDifficultyLevel)) {
            userAnswer = quizView.getUserAnswerTextField().getText();
        }

        String difficulty = null;

        String currentAnswer="";
        try {
            currentAnswer = userAnswer.toLowerCase();
        } catch (Exception e){

        }

        if (currentQuestion.getCorrectAnswer().getName().toLowerCase().equals(currentAnswer)) {
            difficulty = quizDifficultyManager.getDifficulty(true);
            answeredCorrectly = true;
            if (++questionsCount == quizConfiguration.getQuizLength()) {
                new QuizSummaryWindow(-1);
                quizView.dispose();
                return;
            }
        }
        else {
            difficulty = quizDifficultyManager.getDifficulty(false);
        }

        if (!answeredCorrectly) return;

        if (!oldDifficulty.equals(difficulty)) {
            quizView.clear();
            if (difficulty.equals(Constants.easyDifficultyLevel)){
                preBuildEasy(new Random());
                quizView.buildEasyQuestionUI();
            }
            else quizView.buildHardQuestionUI();
            quizView.setNextButtonAction(this);
            oldDifficulty = difficulty;
        }   

        questionsPool.remove(currentQuestion);

        Random rand = new Random();
        int n = rand.nextInt(questionsPool.size());
        currentQuestion = questionsPool.get(n);
        int x = 0;
        if (difficulty.equals(Constants.easyDifficultyLevel)) {
            List<Word> answers = new ArrayList<>();
            answers.add(currentQuestion.getCorrectAnswer());
            for (int i = 0; i < quizConfiguration.getEasyDiffultyQuestions() + x; i++) {
                n = rand.nextInt(wordsPool.size());
                if (!wordsPool.get(n).getName().equals(currentQuestion.getCorrectAnswer().getName())) {
                    Boolean checkIfOnList = false;
                    for (Word a : answers) {
                        if (a.getName().equals(wordsPool.get(n).getName())) {
                            x++;
                            checkIfOnList = true;
                        }
                    }
                    if (!checkIfOnList) answers.add(wordsPool.get(n));
                }
                else x++;
            }
            n = rand.nextInt(quizConfiguration.getEasyDiffultyQuestions());
            if (n > 0) {
                Word temp1 = answers.get(0);
                Word temp2 = answers.get(n);
                answers.remove(n);
                answers.remove(0);
                answers.add(0, temp2);
                answers.add(n, temp1);
            }

            quizView.setAnswers(answers);
            quizView.setQuestion(currentQuestion.getQuestionToAnswer());
        }
        else {          
            quizView.setQuestion(currentQuestion.getQuestionToAnswer());
            quizView.getUserAnswerTextField().setForeground(Color.gray);
            quizView.getUserAnswerTextField().setText("Write your answer:");
        }
        answeredCorrectly = false;
    }
    
}
