package model;

import app.config.Utilities;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("mariotile")
public class MarioTile {

	@Param(0)
	int x;
	@Param(1)
	int y;
	@Param(2)
	int level;
	@Param(3)
	int azione;
	@Param(4)
	int direction;
	@Param(5)
	int invincible;
	

	public MarioTile() {
	}

	public MarioTile(int x, int y, int azione, int direction, int invincible) {
		this.x = x / Utilities.DIM_X_TILE;
		this.y = y / Utilities.DIM_Y_TILE;
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
		this.azione = azione;
		this.direction = direction;
		this.invincible = invincible;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getAzione() {
		return azione;
	}

	public void setAzione(int azione) {
		this.azione = azione;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getInvincible() {
		return invincible;
	}

	public void setInvincible(int invincible) {
		this.invincible = invincible;
	}

	
}
