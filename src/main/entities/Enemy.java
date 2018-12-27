/*
 * Enemy
 * 
 * Damage the player on contact. Managed by the Controller class.
 */

package main.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.Game;
import main.Textures;

public class Enemy extends EntityEnemy {
	
	public static final int DEFAULT_MAX_HEALTH = 1;
	
	public Enemy(double x, double y, int maxHealth, Textures tex) {
		super(x, y, maxHealth);
		this.velY = 2;
		
		// get images
		this.spriteImg = tex.getEnemyImg();
		this.hurtImg = tex.getHurtEnemy();
		this.displayImg = spriteImg;
		
		this.hurtDuration = 6;
		
		this.onScreen = true;
	}
	
	// specify velocity and max health
	public Enemy(double x, double y, int maxHealth, Textures tex, double velY) {
		this(x, y, maxHealth, tex); // use above constructor
		this.velY = velY; // overwrite y-velocity
	}
	
	// default max health
	public Enemy(double x, double y, Textures tex, double velY) {
		this(x, y, DEFAULT_MAX_HEALTH, tex); // use above constructor
		this.velY = velY; // overwrite y-velocity
	}
	
	// update enemy state
	@Override
	public void tick() {
		super.tick();
	
		y += velY;
		
		// temp 
		if (y > Game.HEIGHT * Game.SCALE) {
			y = 0;
			
			Random rand = new Random();
			x = rand.nextInt(Game.WIDTH * Game.SCALE - Game.OBJECT_SIZE);
			velY = rand.nextInt(6) + 1;
		}
			
		
		onScreen = Game.isOnScreen((int) x, (int) y);
	}

}
