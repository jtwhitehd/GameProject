package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SETTINGS
	final int PLAIN_TILE_SIZE = 16; // 16pixel*16pixel
	final int SCALE = 3; // multiply raw tile size by scale to get true pixel size
	final int TRUE_TILE = PLAIN_TILE_SIZE * SCALE; // true size/resolution of tile
	final int MAX_SCREEN_COL = 16; // horizontal tile count - 4x3
	final int MAX_SCREEN_ROW = 12; // vertical tile count - 4x3
	final int SCREENW = TRUE_TILE * MAX_SCREEN_COL; // 48 * 16 = 768p
	final int SCREENH = TRUE_TILE * MAX_SCREEN_ROW; // 48 * 12 = 576p
	final int FPS = 60; // TODO -> undo final?

	KeyHandler keyH = new KeyHandler();
	Thread gameThread;

	// set player default position
	int playerX = SCREENW / 2 - 36; // center of display tile in center of screen
	int playerY = SCREENH / 2 - 36; // center of display tile in center of screen
	int playerSpeed = 5; // kind of arbitrary value

	public GamePanel() // constructor
	{
		this.setPreferredSize(new Dimension(SCREENW, SCREENH));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // improves render performance
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread()
	{
		gameThread = new Thread(this); // pass GamePanel class to thread constructor
		gameThread.start();
	}

	@Override
	public void run() // delta method - game loop
	{
		double drawInterval = 1_000_000_000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if (timer >= 1_000_000_000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}

	public void update()
	{
		if (keyH.upPress) {
			if (playerY > 0) {
				playerY -= playerSpeed;
			}
		}
		if (keyH.downPress) {
			if (playerY + TRUE_TILE < SCREENH) {
				playerY += playerSpeed;
			}
		}
		if (keyH.leftPress) {
			if (playerX > 0) {
				playerX -= playerSpeed;
			}
		}
		if (keyH.rightPress) {
			if (playerX + TRUE_TILE < SCREENW) {
				playerX += playerSpeed;
			}
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// debug v
		g2.setColor(Color.cyan);
		g2.fillRect(playerX, playerY, TRUE_TILE, TRUE_TILE);
		g2.dispose();
	}

}



/*	Other Game loop method!
 * 
 *  
 * @Override
 * public void run() // sleep method - game loop
 * {
 * double drawInterval = 1_000_000_000 / FPS; // 0.016666~ seconds
 * double nextDraw = System.nanoTime() + drawInterval;
 * 
 * while (gameThread != null) {
 * update();
 * 
 * repaint();
 * 
 * try {
 * double remainingTime = (nextDraw - System.nanoTime()) / 1_000_000;
 * if (remainingTime < 0) {
 * remainingTime = 0;
 * }
 * Thread.sleep((long) remainingTime);
 * nextDraw += drawInterval;
 * }
 * catch (Exception e) {
 * System.out.println("Error in time calculation / threadsleep!");
 * }
 * }
 * }

*/
