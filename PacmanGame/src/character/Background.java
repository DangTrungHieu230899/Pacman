package character;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {
	BufferedImage mazeImages ;
	int mazeNo = 0;
	int m = 0;
	public Background() {
		try {
			mazeImages = ImageIO.read(Pacman.class.getResource("/images/00.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g) {
		g.drawImage(mazeImages, 0, 0, null);
	}
}
