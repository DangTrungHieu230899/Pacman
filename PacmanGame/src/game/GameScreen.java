package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import character.PlayerInfo;
import view.DisplayInfo;

public class GameScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 745;
	public static final int HEIGHT = 510;
	public DisplayInfo jPanel;
	private boolean running;
	private Game game;

	public GameScreen() {
		game = new Game();
		PlayerInfo player = new PlayerInfo(game.score);
		jPanel = new DisplayInfo(player);
		add(jPanel);
		
		
		addKeyListener(new Keyboard());
		setFocusable(true);

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		Util.loadAllIamge();

		running = false;

	}

	public void start() {
		if (running) {
			return;
		}
		running = true;
		Thread thread = new Thread(new MainLoop());
		thread.start();
	}

	private void update() {
		game.update();

	}

	private void draw(Graphics2D g) {
		game.draw(g);
		add(jPanel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		draw((Graphics2D) g);
	}

	private class MainLoop implements Runnable {

		@Override
		public void run() {

			long desiredFrameRateTime = 1000 / 60;
			long currentTime = System.currentTimeMillis();
			long lastTime = currentTime - desiredFrameRateTime;
			long unprocessedTime = 1;
			boolean needsRender = false;
			while (running) {
				currentTime = System.currentTimeMillis();
				unprocessedTime += currentTime - lastTime;
				lastTime = currentTime;

				while (unprocessedTime >= desiredFrameRateTime) {
					unprocessedTime -= desiredFrameRateTime;
					update();
					needsRender = true;
				}

				if (needsRender) {
					repaint();
					needsRender = false;
				} else {
					try {
						Thread.sleep(1);
					} catch (InterruptedException ex) {
					}
				}
			}
		}

	}

}
