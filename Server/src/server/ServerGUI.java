package server;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ServerGUI{
	JFrame frame;
	JPanel panel;
	JTextArea dialogue;
	public ServerGUI() {
		frame = new JFrame("Server");
		frame.setBounds(200,300,500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		panel = new JPanel();
		panel.setBounds(0,0,500,500);
		panel.setLayout(null);
		dialogue = new JTextArea("");
		dialogue.setBounds(0,0,500,500);
		dialogue.setEditable(false);
		panel.add(dialogue);
		frame.add(panel);
		frame.setVisible(true);
	}
	public void updateText(String s) {
		dialogue.setText(dialogue.getText() +"\n" + s);
		frame.repaint();
	}
	
}
