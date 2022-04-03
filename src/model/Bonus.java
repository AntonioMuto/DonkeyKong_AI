package model;
import java.awt.Rectangle;

public class Bonus {

	 private int x;
		private int y;
	    private int width;
	    private int height;
	    
	    public Bonus(int x, int y, int width, int height) {
	    	this.x = x;
	    	this.y = y;
	    	this.width = width;
			this.height = height;
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
		
		public Rectangle getRectangle() {
			 Rectangle r = new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
			    return r;
		 }
	
}
