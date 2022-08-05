package model;

import java.util.Random;

public class World {
	private int rows;
	private int columns;
	
	private boolean[][] grid;
	
	public World(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		
		grid = new boolean[rows][columns];
	}
	
	public boolean getCell(int row, int col) {
		return grid[row][col];
	}
	
	public void setCell(int row, int col, boolean status) {
		grid[row][col] = status;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public void clear() {
		//System.out.println("Clearing the board");
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				setCell(r, c, false);
			}
		}
	}
	
	public void randomize() {
		/*
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				Random randomBoolean = new Random();
				boolean status = randomBoolean.nextBoolean();
				setCell(r, c, status);
			}
		}
		*/
		clear();
		Random random = new Random();
		
		for(int i = 0; i < (rows * columns)/10; i++) {
			int row = random.nextInt(rows);
			int col = random.nextInt(columns);
			setCell(row, col, true);
		}
	}
	
	public void next() {
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				int neighbors = countNeighbors(r, c);
				System.out.println("(" + r + "," + c + ") " + neighbors);
				
				switch(neighbors) {
				case 0:
					setCell(r, c, false);
					break;
				case 1:
					setCell(r, c, false);
					break;
				case 2:
				break;
				case 3:
					setCell(r, c, true);
					break;
				default:
					setCell(r, c, false);
					break;
				}
			}
		}
	}
	
	private int countNeighbors(int row, int col) {
		int neighbors = 0;
		
		for(int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			for(int colOffset = -1; colOffset <= 1; colOffset++) {
				if(rowOffset == 0 && colOffset == 0) {
					continue;
				}
				
				int gridRow = row + rowOffset;
				int gridCol = col + colOffset;
				
				if(gridRow < 0) {
					continue;
				} else if(gridRow == rows) {
					continue;
				}
				
				if(gridCol < 0) {
					continue;
				} else if(gridCol == columns) {
					continue;
				}
				
				boolean status = getCell(gridRow, gridCol);
				
				if(status) {
					neighbors++;
				}
			}
			}
		return neighbors;
	}
}
