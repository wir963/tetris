package tetrisPieces;

/*
 * This is the tetris piece that is shaped like a T
 */
public class TTetrisPiece extends TetrisPiece {

	int numberOfRotations = 0;
	
	/* Default constructor
	 * Creates piece as a 2D array with each index either 0 or 5 depending on whether it represents a piece
	 * Keep track of piece using left and top value (x,y)
	 */
	public TTetrisPiece() {
		lengthOfPiece = 3;
		heightOfPiece = 2;
		pieceBox = new int[lengthOfPiece][heightOfPiece];
		
		for (int i = 0; i < heightOfPiece; i++) {//outer loop loops through the rows
			for (int j = 0; j < lengthOfPiece; j++) {// inner loop loops through the columns
				if ((i == 0 && j == 0)||(i == 0 && j == 2)) {
					pieceBox[j][i] = 0;
				}
				else {
					pieceBox[j][i] = 7;
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
		// first need to check to make sure rotate is possible
		// will rotate around the second block from the top (and from the left)
		if (numberOfRotations % 4 == 0) {// this means conversion from horizontal to vertical
			try {
				if (boardDisplay[getFarLeftValue()+1][getTopValue()+2] != 0){
					return;
				}
			}
			catch (ArrayIndexOutOfBoundsException e){
				return;
			}
			heightOfPiece = 3;
			lengthOfPiece = 2;
			pieceBox = new int[lengthOfPiece][heightOfPiece];
			for (int i = 0; i < heightOfPiece; i++) {//outer loop loops through the rows
				for (int j = 0; j < lengthOfPiece; j++) {// inner loop loops through the columns
					if ((i == 0 && j == 1)||(i == 2 && j == 1)) {
						pieceBox[j][i] = 0;
					}
					else {
						pieceBox[j][i] = 7;
					}
				}
			}
			farLeftValue++;
			numberOfRotations++;
		}
		else if (numberOfRotations % 4 == 1) { //numberOfRotations % 2 == 1 => conversion from vertical to horizontal
			try {
				if (boardDisplay[getFarLeftValue()-1][getTopValue()+1] != 0){
					return;
				}
			}
			catch (ArrayIndexOutOfBoundsException e){
				return;
			}
			heightOfPiece = 2;
			lengthOfPiece = 3;
			pieceBox = new int[lengthOfPiece][heightOfPiece];
			for (int i = 0; i < heightOfPiece; i++) {//outer loop loops through the rows
				for (int j = 0; j < lengthOfPiece; j++) {// inner loop loops through the columns
					if ((i == 1 && j == 0)||(i == 1 && j == 2)) {
						pieceBox[j][i] = 0;
					}
					else {
						pieceBox[j][i] = 7;
					}
				}
			}
			farLeftValue--;
			topValue++;
			numberOfRotations++;
		}
		else if (numberOfRotations % 4 == 2) { //numberOfRotations % 2 == 1 => conversion from vertical to horizontal
			try {
				if (boardDisplay[getFarLeftValue()+1][getTopValue()-1] != 0){
					return;
				}
			}
			catch (ArrayIndexOutOfBoundsException e){
				return;
			}
			heightOfPiece = 3;
			lengthOfPiece = 2;
			pieceBox = new int[lengthOfPiece][heightOfPiece];
			for (int i = 0; i < heightOfPiece; i++) {//outer loop loops through the rows
				for (int j = 0; j < lengthOfPiece; j++) {// inner loop loops through the columns
					if ((i == 0 && j == 0)||(i == 2 && j == 0)) {
						pieceBox[j][i] = 0;
					}
					else {
						pieceBox[j][i] = 7;
					}
				}
			}
			topValue--;
			numberOfRotations++;
		}
		else {
			try {
				if (boardDisplay[getFarLeftValue()+2][getTopValue()-1] != 0){
					return;
				}
			}
			catch (ArrayIndexOutOfBoundsException e){
				return;
			}
			heightOfPiece = 2;
			lengthOfPiece = 3;
			pieceBox = new int[lengthOfPiece][heightOfPiece];
			for (int i = 0; i < heightOfPiece; i++) {//outer loop loops through the rows
				for (int j = 0; j < lengthOfPiece; j++) {// inner loop loops through the columns
					if ((i == 0 && j == 0)||(i == 0 && j == 2)) {
						pieceBox[j][i] = 0;
					}
					else {
						pieceBox[j][i] = 7;
					}
				}
			}
			numberOfRotations++;
		}
	}

}
