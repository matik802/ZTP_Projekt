package Controllers;

import Models.Question;
import Models.Word;
import Utils.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.table.DefaultTableModel;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

    // Pula słów i pytań
    List<Word> wordsPool = new ArrayList<>();
    List<Question> questionsPool = new ArrayList<>();

    // Język pytań w bazie danych
    private final String questionsLanguage;

    // Konstruktor klasy
    public DatabaseAccess(String questionsLanguage) {
        this.questionsLanguage = questionsLanguage;
        initializeDatabase();
    }

    // Inicjalizacja bazy danych z pliku JSON
    private void initializeDatabase() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("src/words.json")) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                Long id = (Long) jsonObject.get("id");
                String word = (String) jsonObject.get("word");
                String translation = (String) jsonObject.get("translation");

                // Dodanie słowa i pytania do puli w zależności od języka pytań
                if (questionsLanguage.equals(Constants.languageEng)) {
                    Word tempWord = new Word(word, Constants.languageEng);
                    wordsPool.add(tempWord);
                    Question tempQuestion = new Question(translation, Constants.languagePl);
                    tempQuestion.setCorrectAnswer(tempWord);
                    questionsPool.add(tempQuestion);
                } else {
                    Word tempWord = new Word(translation, Constants.languagePl);
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

    // Pobranie puli słów
    public List<Word> getWords() {
        return wordsPool;
    }

    // Pobranie puli pytań
    public List<Question> getQuestions() {
        return questionsPool;
    }

    // Inicjalizacja pytań (alternatywna metoda)
    public List<Question> initQuestions() {
        List<Question> questionList = new ArrayList<>();

        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("src/words.json")) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                Long id = (Long) jsonObject.get("id");
                String question = (String) jsonObject.get("word");
                String translation = (String) jsonObject.get("translation");

                Question temp = new Question(question, Constants.languageEng);
                temp.setCorrectAnswer(new Word(translation, Constants.languagePl));
                temp.setId(Math.toIntExact(id));

                questionList.add(temp);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    // Dodanie nowego słowa do pliku JSON
    public void addWordToJsonFile(String question, String translation) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("src/words.json")) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            int newId = generateUniqueId(jsonArray);

            JSONObject newWord = new JSONObject();
            newWord.put("id", newId);
            newWord.put("word", question);
            newWord.put("translation", translation);

            jsonArray.add(newWord);

            try (FileWriter fileWriter = new FileWriter("src/words.json")) {
                fileWriter.write(jsonArray.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Generowanie unikalnego identyfikatora dla nowego słowa
    public int generateUniqueId(JSONArray jsonArray) {
        int maxId = 0;

        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            Long id = (long) jsonObject.get("id");
            int currentId = Math.toIntExact(id);
            if (currentId > maxId) {
                maxId = currentId;
            }
        }
        return maxId + 1;
    }

    // Usunięcie słowa z pliku JSON
    public void deleteWordFromJsonFile(String question) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("src/words.json")) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                String word = (String) jsonObject.get("word");
                if (word.equals(question)) {
                    jsonArray.remove(obj);
                    break;
                }
            }

            try (FileWriter fileWriter = new FileWriter("src/words.json")) {
                fileWriter.write(jsonArray.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Edycja słowa w pliku JSON
    public void editWordInJsonFile(String oldQuestion, String newQuestion, String newTranslation) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("src/words.json")) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                String word = (String) jsonObject.get("word");
                if (word.equals(oldQuestion)) {
                    jsonObject.put("word", newQuestion);
                    jsonObject.put("translation", newTranslation);
                    break;
                }
            }

            try (FileWriter fileWriter = new FileWriter("src/words.json")) {
                fileWriter.write(jsonArray.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // Odświeżanie tabeli w interfejsie graficznym
    public void refreshTable(DefaultTableModel tableModel, List<Question> questionList) {
        tableModel.setRowCount(0);

        for (Question question : questionList) {
            Object[] rowData = {question.getId(), question.getQuestionToAnswer(), question.getCorrectAnswer().getName()};
            tableModel.addRow(rowData);
        }
    }
}
