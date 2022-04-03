package model;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.swing.RepaintManager;

import app.GameFrame;
import app.SoundLoader;
import app.config.Utilities;
import view.GamePanel;
import view.MarioView;

public class Game {

	private static Game game = null;
	SoundLoader s;
	private Vector<Floor> floors;
	private Vector<Ladder> ladders;
	private Vector<Bonus> bonusH;
	private Vector<Boxe> boxes;
	private Vector<Ghost> ghosts;
	public DonkeyKong dk;
	public Mario mario;
	public Princess princess;
	public int life;
	public int score;
	public int bonus;
	int[][] livello = new int[32][25];
	public boolean end = false;
	public boolean removing = false;
	public boolean dlvexecute = true;
	public int precedente = 7;
	private int precedenteSpawn = -1;

	public static Game getInstance() {
		if (game == null)
			game = new Game();
		return game;
	}

	private Game() {
		floors = new Vector<Floor>();
		ladders = new Vector<Ladder>();
		bonusH = new Vector<Bonus>();
		boxes = new Vector<Boxe>();
		ghosts = new Vector<Ghost>();
		this.life = 3;
		this.score = 0;
		this.bonus = 60000;
	}

	// per ogni 1 nella matrice livello crea un floor
	public void showMap() {
		int livello[][] = readMap();
		for (int i = 0; i < livello.length; i++) {
			for (int j = 0; j < livello[i].length; j++) {
				switch (livello[i][j]) {
				case 1: {
					Floor f = new Floor(j * Utilities.DIM_X_TILE, i * Utilities.DIM_Y_TILE);
					floors.add(f);
					break;
				}
				case 9: {
					this.mario = new Mario(j * Utilities.DIM_X_TILE, i * Utilities.DIM_Y_TILE, Utilities.DIM_X_MARIO,
							Utilities.DIM_Y_MARIO, Direction.right);
					break;
				}
				case 8: {
					this.dk = new DonkeyKong(j * Utilities.DIM_X_TILE, i * Utilities.DIM_Y_TILE, Utilities.DIM_DONKEY,
							Utilities.DIM_DONKEY, Direction.right);
					break;
				}
				case 7: {
					this.princess = new Princess(j * Utilities.DIM_X_TILE, i * Utilities.DIM_Y_TILE + 10,
							Utilities.DIM_PRINCESS, Utilities.DIM_PRINCESS, Direction.right);
					break;
				}
				case 3: {
					Ladder l = new Ladder(j * Utilities.DIM_X_TILE, i * Utilities.DIM_Y_TILE);
					ladders.add(l);
					break;
				}
				case 5: {
					Bonus b = new Bonus(j * Utilities.DIM_X_TILE, i * Utilities.DIM_Y_TILE, 20, 20);
					bonusH.add(b);
					break;
				}
				case 6: {
					Boxe b = new Boxe(j * Utilities.DIM_X_TILE, i * Utilities.DIM_Y_TILE, Utilities.DIM_X_TILE, 60, i,
							j);
					boxes.add(b);
					break;
				}
				}
			}
		}
	}

	public int[][] getMap() {
		return livello;
	}

