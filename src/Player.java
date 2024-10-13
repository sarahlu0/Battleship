 
public class Player {
	
	private Grid playerGrid; 
	private Grid oppGrid;
	
	public Player() {
		
		// grid with player's ships
		playerGrid = new Grid();
		
		// grid with opponent's guesses
		oppGrid = new Grid();
		
		
	}
		
	// Print your ships on the grid
	public void printMyShips() {
		playerGrid.printShips();
	}

	public boolean hasShip(int r, int c) {
		return (playerGrid.hasShip(r, c))? true: false;
	}

	// Print opponent guesses
	public void printOpponentGuesses() {
		oppGrid.printStatus();
	}
	
	public int countOpponentGuesses() {
		return oppGrid.countStatus();
	}


	// Print your guesses (not used - playerGrid is only used to record player's ship locations) 
	public void printMyGuesses() {
		playerGrid.printStatus();
	}

	public void chooseShipLocation(Ship s, int row, int col, int direction) {
		s.setLocation(row, col);
		s.setDirection(direction);
		playerGrid.addShip(s);
	}
	
	// Record a guess from the opponent
	public boolean recordOpponentGuess(int row, int col) {
		//marks hit in opponent's guessing grid if player's grid has a ship at specified location
		if(playerGrid.hasShip(row, col)) {
			oppGrid.markHit(row, col);
			return (true);
		}
		//otherwise (player's grid does not have a ship), marks miss in opponent's guessing grid
		else {
			oppGrid.markMiss(row, col);
		}
		return (false);
	}
	
}