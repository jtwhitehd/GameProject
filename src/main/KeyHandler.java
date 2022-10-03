package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPress, downPress, leftPress, rightPress;

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode(); // gets associated keycode

		if (code == KeyEvent.VK_W) // up
		{
			upPress = true;
		}
		if (code == KeyEvent.VK_S) // down
		{
			downPress = true;
		}
		if (code == KeyEvent.VK_A) // left
		{
			leftPress = true;
		}
		if (code == KeyEvent.VK_D) // right
		{
			rightPress = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode(); // gets associated keycode

		if (code == KeyEvent.VK_W) // up
		{
			upPress = false;
		}
		if (code == KeyEvent.VK_S) // down
		{
			downPress = false;
		}
		if (code == KeyEvent.VK_A) // left
		{
			leftPress = false;
		}
		if (code == KeyEvent.VK_D) // right
		{
			rightPress = false;
		}
	}

}
