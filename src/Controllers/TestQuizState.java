package Controllers;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Models.Word;
import Utils.Constants;
import Views.QuizSummaryView;

// Klasa reprezentująca stan quizu w trybie testowym
public class TestQuizState extends QuizState {
    private int points = 0;

    // Metoda rozpoczynająca quiz w trybie testowym
    @Override
    public void startQuiz() {
        Random rand = new Random();
        int n;
        if (currentQuestion == null) {
            n = rand.nextInt(questionsPool.size());
            currentQuestion = questionsPool.get(n);

            if (quizDifficultyManager.getDifficulty().equals(Constants.hardDifficultyLevel)) {
                quizView.buildHardQuestionUI();
            } else {
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
                    } else x++;
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
            quizView.setQuestion(currentQuestion.getQuestionToAnswer());
            quizView.setNextButtonAction(this);
        }
    }

    // Metoda przechodząca do kolejnego pytania w trybie testowym
    @Override
    public void getNextQuestion() {
        String userAnswer = null;
        if (quizDifficultyManager.getDifficulty().equals(Constants.easyDifficultyLevel)) {
            userAnswer = quizView.getSelectAnswer();
        } else if (quizDifficultyManager.getDifficulty().equals(Constants.hardDifficultyLevel)) {
            userAnswer = quizView.getUserAnswerTextField().getText();
        }

        String prevDifficulty = quizDifficultyManager.getDifficulty();
        String difficulty = null;

        String currentAnswer = "";
        try {
            currentAnswer = userAnswer.toLowerCase();
        } catch (Exception e) {
            // Obsługa ewentualnych błędów
        }

        if (currentQuestion.getCorrectAnswer().getName().toLowerCase().equals(currentAnswer)) {
            points++;
            difficulty = quizDifficultyManager.getDifficulty(true);
        } else {
            difficulty = quizDifficultyManager.getDifficulty(false);
        }

        // Jeżeli zmienił się poziom trudności, aktualizuj widok pytania
        if (!prevDifficulty.equals(difficulty)) {
            quizView.clear();
            if (difficulty.equals(Constants.easyDifficultyLevel)) quizView.buildEasyQuestionUI();
            else quizView.buildHardQuestionUI();
            quizView.setNextButtonAction(this);
        }

        // Sprawdź czy osiągnięto maksymalną liczbę pytań w quizie
        if (++questionsCount >= quizConfiguration.getQuizLength()) {
            new QuizSummaryView(points); // Wyświetl podsumowanie quizu
            quizView.dispose(); // Zamknij widok quizu
            return;
        }

        // Usuń aktualne pytanie z puli
        questionsPool.remove(currentQuestion);

        // Losuj nowe pytanie z puli
        Random rand = new Random();
        int n = rand.nextInt(questionsPool.size());
        currentQuestion = questionsPool.get(n);
        int x = 0;
        if (difficulty.equals(Constants.easyDifficultyLevel)) {
            // Jeżeli łatwy poziom trudności, zbuduj listę odpowiedzi
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
                } else x++;
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

            // Ustaw odpowiedzi i pytanie w widoku
            quizView.setAnswers(answers);
            quizView.setQuestion(currentQuestion.getQuestionToAnswer());
        } else {
            // Jeżeli trudny poziom trudności, ustaw pytanie w widoku
            quizView.setQuestion(currentQuestion.getQuestionToAnswer());
            quizView.getUserAnswerTextField().setForeground(Color.gray);
            quizView.getUserAnswerTextField().setText("Write your answer:");
        }
    }
}
