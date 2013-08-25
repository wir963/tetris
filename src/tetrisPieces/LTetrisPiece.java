package tetrisPieces;

/* The L Tetris piece is the one that looks like an L
 * for one of its rotations
 */
public class LTetrisPiece extends TetrisPiece {

	int numberOfRotations = 0;
	
	/* Default constructor
	 * Creates piece as a 2D array with each index either 0 or 4 depending on whether it represents a piece
	 * Keep track of piece using left and top value (x,y)
	 */
	public LTetrisPiece() {
		lengthOfPiece = 3;
		heightOfPiece = 2;
		pieceBox = new int[lengthOfPiece][heightOfPiece];
		
		for (int i = 0; i < heightOfPiece; i++) {//outer loop loops through the rows
			for (int j = 0; j < lengthOfPiece; j++) {// inner loop loops through the columns
				if ((i == 0 && j == 0)||(i == 0 && j == 1)) { // two values in the 2D array don't comprise the piece
					pieceBox[j][i] = 0;
				}
				else {
					pieceBox[j][i] = 4;
				}
			}
		}
		
		farLeftValue = 3;
		topValue = 0;
	}
	
	/* Takes the 2D array of ints indicating the values on the board as input parameter and no return value
	 * Rotates the piece if possible and keeps a counter to remember which rotation is next
	 */
	public void rotate(int[][] boardDisplay) {
		if (numberOfRotations % 4 == 0) {// the first rotation
			try {
				if ((boardDisplay[getFarLeftValue()+1][getTopValue()] != 0)||(boardDisplay[getFarLeftValue()+1][getTopValue()+2] != 0)
						||(boardDisplay[getFarLeftValue()+2][getTopValue()+2] != 0)){
					// checks the points that will be filled by the rotation to make sure they're empty
					return;
				}
			}
			catch (ArrayIndexOutOfBoundsException e){
				return;
			}
			lengthOfPiece = 2;
			heightOfPiece = 3;
			pieceBox = new int[lengthOfPiece][heightOfPiece];// create a new 2D array for the piece
			
			for (int i = 0; i < heightOfPiece; i++) {//outer loop loops through the rows
				for (int j = 0; j < lengthOfPiece; j++) {// inner loop loops through the columns
					if ((i == 0 && j == 1)||(i == 1 && j == 1)) {
						pieceBox[j][i] = 0;
					}
					else {
						pieceBox[j][i] = 4;
					}
					
				}
			}
			farLeftValue++;
			numberOfRotations++;
		}
		else if (numberOfRotations % 4 == 1) {// the second rotation
			try {
				if ((boardDisplay[getFarLeftValue()-1][getTopValue()+1] != 0)||(boardDisplay[getFarLeftValue()+1][getTopValue()+1] != 0)
						||(boardDisplay[getFarLeftValue()-1][getTopValue()+2] != 0)){
					// checks the points that will be filled by the rotation to make sure they're empty
					return;
				}
			}
			catch (ArrayIndexOutOfBoundsException e){
				return;
			}
			lengthOfPiece = 3;
			heightOfPiece = 2;
			pieceBox = new int[lengthOfPiece][heightOfPiece];
			
			for (int i = 0; i < heightOfPiece; i++) {//outer loop loops through the rows
				for (int j = 0; j < lengthOfPiece; j++) {// inner loop loops through the columns
					if ((i == 1 && j == 1)||(i == 1 && j == 2)) {
						pieceBox[j][i] = 0;
					}
					else {
						pieceBox[j][i] = 4;
					}
					
				}
			}
			farLeftValue--;
			topValue++;
			numberOfRotations++;
		}
		else if (numberOfRotations % 4 == 2) {// the third rotation
			try {
				if ((boardDisplay[getFarLeftValue()][getTopValue()-1] != 0)||(boardDisplay[getFarLeftValue()+1][getTopValue()+1] != 0)
						||(boardDisplay[getFarLeftValue()+1][getTopValue()-1] != 0)){
					// checks the points that will be filled by the rotation to make sure they're empty
					return;
				}
			}
			catch (ArrayIndexOutOfBoundsException e){
				return;
			}
			lengthOfPiece = 2;// sets new length
			heightOfPiece = 3;// sets new height
			pieceBox = new int[lengthOfPiece][heightOfPiece];// creates new 2D array
			
			for (int i = 0; i < heightOfPiece; i++) {//outer loop loops through the rows
				for (int j = 0; j < lengthOfPiece; j++) {// inner loop loops through the columns
					if ((i == 1 && j == 0)||(i == 2 && j == 0)) {// the indices on the 2D array that aren't part of the piece
						pieceBox[j][i] = 0;
					}
					else {
						pieceBox[j][i] = 4;
					}
					
				}
			}
			topValue--;
			numberOfRotations++;
		}
		else {
			try {
				if ((boardDisplay[getFarLeftValue()+2][getTopValue()] != 0)||(boardDisplay[getFarLeftValue()][getTopValue()+1] != 0)
						||(boardDisplay[getFarLeftValue()+2][getTopValue()+1] != 0)){
					// checks the points that will be filled by the rotation to make sure they're empty
					return;
				}
			}
			catch (ArrayIndexOutOfBoundsException e){
				return;
			}
			lengthOfPiece = 3;
			heightOfPiece = 2;
			pieceBox = new int[lengthOfPiece][heightOfPiece];
			
			for (int i = 0; i < heightOfPiece; i++) {//outer loop loops through the rows
				for (int j = 0; j < lengthOfPiece; j++) {// inner loop loops through the columns
					if ((i == 0 && j == 0)||(i == 0 && j == 1)) {// the indices on the 2D array that aren't part of the piece
						pieceBox[j][i] = 0;
					}
					else {
						pieceBox[j][i] = 4;
					}
					
				}
			}
			numberOfRotations++;
		}
	}
}
