package controller;

import view.GamePanel;

import java.awt.event.KeyEvent;

import app.DLVRun;
import app.config.Utilities;
import model.Direction;
import model.Game;

import java.awt.event.*;

public class MovementController implements KeyListener {

	private GamePanel gp;
	Game game = Game.getInstance();
	boolean rightpressed = true;
	boolean leftpressed = true;
	boolean spacepressed = false;
	private DLVRun dlv;

	public MovementController(GamePanel gp) throws Exception {
		this.gp = gp;
	}

	public void update() {
		gp.update();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void standStill() {
		if (!Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isClimbingDown && !game.getMario().isDead)
			gp.getMarioView().still();
	}

	public void moveRight() throws Exception {
		Game.getInstance().dlvexecute = false;
		if (!Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isFalling && !Game.getInstance().mario.isClimbingDown && !game.getMario().isjumping && !game.getMario().isDead) {
			rightpressed = true;
			game.getMario().setDirection(Direction.right);
			update();
			game.move();
			gp.move();
		}
		Game.getInstance().dlvexecute = true;
	}

	public void moveLeft() throws Exception {
		Game.getInstance().dlvexecute = false;
		if (!Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isFalling && !Game.getInstance().mario.isClimbingDown && !game.getMario().isDead) {
			leftpressed = true;
			game.getMario().setDirection(Direction.left);
			update();
			game.move();
			gp.move();
		}
		Game.getInstance().dlvexecute = true;
	}

	public void moveUp() throws Exception {
		if (!game.nearLadder() && !Game.getInstance().mario.isFalling && !game.getMario().animating && !game.getMario().isDead) {
			gp.turn();
			update();
		} else if (!game.getMario().animating && !Game.getInstance().mario.isFalling && !game.getMario().isjumping && !game.getMario().isDead
				&& !game.getMario().isDead) {
			Game.getInstance().dlvexecute = false;
			update();
			game.climb();
			gp.climb();
		}
		Game.getInstance().dlvexecute = true;
	}

	public void moveDown() throws Exception {
		Game.getInstance().dlvexecute = false;
		if (game.getMario().isClimbingDown && !Game.getInstance().mario.isFalling && !game.getMario().isDead) {
			game.climbdown();
			gp.climb();
		} else if (!game.getMario().isDead && !Game.getInstance().mario.isFalling && !Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isClimbingDown  && !game.getMario().isjumping) {
			if (game.AboveLadder()) {
				game.animationDown();
			} else {
				gp.getMarioView().still();
				gp.stop();
			}
		}
		Game.getInstance().dlvexecute = true;
	}

	public void jump() throws Exception {
		Game.getInstance().dlvexecute = false;
		if (game.getMario().getDirection() == Direction.right && !game.getMario().isjumping
				&& !game.getMario().isFalling && !Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isClimbingDown  && !game.getMario().isDead) {
			update();
			gp.jumpDx();
			game.jumpDx();
		}
		if (game.getMario().getDirection() == Direction.left && !game.getMario().isjumping && !game.getMario().isFalling
				&& !Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isClimbingDown  && !game.getMario().isDead) {
			update();
			gp.jumpSx();
			game.jumpSx();
		}
		Game.getInstance().dlvexecute = true;
	}

	@SuppressWarnings("static-access")
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {
			Game.getInstance().dead();
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (!Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isClimbingDown  && !game.getMario().isjumping && !game.getMario().isDead) {
				rightpressed = true;
				game.getMario().setDirection(Direction.right);
				update();
				game.move();
				gp.move();
				return;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (!Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isClimbingDown  && !game.getMario().isDead) {
				leftpressed = true;
				game.getMario().setDirection(Direction.left);
				update();
				game.move();
				gp.move();
				return;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (!game.getMario().isInvincible && !game.nearLadder() && !game.getMario().animating
					&& !game.getMario().isDead) {
				update();
				gp.turn();
			} else if (!game.getMario().isInvincible && !game.getMario().animating && !game.getMario().isjumping
					&& !game.getMario().isDead && !game.getMario().isDead) {
				update();
				game.climb();
				gp.climb();
				return;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (!game.getMario().isInvincible && !game.getMario().isFalling && game.getMario().isClimbingDown
					&& !game.getMario().isDead) {
				game.climbdown();
				gp.climb();
				return;
			} else if (!game.getMario().isInvincible && !game.getMario().isFalling && !game.getMario().isDead
					&& !Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isClimbingDown   && !game.getMario().isjumping) {
				if (game.AboveLadder()) {
					game.getMario().isClimbingDown = true;
					game.animationDown();
					return;
				} else {
					gp.getMarioView().still();
					gp.stop();
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			spacepressed = true;
			if (game.getMario().getDirection() == Direction.right && !game.getMario().isjumping
					&& !game.getMario().isFalling && !Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isClimbingDown   && !game.getMario().isDead) {
				update();
				gp.jumpDx();
				game.jumpDx();
				return;
			}
			if (game.getMario().getDirection() == Direction.left && !game.getMario().isjumping
					&& !game.getMario().isFalling && !Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isClimbingDown   && !game.getMario().isDead) {
				update();
				gp.jumpSx();
				game.jumpSx();
				return;
			}
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (!game.getMario().isInvincible && !Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isClimbingDown   && !game.getMario().isDead) {
				rightpressed = false;
				gp.getMarioView().still();
				gp.stop();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (!game.getMario().isInvincible && !Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isClimbingDown   && !game.getMario().isDead) {
				leftpressed = false;
				gp.getMarioView().still();
				gp.stop();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (!game.getMario().isInvincible && !Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isClimbingDown   && !game.getMario().isDead) {
				gp.getMarioView().still();
				gp.stop();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			spacepressed = false;
			if (!game.getMario().isInvincible && !Game.getInstance().mario.isClimbingUp && !Game.getInstance().mario.isClimbingDown   && !game.getMario().isDead)
				gp.getMarioView().still();
		}
	}

}
