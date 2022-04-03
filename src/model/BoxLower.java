package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("BoxLower")
public class BoxLower {

	@Param(0)
	int n;
	
	public BoxLower() {
	}
	
	public BoxLower(int n){
		this.n = n;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	
}
