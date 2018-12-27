/*
 * 
 * Game
 * 
 * Implements Runnable: the run() method starts the game loop
 * 
 */

package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import main.entities.Bullet;
import main.entities.EntityEnemy;
import main.entities.EntityFriendly;
import main.entities.Player;

public class Game extends Canvas implements Runnable {
	
	// JFrame dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public static final int OBJECT_SIZE = 32;
	
	// JFrame title
	public final String TITLE = "2D Space Game";
	
	// game running
	private boolean running = false;
	private Thread thread;				// instance of Game will be a thread
	
	// image to draw onto JFrame via Canvas
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	// contains game images
	private Textures textures;
	
	// background image
	private BufferedImage background;
	
	// game states
	public static enum STATE {
		MENU,
		GAME,
		END
	}; 
	
	private STATE state = STATE.MENU;
	
	// game menu screen
	private Menu menu;
	private DeathScreen deathScreen;
	
	// player data
	private Player player;
	public static final int START_X = Game.WIDTH / 2;
	public static final int START_Y = Game.HEIGHT / 2;
	public static final int MOVE_VELOCITY = 5;
	public static final int PLAYER_MAX_HEALTH = 4;
	private boolean isShooting = false;
	
	// for drawing entities
	private Graphics g;
	private BufferStrategy bs;
	
	// manages non-player entities (Bullets, etc.)
	private Controller controller;
	
	
	
	
	/*
	 * helper functions
	 */
	
	// returns true if coordinates are within the JFrame
	public static boolean isOnScreen(int x, int y) {
		
		if (x >= 0 - OBJECT_SIZE && x <= WIDTH * SCALE + OBJECT_SIZE
				&& y >= -OBJECT_SIZE && y <= HEIGHT * SCALE + OBJECT_SIZE) {
			return true;
		}
		
		return false;
	}
	
	/*
	 *  same as isOnScreen, but prints information about where
	 *  the location fails to be on screen
	 */
	public static boolean isOnScreenVerbose(int x, int y) {
		
		/*
		 *  print information to reveal where we went off screen
		 */
		System.out.println("Coordinate (" + x + ", " + y + ")");
		
		if (x < 0 - OBJECT_SIZE)
			System.out.println("left is bad!"); 
		
		if (x > WIDTH * SCALE + OBJECT_SIZE)
			System.out.println("right is bad!"); 
		
		if (y < -OBJECT_SIZE)
			System.out.println("up is bad!"); 
		
		if (y > HEIGHT * SCALE + OBJECT_SIZE)
			System.out.println("down is bad!"); 
		
		return isOnScreen(x, y);
	}
	
	
	public void setState(STATE state) {
		this.state = state;
	}
	
	public STATE getState() {
		return this.state;
	}
	
	/*
	 * end of helper functions
	 */
	
	
	
	
	// initializes game variables
	private void init() {
		
		// from Canvas: no need to click on the window to play
		this.requestFocus();
				
		// add Key and MouseListeners to Canvas
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput(this));
		
		// get game images
		this.textures = new Textures();
		this.background = textures.getBackground();
		
		// initialize game entities
		this.player = new Player(START_X, START_Y, PLAYER_MAX_HEALTH, textures);
		this.controller = new Controller(textures, this.player);
		
