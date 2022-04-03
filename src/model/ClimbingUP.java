package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("climbingUP")
public class ClimbingUP {
	
	@Param(0)
	int state;
	
	public ClimbingUP() {
	}
	
	public ClimbingUP(int state) {
		this.state = state;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
