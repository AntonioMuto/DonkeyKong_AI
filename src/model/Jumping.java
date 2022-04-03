package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("jumping")
public class Jumping {

	@Param(0)
	int j;
	
	public Jumping() {
	}
	
	public Jumping(int j){
		this.j = j;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
}
