package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import model.World;

public class GamePanel extends JPanel {
	
	private final static int CELLSIZE = 50;
	private final static Color backgroundColor = Color.BLACK;
	private final static Color foregroundColor = Color.GREEN;
	private final static Color gridColor = Color.GRAY;
	private int topBottomMargin;
	private int leftRightMargin;
	private World world = null;
	

	public GamePanel() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = (e.getY() - topBottomMargin) / CELLSIZE;
				int col = (e.getX() - leftRightMargin) / CELLSIZE;
				
				if(row >= world.getRows() || col >= world.getColumns()) {
					return;
				}
				
				boolean status = world.getCell(row, col);
				world.setCell(row, col, !status);
				
				repaint();
			}
			
		});
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		int width = getWidth();
		int height = getHeight();
		
		leftRightMargin = ((width % CELLSIZE) + CELLSIZE)/2;
		topBottomMargin = ((height % CELLSIZE) + CELLSIZE)/2;
		
		int rows = 0;
		int columns = 0;
		int intermediateCol = 0;
		int intermediateRow = 0;
		
		intermediateCol = width - (2 * leftRightMargin);
		columns = intermediateCol / CELLSIZE;
		//System.out.println("number of columns = " + columns);
		
		intermediateRow = height - (2 * topBottomMargin);
		rows = intermediateRow / CELLSIZE;
		//System.out.println("number of rows = " + rows);
		
		if(world == null) {
			world = new World(rows, columns);
		}
		else {
			if(world.getRows() != rows || world.getColumns() != columns) {
				world = new World(rows, columns);
			}
		}
		
		g2.setColor(backgroundColor);
		g2.fillRect(0, 0, width, height);
		
		drawGrid(g2, width, height);
		
		for(int col = 0; col < columns; col++) {
			for(int row = 0; row < rows; row ++) {
				boolean status = world.getCell(row, col);
				fillCell(g2, row, col, status);
			}
		}
		
	}
	
	private void fillCell(Graphics2D g2, int row, int col, boolean status) {
		Color color = status ? foregroundColor: backgroundColor;
		g2.setColor(color);
		g2.fillRect((leftRightMargin + col * CELLSIZE) + 1, (topBottomMargin + row * CELLSIZE) + 1, CELLSIZE - 2, CELLSIZE - 2);
	}
	
	private void drawGrid(Graphics2D g2, int width, int height) {
		g2.setColor(gridColor);
		/* My solution, which does work
		//g2.drawLine(leftRightMargin, topBottomMargin, width - leftRightMargin, topBottomMargin);
		//g2.drawLine(leftRightMargin, topBottomMargin + CELLSIZE, width - leftRightMargin, topBottomMargin + CELLSIZE);
		for(int i = 0; i <= (height - 2 * topBottomMargin); i = i + CELLSIZE) {
			g2.drawLine(leftRightMargin, topBottomMargin + i, width - leftRightMargin, topBottomMargin + i);
		}
		
		//g2.drawLine(leftRightMargin, topBottomMargin, leftRightMargin, height - topBottomMargin);
		//g2.drawLine(leftRightMargin + CELLSIZE, topBottomMargin, leftRightMargin + CELLSIZE, height - topBottomMargin);
		for(int j = 0; j <= (width - 2 * leftRightMargin); j = j + CELLSIZE) {
		g2.drawLine(leftRightMargin + j, topBottomMargin, leftRightMargin + j, height - topBottomMargin);
		}
		*/
		
		for(int x = leftRightMargin; x <= width - leftRightMargin; x += CELLSIZE) {
			g2.drawLine(x, topBottomMargin, x, height - topBottomMargin);
		}
		
		for(int y = topBottomMargin; y <= height - topBottomMargin; y += CELLSIZE) {
			g2.drawLine(leftRightMargin, y, width - leftRightMargin, y);
		}
	}
	
	public void clear() {
		world.clear();
		repaint();
	}
	
	public void randomize() {
		world.randomize();
		repaint();
	}
	
	public void next() {
		world.next();
		repaint();
	}
}
