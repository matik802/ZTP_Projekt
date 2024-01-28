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

    List<Word> wordsPool = new ArrayList<>();
    List<Question> questionsPool = new ArrayList<>();
    private final String questionsLanguage;

    public DatabaseAccess(String questionsLanguage) {
        this.questionsLanguage = questionsLanguage;
        initializeDatabase();
    }

    private void initializeDatabase() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("src/words.json")) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                Long id = (Long) jsonObject.get("id");
                String word = (String) jsonObject.get("word");
                String translation = (String) jsonObject.get("translation");

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

    public List<Word> getWords() {
        return wordsPool;
    }

    public List<Question> getQuestions() {
        return questionsPool;
    }

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

    public void refreshTable(DefaultTableModel tableModel, List<Question> questionList) {
        tableModel.setRowCount(0);

        for (Question question : questionList) {
            Object[] rowData = {question.getId(), question.getQuestionToAnswer(), question.getCorrectAnswer().getName()};
            tableModel.addRow(rowData);
        }
    }
}