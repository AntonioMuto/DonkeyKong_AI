package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("cordY")
public class CordY {

	@Param(0)
	int x;
	
	public CordY(){
	}
	
	public CordY(int x){
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

}
