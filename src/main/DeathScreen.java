/*
 * 
 * DeathScreen
 * 
 * The Game class has one instance of DeathScreen.
 * When Game.state is END, the DeathScreen displays.
 * 
 */

package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DeathScreen {
	
	public static final int DEATH_MSG_Y = 100;
	public static final int CENTER = Game.WIDTH / 2;
	public static final int SCORE_Y = 250;
	
	// final score to display
	private int score;

	public Rectangle quitButton = new Rectangle(Menu.BUTTON_X, 
			Menu.QUIT_BUTTON_Y, 
			Menu.BUTTON_WIDTH, 
			Menu.BUTTON_HEIGHT);
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		// display title
		Font titleFont = new Font("arial", Font.BOLD, 
				Menu.TITLE_FONT_SIZE);
		g.setFont(titleFont);
		
		g.setColor(Color.white);
		g.drawString("YOU DIED!", CENTER, DEATH_MSG_Y);
		g.drawString("SCORE: " + this.score, CENTER, 
				SCORE_Y);
		
		// display buttons
		Font buttonFont = new Font("arial", Font.BOLD, Menu.BUTTON_FONT_SIZE);
		
		g.setFont(buttonFont);
		g.drawString("QUIT", quitButton.x + Menu.BUTTON_TEXT_OFFSET_X, 
				quitButton.y + Menu.BUTTON_TEXT_OFFSET_Y);
		g2d.draw(quitButton);
		
	}
	
}
