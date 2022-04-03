package view;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import app.config.Utilities;
import model.Direction;
import model.Game;

public class GhostView {

	private Game game = Game.getInstance();
	
	public static Image currentImage;
	public static ArrayList<Image> imagesDX;
	public static ArrayList<Image> imagesSX;
	private int index;
	private boolean move;
	private boolean climbing;
	int dimX;
	int dimY;
	
	public GhostView() {
		this.imagesDX = new ArrayList<Image>();
		this.imagesSX = new ArrayList<Image>();
		this.index = 0;
		this.move = false;
		this.climbing = false;
		dimX = Utilities.DIM_X_GHOST;
		dimY = Utilities.DIM_Y_GHOST;
			try {
				for (int i = 0; i < 4; i++) {
					Image img;
					img = ImageIO.read(getClass().getResourceAsStream("/resources/ghostDX_" + i + ".png"));
					imagesDX.add(img);
					Image img2 = ImageIO.read(getClass().getResourceAsStream("/resources/ghostSX_" + i + ".png"));
					imagesSX.add(img2);
				}
				currentImage = imagesDX.get(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void setImage(Direction d, int state) {
	  if(!Game.getInstance().getMario().isInvincible) {	
		if (d == Direction.right) {
			if (state == 0) 
				currentImage = imagesDX.get(state);
			else 
				currentImage = imagesDX.get(state);
		}
		else {
			if (state == 0) 
				currentImage = imagesSX.get(state);
			else 
				currentImage = imagesSX.get(state);
		}
	  }
	  else if(Game.getInstance().getMario().isInvincible) {
		  if (d == Direction.right) {
				if (state == 0) 
					currentImage = imagesDX.get(state+2);
				else 
					currentImage = imagesDX.get(state+2);
			}
			else {
				if (state == 0) 
					currentImage = imagesSX.get(state+2);
				else 
					currentImage = imagesSX.get(state+2);
			}
	  }
	  
	}
	
	
	
	
}
