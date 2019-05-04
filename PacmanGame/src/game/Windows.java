package game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import view.DisplayInfo;


public class Windows extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameScreen game;
	DisplayInfo info;
	public Windows() {
		init();
	}

	public void start() {
		game.start();

	}

	private void init() {
		
		setLayout(new BorderLayout());
		setTitle("Pacman");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		game = new GameScreen();
		add(game,BorderLayout.CENTER);
		
		info = new DisplayInfo();
		add(info,BorderLayout.EAST);
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
}
