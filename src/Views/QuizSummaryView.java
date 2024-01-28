package Views;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizSummaryView extends JFrame {
    private final int points;
    private final JLabel background;

    public QuizSummaryView(int points) {
        this.points = points;

        // Ustawienia okna
        setTitle("Quiz Summary");
        setSize(563, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        // Ustawienia tła
        background = new JLabel();
        background.setIcon(new ImageIcon("src/summaryBackground.jpg"));
        background.setLayout(new GridLayout(3, 1, 0, 10));

        // Wyświetlanie informacji w zależności od wyników
        if (points == -1) {
            displayLearningInfo();
        } else {
            displayTestDone();
        }

        // Dodanie przycisku do zamykania okna
        addExitButton();

        // Dodanie tła do okna
        add(background);
        setVisible(true);
    }

    // Wyświetlanie informacji o zakończeniu sesji edukacyjnej
    private void displayLearningInfo() {
        JLabel emptyLabel = new JLabel("");
        background.add(emptyLabel);

        JLabel label = new JLabel("Learning session completed!");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(Color.white);
        label.setHorizontalAlignment(JLabel.CENTER);
        background.add(label);
    }

    // Wyświetlanie informacji o zakończonym teście
    private void displayTestDone() {
        JLabel emptyLabel = new JLabel("");
        background.add(emptyLabel);

        JLabel label = new JLabel("Test done. Points: " + points);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(Color.white);
        label.setHorizontalAlignment(JLabel.CENTER);
        background.add(label);
    }

    // Dodanie przycisku do zamykania okna
    private void addExitButton() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(255, 255, 255));
        exitButton.setBorder(new LineBorder(Color.WHITE, 5));
        exitButton.setPreferredSize(new Dimension(100, 40));
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(exitButton);
        background.add(buttonPanel);
    }
}
