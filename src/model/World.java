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
		int counter = 0;
		//System.out.println("next");
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				//System.out.println("grid square (row/column): " + r + " " + c);
				System.out.println(neighborCount(r, c, counter));
			}
		}
	}
	
	private int neighborCount(int r, int c, int counter) {
		counter = 0;
		if(r == 0 && c == 0) {
			if(getCell(r, c + 1)) {
				counter +=1;
			};
			if(getCell(r + 1, c)) {
				counter += 1;
			};
			if(getCell(r + 1, c + 1)) {
				counter +=1;
			};
		} else if(r == 0 && c < columns - 1) {
			if(getCell(r, c - 1)) {
				counter +=1;
			};
			if(getCell(r, c + 1)) {
				counter +=1;
			};
			if(getCell(r + 1, c - 1)) {
				counter +=1;
			};
			if(getCell(r + 1, c)) {
				counter +=1;
			};
			if(getCell(r + 1, c + 1)) {
				counter +=1;
			}
		}
		return counter;
	}
	
	/*private int rowAtTopCount(int r, int c, int counter) {
		counter = 0;
		for(int row = 0; row <= r + 1; row++) {
			for(int col = c -1; col <= c + 1; c++) {
				if(getCell(row, col)) {
					counter +=1;
				}
			}
		}
		return counter;
	}
	*/
}
