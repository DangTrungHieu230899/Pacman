package character;

import observer.Observer;
import observer.Subject;
import view.DisplayInfo;

public class PlayerInfo implements Observer {
	private int score;
	@SuppressWarnings("unused")
	private Subject subject;
	DisplayInfo info;

	public PlayerInfo(Subject subject) {
		this.subject = subject;
		subject.register(this);
	}

	public void setInfo(DisplayInfo info) {
		this.info = info;
	}

	@Override
	public void update(int score) {
		info.setScore(score);
	}

	public int getScore() {
		return score;
	}
}
