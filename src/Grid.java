 
public class Grid {
	
	private Location[][] grid;

	// Constants for number of rows and columns.
	public static final int NUM_ROWS = 10;
	public static final int NUM_COLS = 10;
	
	// Create a new Grid. Initialize each Location in the grid
	// to be a new Location object.
	public Grid() {
		grid = new Location[NUM_ROWS][NUM_COLS];
		for(int i = 0; i < NUM_ROWS; i++) {
			for(int j = 0; j < NUM_COLS; j++) {
				grid[i][j] = new Location();
			}
		}
	}

	// Mark a hit in this location by calling the markHit method
	// on the Location object.  
	public void markHit(int row, int col) {
		grid[row][col].markHit();
	}

	// Mark a miss on this location.    
	public void markMiss(int row, int col) {
		grid[row][col].markMiss();
	}

	// Set the status of this location object.
	public void setStatus(int row, int col, int status) {
		grid[row][col].setStatus(status);
	}

	// Get the status of this location in the grid  
	public int getStatus(int row, int col) {
		return(grid[row][col].getStatus());
	}

	// Return whether or not this Location has already been guessed.
	public boolean alreadyGuessed(int row, int col) {
		return(!grid[row][col].isUnguessed());
	}

	// Set whether or not there is a ship at this location to the val   
	public void setShip(int row, int col, boolean val) {
		grid[row][col].setShip(val);
	}

	// Return whether or not there is a ship here   
	public boolean hasShip(int row, int col) {
		return(grid[row][col].hasShip());
	}


	// Get the Location object at this row and column position
	public Location get(int row, int col) {
		return(grid[row][col]);
	}

	// Return the number of rows in the Grid
	public int numRows() {
		return NUM_ROWS;
	}

	// Return the number of columns in the grid
	public int numCols() {
		return NUM_COLS;
	}


	// Print the Grid status including a header at the top
	// that shows the columns 1-10 as well as letters across
	// the side for A-J
	// If there is no guess print a -
	// If it was a miss print a O
	// If it was a hit, print an X
	// A sample print out would look something like this:
	// 
	//   1 2 3 4 5 6 7 8 9 10 
	// A - - - - - - - - - - 
	// B - - - - - - - - - - 
	// C - - - O - - - - - - 
	// D - O - - - - - - - - 
	// E - X - - - - - - - - 
	// F - X - - - - - - - - 
	// G - X - - - - - - - - 
	// H - O - - - - - - - - 
	// I - - - - - - - - - - 
	// J - - - - - - - - - - 
	public void printStatus() {
		int letter = 65;
		System.out.println("  1 2 3 4 5 6 7 8 9 10");
		for(int i = 0; i < NUM_ROWS; i++) {
			System.out.print((char)letter + " ");
			letter++;
			for(int j = 0; j < NUM_COLS; j++) {
				if(grid[i][j].getStatus()==0) {
					System.out.print("- ");
				}
				else if(grid[i][j].getStatus()==1) {
					System.out.print("X ");
				}
				else if(grid[i][j].getStatus()==2) {
					System.out.print("O ");
				}
			}
			System.out.println();
		}
	}
	
	public int countStatus() {
		int count = 0;
		for(int i = 0; i < NUM_ROWS; i++) {
			for(int j = 0; j < NUM_COLS; j++) {
				if(grid[i][j].getStatus()==1) {
					count++;
				}
			}
		}
		return count;
	}

	// Print the grid and whether there is a ship at each location.
	// If there is no ship, you will print a - and if there is a
	// ship you will print a X. You can find out if there was a ship
	// by calling the hasShip method.
	//
	//   1 2 3 4 5 6 7 8 9 10 
	// A - - - - - - - - - - 
	// B - X - - - - - - - - 
	// C - X - - - - - - - - 
	// D - - - - - - - - - - 
	// E X X X - - - - - - - 
	// F - - - - - - - - - - 
	// G - - - - - - - - - - 
	// H - - - X X X X - X - 
	// I - - - - - - - - X - 
	// J - - - - - - - - X - 
	public void printShips() {
		int letter = 65;
		System.out.println("  1 2 3 4 5 6 7 8 9 10");
		for(int i = 0; i < NUM_ROWS; i++) {
			System.out.print((char)letter + " ");
			letter++;
			for(int j = 0; j < NUM_COLS; j++) {
				if(grid[i][j].hasShip()) {
					System.out.print("X ");
				}
				else {
					System.out.print("- ");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * This method can be called on your own grid. To add a ship
	 * we will go to the ships location and mark a true value
	 * in every location that the ship takes up.
	 */
	public void addShip(Ship s) {
		int l = s.getLength();
		int r = s.getRow();
		int c = s.getCol();
		int d = s.getDirection();
		//if horizontal
		if(d==0) {
			for(int i = c; i < c+l; i++) {
				grid[r][i].setShip(true);
			}
		}
		//if vertical
		else if(d==1) {
			for(int j = r; j < r+l; j++) {
				grid[j][c].setShip(true);
			}
		}
	}
	
}