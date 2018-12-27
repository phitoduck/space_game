/*
 * EntityFriendly
 * 
 * Anything that does not hurt the player.
 */

package main.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

abstract public class EntityFriendly extends GameObject {

	// constructor for killable children
	public EntityFriendly(double x, double y, int maxHealth) {
		super(x, y, maxHealth);
	}
	
	// constructor for non-killable children
	public EntityFriendly(double x, double y) {
		super(x, y);
	}
	
}
