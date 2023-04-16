package board;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Images {

	public static JLabel water, water2, message, startMess; //message is during game, startMess is at set up
	private static JLabel board1Top, board2Top, board1x, board1y, board2x, board2y; //Top are headers, x is horizontal, y is vertical 
	private JTextField textField;
	private JTextField attackInput;
	private JButton setupButton, start, addShip;
	public static JButton attack;
	public int wait = -1, wait2 = -1;
	public int step = 1, z = 0, pegsDown = 0;
	public String [] whichShip = {"destroyer", "submarine", "cruiser", "battleship", "carrier"};
	public String compStr = "";
	/**
	 * @wbp.parser.entryPoint
	 */
	public void makeGUI(int step) {
		
		System.out.print("START");
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setSize(2000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		int[][]board = new int[][] {{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
		Board player = new Board(board); 
		int [] lenShip = {2, 3, 3, 4, 5};
		Image image = new ImageIcon(this.getClass().getResource("/water.jpg")).getImage();
		Pegs [] pegBoard1 = new Pegs[64];
		Pegs [] pegBoard2 = new Pegs[64];
		//int pegsDown = 0;
		
		//"Your ships" on top of player board
		JLabel board1Top = new JLabel("Your Ships");
		board1Top.setFont(new Font("Dialog", Font.BOLD, 50));
		board1Top.setBounds(300, 30, 500, 85);
		
		//computer board, as seen by player
		water2 = new JLabel("");
		water2.setBounds(1100, 70, 550, 520); 
		Image image2 = new ImageIcon(this.getClass().getResource("/water2.jpg")).getImage();
		water2.setIcon(new ImageIcon(image2));
		
		//Label on top of computer board
		JLabel board2Top = new JLabel("Opponent's Ships");
		board2Top.setFont(new Font("Dialog", Font.BOLD, 40));
		board2Top.setBounds(1140, 40, 480, 45);
		
		//Your Turn label
		JLabel InputLbl = new JLabel("Your Turn :");
		InputLbl.setFont(new Font("Dialog", Font.BOLD, 20));
		InputLbl.setBounds(1020, 610, 240, 40);
		
		//This is the main way of talking to the player, to change it, use message.setText("YOUR TEXT HERE"); (If in another class, do Images.message.etc)
		message = new JLabel("<html>You go first, input a coordinate (Ex: A1, D3, H7) then click 'GO' to shoot<br>A red x means a hit and a white x means a miss");
		message.setFont(new Font("Dialog", Font.BOLD, 16));
		message.setBounds(1020, 710, 500, 100);
		
		//This is where the player writes things during the game
		attackInput = new JTextField();
		attackInput.setText("");
		attackInput.setBounds(1170, 615, 80, 30);
		attackInput.setColumns(2);
		
		//Welcome, and start setup
		JLabel welcome = new JLabel("<html>Hello, to start playing Battleship:<br> We need to set up your ships");
		welcome.setFont(new Font("Dialog", Font.BOLD, 16));
		welcome.setBounds(1000, 20, 400, 100);
		
		//Display message during start setup
		startMess = new JLabel("");
		startMess.setFont(new Font("Dialog", Font.PLAIN, 14));
		startMess.setBounds(1000, 20, 400, 200);
		
		//This is where the player inputs the two coordinates
		JTextField setupInput = new JTextField("");
		setupInput.setBounds(1000, 200, 50, 25);
		
		JTextField setupInput2 = new JTextField("");
		setupInput2.setBounds(1070, 200, 50, 25);
		
		//Ship rectangles
		//To make a single square filled  73, 73   i
		//To get a ship to start at A1   190, 200  increment by 78
		int mult = 78;
		JLabel destroyer2 = new JLabel(" ");
		JLabel submarine3 = new JLabel(" ");
		JLabel cruiser3 = new JLabel(" ");
		JLabel battleship4 = new JLabel(" ");
		JLabel carrier5 = new JLabel(" ");
		
		JLabel [] shipObj = new JLabel[] {destroyer2, submarine3, cruiser3, battleship4, carrier5};
		
		//int[][]board2 = new int[][] {{5,5,5,5,5,0,0,0},{4,4,4,4,0,0,0,0},{3,3,3,0,0,0,0,0},{2,2,2,0,0,0,0,0},{1,1,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0}};
		int[][] board2 = new int [8][8];
		board2 = Battleship.computerSetup(board2);
		Board computer = new Board(board2); //computer board
		Computer ai = new Computer(player);
		
		//Peg
		//Pegs peg1 = new Pegs(-1, 7, 7, true);	// A1 white
	//	Pegs peg2 = new Pegs(-2, 0, 1);	// A2 red
	//	Pegs peg3 = new Pegs(-2, 1, 0);	// B1 red
	//	Pegs peg4 = new Pegs(-1, 7, 7);  // H8 white
		
		attack = new JButton("GO");
		attack.setBounds(1255, 615, 80, 25); 
		attack.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//takes in and parses the input when player clicks "GO" button
							String input = attackInput.getText();
							int [] pGuess = parseInput(input, 1);
							if (pGuess[0] != -1 && pGuess[1] != -1) {
								
								pegBoard1[pegsDown] = Battleship.playerTurn(pGuess[0], pGuess[1], computer);
								panel.add(pegBoard1[pegsDown].getJLabel());
								panel.setComponentZOrder(pegBoard1[pegsDown].getJLabel(), 0);
								
								if (!message.getText().equals("Error, that is not a valid guess. Try again")) {
									pegBoard2[pegsDown] = Battleship.computerTurn(ai, player);
									panel.add(pegBoard2[pegsDown].getJLabel());
									panel.setComponentZOrder(pegBoard2[pegsDown].getJLabel(), 0);
									pegBoard2[pegsDown].getJLabel().setVisible(true);
									attackInput.setText("");
								//panel.add(peg1.getJLabel());
								//peg1.getJLabel().setVisible(true);
									pegsDown ++;
									panel.setVisible(false);
									panel.setVisible(true);
								}
							} else {
								message.setText("Error, invalid guess. Try again.");
							}
						}
					}
			);

		JButton next = new JButton("Next");
		next.setBounds(1000, 350, 80, 25); 
		next.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							panel.add(water2);
							panel.add(board2Top);
							panel.add(InputLbl);
							panel.add(attackInput);
							panel.add(attack);
							panel.add(message);
							
							water2.setVisible(true);
							board2Top.setVisible(true);
							InputLbl.setVisible(true);
							attackInput.setVisible(true);
							attack.setVisible(true);
							message.setVisible(true);
							
							startMess.setVisible(false);
							next.setVisible(false);
							welcome.setVisible(false);
							panel.setVisible(false);
							panel.setVisible(true);
							//frame.setVisible(false);	//this and the next line do serve a purpose
						//	panel.setVisible(true);
							//frame.setVisible(true);	
						}
					}
			);
		 start = new JButton("Start");
		start.setBounds(1000, 100, 80, 25);
		start.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							start.setVisible(false);
							welcome.setVisible(false);
							setupInput.setVisible(true);
							setupInput2.setVisible(true);
							addShip.setVisible(true);
						//	peg1.getJLabel().setVisible(true);
							destroyer2.setVisible(true);
							//setupButton.setVisible(true);
							
							//System.out.print("A");
							startMess.setVisible(true);
							startMess.setText("<html>Enter two coordinates, for the two ends of the ship"
									+ " When you enter a coordinate, make sure you have a capital letter first and a number second."
								+ "<br> For example: 'A1' and 'A2' or 'G8' and 'H8'. Then click 'Add Ship'."
								+ "<br><br>Enter a coordinate for the destroyer of length 2:");
							//System.out.println("C");
							
							
						}
					}
					
			);
		
		addShip = new JButton("Add Ship");
		addShip.setBounds(1150, 200, 100, 25);
		addShip.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							try {
							String space1 = setupInput.getText();
							String space2 = setupInput2.getText();
							setupInput.setText("");
							setupInput2.setText("");
							//System.out.println("space1 is " + space1 + " and space2 is " + space2);
							int [] temporary = parseInput(space1, 0); //WRONG
							int xS = temporary[0], yS = temporary[1]; //for ease of reading/typing wait2 is 1-8  and wait is A-H //S is for start E is for end
							//System.out.println("xS is " + xS + " and yS is " + yS +"!");
							int [] temporary2 = parseInput(space2, 0); //WRONG
							if (xS == -1 || yS == -1) {
								//System.out.println("A");
								throw new ArrayIndexOutOfBoundsException();
							}
							else {
								int xE = temporary2[0], yE = temporary2[1]; //for ease of reading/typing wait2 is 1-8  and wait is A-H
								//System.out.println("xE is " + xE + " and yE is " + yE + "!");
								if ((xS < 0 || xS > 8) || (xE < 0 || xE > 8) || (yS < 0 || yS > 8) || (yE < 0 || yE > 8)) {
									//System.out.println("B");
									throw new ArrayIndexOutOfBoundsException();
								}
								else {
									if (xS == xE) { 		//this means the ship is horizontal
										if (yS > yE) {
											int yTemp = yS;
											yS = yE;
											yE = yTemp;
										}
										if ((yE - yS + 1) != lenShip[z]) {
											//System.out.println("C");
											throw new ArrayIndexOutOfBoundsException();
										}
										else { //getBoard setBoard
											int [][] b = player.getBoard(); 
											//System.out.println("Got here horiz ship, board");
											for (int i = 0; i < lenShip[z]; i++) {
												if (board[xS][yS+i] != 0) {
													//System.out.println("D it was " + board[xS][yS+i] );
													throw new ArrayIndexOutOfBoundsException();
												}
												b[xS][yS+i] = z+1; 
												//System.out.println(b[xS][yS+i]);
											}
											player.setBoard(b); //waits until there was no exception to set the actual board
											shipObj[z].setBounds(190+(mult*yS), 200+(mult*xS), 73+(mult*(lenShip[z]-1)), 73);
											//z++;
										}
									}
									else {
										if (yS == yE) { 	//this means the ship is vertical
											if (xS > xE) {
												int xTemp = xS;
												xS = xE;
												xE = xTemp;
											}
											if ((xE - xS + 1) != lenShip[z]) {
												//System.out.println("E");
												throw new ArrayIndexOutOfBoundsException();
											}
											else { //getBoard setBoard
												int [][] b = player.getBoard(); 
												//System.out.println("Got here vert board");
												for (int i = 0; i < lenShip[z]; i++) {
													if (board[xS+i][yS] != 0) {
														//System.out.println("F");
														throw new ArrayIndexOutOfBoundsException();
													}
													b[xS+i][yS] = z+1; 
													//System.out.println(b[xS+i][yS]);
												}
												player.setBoard(b); //waits until there was no exception to set the actual board
												shipObj[z].setBounds(190+(mult*yS), 200+(mult*xS), 73, 73+(mult*(lenShip[z]-1)));
												//cut from here
												//z++;
												//System.out.println("Z is " + z);
											}
									} else  {
										//System.out.println("Diagonal");
										throw new ArrayIndexOutOfBoundsException();
									}
									
									//System.out.println("Does the program ever get here? " + z);
									
									}
									
								}
								shipObj[z].setBackground(Color.GRAY);
								shipObj[z].setOpaque(true);
								shipObj[z].setVisible(true);
								panel.setComponentZOrder(shipObj[z], 3);
								z++;
								//System.out.println("Should be visible. Shoot is it? z is " + z);
								if(z == 5) {
									startMess.setText("All the ships are placed! Click 'Next' to continue");
									//System.out.println("All ships placed");
									panel.add(next);
									next.setVisible(true);
									addShip.setVisible(false);
									setupInput.setVisible(false);
									setupInput2.setVisible(false);
								} else {
								if (z < 5 && z > -1) {
									//System.out.print("z is " + z);
									startMess.setText("<html>Enter two coordinates, for the two ends of the ship. "
											+ "<br>When you enter a coordinate, make sure you have a capital letter first and a number second."
										+ "<br> For example: 'A1' and 'A2' or 'G8' and 'H8'. Then click 'Add Ship'."
										+ "<br><br>Enter a coordinates for the " + whichShip[z] + " of length " + lenShip[z] + ":");
								}
								else {
									//System.out.println("G");
									throw new ArrayIndexOutOfBoundsException();
								}
								} 
								
							}
						}
					 catch (ArrayIndexOutOfBoundsException e13) {
						System.out.println("Error, wrong input " + e13.toString());
						startMess.setText("<html>Error, make sure your coordinates have one uppercase letter first and a number second.<br> "
								+ "The letter must be between A and H (inclusive) and the number needs to be between 1 and 8 (inclusive)"
								+ "<br> The ship should cover the length number of spaces, and may not overlap other ships"
								+ "<br> Try again for the " + whichShip[z] + " of length " + lenShip[z] + ":");
					 }catch (Exception e) {
						 System.out.print("Something went wrong "  + e.toString());
						 startMess.setText("Something went wrong " + e.toString());
					 }
						}
						}
					
			);
		
		/*setupButton = new JButton("Set");
		setupButton.setBounds(1150, 200, 80, 25);
		setupButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							String input = setupInput.getText();
							parseInput(input, 0);
						}
					}
					
			);*/
		panel.add(board1Top);
		panel.add(welcome);
		panel.add(start);
		panel.add(setupInput);
		panel.add(setupInput2);
		//panel.add(destroyer);
		panel.add(start);
		
		for (int y = 0; y < 5; y++) {
			panel.add(shipObj[y]);
			//shipObj[y].setVisible(false);
		}
		
		panel.add(startMess);
		panel.add(addShip);
		//panel.add(peg1.getJLabel()); 			//peg
		//panel.add(peg2.getJLabel());
		//panel.add(peg3.getJLabel());
		//panel.add(peg4.getJLabel());
		
		
		//player board
		water = new JLabel("");
		water.setBounds(105, -30, 1000, 1000);
		water.setIcon(new ImageIcon(image));
		
		//adds the gui stuff to the panel
		panel.add(water);
		//panel.add(setupButton);
		setupInput.setVisible(false);
		setupInput2.setVisible(false);
		addShip.setVisible(false);
		//setupButton.setVisible(false);
		panel.setVisible(true);
		frame.setVisible(true);
		
			
	}
	
	public int[] parseInput(String s, int step) {
		int [] i = new int [2];
		
		if (s.length() == 2) {
			String letters = "ABCDEFGH";
			for (int j = 0; j < 8; j++) {
				if (s.charAt(0) == letters.charAt(j)) {
					wait = j;
					//System.out.println(j + " = x");
					wait2 = 0 + s.charAt(1) - 1; 
					//System.out.println(s.charAt(1) + " = y");
					i[0] = j; 		//x
					int t = Character.getNumericValue(s.charAt(1))-1;
					i[1] = t; 	//y
					return i;
				}
			}
			i[0] = -1;
			i[1] = -1;
		}
		else {
			i[0] = -1;
			i[1] = -1;
		}
		
		return i;
	}
	
	public String numToStr(int num) {
		String alph = "ABCDEFGH";
		return "" + alph.charAt(num-1);		
	}
	public void change(int num) {
		step = num;
	}
	
	
}