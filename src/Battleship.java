
import java.util.Scanner;

public class Battleship {
	
	Player p1 = new Player();
	Player p2 = new Player();
	Scanner input = new Scanner(System.in);
	
	
	// asks for input, only accepts letters a to j and turns it into a capital letter
	// if input is invalid, asks player for another letter 
	public String askForRow() {
		String row = input.next();
		row = row.replaceAll("//s","");
		row = row.toUpperCase();
		
		switch (row){
		case "A":
			return "A";
		case "B":
			return "B";
		case "C":
			return "C";
		case "D":
			return "D";
		case "E":
			return "E";
		case "F":
			return "F";
		case "G":
			return "G";
		case "H":
			return "H";
		case "I":
			return "I";
		case "J":
			return "J";
		default:
			System.err.println("Invalid row.");
			row = askForRow();
		}
		return row;
	}
	
	// converts the string A to J into its index number 
	public int rowToNum(String row) {
		switch (row){
		case "A":
			return 0;
		case "B":
			return 1;
		case "C":
			return 2;
		case "D":
			return 3;
		case "E":
			return 4;
		case "F":
			return 5;
		case "G":
			return 6;
		case "H":
			return 7;
		case "I":
			return 8;
		case "J":
			return 9;
		default:
			return 10;
		}
		
	}
	
	// puts together askForRow and rowToNum to get a valid letter input
	// and then converts it to its index number 
	public int getRow() {
		String rowString = askForRow();
		return rowToNum(rowString);
	}
	
	// same thing as getRow but for setting up ships specifically
	public int getRow(int length) {
		String rowString = askForRow();
		int r=  rowToNum(rowString);
		if (r + length > 10) {
			System.err.println("Your ship goes off the board. Please enter a new row.");
			 r=  getRow(length);
		}
		return r;
	}
	
	
	// asks for input for column number (1 to 10) 
	// subtracts 1 to convert to index value (0 to 9) 
	public int askForCol() {
		int col=input.nextInt() -1;
		if(col > 9 || col < 0) {
			System.err.println("Invalid column.");
			col = askForCol();
		}
		return col;
	}
	
	// same thing as askForCol but for setting up ships
	public int askForCol(int length) {
		int col=input.nextInt() -1;
		if(col > 9 || col < 0) {
			System.err.println("Invalid column.");
			col = askForCol();
		} else if ( length + col > 10) {
			System.err.println("Your ship goes off the board. Please enter a new column.");
			col = askForCol(length);
		}
		return col;
	}
	
	// asks for input for direction (0 - horizontal, 1 - vertical)
	public int askForDirection() {
		boolean go = true;
		int direction = -1;
		while (go) {
			while (!input.hasNextInt()) {
				System.err.println("Please select 0 for horizontal or 1 for vertical.");
				input.nextLine();
			}
			direction = input.nextInt();
			if (direction !=1 && direction!= 0) {
				System.err.println("Please select 0 for horizontal or 1 for vertical.");
				direction = askForDirection();
			} 
			if (direction == 1 || direction ==0) {
				break;
			}
		}
		return direction;
	}
	
	// asks for guess on where to hit on opponent's ships
	public void askForGuess(Player opp) {
		
		System.out.println("Enter the row you wish to guess.");
		int row = getRow();
		System.out.println("Enter the column you wish to guess.");
		int col = askForCol();
		
		if (opp.recordOpponentGuess(row, col)) {
			System.out.println("Hit!");
		} else {
			System.out.println("Miss.");
		}
				
		
	}
	
	// checks if ship does not overlap
	public boolean checkValidity(int r, int c, int l, int d, Player p) {
		if (d == 0) {
			for (int i = c; i<c+l; i++) {
				if (p.hasShip(r, i)) {
					return false;
				}
			}
		} else if (d ==1) {
			for (int i= r; i<r+l; i++) {
				if (p.hasShip(i, c)) {
					return false;
				}
			}
		}
		return true; 
	}
	
	

	// sets up a player's ships
	public void setShips(Player p, int[] shipLengths) {
		for (int length : shipLengths) {
			p.printMyShips();
			boolean go = true;
			
			while (go) {
			
				System.out.println("You are placing your ship of length " + length + " down.");
				System.out.println("Would you like your ship to be horizontal (0) or vertical (1)?");
				int direction = askForDirection();
				boolean horiz = (direction==0)? true:false;
				System.out.println("Enter the row you wish to put your ship in.");
				int row; 
				if (!horiz) {
					 row = getRow(length);
				} else {
					 row = getRow();
				}
				System.out.println("Enter the column you wish to put your ship in.");
				int col; 
				if (!horiz) {
					 col = askForCol();
				} else {
					 col = askForCol(length);
				}
				
				if (checkValidity(row, col, length, direction, p)) {
	
					Ship s = new Ship(length);
					p.chooseShipLocation(s, row, col, direction);
					go = false;
				} else {
					System.err.println("Your ship is overlapping another ship. Please try again.");
					
				}
			}
		}
		System.out.println("All your ships have been placed!");
		p.printMyShips();		
	}
	
	// checks if the number of X's equals the total length of all the ships
	// returns winner number if there is a winner, otherwise return 0
	public int checkWin(int[] shipLengths, Player p1, Player p2) {
		
		int count1 = p2.countOpponentGuesses();;
		int count2 = p1.countOpponentGuesses();;
		
		int shipLength = 0;
		for (int length : shipLengths) {
			shipLength+= length;
		}
		
		if (count1 == shipLength) {
			return 1;
		} else if (count2 == shipLength) {
			return 2;
		} else {
			return 0;
		}
	}
	
	// method that brings everything together to play game
	public void playGame() {

		// all the ships available 
		int[] shipLengths =  {2, 3, 3, 4, 5};
		
		int currentPlayer = 1;
		
		//setting up ships
		System.out.println("Setting up ships for player 1");
		setShips(p1, shipLengths);
		System.out.println("Setting up ships for player 2");
		setShips(p2, shipLengths);
		
		
		// pre-made boards for easier testing *note: must comment out earlier code for actual setup
		/*
		int[] shipLengths =  {2};
		int row = 0;
		int col = 0;
		int direction =0;
		for (int length : shipLengths) {
			Ship s = new Ship(length);
			p1.chooseShipLocation(s, row, col, direction);
			row++;
		}
		
		 
		row = 0;
		for (int length : shipLengths) {
			Ship s = new Ship(length);
			p2.chooseShipLocation(s, row, col, direction);
			row++;
		}
		*/
		
			
		

		// playing the game
		
		boolean keepPlaying = true;
		while (keepPlaying) {
			
			// sets current player and opponent player to the Player object
			Player currentPlayerObj = (currentPlayer == 1) ? p1:p2;
			Player oppPlayerObj = (currentPlayer == 1) ? p2:p1;
			
						
			
			System.out.println("Player "  + currentPlayer + "'s turn: ");
			
			// prints out the guesses the current player made on the opponent player's grid 
			oppPlayerObj.printOpponentGuesses();
			
			// asks for guess 
			askForGuess(oppPlayerObj);
			
			
			// checks for win
			int win = checkWin(shipLengths, p1, p2);
			if (win == 1) {
				keepPlaying = false;
				System.out.println("Player 1 has won the game!");
				break;
			} else if (win==2) {
				keepPlaying = false;
				System.out.println("Player 2 has won the game!");
				break;
			}
			
			// prints out the guesses the current player made on the opponent player's grid again
			oppPlayerObj.printOpponentGuesses();
			
			
			// changes current player
			currentPlayer = (currentPlayer ==1) ? 2:1;

			
		}
	}
}