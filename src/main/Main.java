package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args)
	{

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("<NULL>");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack(); // automatically adjust window for the components; gamePanel in this case

		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.startGameThread();

	}
}
