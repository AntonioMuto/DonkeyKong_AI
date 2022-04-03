package model;

import java.awt.Rectangle;

import app.config.Utilities;

public class Ladder {
	 private int x;
		private int y;

	    public Ladder(int x, int y) {
	    	this.x = x;
	    	this.y = y;

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

		public Rectangle getRectangle() {
			 Rectangle r = new Rectangle(this.getX(),this.getY(),Utilities.DIM_X_TILE,Utilities.DIM_Y_TILE);
			    return r;
		 }

}
