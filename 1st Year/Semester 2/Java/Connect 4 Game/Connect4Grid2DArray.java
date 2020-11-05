package connect4Game;

import java.util.Arrays;

/* This class implements the Connect4Grid interface and provides appropriate functionality for each of the methods
 * in the interface.
 */

public class Connect4Grid2DArray implements Connect4Grid{

	private String[][] grid = new String[6][7];

	public void emptyGrid() {
		for(int i=0; i<grid.length; i++) {		// grid.length = number of rows
			for (int j=0; j<grid[0].length; j++) {	// grid[0].length = number of columns.
				grid[i][j] = "empty";
			}
		}
		System.out.println(toString());
	}

	@Override
	public String toString() {
		String gridString = Arrays.deepToString(grid).replace("], ", "]\n").replace("[[", "[")
				.replace("]]", "]");
		return(gridString);
	}

	@Override
	public boolean isValidColumn(int column) {
		if(column>6 || column <0) {
			return false;
		}
		else {
			return true;
		}
	}

	// only need to check if top one is occupied
	@Override
	public boolean isColumnFull(int column) {
		if (column >= 0 && column <=6) {
			if(grid[0][column].equalsIgnoreCase("empty")) {		// i.e. if position is occupied.
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return true;
		}
	}

	@Override
	public void dropPiece(ConnectPlayer player, int column) {
		for(int i=(grid.length-1); i>=0; i--) {
			if(grid[i][column]!=null) {			// grid[][] = null, we don't want this, we want grid[][]="null"
				if((grid[i][column]).equals("empty")) {		
					grid[i][column] = player.getColour();
					break;
				}
			}
		}
		System.out.println("");
		System.out.println(toString());
	}

	//@Override
	public boolean didLastPieceConnect4() {
		if(testHorizontal()||testVertical()||testUpRightDiagonal()||testUpLeftDiagonal()||testDownRightDiagonal()||testDownLeftDiagonal()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isGridFull() {
		for(int i=0; i<grid.length; i++) {		
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j].equalsIgnoreCase("empty")) {
					return false;
				}
			}
		}
		System.out.println("Draw Game!");
		return true;
	}

	//-------------------------------------------------------------------------------------------------------------------------------------
	public boolean testHorizontal() {
		int count = 1;
		for(int i=5; i>=0; i--) {
			for(int j=0; j<6; j++) {
				if(grid[i][j].equalsIgnoreCase(grid[i][j+1]) && (!grid[i][j].equalsIgnoreCase("empty"))) {
					count++;
				}
				else {
					count = 1;
				}
				if (count==4) {
					return true;
				}
			}
			count = 1;
		}
		return false;
	}

	public boolean testVertical() {
		int count = 1;
		for(int j=0; j<=6; j++) {
			for(int i=5; i>0; i--) {
				if(grid[i][j].equalsIgnoreCase(grid[i-1][j]) && (!grid[i][j].equalsIgnoreCase("empty"))) {
					count++;
				}
				else {
					count = 1;
				}
				if (count==4) {
					return true;
				}
			}
			count = 1;
		}
		return false;
	}

	public boolean testUpRightDiagonal() {
		int count = 1;
		for(int j=0; j<4; j++) {
			int j2 = j;
			for(int i=5; i>2; i--) {
					if(grid[i][j2].equalsIgnoreCase(grid[i-1][j2+1]) && (!grid[i][j2].equalsIgnoreCase("empty"))) {
						count++;
						j2++;
					}
					else {
						count = 1;
						j2 = j;
					}
					if (count==4) {
						return true;
					}
			}
			count = 1;
		}
		return false;
	}

	public boolean testUpLeftDiagonal() {
		int count = 1;
		for(int j=6; j>2; j--) {
			int j2 = j;
			for(int i=5; i>2; i--) {
				if(grid[i][j2].equalsIgnoreCase(grid[i-1][j2-1]) && (!grid[i][j2].equalsIgnoreCase("empty"))) {
					count++;
					j2--;
				}
				else {
					count = 1;
					j2=j;
				}
				if (count==4) {
					return true;
				}
			}
			count = 1;
		}
		return false;
	}

	public boolean testDownRightDiagonal() {
		int count = 1;
		for(int j=0; j<4; j++) {
			int j2 = j;
			for(int i=0; i<3; i++) {
				if(grid[i][j2].equalsIgnoreCase(grid[i+1][j2+1]) && (!grid[i][j2].equalsIgnoreCase("empty"))) {
					count++;
					j2++;
				}
				else {
					count = 1;
					j2=j;
				}
				if (count==4) {
					return true;
				}
			}
			count = 1;
		}
		return false;
	}

	public boolean testDownLeftDiagonal() {
		int count = 1;
		for(int j=6; j>2; j--) {
			int j2 = j;
			for(int i=0; i<3; i++) {
				if(grid[i][j2].equalsIgnoreCase(grid[i+1][j2-1]) && (!grid[i][j2].equalsIgnoreCase("empty"))) {
					count++;
					j2--;
				}
				else {
					count = 1;
					j2=j;
				}
				if (count==4) {
					return true;
				}
			}
			count = 1;
		}
		return false;
	}
}
