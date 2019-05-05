package character;

import java.util.Observable;
import java.util.Observer;

import view.DisplayInfo;



public class PlayerInfo  implements Observer {
	private int score;
	public Observable observable;
	DisplayInfo info;
	public PlayerInfo(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}
	public void setInfo(DisplayInfo info) {
		this.info = info;
	}
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ControlScore) {
			ControlScore controlScore = (ControlScore) o;
			this.score = controlScore.getScore();
			info.setScore(score);
		}
	}

	public int getScore() {
		return score;
	}
	
	

}
