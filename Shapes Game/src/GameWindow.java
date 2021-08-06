import java.awt.Dimension;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
	/*
	 * Frame of game that adds the game panel
	 */
	public GameWindow(String title) {
		
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		GamePanel panel = new GamePanel();
		this.add(panel);
		this.setSize(panel.getWidth(), panel.getHeight());
		this.setVisible(true);
		
		
	}
	


}
