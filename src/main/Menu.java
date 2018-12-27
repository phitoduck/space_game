/*
 * 
 * Menu
 * 
 * The Game class has one instance of Menu.
 * When Game.state is MENU, the Menu displays.
 * 
 */

package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	// button location data
	public static final int BUTTON_X = Game.WIDTH / 2 + 120;
	public static final int BUTTON_WIDTH = 100;
	public static final int BUTTON_HEIGHT = 50;
	public static final int PLAY_BUTTON_Y = 250;
	public static final int QUIT_BUTTON_Y = 350;
	public static final int HELP_BUTTON_Y = -1; // not implemented
	public static final int BUTTON_TEXT_OFFSET_X = 10;
	public static final int BUTTON_TEXT_OFFSET_Y = 33;
	
	public static final int TITLE_FONT_SIZE = 50;
	public static final int BUTTON_FONT_SIZE = 30;
	
	// private final int

	public Rectangle playButton = new Rectangle(BUTTON_X, PLAY_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
//	public Rectangle helpButton = new Rectangle(BUTTON_X, HELP_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
	public Rectangle quitButton = new Rectangle(BUTTON_X, QUIT_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		// display title
		Font titleFont = new Font("arial", Font.BOLD, TITLE_FONT_SIZE);
		g.setFont(titleFont);
		g.setColor(Color.white);
		g.drawString("SPACE GAME", Game.WIDTH / 2, 100);
		
		// display buttons
		Font buttonFont = new Font("arial", Font.BOLD, BUTTON_FONT_SIZE);
		g.setFont(buttonFont);
		
		g.drawString("PLAY", playButton.x + BUTTON_TEXT_OFFSET_X, 
				playButton.y + BUTTON_TEXT_OFFSET_Y);
		g2d.draw(playButton);
		
//		g.drawString("HELP", helpButton.x + BUTTON_TEXT_OFFSET_X, 
//		helpButton.y + BUTTON_TEXT_OFFSET_Y);
//		g2d.draw(helpButton);
		
		g.drawString("QUIT", quitButton.x + BUTTON_TEXT_OFFSET_X, 
				quitButton.y + BUTTON_TEXT_OFFSET_Y);
		g2d.draw(quitButton);
		
		
	}
	
}
