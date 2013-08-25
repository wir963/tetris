package tetrisPieces;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;


public class TetrisPiece {

	int farLeftValue, topValue, lengthOfPiece, heightOfPiece;
	int[][] pieceBox;
	
	/* Takes the 2D array of JPanels as input parameter and has no return value
	 * Displays the piece by coloring the JPanels depending on the integers
	 * in the pieceBox
	 */
	public void display( JPanel[][] jPanel2DArray) {
		
		for (int i = 0; i < heightOfPiece; i++) {
			for (int j = 0; j < lengthOfPiece; j++) {
				switch(pieceBox[j][i]){
				case 1: jPanel2DArray[j+farLeftValue][topValue+i].setBackground(Color.cyan);
					break;
				case 2: jPanel2DArray[j+farLeftValue][topValue+i].setBackground(Color.yellow);
					break;
				case 3: jPanel2DArray[j+farLeftValue][topValue+i].setBackground(Color.blue);
					break;
				case 4: jPanel2DArray[j+farLeftValue][topValue+i].setBackground(Color.orange);
					break;
				case 5: jPanel2DArray[j+farLeftValue][topValue+i].setBackground(Color.green);
					break;
				case 6: jPanel2DArray[j+farLeftValue][topValue+i].setBackground(Color.red);
					break;
				case 7: jPanel2DArray[j+farLeftValue][topValue+i].setBackground(Color.PINK);
					break;
				}
			}
		}
		
	}
	
	/* Takes the 2D array of integers as input parameters and returns true
	 * if the piece can move down one row and false if it can't
	 */
	public Boolean moveDown(int[][] boardDisplay) {
		if (checkForVerticalRoom(boardDisplay) == true) {
			topValue++;// increments the vertical value of the piece
			return true;
		}
		else { //can't move down, which means that it's now stuck in its current location
			// add them where they are to the boardDisplay array
			for (int i = 0; i < heightOfPiece; i++) {// loops through the columns of the piece
				for (int j = 0; j < lengthOfPiece; j++) {// loops through the row of each column of the piece
					if (pieceBox[j][i] != 0) {
						boardDisplay[getFarLeftValue() + j][getTopValue() + i] = pieceBox[j][i];
					}
				}
			}
			return false;
		}
	}
	
	/* Takes the 2D array of ints indicating the values on the board as input parameter
	 * Returns a boolean as its return value; true if there is room to move down one
	 * false if there is not room
	 */
	public Boolean checkForVerticalRoom(int[][] boardDisplay) {
		ArrayList<Point> pointArray = new ArrayList<Point>();// create an array of points to hold the "vulnerable" points
		// the "vulnerable" points will be the furthest down points w/ value > 0 for each column
		Point p1;
		for (int j = 0; j < lengthOfPiece; j++) {// loops through the rows
			for (int i = heightOfPiece-1; i >= 0 ; i--) {// loops through the columns of each row, starting from the bottom
				if (pieceBox[j][i] != 0) {
					pointArray.add( new Point(j,(i+1)));// add one to the y-value because that will be its location if it moves down
					break;
				}
			}
		}
		// checks to see if the points directly below the shape have anything other than a 1
		for (int n = 0; n < pointArray.size(); n++) {
			p1 = pointArray.get(n);
			try {
				if (boardDisplay[(int) (getFarLeftValue()+p1.getX())][(int)  (getTopValue()+p1.getY())] != 0) {
					return false;
				}
			}
			catch (ArrayIndexOutOfBoundsException e) { // catches the error in case the next place is outside of the game
				//which is the same error caught below so no need for any handling code here
			}
		}
		if (getBottomValue() >= 21) {
			return false;
		}
		return true;
	}
	
	/*
	 * Begin Section - Move Left
	 */
	
	/* Takes the 2D array of ints indicating the values on the board as input parameter
	 * No return value
	 * Checks to see if there is room to move one column to the left and if there is
	 * then it moves the piece one column to the left
	 */
	public void moveLeft(int[][] boardDisplay){
		if (checkForLeftRoom(boardDisplay) == true) {
			farLeftValue--;
		}
	}
	
