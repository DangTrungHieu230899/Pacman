package character;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.Keyboard;

public class Pacman extends Actor {

	BufferedImage packman;
	BufferedImage powerPill;
	BufferedImage overGame;
	BufferedImage winGame;
	BufferedImage pill;
	int frame;
	int reqDir, curDir;

	public int columns, rows;
	ArrayList<String> lines = new ArrayList<String>();
	public char[][] cells;
	public boolean dead;
	public int totalPill;
	public int countPill;
	public boolean over;
	public boolean win;

	public Pacman() {
		over = false;
		dead = false;
		win = false;
		cells = mazes.getCells();
		rows = mazes.rows;// 260
		columns = mazes.columns;// 228

		y = mazes.row;
		x = mazes.column;

		frame = 0;
		totalPill = mazes.countPill;
		countPill = 0;
		curDir = reqDir = MOVE_RIGHT;

		try {
			packman = ImageIO.read(Pacman.class.getResource("/images/packman.png"));
			powerPill = ImageIO.read(Pacman.class.getResource("/images/powerpink.png"));
			overGame = ImageIO.read(Pacman.class.getResource("/images/over.png"));
			winGame = ImageIO.read(Pacman.class.getResource("/images/winner.png"));
			pill = ImageIO.read(Pacman.class.getResource("/images/pill.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updatePlaying() {

		if (Keyboard.keys[KeyEvent.VK_LEFT]) {

			reqDir = KeyEvent.VK_LEFT;
		}
		if (Keyboard.keys[KeyEvent.VK_RIGHT]) {
			reqDir = KeyEvent.VK_RIGHT;

		}
		if (Keyboard.keys[KeyEvent.VK_DOWN]) {

			reqDir = KeyEvent.VK_DOWN;
		}
		if (Keyboard.keys[KeyEvent.VK_UP]) {
			reqDir = KeyEvent.VK_UP;
		}
	}

	public void update() {
		if (!dead) {
			updatePlaying();
			frame++;
			if (frame > 5) {
				frame = 0;
			}
			if (move(reqDir) == SUCCESS) {
				curDir = reqDir;

			} else {
				move(curDir);
			}

		}
	}

	static final int SUCCESS = 1, FAIL = 0;

	private int move(int reqDir) {

		switch (reqDir) {

		case KeyEvent.VK_LEFT: // 37
			if (x > 0 && mazes.charAt(y, x - 1) != WALL) {
				x -= 1;

				return SUCCESS;
			}
			if (x == 0 && cells[y][columns - 1] == STREET) {
				x = columns - 1;
				return SUCCESS;
			}

			break;
		case KeyEvent.VK_UP: // 38
			if (y > 0 && mazes.charAt(y - 1, x) != WALL) {
				y -= 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_RIGHT: // 39
			if (x < columns - 1 && mazes.charAt(y, x + 1) != WALL) {
				x += 1;
				return SUCCESS;
			}
			break;
		case KeyEvent.VK_DOWN: // 40
			if (y < rows - 1 && mazes.charAt(y + 1, x) != WALL) {
				y += 1;
				return SUCCESS;
			}
			break;
		}
		return FAIL;

	}

	final int radius = 14;
	final int widthPacman = 28;
	final int heightPacman = 28;
	final int distanceImage = 30;
	final int alignPill = 5;
	final int alignPower = 8;
	final char PILL = '2';
	final char POWER_PILL = '3';
	final char STREET = '1';
	final char WALL = '0';

	public void draw(Graphics2D g) {
		if (dead == false) {
			g.setColor(Color.WHITE);
			for (int r = 0; r < mazes.rows; r++) {
				for (int c = 0; c < mazes.columns; c++) {
					if (cells[r][c] == PILL) {
						g.drawImage(pill, c * STEP - alignPill, r * STEP - alignPill, null);
					} else if (cells[r][c] == POWER_PILL) {
						g.drawImage(powerPill, c * STEP - alignPower, r * STEP - alignPower, null);

					}
				}
			}
			g.drawImage(packman.getSubimage((frame / 2) * distanceImage, (curDir - MOVE_LEFT) * distanceImage,
					widthPacman, heightPacman), x * STEP - radius, y * STEP - radius, null);
		}
		if (over && dead == true) {

			g.drawImage(overGame, 160, 217, null);

			g.drawRect(0, 0, 456, 520);
		}

		if (win) {
			g.drawImage(winGame, 160, 217, null);
		}

	}

}
