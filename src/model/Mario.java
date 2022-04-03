package model;

import java.awt.Rectangle;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("mario")
public class Mario {

	@Param(0)
	private int x;
	@Param(1)
	private int y;
	private int width;
	private int height;
	@Param(2)
	public Direction d;
	public boolean isjumping;
	public boolean isFalling;
	@Param(3)
	public boolean isClimbingUp;
	public boolean isClimbingDown;
	public boolean isDead;
	public boolean isInvincible;
	public boolean animating;
	public boolean blocked;
	double velocityY = 0;
	public double maxVelocityY = 10;
	public double gravity = 9.5;
	Rectangle r;
	
	public Mario() {
	}
	
	public Mario(int x, int y, int width, int height, Direction d) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.d = d;
		this.isjumping = false;
		this.isFalling = false;
		this.isDead = false;
		this.isInvincible = false;
		this.isClimbingUp = false;
		this.isClimbingDown = false;
		this.gravity = 9.5;
		this.velocityY = 0;
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

	public Direction getDirection() {
		return d;
	}

	public void setDirection(Direction d) {
		this.d = d;
	}

	public boolean isClimbingUp() {
		return isClimbingUp;
	}
	
	public boolean isClimbingDown() {
		return isClimbingDown;
	}

	public void setClimbingUp(boolean isClimbing) {
		this.isClimbingUp = isClimbing;
	}
	
	public void setClimbingDown(boolean isClimbing) {
		this.isClimbingDown = isClimbing;
	}

	public Rectangle getRectangle() {
		r = new Rectangle(this.getX(), this.getY(), this.getWidth() - 10, this.getHeight());
		return r;
	}
	public void setRectangle(Rectangle r) {
		this.r = r;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public double getGravity() {
		return gravity;
	}

	public double setVelocityY(double e) {
		return velocityY;
	}

	public double getMaxVelocityY() {
		return maxVelocityY;
	}

}
