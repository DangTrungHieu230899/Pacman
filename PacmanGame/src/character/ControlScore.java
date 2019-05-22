package character;

import java.util.ArrayList;

import observer.Observer;
import observer.Subject;

public class ControlScore implements Subject {
	private int score;
	private ArrayList<Observer> observes;

	public ControlScore() {
		observes = new ArrayList<Observer>();
	}

	public int getScore() {
		return score;
	}

	@Override
	public void register(Observer o) {
		observes.add(o);
	}

	@Override
	public void remove(Observer o) {
		observes.remove(o);
	}

	@Override
	public void notifyObserver() {
		for (Observer observer : observes) {
			observer.update(score);
		}
	}

	public void setScore(int score) {
		this.score = score;
		measurementChanged();
	}

	public void measurementChanged() {
		notifyObserver();
	}
}
