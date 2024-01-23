package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

            if (quizDifficultyManager.getDifficulty().equals(Constants.hardDifficultyLevel)) {
                quizView.buildHardQuestionUI();
            }
            else {
                int x = 0;
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
                quizView.buildEasyQuestionUI();
                quizView.setAnswers(answers);
            }
            quizView.setQuestion(currentQuestion.getQuestionToAnswer());
            quizView.setNextButtonAction(this);
        }
    }

    @Override
    public void getNextQuestion() {
        String userAnswer = null;
        if (quizDifficultyManager.getDifficulty().equals(Constants.easyDifficultyLevel)) {
            userAnswer = quizView.getSelectAnswerList().getSelectedValue().toString();
        }
        else if (quizDifficultyManager.getDifficulty().equals(Constants.hardDifficultyLevel)) {
            userAnswer = quizView.getUserAnswerTextField().getText();
        }

        String prevDifficulty = quizDifficultyManager.getDifficulty();
        String difficulty = null;
        if (currentQuestion.getCorrectAnswer().getName().toLowerCase().equals(userAnswer.toLowerCase())) {
            points++;
            difficulty = quizDifficultyManager.getDifficulty(true);
        }
        else {
            difficulty = quizDifficultyManager.getDifficulty(false);
        }

        if (!prevDifficulty.equals(difficulty)) {
            quizView.clear();
            if (difficulty.equals(Constants.easyDifficultyLevel)) quizView.buildEasyQuestionUI();
            else quizView.buildHardQuestionUI();
            quizView.setNextButtonAction(this);
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
        }
    }
    
}