	/* Takes the 2D array of ints indicating the values on the board as input parameter
	 * Return value is a boolean; true if there is room to move the piece to the left
	 * and false is there isn't room
	 */
	public Boolean checkForLeftRoom(int[][] boardDisplay) {
		if (getFarLeftValue() <= 0) {
			return false;
		}
		ArrayList<Point> pointArray = new ArrayList<Point>();// create an array of points to hold the vulnerable points
		Point p1;
		// loop through the pieceBox to find the vulnerable points
		for (int i = heightOfPiece-1; i >= 0 ; i--) {// outside loop is the columns
			for (int j = 0; j < lengthOfPiece; j++) {// inside loop is each row in the column, starting at the far left
				if (pieceBox[j][i] != 0) {
					pointArray.add( new Point((j-1), i));
					break;
				}
			}
		}
		for (int n = 0; n < pointArray.size(); n++) {
			p1 = pointArray.get(n);
			try {
				if (boardDisplay[(int) (getFarLeftValue()+p1.getX())][(int)  (getTopValue()+p1.getY())] != 0) {
					return false;
				}
			}
			catch (ArrayIndexOutOfBoundsException e) { // catches the error in case the move left would place a part of the piece outside of the board
				return false;
			}
		}
		return true;
	}
	
	/*
	 * End Section - Move Left
	 */
	
	/*
	 * Begin Section - Move Right
	 */
	
	/* Takes the 2D array of ints indicating the values on the board as input parameter
	 * No return value
	 * If there's room, move right
	 */
	public void moveRight(int[][] boardDisplay){
		if (checkForRightRoom(boardDisplay) == true) {
			farLeftValue++;
		}
	}
	
	/* Takes the 2D array of ints indicating the values on the board as input parameter
	 * Return value is boolean; true if there is room; false if there isn't
	 * If there's room, move right
	 */
	public Boolean checkForRightRoom(int[][] boardDisplay) {
		if (getFarRightValue() >= 9) {
			return false;
		}
		ArrayList<Point> pointArray = new ArrayList<Point>();// create an array of points to hold the vulnerable points
		Point p1;
		// loop through the pieceBox to find the vulnerable points*** relook at this ***
		for (int i = heightOfPiece-1; i >= 0 ; i--) {// outside loop is rows starting at the bottom
			for (int j = lengthOfPiece-1; j >= 0 ; j--) {// inside loop is each block in the row starting at the right
				if (pieceBox[j][i] != 0) {
					pointArray.add( new Point((j+1), i));
					break;
				}
			}
		}
		
		for (int n = 0; n < pointArray.size(); n++) {
			p1 = pointArray.get(n);
			try {
				if (boardDisplay[(int) (getFarLeftValue()+p1.getX())][(int)  (getTopValue()+p1.getY())] != 0) {
					return false;
				}
			}
			catch (ArrayIndexOutOfBoundsException e) { // catches the error if the next place is outside of the board
				return false;
			}
		}
		return true;
	}
	
	
	/* Returns an int indicating the top of the 2D array (where 0 is the top)
	 * no input parameters
	 */
	public int getTopValue() {
		return topValue;
	}
	
	/* Returns an int indicating the top of the 2D array (where 0 is the top)
	 * no input parameters
	 */
	public int getBottomValue() {
		return (topValue + (heightOfPiece - 1));
	}
	
	/* Returns an int indicating the furthest most right of the 2D array (where 0 is the furthest left)
	 * no input parameters
	 */
	public int getFarRightValue() {
		return (farLeftValue + (lengthOfPiece - 1));
	}
	
	/* Returns an int indicating the furthest most left of the 2D array (where 0 is the furthest left)
	 * no input parameters
	 */
	public int getFarLeftValue() {
		return farLeftValue;
	}
	
	/* No input parameters
	 * Returns an int indicating the height of the tetris piece
	 */
	public int getHeight() {
		return heightOfPiece;
	}
	
	/* virtual function
	 * Usually overriden in subclasses
	 */
	public void rotate(int[][] boardDisplay) {
		
	}
	
	
}
