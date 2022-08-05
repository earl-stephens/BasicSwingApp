package model;

import java.util.Random;

public class World {
	private int rows;
	private int columns;
	
	private boolean[][] grid;
	private boolean[][] buffer;
	
	public World(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		
		grid = new boolean[rows][columns];
		buffer = new boolean[rows][columns];
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
				//System.out.println("(" + r + "," + c + ") " + neighbors);
				
				boolean status = false;
				
				if(neighbors < 2) {
					status = false;
				}
				else if(neighbors > 3) {
					status = false;
				}
				else if(neighbors == 3) {
					status = true;
				}
				else if(neighbors == 2) {
					status = getCell(r, c);
				}
				
				buffer[r][c] = status;
			}
		}
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				grid[r][c] = buffer[r][c];
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
