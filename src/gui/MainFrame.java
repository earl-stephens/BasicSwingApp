package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private GamePanel gamePanel = new GamePanel();
	public MainFrame() {
		super("Game of Life");
		
		setLayout(new BorderLayout());
		add(gamePanel, BorderLayout.CENTER);
		
		addKeyListener((KeyListener) new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("key pressed " + e.getKeyCode());
				
				int code = e.getKeyCode();
				
				switch(code) {
				case 32:
					System.out.println("spacebar");
					break;
				case 8:
					System.out.println("backspace");
					break;
				case 10:
					System.out.println("enter");
					break;
				}
			}
			
		});
		
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
