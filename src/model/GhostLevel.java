package model;

import app.config.Utilities;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("ghostLevel")
public class GhostLevel {
	
	@Param(0)
	int x;
	
	public GhostLevel() {}
	
	public GhostLevel(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
}

