package model;

import app.config.Utilities;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("ghostNearSX")
public class GhostNearSX {
	
	@Param(0)
	int x;
	
	public GhostNearSX() {}
	
	public GhostNearSX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
}

