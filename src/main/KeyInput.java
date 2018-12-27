/*
 * KeyInput
 * 
 * An instance of this acts as a key listener.
 * 
 * It is added to the Canvas object in the Game class
 * to enable the player to move.
 */

package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Game game;
	
	public KeyInput(Game game) {
		this.game = game;
	}
	
	/*
	 * 
	 * keyPressed is overriding the function 
	 * from KeyAdapter. 
	 * 
	 * When a key is pressed, it triggers
	 * the "game.keyPressed()" method in an instance
	 * of a game.
	 * 
	 * Likewise for keyReleased.
	 * 
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}
	
}
