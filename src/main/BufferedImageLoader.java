/*
 * 
 * BufferedImageLoader
 * 
 * Constructor takes 'String path' parameter
 * of an image to be loaded as a BufferedImage.
 * 
 */

package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	private BufferedImage image;
	
	public BufferedImage loadImage(String path) throws IOException {

		/*
		 *  this.getClass().getResource() currently
		 *  returns null if the path is anywhere outside
		 *  of com.game.src.main
		 */
		image = ImageIO.read(getClass().getResource(path));
		
		System.out.println("Loaded file: " + getClass().getResource(path));

		return image;
		
	}
	
}
