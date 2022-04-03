package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("typeTile")
public class TypeTile {

	@Param(0)
	int t;
	
	public TypeTile() {
	}
	
	public TypeTile(int t){
		this.t = t;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}
	
}
