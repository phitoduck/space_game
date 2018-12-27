/*
 * 
 * Controller
 * 
 * This handles all on screen GameObjects and Entities.
 * The Game class instance has a single Controller instance
 * which handles these.
 * 
 */

package main;

import java.awt.Graphics;
import java.util.ArrayList;

import main.entities.Bullet;
import main.entities.Enemy;
import main.entities.EntityEnemy;
import main.entities.EntityFriendly;
import main.entities.GameObject;
import main.entities.Player;

public class Controller {

	/*
	 * ArrayLists of entities
	 * 
	 * 'friends' and 'enemies' are sublists of 'entities' for efficient searching
	 */
	private ArrayList<GameObject> entities = new ArrayList<GameObject>();
	private ArrayList<EntityFriendly> friends = new ArrayList<EntityFriendly>();
	private ArrayList<EntityEnemy> enemies = new ArrayList<EntityEnemy>();
	
	public static final int DAMAGE_AMT = 1;
	
	// keep track of wave
	private int wave;
	private int enemyCount;
	
	// used to check for collisions
	private Player player;
	
	// access to game images
	private Textures tex;
	
	// score counter
	private ScoreCounter scoreCounter;
	
	// access to canvas
	private Graphics g;
	
	// constructor
	public Controller(Textures tex, Player player) {
		
		this.tex = tex;

		System.out.println("enemy added");
		
		this.g = g;
		this.player = player;
		
		this.wave = 1;
		this.enemyCount = wave;
		
		// generate first enemy
		addEntity(new Enemy(400, 0, 2, tex, 2));
		
		// create score counter
		scoreCounter = new ScoreCounter();
		
	}
	
	/*
	 * 
	 * update all entities:
	 * 
	 * 1) tick each entity
	 * 2) check for collisions
	 * 3) remove correct entities
	 * 
	 */
	public synchronized void tick() {
		
		// spawn new wave?
		if (enemyCount == 0) {
			this.wave++;
			createEnemies(wave);
			System.out.println("Wave " + wave);
		}
		
		ArrayList<GameObject> toRemove = new ArrayList<GameObject>();
		
		for (GameObject entity : entities) {
			
			// update entity
			entity.tick();
			
			// check for collisions between entity types
			if (entity instanceof EntityFriendly) {
				
				// check for collision
				EntityEnemy collisionEntity;
				collisionEntity = Physics.collision((EntityFriendly) entity, enemies);
				
				if (collisionEntity != null) {
					
					// if bullet, remove bullet and damage enemy
					if (entity instanceof Bullet) {
					
					System.out.println("Collision!");
					
					if (collisionEntity.getHealth() <= 0) {
						// remove dead enemy
						toRemove.add(collisionEntity);
						enemyCount--;
						
						// increment score
						scoreCounter.setScore(scoreCounter.getScore() + 1);
						
					}
										
					// remove bullet
					toRemove.add(entity);
					
					// hurt enemy
					collisionEntity.takeDamage(DAMAGE_AMT);
					
					}
				}
				
			} 
			
			// check for collisions with player
			else if (entity instanceof EntityEnemy) {
				
				// check for collision
				if (Physics.collision(player, entity)) {
					
					// if player is not hurt, take damage
					if (!player.isHurt()) {
						player.takeDamage(DAMAGE_AMT);
						System.out.println("HIT!");
					}
				}
				
			}
			
			// identify off screen entities
			if (!entity.isOnScreen()) {
				toRemove.add(entity);
				
				// print type of entity to remove
				String type = "";
				
				if (entity instanceof Bullet) {
					type = "Bullet";
				}
				
				System.out.println(type + " is removed.");
			}
		}
			
		// remove off screen entitites
		for (GameObject entity : toRemove) {
			this.removeEntity(entity);
		}
		
	}
	
	/*
	 * 
	 * IMPORTANT
	 * 
	 * without 'synchronized' on BOTH render and addEntity,
	 * a ConcurrentModificationException is thrown when the player shoots.
	 * 
	 * I think this is because the KeyListener is able to call addEntity()
	 * at the same time as render() is iterating over the list.
	 * 
	 */
	
	// render each entity
	public synchronized void render(Graphics g) {
		// show score
		scoreCounter.render(g);
		
		for (GameObject entity : entities) {
			entity.render(g);
		}
	}
	
	// add entity to 'entities' and correct sublist
	public synchronized void addEntity(GameObject entity) {
		entities.add(entity);
		
		if (entity instanceof EntityFriendly) {
			friends.add((EntityFriendly) entity);
		} else if (entity instanceof EntityEnemy) {
			enemies.add((EntityEnemy) entity);
		}
	}
	
	// remove entity from 'entities' and correct sublist
	public synchronized void removeEntity(GameObject entity) {
		entities.remove(entity);
		
		if (entity instanceof EntityFriendly) {
			friends.remove(entity);
		} else if (entity instanceof EntityEnemy) {
			enemies.remove(entity);
		}
		
		entity = null;
	}
	
	public synchronized void createEnemies(int numEnemies) {
		for (int i = 0; i < numEnemies * 2; i++) {
			addEntity(new Enemy(((i * 100) ) % (Game.WIDTH * Game.SCALE), 0, tex, 2));
			enemyCount++;
		}
	}
	
	public ArrayList<EntityFriendly> getFriendlyEntities() {
		return friends;
	}
	
	public ArrayList<EntityEnemy> getEnemyEntities() {
		return enemies;
	}
	
	public ArrayList<GameObject> getAllEntities() {
		return entities;
	}
	
	public int getScore() {
		return scoreCounter.getScore();
	}
	
}
