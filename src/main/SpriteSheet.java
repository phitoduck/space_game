/*
 * 
 * SpriteSheet
 * 
 * Interacts with sprite_sheet.png
 * 
 * */

package main;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	public final int SIZE = 32; // height/width of sprite
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage ss) {
		this.image = ss;		
	}
	
	/*
	 * 
	 * Selects and returns a portion of the sprite sheet
	 * as a BufferedImage
	 * 
	 * */
	public BufferedImage grabImage(int col, int row, int width, int height) {
		
		BufferedImage img = image.getSubimage((col * SIZE) - SIZE, (row * SIZE) - SIZE, width, height);
		return img;
		
	}
	
}
