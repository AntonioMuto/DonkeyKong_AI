package model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;


@Id("movement")
public class Movement{

		@Param(0)
	 	private int x;

		public Movement() {
		}
		
		public Movement(int x) {
	    	this.x = x;
	    }	
		
		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}
		
}

