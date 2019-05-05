package view;

import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import character.PlayerInfo;

public class DisplayInfo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel jtime, jscore, jhightScore;
	public JButton button1, button2, button3;
	public JTextField textScrore, textHightScore;
	public JTextArea textArea;
	PlayerInfo player;

	public DisplayInfo(PlayerInfo player) {
		this.player = player;
		init();
	}
	public DisplayInfo() {
	}


	public void init() {
		setSize(200,510);
		jscore = new JLabel("SCORE:");
		jhightScore = new JLabel("HIGH SCORE:");
		jtime = new JLabel("TIME");
		textScrore = new JTextField(10);
		textScrore.setEditable(false);
		textHightScore = new JTextField(10);
		textHightScore.setEditable(false);

		JPanel panel1 = new JPanel();

		panel1.setLayout(new GridLayout(3, 2, 10, 10));
		panel1.add(jscore);
		panel1.add(textScrore);
		panel1.add(jhightScore);
		panel1.add(textHightScore);
		panel1.add(jtime);

		add(panel1);

		Panel panel2 = new Panel();
		button1 = new JButton("START");
		panel2.add(button1);
		add(panel2);

		Panel panel3 = new Panel();
		textArea = new JTextArea(5, 20);
		panel3.add(textArea);
		add(panel3);

		Panel panel4 = new Panel();
		button2 = new JButton("MUTE");
		panel4.add(button2);
		add(panel4);

		Panel panel5 = new Panel();
		panel5.setLayout(new GridLayout(4, 1, 20, 20));
		panel5.add(panel1);
		panel5.add(panel2);
		panel5.add(panel3);
		panel5.add(panel4);
		add(panel5);

	}
	
	public void setScore(int score) {
		
		textScrore.setText(String.valueOf(score));
		
	}

}
