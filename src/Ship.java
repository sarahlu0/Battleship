 
public class Ship {
	
	// Direction constants
	private static final int UNSET = -1;
	private static final int HORIZONTAL = 0;
	private static final int VERTICAL = 1;
	
	//Instance Variables
	//row       - What row location is the ship at?
	//col       - What column location is the ship at?
	//length    - How long is this ship?
	//direction - Is this ship vertical or horizontal?
	private int row, col, length, direction;
		
	// Constructor. Create a ship and set the length.
	public Ship(int length) {
		this.length = length; 
		row = UNSET;
		col = UNSET;
		direction = UNSET;
	}

	// Has the location been initialized
	public boolean isLocationSet() {
		
		return (row == UNSET || col == UNSET)? false : true;
	}

	// Has the direction been initialized
	public boolean isDirectionSet() {
		
		return (direction == UNSET) ? false : true;
		
	}

	// Set the location of the ship 
	public void setLocation(int row, int col) {
		this.row = row;
		this.col = col;
	}

	// Set the direction of the ship
	public void setDirection(int direction) {
		this.direction = direction; 
	}

	// Getter for the row value
	public int getRow() {
		return row;
	}
	
	// Getter for the column value
	public int getCol() {
		return col;
	}

	// Getter for the length of the ship
	public int getLength() {
		return length;
	}

	// Getter for the direction
	public int getDirection() {
		return direction;
	}

	// Helper method to get a string value from the direction
	private String directionToString() {
		if (this.isDirectionSet()== false) {
			return "unset direction";
		}
		if (direction == HORIZONTAL) {
			return "HORIZONTAL";
		} else if (direction == VERTICAL){
			return "VERTICAL";
		}
		return "unset direction";
	}

	// Helper method to get a (row, col) string value from the location
	private String locationToString() {

		if (this.isLocationSet() == false) {
			return "unset location";
		} else {
		return "(" + row + ", " + col + ")";
		}
	}

	// toString value for this Ship
	public String toString() {
		return directionToString() + " ship with length " + length + " at " + locationToString();
	}

}
