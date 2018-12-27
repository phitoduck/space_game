/*
 * Bullet
 * 
 * Fired by the player. Managed by the Controller class.
 */

package main.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Game;
import main.Physics;
import main.Textures;

public class Bullet extends EntityFriendly {
	
	public static final int BULLET_VELOCITY = 15;
	public static final int BULLET_SIZE = 32;
	
	// to delete when off screen
	private boolean onScreen; 
		
	public Bullet(double x, double y, Textures tex) {
		super(x, y); // maxHealth = -1 because it is irrelevant
		
		// get image
		this.spriteImg = tex.getBulletImg();
		this.displayImg = spriteImg;
		
		this.onScreen = true;
	    
	}
	
	// updates bullet
	@Override
	public void tick() {
		super.tick();
		
		if (this.onScreen)
			y -= BULLET_VELOCITY;
		
		// is it on the screen?
		if (!Game.isOnScreen((int) x, (int) y)) {
			this.onScreen = false;
		}
		
	}
	
}
