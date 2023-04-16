package board;
//import TerminalIO.KeyboardReader;
public class Battleship {
		
		//int[][]board2 = new int[][] {{5,5,5,5,5,0,0,0},{4,4,4,4,0,0,0,0},{3,3,3,0,0,0,0,0},{2,2,2,0,0,0,0,0},{1,1,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
		//public  Board computer = new Board(board2); //computer board
	public static void main(String[] args) {
		//This section is just to generate a test board, and will be replaced with with actual ship placement
		int[][]board = new int[][] {{0,0,0,1,2,3,4,5},{0,0,0,1,2,3,4,5},{0,0,0,0,2,3,4,5},{0,0,0,0,2,3,4,5},{0,0,0,0,0,0,0,5},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
		Board player = new Board(board); //player board
		int[][]board2 = new int[8][8];

		//public Board computer = new Board(board2); //computer board
		//End of test board generation
		//Right now, this program takes input from console, and outputs to console. This will be changed to a GUI interface.
		//KeyboardReader reader = new KeyboardReader(); //temporary keyboard reader object
		
		Images a = new Images();
		a.makeGUI(0); 		// starts the gui
		
		//Computer ai = new Computer(player);
		
		boolean computerWon = false; //checks if computer won at the end of its turn
		String winner = "none"; //will change when there is a winner
		boolean playerTurn = true; //checks whose turn it is
		boolean guessComplete = false; //checks if turn is complete, or if there was an error
		//int x = -1; //x coordinate of player's guess
		//int y = -1; //y coordinate of player's guess
		String guess; //outputs result of guess
		int guessInt; //converts guess to an integer, for checking if a ship sunk
		
		
		
		
		
	}
	public static int[][] computerSetup(int[][]board) { //argument should be an empty array of length [8][8]
		int shipLength = 0; //length of ship
		int vert; //horizontal or vertical
		for (int shipNum = 1; shipNum <= 5; shipNum++) { //repeat for each ship
			if (shipNum == 1) { //determines ship length for each ship
				shipLength = 2;
			}else if (shipNum == 2 || shipNum == 3) {
				shipLength = 3;
			}else if (shipNum == 4) {
				shipLength = 4;
			}else if (shipNum == 5) {
				shipLength = 5;
			}
			vert = (int)(Math.floor(Math.random() * 2)); //decides if horizontal or vertical
			if (vert == 0) { //if horizontal
				boolean done = false;
				while (done == false) {
					int beginX = (int)(Math.floor(Math.random() * 8)); //picks starter coordinate
					int beginY = (int)(Math.floor(Math.random() * 8));
					int endX = beginX + shipLength; //picks end x coordinate
					if (endX > 8) { //checks if ship will go off map
						continue;
					}else {
						for (int i = beginX; i < endX; i++) { //attempts to place ship
							if (board[i][beginY] == 0) {
								done = true;
							}else {
								done = false;
								break;
							}
						}
						if (done == true) {
							for (int i = beginX; i < endX; i++) { //places ship
								board[i][beginY] = shipNum;
							}
						}
					}
				}
			}else { //if vertical
				boolean done = false;
				while (done == false) {
					int beginX = (int)(Math.floor(Math.random() * 8)); //picks starter coordinate
					int beginY = (int)(Math.floor(Math.random() * 8));
					int endY = beginY + shipLength; //picks end y coordinate
					if (endY > 8) { //checks if ship will go off map
						continue;}
					else {
							for (int i = beginY; i < endY; i++) { //checks if ship placement is valid
								if (board[beginX][i] == 0) {
									done = true;
								}else {
									done = false;
									break;
								}
							}
							if (done == true) {
								for (int i = beginY; i < endY; i++) { //places ship
									board[beginX][i] = shipNum;
								}
							}
						}
					}
				}
			}
			return board;
		}


	public static Pegs playerTurn(int x, int y, Board computer) {
		String guess; //outputs result of guess
		int guessInt; //converts guess to an integer, for checking if a ship sunk
		String output = "";
		
		guess = computer.guess(x, y); //guess
		if (guess.equals("error")) { //error
			//System.out.println(guess);
			output = "Error, that is not a valid guess. Try again";
		}else  {if (guess.equals("miss")) { //miss
			//guessComplete = true;
			//System.out.println(guess);
			output = "You missed";
		}else {
			//guessComplete = true;
			//System.out.println("hit"); //hit
			output = "You hit ";
			guessInt = Integer.valueOf(guess);
			if (computer.isSunk(guessInt)) { //hit and sunk
				//System.out.println("sunk");
				output += "and sunk a ship";
				if (computer.allSunk()) { //every ship is sunk, so player wins
					//System.out.println("player wins");
					//winner = "player";
					output += " Congratulations! You win!";
					Images.attack.setVisible(false);
				}
			}
		}}
		Images.message.setText(output);
		return computer.getPeg(x, y, false);
		
		
	}
	public static Pegs computerTurn(Computer ai, Board player) {
		boolean computerWon =  ai.guessAlgorithm();
		if (computerWon) {
			Images.message.setText("<html>The computer has won, you mortals were no match <br>for its brilliance. Mwahahahahaha");
			Images.attack.setVisible(false);
		}
		//System.out.println("Got here, the computer guessed " + Computer.x + ". " + Computer.y + " and we're here now");
		return player.getPeg(Computer.x, Computer.y, true);
		
	}
	
}