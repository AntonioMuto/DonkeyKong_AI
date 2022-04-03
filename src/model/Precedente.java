package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("precedente")
public class Precedente {

	@Param(0)
	int p;
	
	public Precedente() {
	}
	
	public Precedente(int p) {
		this.p = p;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}
	
}
