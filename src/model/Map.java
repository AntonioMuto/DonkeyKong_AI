package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("map")
public class Map {

	@Param(0)
	int x;
	@Param(1)
	int y;
	@Param(2)
	int type;
	@Param(3)
	int level;

	public Map() {
	}

	public Map(int x, int y, int type, int level) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.level = level;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
