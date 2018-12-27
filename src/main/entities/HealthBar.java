/*
 * HealthBar
 * 
 * Displays a health bar for a killable game object.
 * The Player class contains a reference to HealthBar
 * and is responsible for updating and rendering it.
 */

package main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealthBar extends GameObject {
	
	// location of health on screen
	public static final int HEALTH_BAR_X = 10;
	public static final int HEALTH_BAR_Y = 10;
	
	// dimensions of health bar
	public static final int HEALTH_BAR_WIDTH = 200;
	private final double WIDTH_ONE_HEALTH;
	public static final int HEALTH_BAR_HEIGHT = 30;
	
	public HealthBar(int maxHealth) {
		// position HealthBar and initialize maxHealth
		super(HEALTH_BAR_X, HEALTH_BAR_Y, maxHealth);
		this.WIDTH_ONE_HEALTH = HEALTH_BAR_WIDTH / (double) maxHealth;		
		
	}

	public void tick() {}
	
	// render health bar
	public void render(Graphics g) {		
		
		// draw background
		g.setColor(Color.gray);
		g.fillRect(HEALTH_BAR_X, HEALTH_BAR_Y, 
				HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
		
		// draw outline
		g.setColor(Color.white);
		g.drawRect(HEALTH_BAR_X, HEALTH_BAR_Y, 
				HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
		
		// draw health
		g.setColor(Color.green);
		g.fillRect(HEALTH_BAR_X, HEALTH_BAR_Y, 
				(int) (this.health * WIDTH_ONE_HEALTH), HEALTH_BAR_HEIGHT);
		
	}

}
