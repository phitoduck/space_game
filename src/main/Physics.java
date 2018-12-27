package main;

import java.util.ArrayList;

import main.entities.EntityEnemy;
import main.entities.EntityFriendly;
import main.entities.GameObject;

public class Physics {

	/*
	 * returns a reference to an enemy if friend is in collision with enemy
	 * 
	 * otherwise returns null
	 */
	public static EntityEnemy collision(EntityFriendly friend, ArrayList<EntityEnemy> enemies) {
		
		for (EntityEnemy enemy : enemies) {
			if (friend.getBounds().intersects(enemy.getBounds())) {
				return enemy;
			}
		}
		
		return null;
	}
	
	/*
	 * returns true if two entities are in collision
	 */
	public static boolean collision(GameObject entityA, GameObject entityB) {
		
		if (entityA.getBounds().intersects(entityB.getBounds())) {
			return true;
		}
		
		return false;
	}
	
}
