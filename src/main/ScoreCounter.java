/*
 * ScoreCounter
 * 
 * Displays the score in the upper right hand corner 
 * of the screen. The Controller class contains an instance
 * of ScoreCounter and is responsible for updating and rendering
 * that instance.
 */

package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ScoreCounter {
	
	// location of score counter
	public static final int SCORE_X = Game.WIDTH * Game.SCALE - 170;
	public static final int SCORE_Y = 40;
	public static final int SCORE_FONT_SIZE = 25;
	
	private Font scoreFont = new Font("arial", Font.BOLD, 
			SCORE_FONT_SIZE);
		
	private int score = 0;
	
	public void render(Graphics g) {
		// display score		
		g.setFont(this.scoreFont);
		g.setColor(Color.yellow);
		g.drawString("SCORE: " + this.score, SCORE_X, SCORE_Y);
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
}
