package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("nearestStairsDown")
public class NearestStairsDown {

	@Param(0)
	int n;
	
	public NearestStairsDown() {
	}
	
	public NearestStairsDown(int n){
		this.n = n;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
}
