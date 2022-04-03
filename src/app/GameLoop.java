package app;

import controller.MovementController;
import model.Game;

public class GameLoop implements Runnable {

	private int frequency = 60;
	private MovementController controller;
	
	public GameLoop(MovementController controller) {
		this.controller = controller;
	}
	
	@Override
	public void run() {	
		while(true) {
				Game.getInstance().ghostMove();
				Game.getInstance().ghostClimb();
			try {
				Thread.sleep(frequency);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}