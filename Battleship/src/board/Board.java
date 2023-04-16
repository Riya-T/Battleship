package board;


public class Board {
	private int[][] board = new int[8][8]; //board array
	
	
	/* Possible number codes (subject to change)
	 * 0 = nothing
	 * 1 = ship 1 (length 2)
	 * 2 = ship 2 (length 3)
	 * 3 = ship 3 (length 3)
	 * 4 = ship 4 (length 4)
	 * 5 = ship 5 (length 5)
	 * -1 = miss
	 * -2 = hit
	 */
	
	//constructor methodsA1
	public Board(int[][] s) {
		board = s;
	}
	
	//mutator methods
	public void setBoard (int[][] s) {
		board = s;
	}
	//Checks if a guess hit the opponent's boats, and updates board accordingly
	public String guess(int x, int y) { //Call on opponents board object
		try {if (board[x][y] == 0) {
			board[x][y] = -1;
			//this.pegBoard[x][y] = new Pegs(board[x][y], x, y);
			return "miss";
		}else if (board[x][y] > 0) {
			String ship  = Integer.toString(board[x][y]);
			board[x][y] = -2;
			//this.pegBoard[x][y] = new Pegs(board[x][y], x, y);
			return ship; //will return number code of the ship that just got hit
		}else {
			return "error"; //This handles the error of guessing a coordinate that's already been guessed 
							//Ensuring that the guess is within array parameters needs to be done before calling the function
		}}
		catch (Exception e) {
			return "That was not a valid coordinate. Try again";
		}
	}
	
	//accessor methods
	public int[][] getBoard () {
		return board;
	}
	
	//Checks if a specific ship is sunk
	public boolean isSunk(int ship) {
		for (int[] i : board){
			for (int j : i) {
				if (j == ship) {
					return false;
				}
			}
		}
		return true;
	}
	
	//Checks if all ships on board are sunk
	public boolean allSunk() {
		for (int[] i : board){
			for (int j : i) {
				if (j > 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	//Gives a peg at a certain location
	public Pegs getPeg(int x1, int y1, boolean pBoard) {
		return new Pegs(board[x1][y1], x1, y1, pBoard);
	}
	
}