package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("level")
public class Level {

	@Param(0)
	private int level;
	
	public Level() {
	}
	
	public Level(int l) {
		this.level = l;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
