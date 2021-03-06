package character;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import game.Util;

public class Ghost extends Actor {
	char[][] cells;
	int width = 28;
	int height = 28;
	public int rows;
	public int columns;
	int frame, count;
	int frame2, count2;
	public boolean dead;
	public int edibleCountDown;
	BufferedImage ghost;

	public Ghost() {
		dead = false;
	}

	public Ghost(String path) {
		try {
			ghost = ImageIO.read(Pacman.class.getResource("/images/DieGhost.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		image = Util.loadImage(path);
		rows = mazes.rows;
		columns = mazes.columns;
		y = mazes.row2;
		x = mazes.column2;
		frame = 0;
		count = 1;
		speed = 5;
		frame2 = 0;
		count2 = 1;
		Random random = new Random();
		direction = random.nextInt(4) + MOVE_LEFT;

	}

	public void update() {
		if (!dead) {
			frame++;
			count++;
			if (frame > 7) {
				frame = 0;
				count = 1;
			}
			frame2++;
			count2++;
			if (frame2 > 1) {
				frame2 = 0;
				count2 = 1;
			}

			move(direction);
			if (isCollission()) {
				Random r = new Random();
				int temp = r.nextInt(4) + MOVE_LEFT;

				direction = temp;
			}
		}
	}

	private boolean isCollission() {
		// Right
		switch (direction) {
		case KeyEvent.VK_LEFT: // 37
			if (x > 0 && mazes.charAt(y, x - 1) == '0') {
				return true;
			}
			break;
		case KeyEvent.VK_UP: // 38
			if (y > 0 && mazes.charAt(y - 1, x) == '0') {
				return true;
			}
			break;
		case KeyEvent.VK_RIGHT: // 39
			if (x < columns - 1 && mazes.charAt(y, x + 1) == '0') {
				return true;
			}
			break;
		case KeyEvent.VK_DOWN: // 40
			if (y < rows - 1 && mazes.charAt(y + 1, x) == '0') {
				return true;
			}
			break;
		}
		return false;
	}

	static final int SUCCESS = 1, FAIL = 0;

	private int move(int reqDir) {

		switch (reqDir) {

		case MOVE_LEFT:
			if (x > 0 && mazes.charAt(y, x - 1) != '0') {
				x -= 1;

				return SUCCESS;
			}

			break;
		case MOVE_UP:
			if (y > 0 && mazes.charAt(y - 1, x) != '0') {
				y -= 1;
				return SUCCESS;
			}
			break;
		case MOVE_RIGHT: // 39
			if (x < columns - 1 && mazes.charAt(y, x + 1) != '0') {
				x += 1;
				return SUCCESS;
			}
			break;
		case MOVE_DOWN: // 40
			if (y < rows - 1 && mazes.charAt(y + 1, x) != '0') {
				y += 1;
				return SUCCESS;
			}
			break;
		}
		return FAIL;

	}

	public void draw(Graphics2D g) {
		if (edibleCountDown <= 0) {
			g.drawImage(image.getSubimage((frame * 26) + (count - 1) * 3, 0, 26, 26), x * 2 - 14, y * 2 - 14, null);
		} else {
//			g.drawImage(ghost, x * 2 - 14, y * 2 - 14, null);
			g.drawImage(ghost.getSubimage((frame2 * 27) + (count2 - 1) * 4, 0, 27, 27), x * 2 - 14, y * 2 - 14, null);

		}

	}

}
