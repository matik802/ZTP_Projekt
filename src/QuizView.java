import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class QuizView extends JFrame {
    private JLabel text;
	private JButton button;

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
		this.add(button);
	}
	
	public JLabel getText() {
		return text;
	}

	public void setText(JLabel text) {
		this.text = text;
		this.add(text);
	}
}
