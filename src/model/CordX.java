package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("cordX")
public class CordX {

	@Param(0)
	int x;
	
	public CordX(){
	}
	
	public CordX(int x){
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

}
