package character;

import java.util.Observable;

public class ControlScore extends Observable {

	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		measurementChanged();
	}
	
	public void measurementChanged() {
		setChanged();
		notifyObservers();
	}
}
