package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("boxesDiff")
public class BoxesDiff {

	@Param(0)
	int n;
	@Param(1)
	int m;
	
	
	public BoxesDiff() {
	}
	
	public BoxesDiff(int i, int m){
		this.n = i;
		this.m = m;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}
	
	
	
}
