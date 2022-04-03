package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;


@Id("move")
public class Move{

		@Param(0)
	 	private int x;

		public Move() {
		}
		
		public Move(int x) {
	    	this.x = x;
	    }	
		
		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}
		
}

