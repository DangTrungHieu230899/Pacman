package view;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import character.PlayerInfo;

public class DisplayInfo extends JPanel {
	public JLabel lblScore, lblHighScore, lblTimer;
	public JButton btStart, btSound;
	PlayerInfo player;

	public DisplayInfo(PlayerInfo player) {
		setSize(300, 510);
		this.player = player;
		init();
	}

	public void init() {
		lblScore = new JLabel("SCORE: ");
		lblHighScore = new JLabel("HIGH SCORE: ");
		lblTimer = new JLabel("TIME: ");
		
		btStart = new JButton("START");
		btSound = new JButton("mute");
		
		add(lblScore);
		add(lblHighScore);
		add(lblTimer);
		add(btStart);
		add(btSound);
		
		
	}

}
