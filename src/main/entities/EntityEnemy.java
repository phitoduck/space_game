/*
 * EntityEnemy
 * 
 * Anything that hurts the player.
 */

package main.entities;

abstract public class EntityEnemy extends GameObject {

	// constructor for killable children
	public EntityEnemy(double x, double y, int maxHealth) {
		super(x, y, maxHealth);
	}
	
	// constructor for non-killable children
	public EntityEnemy(double x, double y) {
		super(x, y);
	}
	
}
