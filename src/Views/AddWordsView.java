package Views;

import Controllers.QuizController;
import Models.Question;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Klasa reprezentująca widok do dodawania, edytowania i usuwania słów
public class AddWordsView extends JDialog {
    private final DefaultTableModel tableModel;
    private final JTable wordsTable;
    private final JButton addButton;
    private final JButton deleteButton;
    private final JButton editButton;
    private List<Question> questionList;
    private QuizController quizController;

    // Konstruktor widoku
    public AddWordsView(JFrame parent, QuizController quizController) {
        super(parent, "Add/Edit Words", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        this.quizController = quizController;
        String[] columnNames = {"ID", "Word (ENG)", "Translation (PL)"};
        tableModel = new DefaultTableModel(columnNames, 0);
        wordsTable = new JTable(tableModel);

        // Inicjalizacja listy pytań
        questionList = quizController.initQuestions();
        refreshTable();

        JScrollPane scrollPane = new JScrollPane(wordsTable);
        scrollPane.setPreferredSize(new Dimension(500, 200));

        addButton = new JButton("Add Word");
        deleteButton = new JButton("Delete Word");
        editButton = new JButton("Edit Word");

        // Obsługa dodawania nowego słowa
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

        // Obsługa usuwania słowa
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = wordsTable.getSelectedRow();
                if (selectedRow != -1) {
                    deleteWordFromJsonFile((String) tableModel.getValueAt(selectedRow, 1));
                }
            }
        });

        // Obsługa edycji słowa
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

    // Metoda dodająca nowe słowo do pliku JSON i odświeżająca tabelę
    private void addWordToJsonFile(String question, String translation) {
        quizController.addWordToJsonFile(question, translation);
        questionList = quizController.initQuestions();
        quizController.refreshTable(tableModel, questionList);
    }

    // Metoda usuwająca słowo z pliku JSON i odświeżająca tabelę
    private void deleteWordFromJsonFile(String question) {
        quizController.deleteWordFromJsonFile(question);
        questionList = quizController.initQuestions();
        refreshTable();
    }

    // Metoda edytująca słowo w pliku JSON i odświeżająca tabelę
    private void editWordInJsonFile(String oldQuestion, String newQuestion, String newTranslation) {
        quizController.editWordInJsonFile(oldQuestion, newQuestion, newTranslation);
        questionList = quizController.initQuestions();
        refreshTable();
    }

    // Metoda odświeżająca tabelę
    private void refreshTable() {
        quizController.refreshTable(tableModel, questionList);
    }
}
