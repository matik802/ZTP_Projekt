package Views;

import Controllers.DatabaseAccess;
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
    private final DatabaseAccess databaseAccess;

    public AddWordsView(JFrame parent) {
        super(parent, "Add/Edit Words", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        String[] columnNames = {"ID", "Word (ENG)", "Translation (PL)"};
        tableModel = new DefaultTableModel(columnNames, 0);
        wordsTable = new JTable(tableModel);

        databaseAccess = new DatabaseAccess(Constants.languageEng); // Change accordingly

        questionList = databaseAccess.initQuestions();
        refreshTable();

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

    private void addWordToJsonFile(String question, String translation) {
        databaseAccess.addWordToJsonFile(question, translation);
        questionList = databaseAccess.initQuestions();
        refreshTable();
    }

    private void deleteWordFromJsonFile(String question) {
        databaseAccess.deleteWordFromJsonFile(question);
        questionList = databaseAccess.initQuestions();
        refreshTable();
    }

    private void editWordInJsonFile(String oldQuestion, String newQuestion, String newTranslation) {
        databaseAccess.editWordInJsonFile(oldQuestion, newQuestion, newTranslation);
        questionList = databaseAccess.initQuestions();
        refreshTable();
    }

    private void refreshTable() {
        databaseAccess.refreshTable(tableModel, questionList);
    }
}