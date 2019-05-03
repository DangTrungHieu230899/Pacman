package character;

import java.util.Observable;
import java.util.Observer;


public class PlayerInfo implements Observer {
	private int score;
	public Observable observable;
	
	public PlayerInfo(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ControlScore) {
			ControlScore controlScore = (ControlScore) o;
			this.score = controlScore.getScore();
			System.out.println(score);
		}
	}

}
