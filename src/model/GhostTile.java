package model;

import app.config.Utilities;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("ghosttile")
public class GhostTile {
	
	@Param(0)
	int x;
	@Param(1)
	int y;
	@Param(2)
	int direction;
	@Param(3)
	int level;
	@Param(4)
	int climbStatus;
	
	public GhostTile() {}
	
	public GhostTile(int x, int y, int d, int c) {
		this.x = x / Utilities.DIM_X_TILE;
		this.y = y / Utilities.DIM_Y_TILE;
		this.direction = d;
		if (this.y >= 27 && this.y <= 32)
			level = 1;
		else if (this.y >= 22 && this.y <= 26)
			level = 2;
		else if (this.y >= 17 && this.y <= 21)
			level = 3;
		else if (this.y >= 12 && this.y <= 16)
			level = 4;
		else if (this.y >= 7 && this.y <= 11)
			level = 5;
		else if (this.y >= 0 && this.y <= 6)
			level = 6;
		this.climbStatus = c;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getClimbStatus() {
		return climbStatus;
	}

	public void setClimbStatus(int climbStatus) {
		this.climbStatus = climbStatus;
	}
	
}