	public void spawnGhost() {
		Random spawn = new Random();
		int x = spawn.nextInt(10);
		if (x == precedenteSpawn) {
			spawnGhost();
			return;
		}
		precedenteSpawn = x;
//		int x = -1;
		Ghost g = null;
		switch (x) {
		case -1: {
			if (GhostForLevel(2) < 2) {
				g = new Ghost(4 * Utilities.DIM_X_TILE, 20 * Utilities.DIM_Y_TILE, Utilities.DIM_X_GHOST,
						Utilities.DIM_Y_GHOST, Direction.right);
				ghosts.add(g);
				break;
			} else {
				spawnGhost();
				break;
			}
		}
		case 0: {
			if (GhostForLevel(2) < 2) {
				g = new Ghost(1 * Utilities.DIM_X_TILE, 25 * Utilities.DIM_Y_TILE, Utilities.DIM_X_GHOST,
						Utilities.DIM_Y_GHOST, Direction.right);
				ghosts.add(g);
				break;
			} else {
				spawnGhost();
				break;

			}
		}
		case 1: {
			if (GhostForLevel(3) < 2) {
				g = new Ghost(2 * Utilities.DIM_X_TILE, 20 * Utilities.DIM_Y_TILE, Utilities.DIM_X_GHOST,
						Utilities.DIM_Y_GHOST, Direction.right);
				ghosts.add(g);
				break;
			} else {
				spawnGhost();
				break;
			}
		}
		case 2: {
			if (GhostForLevel(4) < 2) {
				g = new Ghost(3 * Utilities.DIM_X_TILE, 15 * Utilities.DIM_Y_TILE, Utilities.DIM_X_GHOST,
						Utilities.DIM_Y_GHOST, Direction.right);
				ghosts.add(g);
				break;
			} else {
				spawnGhost();
				break;
			}
		}
		case 3: {
			if (GhostForLevel(5) < 2) {
				g = new Ghost(4 * Utilities.DIM_X_TILE, 10 * Utilities.DIM_Y_TILE, Utilities.DIM_X_GHOST,
						Utilities.DIM_Y_GHOST, Direction.right);
				ghosts.add(g);
				break;
			} else {
				spawnGhost();
				break;
			}
		}
		case 4: {
			if (GhostForLevel(2) < 2) {
				g = new Ghost(23 * Utilities.DIM_X_TILE + 15, 25 * Utilities.DIM_Y_TILE, Utilities.DIM_X_GHOST,
						Utilities.DIM_Y_GHOST, Direction.right);
				ghosts.add(g);
				break;
			} else {
				spawnGhost();
				break;
			}
		}
		case 5: {
			if (GhostForLevel(3) < 2) {
				g = new Ghost(22 * Utilities.DIM_X_TILE, 20 * Utilities.DIM_Y_TILE, Utilities.DIM_X_GHOST,
						Utilities.DIM_Y_GHOST, Direction.right);
				ghosts.add(g);
				break;
			} else {
				spawnGhost();
				break;
			}
		}
		case 6: {
			if (GhostForLevel(4) < 2) {
				g = new Ghost(21 * Utilities.DIM_X_TILE, 15 * Utilities.DIM_Y_TILE, Utilities.DIM_X_GHOST,
						Utilities.DIM_Y_GHOST, Direction.right);
				ghosts.add(g);
				break;
			} else {
				spawnGhost();
				break;
			}
		}
		case 7: {
			if (GhostForLevel(5) < 2) {
				g = new Ghost(20 * Utilities.DIM_X_TILE, 10 * Utilities.DIM_Y_TILE, Utilities.DIM_X_GHOST,
						Utilities.DIM_Y_GHOST, Direction.right);
				ghosts.add(g);
				break;
			} else {
				spawnGhost();
				break;
			}
		}
		case 8: {
			if (GhostForLevel(1) < 2) {
				g = new Ghost(0 * Utilities.DIM_X_TILE, 30 * Utilities.DIM_Y_TILE, Utilities.DIM_X_GHOST,
						Utilities.DIM_Y_GHOST, Direction.right);
				ghosts.add(g);
				break;
			} else {
				spawnGhost();
				break;
			}
		}
		case 9: {
			if (GhostForLevel(1) < 2) {
				g = new Ghost(24 * Utilities.DIM_X_TILE, 30 * Utilities.DIM_Y_TILE, Utilities.DIM_X_GHOST,
						Utilities.DIM_Y_GHOST, Direction.right);
				ghosts.add(g);
				break;
			} else {
				spawnGhost();
				break;
			}
		}
		}
	}

