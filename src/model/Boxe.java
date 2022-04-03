package model;
import java.awt.Rectangle;

import app.config.Utilities;

public class Boxe {

	 	private int x;
		private int y;
	    private int width;
	    private int height;
	    private int cordX;
	    private int cordY;
	    
	    public Boxe(int x, int y, int width, int height, int j, int i) {
	    	this.x = x;
	    	this.y = y;
	    	this.width = width;
			this.height = height;
			this.cordX = j;
			this.cordY = i;
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

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}
		
		public int getCordX() {
			return cordX;
		}

		public int getCordY() {
			return cordY;
		}
		
		public Rectangle getRectangle() {
			 Rectangle r = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
			    return r;
		 }
	
		public Rectangle getRectangle(int x, int y) {
			 Rectangle r = new Rectangle(x,y,Utilities.DIM_X_TILE+5,Utilities.DIM_Y_TILE);
			    return r;
		 }
		
		public Rectangle getRectangleJump() {
			 Rectangle r = new Rectangle(x-10,y-50,Utilities.DIM_X_TILE,60);
			    return r;
		 }
}
