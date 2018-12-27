/*
 * GameObject
 * 
 * A GameObject instance is any entity that is rendered on the screen.
 * This includes the player, enemies, bullets, score, health bar, etc.
 */

package main.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Game;

abstract public class GameObject {

	protected double x, y;		 // coordinates of GameObject
	protected boolean onScreen;  // whether object is in JFrame bounds
	protected double velX, velY; // velocity in x, y directions
	
	// attributes of killable entities
	protected final int MAX_HEALTH;
	protected int health;
	protected boolean isHurt;
	
	// timer for hurt effect
	protected int hurtDuration; // # of ticks invincible after taking damage
	protected int tickCount;

	// sprite
	protected BufferedImage spriteImg;
	protected BufferedImage hurtImg;
	protected BufferedImage displayImg;  // image currently displayed
	
	
	
	public GameObject(double x, double y, int maxHealth) {
		this.x = x;
		this.y = y;
		this.MAX_HEALTH = maxHealth;
		this.health = this.MAX_HEALTH;
		this.isHurt = false;
		this.tickCount = 0; 
		this.onScreen = true;
	}
	
	// initialize maxHealth to -1 when irrelevant to child
	public GameObject(double x, double y) {
		this(x, y, -1); 
	}
	
	public int getTickCount() {
		return tickCount;
	}
	
	public void setTickCount(int tickCount) {
		this.tickCount = tickCount;
	}

	// cause entity to take damage and enter isHurt state
	public void takeDamage(int dmg) {
		this.tickCount = 0;
		this.health -= dmg;
		this.displayImg = hurtImg;
		this.isHurt = true;
	}
	
	// cause entity to exit isHurt state
	public void stopDamage() {
		this.isHurt = false;
		displayImg = spriteImg;
	}
	
	// update entity state
	public void tick() {
		
		/*
		 * update damaged state
		 */
		if (this.isHurt) {
			this.tickCount++;
		}
		
		// if hurt time is up, stop hurting
		if (tickCount == hurtDuration) {
			this.stopDamage();
		} 
		
	}

	// for collision detection
	public Rectangle getBounds(int width, int height) {
		return new Rectangle((int) x, (int) y, width, height);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, Game.OBJECT_SIZE, Game.OBJECT_SIZE);
	}
		
	// renders the GameObject at the proper coordinates
	public void render(Graphics g) {
		g.drawImage(displayImg, (int) x, (int) y, null);
	}
	
	// check that entity is on screen
	public boolean isOnScreen() {
		return this.onScreen;
	}
	
	/*
	 * location methods
	 */

	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	/*
	 * movement methods
	 */
	
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public double getVelX() {
		return this.velX;
	}
	
	public double getVelY() {
		return this.velY;
	}
	
	/*
	 * health methods
	 */
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
		
	public boolean isHurt() {
		return isHurt;
	}

	public void setHurt(boolean isHurt) {
		this.isHurt = isHurt;
	}
	
}
