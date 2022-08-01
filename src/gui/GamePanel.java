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
				//System.out.println("leftRightMargin: " + leftRightMargin);
				//System.out.println("topBottomMargin: " + topBottomMargin);
				
				int row = (e.getY() - topBottomMargin) / CELLSIZE;
				int col = (e.getX() - leftRightMargin) / CELLSIZE;
				//System.out.println(row + ", " + col);
				
				if(row >= world.getRows() || col >= world.getColumns()) {
					return;
				}
				
				boolean status = world.getCell(row, col);
				world.setCell(row, col, !status);
				
				//world.setCell(3, 3, true);
				/*
				int colCell = (e.getX() - leftRightMargin) / CELLSIZE;
				System.out.println(colCell);
				
				int rowCell = (e.getY() - topBottomMargin) / CELLSIZE;
				System.out.println(rowCell);
				
				boolean worldFeedback = world.getCell(rowCell, colCell);
				System.out.println(worldFeedback);
				
				if(!worldFeedback) {
					world.setCell(rowCell, colCell, true);
				}
				*/
				
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
		
		//world.setCell(0, 0, true);
		//world.setCell(2, 1, true);
		
		g2.setColor(backgroundColor);
		g2.fillRect(0, 0, width, height);
		
		//g2.setColor(foregroundColor);
		//g2.fillRect(leftRightMargin, topBottomMargin, width - 2 * leftRightMargin, height - 2 * topBottomMargin);
		
		drawGrid(g2, width, height);
		
		//fillCell(g2, 2, 4, true);
		//fillCell(g2, 2, 4, false);
		//fillCell(g2, 3, 5, true);
		
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
}
