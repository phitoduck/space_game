/*
 * Player
 * 
 * User controlled space ship.
 */

package main.entities;

import java.awt.Graphics;

import main.Game;
import main.Textures;

public class Player extends EntityFriendly {
	
	public static final int DEFAULT_MAX_HEALTH = 3;
	public static final int PLAYER_SIZE = 32;
	
	private HealthBar healthBar;
		
	public Player(double x, double y, int maxHealth, Textures tex) {
		super(x, y, maxHealth); // updates x, y from GameObject
		
		// get images
		this.spriteImg = tex.getPlayerImg();
		this.hurtImg = tex.getHurtPlayer();
		this.displayImg = spriteImg;
		
		this.onScreen = true;
		
		this.healthBar = new HealthBar(maxHealth);
		this.hurtDuration = 15;
	}
	
	// update player state
	@Override
	public void tick() {
		super.tick();
		
		// prevent player from leaving screen
		if (this.x <= 0) 
			x = 0;
	
		if (x >= Game.WIDTH * Game.SCALE - PLAYER_SIZE) 
			x = Game.WIDTH * Game.SCALE - PLAYER_SIZE;
		
		if (y <= 0)
			y = 0;
		
		if (y >= Game.HEIGHT * Game.SCALE - PLAYER_SIZE)
			y = Game.HEIGHT * Game.SCALE - PLAYER_SIZE;
		
		// update onScreen
		this.onScreen = Game.isOnScreen((int) x, (int) y); 
		
		// update health bar
		healthBar.setHealth(this.health);
		
		// update position
		x += velX;
		y += velY;
		
	}
	
	/*
	 * render player and health bar
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
		healthBar.render(g);
	}
	
	
}
