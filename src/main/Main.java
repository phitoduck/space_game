/*
 * Main
 * 
 * Sets up JFrame, initializes game object, starts the game.
 */

package main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	
public static void main(String args[]) {
		
		// create game object
		Game game = new Game();
		
		// configure canvas size
		game.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		game.setMaximumSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		game.setMinimumSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		
		// configure JFrame
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game); 										// add a canvas to the JFrame
		frame.pack();    										// extends from 'Window' class. Formats things on resizing..
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		// start game as thread
		game.start();
				
	}

}
