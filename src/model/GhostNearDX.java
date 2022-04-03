package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("ghostNearDX")
public class GhostNearDX {

	@Param(0)
	int n;
	
	public GhostNearDX() {
	}

	public GhostNearDX(int n){
		this.n = n;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
}
