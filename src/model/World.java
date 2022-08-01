package model;

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
	
	
}