		// initialize menu
		this.menu = new Menu();
		this.deathScreen = new DeathScreen();
		
	}
	
	/*
	 * start game as a thread
	 * */
	public synchronized void start() {
		if (running) {
			return;
		} else {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
				
		return;
	}
	
	/*
	 * stop the game
	 */
	public void stop() {
		if (!running) {
			return;
		}
		
		// no longer running
		running = false;
		
		// join thread
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		// halt process
		System.exit(1);
	}
	
	// update game entities
	private void tick() {
		if (this.state == STATE.GAME) {
			player.tick();
			
			// if player has died
			if (player.getHealth() <= 0) {
				// change to death screen
				this.state = STATE.END;
				// update score
				this.deathScreen.setScore(controller.getScore());
			}
			
			// update state of non-player entities
			controller.tick();
		}
				
	}
	
	/*
	 * draw current state of each game entity onto the screen
	 */
	private void render() {
		
		/*
		 * canvas related data members
		 */
		
		// BufferStrategy comes from the Canvas class
		this.bs = this.getBufferStrategy(); // returns null if not initialized
		
		// create strategy if there isn't one
		if (this.bs == null) {
			// triple buffering: if there is time, load up to 2 screens more than is displayed
			createBufferStrategy(3);
			return;
		}
		
		// create graphics context for rendering operations
		this.g = bs.getDrawGraphics();
		
		/*
		 * within this block we specify which things
		 * get drawn with every game tick
		 */
		
		////////////////////////////////////////////
		
		// draws image onto entire JFrame, getWidth gets width of JFrame, etc.
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			
		if (this.state == STATE.GAME) {
			// draw background
			g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
			
			// draw player
			player.render(g);
			
			// draw Non-Player entities (Bullets, etc.)
			controller.render(g);
			
		} else if (this.state == STATE.MENU) {
			this.menu.render(g);
		} else if (this.state == STATE.END) {
			this.deathScreen.render(g);
		}
		
		////////////////////////////////////////////
		
		// dispose of graphics context and render
		g.dispose(); 
		bs.show(); // shows buffer strategy
	}
	
	// called when KeyInput detects keyPressed
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		// get player location
		int playerX = (int) player.getX();
		int playerY = (int) player.getY();
		
		// move player
		if (key == KeyEvent.VK_RIGHT) {
			player.setVelX(MOVE_VELOCITY);
		} else if (key == KeyEvent.VK_LEFT) {
			player.setVelX(-MOVE_VELOCITY);
		} else if (key == KeyEvent.VK_UP) {
			player.setVelY(-MOVE_VELOCITY);
		} else if (key == KeyEvent.VK_DOWN) {
			player.setVelY(MOVE_VELOCITY);
		} 
		
		// shoot bullet
		else if (key == KeyEvent.VK_SPACE && !isShooting) {
			isShooting = true; // prevent holding down shooting
			controller.addEntity(new Bullet(playerX, playerY, textures));
		}
		
	}
	
	// called when KeyInput detects keyReleased
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		// get player location
		int playerX = (int) player.getX();
		int playerY = (int) player.getY();
		
		// stop player
		if (key == KeyEvent.VK_RIGHT) {
			player.setVelX(0);
		} else if (key == KeyEvent.VK_LEFT) {
			player.setVelX(0);
		} else if (key == KeyEvent.VK_UP) {
			player.setVelY(0);
		} else if (key == KeyEvent.VK_DOWN) {
			player.setVelY(0);
		} else if (key == KeyEvent.VK_SPACE) {
			// allow more shots after key release
			isShooting = false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * 
	 * initializes game variables and runs the game loop
	 */
	public void run() {
		
		// initialize all game attributes
		init();
		
		// constants to calculate ticks/fps
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60;
		double ns = 1e9 / amountOfTicks; // a tick is 1/60 of a second
		double delta = 0;
		
		int updates = 0; //  number of ticks
		int frames = 0;  //  fps
		long timer = System.currentTimeMillis(); // keep track of seconds

		// game loop
		while (running) {
			long now = System.nanoTime();        // get current time
			delta += (now - lastTime) / ns;      // delta += elapsed time (in seconds) * 60
			lastTime = now;
			
			// tick every 1/60 of a second
			if (delta >= 1) {
				tick();
				delta--;
				updates++;
			}
			
			// render every time!
			render();
			frames++;
			
			// execute once per second
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ticks, FPS: " + frames);
				updates = 0;
				frames = 0;
			}
			
		}
		
		// stop program
		stop();
	}
	
}