	// legge la mappa da input
	public int[][] readMap() {
		try {
			BufferedReader br = new BufferedReader(
					new FileReader(getClass().getResource("/resources/map.txt").getPath()));
			int i = 0;
			while (br.ready()) {
				String line = br.readLine();
				String[] row = line.split(";");
				for (int j = 0; j < 25; j++) {
					livello[i][j] = Integer.parseInt(row[j].trim());
				}
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return livello;
	}

	public Vector<Floor> getFloors() {
		return floors;
	}

	public Vector<Ladder> getLadder() {
		return ladders;
	}

	public Vector<Bonus> getBonusH() {
		return bonusH;
	}

	public Vector<Boxe> getBoxes() {
		return boxes;
	}

	public Vector<Bonus> getBonusHUsed() {
		return bonusH;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public DonkeyKong getDk() {
		return dk;
	}

	public Mario getMario() {
		return mario;
	}

	public Vector<Ghost> getGhosts() {
		return ghosts;
	}

	public void move() {
		if (!mario.blocked) {
			if (mario.getDirection() == Direction.right) {
				if (mario.getX() + 7 <= Utilities.WIDTH_SIZE - Utilities.DIM_X_MARIO - 12) {
					mario.setX(mario.getX() + 5);
				}
			} else {
				if (mario.getX() - 7 >= 0)
					mario.setX(mario.getX() - 5);
			}
		}
	}

	public void jumpDx() {
		mario.isjumping = true;
		new Thread() {
			@Override
			public void run() {
				while (mario.isjumping) {
					mario.setDirection(Direction.right);
					try {
						for (int i = 0; i < 4 || !mario.isjumping; i++) {
							if (mario.getX() + 19 > 1056) {
								mario.isjumping = false;
								break;
							}
							mario.setX(mario.getX() + 19);
							mario.setY(mario.getY() - 12);
							sleep(50);
						}
						for (int i = 0; i < 4 || !mario.isjumping; i++) {
							if (!mario.isjumping)
								break;
							if (mario.getX() + 19 > 1056) {
								mario.isjumping = false;
								break;
							}
							mario.setX(mario.getX() + 19);
							mario.setY(mario.getY() + 12);
							sleep(50);
						}
						mario.isjumping = false;
					} catch (InterruptedException e) {
					}
				}
				mario.isjumping = false;
				Game.getInstance().dlvexecute = true;
			};
		}.start();
	}

	public void jumpSx() {
		mario.isjumping = true;
		new Thread() {
			@Override
			public void run() {
				while (mario.isjumping) {
					mario.setDirection(Direction.left);
					try {
						for (int i = 0; i < 4 || !mario.isjumping; i++) {
							if (mario.getX() - 18 < 0) {
								mario.isjumping = false;
								break;
							}
							mario.setX(mario.getX() - 18);
							mario.setY(mario.getY() - 12);
							sleep(50);
						}
						for (int i = 0; i < 4 || !mario.isjumping; i++) {
							if (mario.getX() - 18 < 0) {
								mario.isjumping = false;
								break;
							}
							mario.setX(mario.getX() - 18);
							mario.setY(mario.getY() + 12);
							sleep(50);
						}
						mario.isjumping = false;
					} catch (InterruptedException e) {
					}
					mario.isjumping = false;
				}
				Game.getInstance().dlvexecute = true;
			};
		}.start();
	}

	public Rectangle getTileRectangle(int i, int j) {
		Rectangle r = new Rectangle(j * Utilities.DIM_X_TILE, i * Utilities.DIM_Y_TILE - 5, Utilities.DIM_X_TILE,
				Utilities.DIM_Y_TILE);
		return r;
	}

	public Rectangle getTileRectangleLadder(int i, int j) {
		Rectangle r = new Rectangle(j * Utilities.DIM_X_TILE + 30, i * Utilities.DIM_Y_TILE - 5,
				Utilities.DIM_X_TILE - 30, Utilities.DIM_Y_TILE);
		return r;
	}

	public Rectangle getTileRectangleLadderSx(int i, int j) {
		Rectangle r = new Rectangle(j * Utilities.DIM_X_TILE + 10, i * Utilities.DIM_Y_TILE - 5,
				Utilities.DIM_X_TILE - 30, Utilities.DIM_Y_TILE);
		return r;
	}

	public Rectangle getTileRectangleW(int i, int j) {
		Rectangle r = new Rectangle(j * Utilities.DIM_X_TILE, i * Utilities.DIM_Y_TILE - 5, Utilities.DIM_X_TILE - 40,
				Utilities.DIM_Y_TILE - 5);
		return r;
	}

	public boolean isOnGround() { // controlla se mario è sul pavFnto
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 25; j++) {
				if (mario.getRectangle().intersects(getTileRectangle(i, j)) && livello[i][j] == 1) {
					mario.isFalling = false;
					mario.blocked = false;
					return true;
				}
				for (int k = 0; k < boxes.size(); k++) {
					if (mario.getRectangle().intersects(boxes.elementAt(k).getRectangle())) {
						mario.isFalling = false;
						mario.blocked = false;
						return true;
					}
				}
			}
		}
		return false;
	}

	public void fall() {
		if (!isOnGround() && !mario.isClimbingUp && !mario.isClimbingDown) { // controlla se è sul pavimento
			if (!mario.isjumping) { // se non sta saltando allora applica la gravità
				mario.isFalling = true;
				double velocity = mario.getVelocityY() + mario.getGravity();
				mario.setY((int) (mario.getY() + velocity));
				if (mario.getVelocityY() >= mario.getMaxVelocityY()) {
					mario.setVelocityY(mario.getMaxVelocityY());
				}
			}
		}
	}

	public void updateFall() { // thread che gestisce la caduta
		new Thread() {
			@Override
			public void run() {
				while (!end) {
					fall();
					intersection();
					try {
						sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	public void climb() {
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 25; j++) {
				if (mario.getRectangle().intersects(getTileRectangle(i, j)) && livello[i][j] == 3) {
					mario.isClimbingUp = true;
					mario.setY(mario.getY() - 4);
				}
				if (mario.isClimbingUp && mario.getRectangle().intersects(getTileRectangle(i, j))
						&& livello[i][j] == 1) {
					mario.isClimbingUp = false;
					animationUp();
				}
			}
		}
	}

	public void climbdown() {
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 25; j++) {
				if (mario.getRectangle().intersects(getTileRectangle(i, j)) && livello[i][j] == 3) {
					mario.isClimbingDown = true;
					mario.setY(mario.getY() + 4);
				}
				if (mario.isClimbingDown && mario.getRectangle().intersects(getTileRectangle(i, j))
						&& livello[i][j] == 1) {
					mario.isClimbingDown = false;
				}
			}
		}
	}

	public boolean ghostOnTheFloor(int ghost) {
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 25; j++) {
				if (ghosts.elementAt(ghost).getRectangle().intersects(getTileRectangle(i, j)) && livello[i][j] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean nearLadder() {
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 25; j++) {
				if (mario.getRectangle().intersects(getTileRectangle(i, j)) && livello[i][j] == 3) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean AboveLadder() {
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 25; j++) {
				if (mario.getRectangle().intersects(getTileRectangle(i, j)) && livello[i + 2][j] == 3) {
					return true;
				}
			}
		}
		return false;
	}

	public void animationDown() {
		mario.animating = true;
		new Thread() {
			@Override
			public void run() {
				while (mario.animating) {
					try {
						mario.isClimbingDown = true;
						MarioView.currentImage = MarioView.imagesClimbing.get(4);
						sleep(100);
						mario.setY(mario.getY() + 20);
						MarioView.currentImage = MarioView.imagesClimbing.get(3);
						sleep(100);
						mario.setY(mario.getY() + 20);
						MarioView.currentImage = MarioView.imagesClimbing.get(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					mario.animating = false;
				}
			};
		}.start();
	}

	public void animationUp() {
		mario.animating = true;
		new Thread() {
			@Override
			public void run() {
				while (mario.isClimbingUp) {
					try {
						MarioView.currentImage = MarioView.imagesClimbing.get(3);
						sleep(100);
						mario.setY(mario.getY() - 25);
						MarioView.currentImage = MarioView.imagesClimbing.get(4);
						sleep(100);
						mario.setY(mario.getY() - 25);
						MarioView.still();
						mario.isClimbingUp = false;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Game.getInstance().dlvexecute = true;
				mario.animating = false;
			};
		}.start();
	}

	public boolean ghostNearLadder(Ghost ghost) {

		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 25; j++) {
				if (ghost.getD() == Direction.right) {
					if (ghost.getRectangle().intersects(getTileRectangleLadder(i, j)) && livello[i][j] == 3) {
						return true;
					}
				}
				if (ghost.getD() == Direction.left) {
					if (ghost.getRectangle().intersects(getTileRectangleLadderSx(i, j)) && livello[i][j] == 3) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean ghostAboveLadder(Ghost ghost) {
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 25; j++) {
				if (ghost.getD() == Direction.right) {
					if (ghost.getRectangle().intersects(getTileRectangleLadder(i, j)) && livello[i + 2][j] == 3) {
						return true;
					}
				}
				if (ghost.getD() == Direction.left) {
					if (ghost.getRectangle().intersects(getTileRectangleLadderSx(i, j)) && livello[i + 2][j] == 3) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public int getLevel(Object obj) {
		int level = 0;
		if (obj instanceof Ghost) {
			int n = ((Ghost) obj).getY() / Utilities.DIM_Y_TILE;
			if (n >= 27 && n <= 32)
				level = 1;
			else if (n >= 22 && n <= 26)
				level = 2;
			else if (n >= 17 && n <= 21)
				level = 3;
			else if (n >= 12 && n <= 16)
				level = 4;
			else if (n >= 7 && n <= 11)
				level = 5;
			else if (n >= 0 && n <= 6)
				level = 6;
		}
		if (obj instanceof Mario) {
			int n = ((Mario) obj).getY() / Utilities.DIM_Y_TILE;
			if (n >= 27 && n <= 32)
				level = 1;
			else if (n >= 22 && n <= 26)
				level = 2;
			else if (n >= 17 && n <= 21)
				level = 3;
			else if (n >= 12 && n <= 16)
				level = 4;
			else if (n >= 7 && n <= 11)
				level = 5;
			else if (n >= 0 && n <= 6)
				level = 6;
		}
		return level;
	}

	public int GhostForLevel(int level) {
		int x = 0;
		for (int i = 0; i < getGhosts().size(); i++) {
			if (getLevel(ghosts.elementAt(i)) == level) {
				x++;
			}
			if (getLevel(ghosts.elementAt(i)) == level - 1 && ghosts.elementAt(i).getClimbStatus() == 2) {
				x++;
			}
			if (getLevel(ghosts.elementAt(i)) == level + 1 && ghosts.elementAt(i).getClimbStatus() == 1) {
				x++;
			}
		}
		return x;
	}

	public void ghostClimb() {
		for (int ghost = 0; ghost < ghosts.size(); ghost++) {
			final int g = ghost;
			int diff = mario.getY() - ghosts.get(ghost).getY();
			if (mario.isInvincible)
				diff = diff * -1;
			if (getLevel(ghosts.elementAt(g)) != 5 && !ghosts.elementAt(g).isClimbing() && GhostForLevel(getLevel(mario)) < 2 && diff < -70
					&& ghostNearLadder(ghosts.elementAt(g))) {
				new Thread() {
					int y = ghosts.elementAt(g).getY();

					@Override
					public void run() {
						while (!Game.getInstance().end && ghosts.elementAt(g).getY() != y - 100) {
							try {
								ghosts.elementAt(g).setClimbStatus(2);
								ghosts.elementAt(g).setY(ghosts.elementAt(g).getY() - 5);
								ghosts.elementAt(g).setClimbing(true);
								sleep(400);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						ghosts.elementAt(g).setClimbing(false);
						ghosts.elementAt(g).setClimbStatus(0);
					};
				}.start();
			} else if (!ghosts.elementAt(g).isClimbing() && GhostForLevel(getLevel(mario)) < 2 && diff > 70
					&& ghostAboveLadder(ghosts.elementAt(g))) {
				new Thread() {
					int y = ghosts.elementAt(g).getY();

					@Override
					public void run() {
						while (!Game.getInstance().end && ghosts.elementAt(g).getY() != y + 100) {
							ghosts.elementAt(g).setClimbStatus(1);
							ghosts.elementAt(g).setY(ghosts.elementAt(g).getY() + 5);
							ghosts.elementAt(g).setClimbing(true);
							try {
								sleep(400);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						ghosts.elementAt(g).setClimbing(false);
						ghosts.elementAt(g).setClimbStatus(0);
					};
				}.start();
			}
		}
	}

	public void ghostMove() {
		for (int ghost = 0; ghost < ghosts.size(); ghost++) {
			if (!ghosts.elementAt(ghost).isClimbing()) {
				boolean fall = false;
				for (int i = 0; i < 32; i++) {
					for (int j = 0; j < 25; j++) {
						if (ghosts.get(ghost).getD() == Direction.right && j + 1 < 25) {
							if (ghosts.get(ghost).getRectangle().intersects(getTileRectangle(i, j))
									&& (livello[i][j + 1] == 1 || livello[i][j + 1] == 6)) {
								fall = true;
							}
						} else if (ghosts.get(ghost).getD() == Direction.left && j - 1 >= 0) {
							if (ghosts.get(ghost).getRectangle().intersects(getTileRectangle(i, j))
									&& (livello[i][j - 1] == 1 || livello[i][j - 1] == 6)) {
								fall = true;
							}
						}
					}
				}
				if (!fall && !mario.isjumping) {
					ghosts.get(ghost).changeDirection();
				}
				if (ghosts.get(ghost).getD() == Direction.right) {
					ghosts.get(ghost).setX(ghosts.get(ghost).getX() + 2);
				} else if (ghosts.get(ghost).getD() == Direction.left) {
					ghosts.get(ghost).setX(ghosts.get(ghost).getX() - 2);
				}
			}
		}
	}

	public void time() {
		new Thread() {
			@Override
			public void run() {
				while (bonus > 0 && !end) {
					try {
						bonus -= 1000;
						sleep(2000);
						if (bonus == 58000 || bonus == 56000 || bonus == 53000 || bonus == 50000 || bonus == 47000) {
							spawnGhost();
						}
//						if (bonus == 58000 ) {
//							spawnGhost();
//						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (!end)
					dead();
			};
		}.start();
	}

	public void removeBox(int i) {
		livello[boxes.elementAt(i).getCordX()][boxes.elementAt(i).getCordY()] = 0;
		//System.out.println("RIMOSSO: " + boxes.elementAt(i).getCordX() + " " + boxes.elementAt(i).getCordY() + " = "
		//		+ livello[boxes.elementAt(i).getCordX()][boxes.elementAt(i).getCordY()]);
		boxes.remove(i);
		bonus();
	}

	private void controlBoxJump(int i) {
		if (!mario.isFalling) {
			while (mario.getRectangle().intersects(
					boxes.elementAt(i).getRectangle(boxes.elementAt(i).getX(), boxes.elementAt(i).getY()))) {
			}
			if (mario.isjumping) {
				//System.out.println("BOX REMOVED");
				removeBox(i);
			}
		}
	}

	public void intersection() {
		if(mario.getRectangle().intersects(princess.getRectangle())) {
			win();
		}
		
		for (int i = 0; i < bonusH.size(); ++i) {
			if (mario.getRectangle().intersects(bonusH.elementAt(i).getRectangle())) {
				startBonus();
				bonusH.remove(i);
				bonus();
				break;
			}
		}
		for (int i = 0; i < boxes.size(); ++i) {
			if (mario.getRectangle().intersects(boxes.elementAt(i).getRectangleJump())) {
				controlBoxJump(i);
				break;
			}
		}
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 25; j++) {
				if (mario.getRectangle().intersects(dk.getRectangle())) {
					dead();
				}
			}
		}
		for (int i = 0; i < ghosts.size(); ++i) {
			if (!mario.isInvincible && mario.getRectangle().intersects(ghosts.elementAt(i).getRectangle())) {
				dead();
				break;
			} else if (mario.isInvincible && mario.getRectangle().intersects(ghosts.elementAt(i).getRectangle())) {
				ghosts.remove(i);
				bonus();
				spawnGhost();
			}
		}

	}

	public void startBonus() {
		mario.isInvincible = true;
		MarioView.invincible();
	}

	public void bonus() { // implementare bonus invincibilità
		score += 50;
	}

	public void dead() {
		mario.isDead = true;
		mario.isInvincible = false;
		ghosts.clear();
		end = true;
		GameFrame.s.stop();
		GameFrame.death.start();
		MarioView.dead();
	}
	
	public void win() {
		mario.isDead = true;
		mario.isInvincible = false;
		end = true;
		GameFrame.s.stop();
		MarioView.win();
	}
	
	

}
