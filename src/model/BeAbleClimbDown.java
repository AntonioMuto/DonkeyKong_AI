package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("beAbleClimbDown")
public class BeAbleClimbDown {

	@Param(0)
	int x;
	@Param(1)
	int y;
	@Param(2)
	int t;
	@Param(3)
	int l;
	
	public BeAbleClimbDown(){
	}
	
	public BeAbleClimbDown(int x, int y, int t, int l) {
		this.x= x;
		this.y = y;
		this.t = t;
		this.l = l;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

}
