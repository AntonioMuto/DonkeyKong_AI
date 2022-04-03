package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("BoxHigh")
public class BoxHigh {

	@Param(0)
	int n;
	
	public BoxHigh() {
	}
	
	public BoxHigh(int n){
		this.n = n;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	
}
