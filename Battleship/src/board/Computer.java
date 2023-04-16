 package board;

public class Computer {
	private static int stage = 1; //for computer guessing purposes
	private static String direction = "north"; //direction from original point that computer should guess in
	private static int origX = -1; //for storing x-coordinates for computer guessing purposes
	private static int origY = -1; //for storing y-coordinates for computer guessing purposes
	private static int counter = 2; //for determining how far from the original point a computer's guess should be
	private static boolean alreadyChanged = false; //for preventing infinite loops
	private Board player; //player's board
	public static int x = -1, y = -1;
	
	public Computer(Board s) {
		player = s;
	}
	
	public boolean guessAlgorithm () {
		boolean guessComplete = false;
		String guess = "";
		int guessInt = 0;
		while (guessComplete == false) {
			System.out.println("Hi!");
			if (stage == 1) { //guessing randomly until a ship is found
				System.out.println("stage " + stage);
				x = (int)(Math.floor(Math.random() * 8));
				y = (int)(Math.floor(Math.random() * 8));
				guess = player.guess(x, y); //guess
				if (guess.equals("error")) { //error
					System.out.println(x + ", " + y);
					System.out.println(guess);
					//Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer threw an error");
				}else if (guess.equals("miss")) { //miss
					guessComplete = true;
					System.out.println(x + ", " + y);
					System.out.println(guess);
					Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has missed ");
				}else {
					guessComplete = true;
					System.out.println(x + ", " + y);
					System.out.println("hit"); //hit
					Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has hit");
					guessInt = Integer.valueOf(guess);
					origX = x;
					origY = y;
					direction = "north";
					stage = 2;
					if (player.isSunk(guessInt)) { //hit and sunk
						System.out.println("sunk");
						Images.message.setText("<html>" + Images.message.getText() + " and sunk your ship");
						if (player.allSunk()) { //every ship is sunk, so computer wins
							System.out.println("computer wins");
							return true;
						}
					}
				}
				
			}else if (stage == 2) { //determining direction to guess in
				System.out.println("stage " + stage);
				if (direction == "north") { //north
					if (origY + 1 <= 7) {
						guess = player.guess(origX, origY + 1);
						x = origX; y = origY+1;
						if (guess.equals("error")) { //error
							System.out.println(origX + ", " + Integer.toString(origY + 1));
							System.out.println(guess);
						//	Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer threw an error");
							direction = "east";
						}else if (guess.equals("miss")) { //miss
							guessComplete = true;
							System.out.println(origX + ", " + Integer.toString(origY + 1));
							System.out.println(guess);
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has missed ");
							direction = "east";
						}else {
							guessComplete = true;
							System.out.println(origX + ", " + Integer.toString(origY + 1));
							System.out.println("hit"); //hit
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has hit");
							guessInt = Integer.valueOf(guess);
							counter = 2;
							alreadyChanged = false;
							stage = 3;
							if (player.isSunk(guessInt)) { //hit and sunk
								System.out.println("sunk");
								Images.message.setText("<html>" + Images.message.getText() + " and sunk your ship");
								stage = 1;
								if (player.allSunk()) { //every ship is sunk, so computer wins
									System.out.println("computer wins");
									return true;
								}
							}
						}
					}else {
						direction = "east";
					}
				}else if (direction == "east") { //east
					if (origX + 1 <= 7) {
						guess = player.guess(origX + 1, origY);
						x = origX+1; y = origY;
						if (guess.equals("error")) { //error
							System.out.println(Integer.toString(origX + 1) + ", " + origY);
							System.out.println(guess);
							//Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer threw an error");
							direction = "west";
						}else if (guess.equals("miss")) { //miss
							guessComplete = true;
							System.out.println(Integer.toString(origX + 1) + ", " + origY);
							System.out.println(guess);
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has missed ");
							direction = "west";
						}else {
							guessComplete = true;
							System.out.println(Integer.toString(origX + 1) + ", " + origY);
							System.out.println("hit"); //hit
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has hit");
							guessInt = Integer.valueOf(guess);
							counter = 2;
							alreadyChanged = false;
							stage = 3;
							if (player.isSunk(guessInt)) { //hit and sunk
								System.out.println("sunk");
								Images.message.setText("<html>" + Images.message.getText() + " and sunk your ship");
								stage = 1;
								if (player.allSunk()) { //every ship is sunk, so computer wins
									System.out.println("computer wins");
									return true;
								}
							}
						}
					}else {
						direction = "west";
					}
					
				}else if (direction == "west") { //west
					if (origX - 1 >= 0) {
						guess = player.guess(origX - 1, origY);
						x = origX-1; y = origY;
						if (guess.equals("error")) { //error
							System.out.println(Integer.toString(origX - 1) + ", " + origY);
							System.out.println(guess);
							//Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer threw an error");
							direction = "south";
						}else if (guess.equals("miss")) { //miss
							guessComplete = true;
							System.out.println(Integer.toString(origX - 1) + ", " + origY);
							System.out.println(guess);
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has missed ");
							direction = "south";
						}else {
							guessComplete = true;
							System.out.println(Integer.toString(origX - 1) + ", " + origY);
							System.out.println("hit"); //hit
							guessInt = Integer.valueOf(guess);
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has hit");
							counter = 2;
							alreadyChanged = false;
							stage = 3;
							if (player.isSunk(guessInt)) { //hit and sunk
								System.out.println("sunk");
								Images.message.setText("<html>" + Images.message.getText() + " and sunk your ship");
								stage = 1;
								if (player.allSunk()) { //every ship is sunk, so computer wins
									System.out.println("computer wins");
									return true;
								}
							}
						}
					}else {
						direction = "south";
					}
					
				}else { //south
					if (origY - 1 >= 0) {
						guess = player.guess(origX, origY - 1);
						x = origX; y = origY-1;
						if (guess.equals("error")) { //error
							System.out.println(origX + ", " + Integer.toString(origY - 1));
							System.out.println(guess);
							//Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer threw an error");
							stage = 1;
						}else if (guess.equals("miss")) { //miss
							guessComplete = true;
							System.out.println(origX + ", " + Integer.toString(origY - 1));
							System.out.println(guess);
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has missed ");
							stage = 1;
						}else {
							guessComplete = true;
							System.out.println(origX + ", " + Integer.toString(origY - 1));
							System.out.println("hit"); //hit
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has hit");
							guessInt = Integer.valueOf(guess);
							counter = 2;
							alreadyChanged = false;
							stage = 3;
							if (player.isSunk(guessInt)) { //hit and sunk
								System.out.println("sunk");
								Images.message.setText("<html>" + Images.message.getText() + " and sunk your ship");
								stage = 1;
								if (player.allSunk()) { //every ship is sunk, so computer wins
									System.out.println("computer wins");
									return true;
								}
							}
						}
					}else {
						stage = 1;
					}
				}
			}else { //continuing on in that direction, until the ship is sunk, switching to opposite direction if necessary (stage 3)
				System.out.println("stage " + stage);
				if (direction == "north") { //north
					if (origY + counter <= 7) {
						guess = player.guess(origX, origY + counter);
						x = origX; y = origY+counter;
						if (guess.equals("error")) { //error
							System.out.println(origX + ", " + Integer.toString(origY + counter));
							System.out.println(guess);
							//Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has thrown an error");
							if (alreadyChanged == false) {
								System.out.println(alreadyChanged);
								counter = 1;
								alreadyChanged = true;
								direction = "south";
							}else {
								System.out.println(alreadyChanged);
								direction = "north";
								stage = 2;
							}
						}else if (guess.equals("miss")) { //miss
							guessComplete = true;
							System.out.println(origX + ", " + Integer.toString(origY + counter));
							System.out.println(guess);
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has missed ");
							counter = 1;
							direction = "south";
						}else {
							guessComplete = true;
							System.out.println(origX + ", " + Integer.toString(origY + counter));
							System.out.println("hit"); //hit
							guessInt = Integer.valueOf(guess);
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has hit");
							counter++;
							if (player.isSunk(guessInt)) { //hit and sunk
								System.out.println("sunk");
								Images.message.setText("<html>" + Images.message.getText() + " and sunk your ship");
								stage = 1;
								if (player.allSunk()) { //every ship is sunk, so computer wins
									System.out.println("computer wins");
									return true;
								}
							}
						}
					}else {
						if (alreadyChanged == false) {
							System.out.println(alreadyChanged);
							counter = 1;
							alreadyChanged = true;
							direction = "south";
						}else {
							System.out.println(alreadyChanged);
							direction = "north";
							stage = 2;
						}
					}
				}else if (direction == "east") { //east
					if (origX + counter <= 7) {
						guess = player.guess(origX + counter, origY);
						x = origX+counter; y = origY;
						if (guess.equals("error")) { //error
							System.out.println(Integer.toString(origX + counter) + ", " + origY);
							System.out.println(guess);
							//Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer threw an error");
							if (alreadyChanged == false) {
								System.out.println(alreadyChanged);
								counter = 1;
								alreadyChanged = true;
								direction = "west";
							}else {
								System.out.println(alreadyChanged);
								direction = "north";
								stage = 2;
							}
						}else if (guess.equals("miss")) { //miss
							guessComplete = true;
							System.out.println(Integer.toString(origX + counter) + ", " + origY);
							System.out.println(guess);
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has missed ");
							counter = 1;
							direction = "west";
						}else {
							guessComplete = true;
							System.out.println(Integer.toString(origX + counter) + ", " + origY);
							System.out.println("hit"); //hit
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has hit");
							guessInt = Integer.valueOf(guess);
							counter++;
							if (player.isSunk(guessInt)) { //hit and sunk
								System.out.println("sunk");
								Images.message.setText("<html>" + Images.message.getText() + " and sunk your ship");
								stage = 1;
								if (player.allSunk()) { //every ship is sunk, so computer wins
									System.out.println("computer wins");
									return true;
								}
							}
						}
					}else {
						if (alreadyChanged == false) {
							System.out.println(alreadyChanged);
							counter = 1;
							alreadyChanged = true;
							direction = "west";
						}else {
							System.out.println(alreadyChanged);
							direction = "north";
							stage = 2;
						}
					}
					
				}else if (direction == "west") { //west
					if (origX - counter >= 0) {
						guess = player.guess(origX - counter, origY);
						x = origX-counter; y = origY;
						if (guess.equals("error")) { //error
							System.out.println(Integer.toString(origX - counter) + ", " + origY);
							System.out.println(guess);
							//Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer threw an error");
							if (alreadyChanged == false) {
								System.out.println(alreadyChanged);
								counter = 1;
								alreadyChanged = true;
								direction = "east";
							}else {
								System.out.println(alreadyChanged);
								direction = "north";
								stage = 2;
							}
						}else if (guess.equals("miss")) { //miss
							guessComplete = true;
							System.out.println(Integer.toString(origX - counter) + ", " + origY);
							System.out.println(guess);
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has missed ");
							counter = 1;
							direction = "east";
						}else {
							guessComplete = true;
							System.out.println(Integer.toString(origX - counter) + ", " + origY);
							System.out.println("hit"); //hit
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has hit");
							guessInt = Integer.valueOf(guess);
							counter++;
							if (player.isSunk(guessInt)) { //hit and sunk
								System.out.println("sunk");
								Images.message.setText("<html>" + Images.message.getText() + " and sunk your ship");
								stage = 1;
								if (player.allSunk()) { //every ship is sunk, so computer wins
									System.out.println("computer wins");
									return true;
								}
							}
						}
					}else {
						if (alreadyChanged == false) {
							System.out.println(alreadyChanged);
							counter = 1;
							alreadyChanged = true;
							direction = "east";
						}else {
							System.out.println(alreadyChanged);
							direction = "north";
							stage = 2;
						}
					}
					
				}else { //south
					if (origY - counter >= 0) {
						guess = player.guess(origX, origY - counter);
						x = origX; y = origY-counter;
						if (guess.equals("error")) { //error
							System.out.println(origX + ", " + Integer.toString(origY - counter));
							System.out.println(guess);
							//Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer threw an error");
							if (alreadyChanged == false) {
								System.out.println(alreadyChanged);
								counter = 1;
								alreadyChanged = true;
								direction = "north";
							}else {
								System.out.println(alreadyChanged);
								direction = "north";
								stage = 2;
							}
						}else if (guess.equals("miss")) { //miss
							guessComplete = true;
							System.out.println(origX + ", " + Integer.toString(origY - counter));
							System.out.println(guess);
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has missed ");
							counter = 1;
							direction = "north";
						}else {
							guessComplete = true;
							System.out.println(origX + ", " + Integer.toString(origY - counter));
							System.out.println("hit"); //hit
							guessInt = Integer.valueOf(guess);
							Images.message.setText("<html>" + Images.message.getText() + "<br> and the computer has hit");
							counter++;
							if (player.isSunk(guessInt)) { //hit and sunk
								System.out.println("sunk");
								Images.message.setText("<html>" + Images.message.getText() + " and sunk your ship");
								stage = 1;
								if (player.allSunk()) { //every ship is sunk, so computer wins
									System.out.println("computer wins");
									return true;
								}
							}
						}
					}else {
						if (alreadyChanged == false) {
							System.out.println(alreadyChanged);
							counter = 1;
							alreadyChanged = true;
							direction = "north";
						}else {
							System.out.println(alreadyChanged);
							direction = "north";
							stage = 2;
						}
					}
				}
			}
		}
		return false;
	}
}