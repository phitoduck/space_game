/*
 * 
 * Textures
 * 
 * Handles BufferedImageLoader and SpriteSheet.
 * 
 * */

package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Textures {
	
	// path to sprite sheet
	public static final String SPRITE_SHEET_PATH = "sprite_sheet.png";
	public static final String BACKGROUND_PATH = "background.png";

	// game images
	private BufferedImage player;
	private BufferedImage hurtPlayer;
	private BufferedImage enemy;
	private BufferedImage hurtEnemy;
	private BufferedImage bullet;
	private BufferedImage background;
	
	// player info in sprite sheet
	public final int PLAYER_ROW = 1;
	public final int PLAYER_COL = 1;
	public final int HURT_PLAYER_ROW = 2;
	public final int HURT_PLAYER_COL = 1;
	public static final int PLAYER_SIZE = 32;
	
	// bullet info in sprite sheet
	public final int BULLET_ROW = 1;
	public final int BULLET_COL = 3;
	public static final int BULLET_SIZE = 32;
	
	// enemy info in sprite sheet
	public final int ENEMY_ROW = 1;
	public final int ENEMY_COL = 4;
	public final int HURT_ENEMY_ROW = 2;
	public final int HURT_ENEMY_COL = 4;
	public static final int ENEMY_SIZE = 32;
	
	// interface with sprite sheet
	private SpriteSheet spriteSheet;
	
	// loads in sprites from sprite sheets
	private BufferedImageLoader loader;
		
	public Textures() {
		
		loader = new BufferedImageLoader();
		BufferedImage spriteSheetImage;
		
		try {
			// load sprite sheet
			spriteSheetImage = loader.loadImage(SPRITE_SHEET_PATH);
			spriteSheet = new SpriteSheet(spriteSheetImage);
			
			// load background
			background = loader.loadImage("background.jpg");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: Failed to load sprite sheet.");
		}
		
		getTextures();

	}

	// initializes game images
	private void getTextures() {
		player = spriteSheet.grabImage(PLAYER_COL, PLAYER_ROW, PLAYER_SIZE, PLAYER_SIZE);
		hurtPlayer = spriteSheet.grabImage(HURT_PLAYER_COL, HURT_PLAYER_ROW, PLAYER_SIZE, PLAYER_SIZE);
		bullet = spriteSheet.grabImage(BULLET_COL, BULLET_ROW, BULLET_SIZE, BULLET_SIZE);
		enemy = spriteSheet.grabImage(ENEMY_COL, ENEMY_ROW, ENEMY_SIZE, ENEMY_SIZE);
		hurtEnemy = spriteSheet.grabImage(HURT_ENEMY_COL, HURT_ENEMY_ROW, PLAYER_SIZE, PLAYER_SIZE);
	}
	
	
	/*
	 * get images
	 */
	
	public BufferedImage getPlayerImg() {
		return player;
	}
	
	public BufferedImage getBulletImg() {
		return bullet;
	}	
	
	public BufferedImage getEnemyImg() {
		return enemy;
	}	
	
	public BufferedImage getBackground() {
		return background;
	}
	
	public BufferedImage getHurtPlayer() {
		return hurtPlayer;
	}

	public BufferedImage getHurtEnemy() {
		return hurtEnemy;
	}
}
