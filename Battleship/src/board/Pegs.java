package board;
import javax.swing.*;
import java.awt.*;

public class Pegs {

	JLabel peg = new JLabel("x");
	
	//this class makes and draws the red and white pegs
	//takes in color and position on board
	//calculates x and y and puts x and y on the board
	
	//if color = 1 then white, color = 2 is red
	//xSpot and ySpot are 0-7
	public Pegs(int color, int xSpot, int ySpot, boolean pBoard) {
		int xCoor, yCoor;
		if (color == -1) {
			this.peg.setForeground(Color.WHITE);
		}
		if (color == -2) {
			this.peg.setForeground(Color.RED);
		}
		if (pBoard) {
			xCoor = 213 + ySpot*78;	//note: these are very exact calculations. Don't change the numbers
			yCoor = 212 + xSpot*78;
			//System.out.println("Is there an z?");
		}
		else {
			xCoor = 1164 + ySpot*50;
			yCoor = 160 + xSpot*50;
			//System.out.println("X exists");
		}
		//System.out.print("Add peg at x " + xSpot + ", y " + ySpot);
		this.peg.setBounds(xCoor, yCoor, 40, 40);
		this.peg.setFont(new Font("Dialog", Font.PLAIN, 35));
	}
	public JLabel getJLabel() {
		return this.peg;
	}
	
}
