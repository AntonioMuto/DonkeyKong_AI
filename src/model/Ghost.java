package model;

import java.awt.Rectangle;

public class Ghost {
	private int x;
	private int y;
    private int width;
    private int height;
    private boolean isClimbing;
    public Direction d;
    public int climbStatus;
    public int tileX;
    public int tileY;
	
    public Ghost(int x, int y, int width, int height, Direction d) {
    	this.x = x;
    	this.y = y;
    	this.width = width;
		this.height = height;
		this.isClimbing = false;
    	this.d = d;
    	this.climbStatus = 0;
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
	public Direction getD() {
		return d;
	}
	public void setD(Direction d) {
		this.d = d;
	}
	
	public boolean isClimbing() {
		return isClimbing;
	}

	public void setClimbing(boolean isClimbing) {
		this.isClimbing = isClimbing;
	}

	public Rectangle getRectangle() {
		 Rectangle r = new Rectangle(this.getX(),this.getY(),this.getWidth()-10,this.getHeight());
		    return r;
	}
	
	public void changeDirection() {
		if (this.d == Direction.right) 
			setD(Direction.left);
		else 
			setD(Direction.right);
	}

	public int getClimbStatus() {
		return climbStatus;
	}

	public void setClimbStatus(int climbStatus) {
		this.climbStatus = climbStatus;
	}

}
