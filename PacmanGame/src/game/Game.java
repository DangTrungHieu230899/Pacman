package game;

import java.awt.Graphics2D;
import java.util.ArrayList;

import character.Actor;
import character.Background;
import character.ControlScore;
import character.Ghost;
import character.Pacman;

public class Game {
	ArrayList<Actor> ghostList = new ArrayList<Actor>();
	Background background;
	public ControlScore score = new ControlScore();
	Pacman pacman;

	public Game() {
		background = new Background();
		pacman = new Pacman();
		ghostList.add(new Ghost("C:/Users/Admin/eclipse-workspace/PacmanGame/src/images/pinkGhost.png"));
		ghostList.add(new Ghost("C:/Users/Admin/eclipse-workspace/PacmanGame/src/images/redGhost.png"));
		ghostList.add(new Ghost("C:/Users/Admin/eclipse-workspace/PacmanGame/src/images/yellowGhost.png"));
		ghostList.add(new Ghost("C:/Users/Admin/eclipse-workspace/PacmanGame/src/images/cyanGhost.png"));
	}

	public boolean touching(Actor a, Actor b) {

		return Math.abs(a.getY() - b.getY()) + Math.abs(a.getX() - b.getX()) < 3;
	}

	public void checkCollisstion() {
		if (pacman.cells[pacman.getY()][pacman.getX()] == '2') {
			pacman.countPill++;
			score.setScore(score.getScore() + 1);
			pacman.cells[pacman.getY()][pacman.getX()] = '1';
		} else if (pacman.cells[pacman.getY()][pacman.getX()] == '3') {
			pacman.countPill++;
			score.setScore(score.getScore() + 20);
			pacman.cells[pacman.getY()][pacman.getX()] = '1';
			for (int i = 0; i < 4; i++) {
				Ghost ghost = (Ghost) ghostList.get(i);
				ghost.edibleCountDown = 500;
			}
		}

		for (int i = 0; i < 4; i++) {
			Ghost ghost = (Ghost) ghostList.get(i);
			if (ghost.edibleCountDown > 0) {
				if (touching(ghost, pacman)) {
					score.setScore(score.getScore() + 100);
					ghost.setX(114);
					ghost.setY(98);
				}
				ghost.edibleCountDown--;
			} 
			else {
				if (touching(ghost, pacman)) {
					pacman.dead = true;
					pacman.over = true;
				}
			}

		}
		if (pacman.countPill == pacman.totalPill) {
			for (int i = 0; i < 4; i++) {
				Ghost ghost = (Ghost) ghostList.get(i);
				pacman.dead = true;
				ghost.dead = true;
				pacman.win = true;
			}
		}

	}

	public void update() {
		for (Actor actor : ghostList) {
			actor.update();
		}
		pacman.update();
		checkCollisstion();
	}

	public void draw(Graphics2D g) {
		background.draw(g);
		pacman.draw(g);
		for (Actor actor : ghostList) {
			actor.draw(g);
		}
	}
}
