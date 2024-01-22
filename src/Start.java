import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Start {

	public static void main(String[] args) {
		QuizView frame = new QuizView();
		frame.setLayout(null);
		frame.setSize(500,500);
		frame.setVisible(true);

		JLabel text=new JLabel("Quiz.");  
		text.setBounds(10,10, 100,30);

		JButton button;
		button = new JButton("Start");
		button.setBounds(10,50,95,30);
		button.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				frame.getText().setVisible(false);  
					}  
				});  

		frame.setText(text);
		frame.setButton(button);
		
	}
}