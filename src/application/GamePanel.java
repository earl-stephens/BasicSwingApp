package application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	public GamePanel() {
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		int width = getWidth();
		int height = getHeight();
		
		g2.setColor(Color.GREEN);
		g2.fillRect(0, 0, width, height);
	}
	
	
}
