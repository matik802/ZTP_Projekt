package Views;

import Models.Question;
import Models.Word;
import Utils.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddWordsView extends JDialog {
    private final DefaultTableModel tableModel;
    private final JTable wordsTable;
    private final JButton addButton;
    private final JButton deleteButton;
    private final JButton editButton;
    private List<Question> questionList;

    public AddWordsView(JFrame parent) {
        super(parent, "Add/Edit Words", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        String[] columnNames = {"ID", "Word (ENG)", "Translation (PL)"};
        tableModel = new DefaultTableModel(columnNames, 0);
        wordsTable = new JTable(tableModel);

        questionList = initQuestions();

        for (Question question : questionList) {
            Object[] rowData = {question.getId(), question.getQuestionToAnswer(), question.getCorrectAnswer().getName()};
            tableModel.addRow(rowData);
        }

        JScrollPane scrollPane = new JScrollPane(wordsTable);
        scrollPane.setPreferredSize(new Dimension(500, 200));

        addButton = new JButton("Add Word");
        deleteButton = new JButton("Delete Word");
        editButton = new JButton("Edit Word");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String question = JOptionPane.showInputDialog("Enter Word (ENG)");
                String translation = JOptionPane.showInputDialog("Enter Translation (PL):");

                if (question != null && !question.trim().isEmpty() && translation != null && !translation.trim().isEmpty()) {
                    addWordToJsonFile(question, translation);
                    questionList = initQuestions();
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(AddWordsView.this, "Both Question and Translation fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = wordsTable.getSelectedRow();
                if (selectedRow != -1) {
                    deleteWordFromJsonFile((String) tableModel.getValueAt(selectedRow, 1));
                    questionList = initQuestions();
                    refreshTable();
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = wordsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String question = JOptionPane.showInputDialog("Enter Question:", tableModel.getValueAt(selectedRow, 1));
                    String translation = JOptionPane.showInputDialog("Enter Translation to English:", tableModel.getValueAt(selectedRow, 2));

                    if (question != null && !question.trim().isEmpty() && translation != null && !translation.trim().isEmpty()) {
                        editWordInJsonFile((String) tableModel.getValueAt(selectedRow, 1), question, translation);
                        questionList = initQuestions();
                        refreshTable();
                    } else {
                        JOptionPane.showMessageDialog(AddWordsView.this, "Both Question and Translation fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
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

    private void addWordToJsonFile(String question, String translation) {
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

    private int generateUniqueId(JSONArray jsonArray) {
        int maxId = 0;

        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            Long id = (long) jsonObject.get("id");
            int currentId = Math.toIntExact(id);
            if (currentId > maxId) {
                maxId = currentId;
            }
        }
        return maxId+1;
    }

    private void deleteWordFromJsonFile(String question) {
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

    private void editWordInJsonFile(String oldQuestion, String newQuestion, String newTranslation) {
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

    private void refreshTable() {
        tableModel.setRowCount(0);

        for (Question question : questionList) {
            Object[] rowData = {question.getId(), question.getQuestionToAnswer(), question.getCorrectAnswer().getName()};
            tableModel.addRow(rowData);
        }
    }
}
