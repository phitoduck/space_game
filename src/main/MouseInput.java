/*
 * MouseInput
 * 
 * An instance of this acts as a mouse listener.
 * 
 * It is added to the Canvas object in the Game class
 * to detect when the user clicks on buttons in a menu.
 */

package main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	private Game game;
	
	public MouseInput(Game game) {
		this.game = game;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		// get mouse click location
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		// mouse actions for main menu
		if (this.game.getState() == Game.STATE.MENU) {
		
			// pressed play
			if (mouseX >= Menu.BUTTON_X 
					&& mouseX <= Menu.BUTTON_X + Menu.BUTTON_WIDTH
					&& mouseY >= Menu.PLAY_BUTTON_Y 
					&& mouseY <= Menu.PLAY_BUTTON_Y + Menu.BUTTON_HEIGHT) {
				
				System.out.println("Pressed Play");
				
				this.game.setState(Game.STATE.GAME);
			} 
			
			// pressed quit
			else if (mouseX >= Menu.BUTTON_X 
					&& mouseX <= Menu.BUTTON_X + Menu.BUTTON_WIDTH
					&& mouseY >= Menu.QUIT_BUTTON_Y 
					&& mouseY <= Menu.QUIT_BUTTON_Y + Menu.BUTTON_HEIGHT) {
				
				System.out.println("Pressed Stop");
				
				game.stop();
			}
		
		} 
		
		// mouse actions for end screen
		else if (this.game.getState() == Game.STATE.END) {
			
			// pressed quit
			if (mouseX >= Menu.BUTTON_X 
					&& mouseX <= Menu.BUTTON_X + Menu.BUTTON_WIDTH
					&& mouseY >= Menu.QUIT_BUTTON_Y 
					&& mouseY <= Menu.QUIT_BUTTON_Y + Menu.BUTTON_HEIGHT) {
				
				System.out.println("Pressed Quit");
				
				game.stop();
			}
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
