package view;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import app.GameFrame;
import app.config.Utilities;
import model.Direction;
import model.Game;

public class MarioView {

	private Game game = Game.getInstance();

	public static Image currentImage;
	public static ArrayList<Image> imagesDX;
	public static ArrayList<Image> imagesSX;
	public static ArrayList<Image> imagesHammerDX;
	public static ArrayList<Image> imagesHammerSX;
	public static ArrayList<Image> imagesClimbing;
	public static ArrayList<Image> imagesDeath;
	private int index;
	public boolean move;
	public boolean jumping;
	boolean climbing;
	public static int dimX;
	public static int dimY;

	@SuppressWarnings("static-access")
	public MarioView() {
		this.imagesClimbing = new ArrayList<Image>();
		this.imagesDX = new ArrayList<Image>();
		this.imagesSX = new ArrayList<Image>();
		this.imagesHammerDX = new ArrayList<Image>();
		this.imagesHammerSX = new ArrayList<Image>();
		this.imagesDeath = new ArrayList<Image>();
		this.index = 0;
		this.move = false;
		this.climbing = false;
		this.jumping = false;
		dimX = Utilities.DIM_X_MARIO;
		dimY = Utilities.DIM_Y_MARIO;
		try {
			for (int i = 0; i < 5; i++) {
				Image img = ImageIO.read(getClass().getResourceAsStream("/resources/marioUp_" + i + ".png"));
				imagesClimbing.add(img);
			}
			for (int i = 0; i < 3; i++) {
				Image img = ImageIO.read(getClass().getResourceAsStream("/resources/marioDX_" + i + ".png"));
				imagesDX.add(img);
				Image img2 = ImageIO.read(getClass().getResourceAsStream("/resources/marioSX_" + i + ".png"));
				imagesSX.add(img2);
			}
			for (int i = 0; i < 5; i++) {
				Image img = ImageIO.read(getClass().getResourceAsStream("/resources/death_" + i + ".png"));
				imagesDeath.add(img);
			}
			for (int i = 0; i < 5; i++) {
				Image img = ImageIO.read(getClass().getResourceAsStream("/resources/marioHammerDX_" + i + ".png"));
				imagesHammerDX.add(img);
			}
			for (int i = 0; i < 5; i++) {
				Image img = ImageIO.read(getClass().getResourceAsStream("/resources/marioHammerSX_" + i + ".png"));
				imagesHammerSX.add(img);
			}
			currentImage = imagesDX.get(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void move() {
		if (!game.getMario().isInvincible) {
			move = true;
			if (game.getMario().getDirection() == Direction.right) {
				index++;
				if (index == imagesDX.size()) {
					index = 0;
				}
				currentImage = imagesDX.get(index);
			} else if (game.getMario().getDirection() == Direction.left) {
				index++;
				if (index == imagesSX.size())
					index = 0;
				currentImage = imagesSX.get(index);
			}
		}
	}

	public void stop() {
		move = false;
		jumping = false;
		index = 0;
	}

	public static void still() {
		if (Game.getInstance().getMario().getDirection() == Direction.right)
			currentImage = imagesDX.get(0);
		else
			currentImage = imagesSX.get(0);
	}

	public void turn() {
		currentImage = imagesClimbing.get(0);
	}

	public void jumpDx() {
	  if(!game.getMario().isInvincible)
		currentImage = imagesDX.get(1);
	}

	public void jumpSx() {
		if(!game.getMario().isInvincible)
		currentImage = imagesSX.get(1);
	}

	public void climb() {
		if (currentImage == imagesClimbing.get(2)) {
			currentImage = imagesClimbing.get(1);
		} else if (currentImage == imagesClimbing.get(1)) {
			currentImage = imagesClimbing.get(2);
		} else {
			currentImage = imagesClimbing.get(1);
		}
	}
	
	public static void win() {
		GamePanel.win();
	}

	public static void dead() {
		new Thread() {
			boolean stop = false;

			@Override
			public void run() {
				while (!stop) {
					try {
						for (int i = 0; i < 4; i++) {
							currentImage = imagesDeath.get(i);
							sleep(300);
						}
						for (int i = 0; i < 4; i++) {
							currentImage = imagesDeath.get(i);
							sleep(300);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					currentImage = imagesDeath.get(4);
					stop = true;
				}
				try {
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				GamePanel.Death();
				still();
			};
		}.start();
	}

	public static void invincible() {
		new Thread() {
			int n = Game.getInstance().bonus;
			int i = 0;

			@Override
			public void run() {
				while (n - Game.getInstance().bonus < 10000 && !Game.getInstance().end) {
					try {
						if (Game.getInstance().getMario().getDirection() == Direction.right) {
							currentImage = imagesHammerDX.get(i);
							sleep(300);
						}
						if (Game.getInstance().getMario().getDirection() == Direction.left) {
							currentImage = imagesHammerSX.get(i);
							sleep(300);
						}
						if (i == 4)
							i = 0;
						else
							i++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Game.getInstance().getMario().isInvincible = false;
				if (Game.getInstance().getMario().getDirection() == Direction.right)
					currentImage = imagesDX.get(0);
				if (Game.getInstance().getMario().getDirection() == Direction.left)
					currentImage = imagesSX.get(0);
			};
		}.start();
	}

}
