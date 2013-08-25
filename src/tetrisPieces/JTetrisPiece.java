package tetrisPieces;


public class JTetrisPiece extends TetrisPiece {

	int numberOfRotations = 0;
	
	/* Default constructor
	 * Creates piece as a 2D array with each index either 0 or 3 depending on whether it represents a piece
	 * Keep track of piece using left and top value (x,y)
	 */
	public JTetrisPiece() {
		lengthOfPiece = 3;
		heightOfPiece = 2;
		pieceBox = new int[lengthOfPiece][heightOfPiece];
		
		for (int i = 0; i < heightOfPiece; i++) {//outer loop loops through the rows
			for (int j = 0; j < lengthOfPiece; j++) {// inner loop loops through the columns
				if ((i == 0 && j == 1)||(i == 0 && j == 2)) {// two values in the 2D array don't comprise the piece
					pieceBox[j][i] = 0;
				}
				else {
					pieceBox[j][i] = 3;
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
		if (numberOfRotations % 4 == 0) {// this means conversion from horizontal to vertical
			try {
				if ((boardDisplay[getFarLeftValue()+1][getTopValue()] != 0)||(boardDisplay[getFarLeftValue()+2][getTopValue()] != 0)
						||(boardDisplay[getFarLeftValue()+1][getTopValue()+2] != 0)){
					return;
				}
			}
			catch (ArrayIndexOutOfBoundsException e){
				return;
			}
			lengthOfPiece = 2;
			heightOfPiece = 3;
			pieceBox = new int[lengthOfPiece][heightOfPiece];
			
			for (int i = 0; i < heightOfPiece; i++) {//outer loop loops through the rows
				for (int j = 0; j < lengthOfPiece; j++) {// inner loop loops through the columns
					if ((i == 1 && j == 1)||(i == 2 && j == 1)) {
						pieceBox[j][i] = 0;
					}
					else {
						pieceBox[j][i] = 3;
					}
					
				}
			}
			farLeftValue++;
			numberOfRotations++;
		}
		else if (numberOfRotations % 4 == 1) {// this means conversion from horizontal to vertical
			try {
				if ((boardDisplay[getFarLeftValue()-1][getTopValue()+1] != 0)||(boardDisplay[getFarLeftValue()+1][getTopValue()+1] != 0)
						||(boardDisplay[getFarLeftValue()+1][getTopValue()+2] != 0)){
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
					if ((i == 1 && j == 0)||(i == 1 && j == 1)) {
						pieceBox[j][i] = 0;
					}
					else {
						pieceBox[j][i] = 3;
					}
					
				}
			}
			farLeftValue--;
			topValue++;
			numberOfRotations++;
		}
		else if (numberOfRotations % 4 == 2) {// this means conversion from horizontal to vertical
			try {
				if ((boardDisplay[getFarLeftValue()][getTopValue()+1] != 0)||(boardDisplay[getFarLeftValue()+1][getTopValue()+1] != 0)
					||(boardDisplay[getFarLeftValue()+1][getTopValue()-1] != 0)){
				return;
				}
			}
			catch (ArrayIndexOutOfBoundsException e){
				return;
			}
			lengthOfPiece = 2;
			heightOfPiece = 3;
			pieceBox = new int[lengthOfPiece][heightOfPiece];
			
			for (int i = 0; i < heightOfPiece; i++) {//outer loop loops through the rows
				for (int j = 0; j < lengthOfPiece; j++) {// inner loop loops through the columns
					if ((i == 0 && j == 0)||(i == 1 && j == 0)) {
						pieceBox[j][i] = 0;
					}
					else {
						pieceBox[j][i] = 3;
					}
					
				}
			}
			topValue--;
			numberOfRotations++;
		}
		else {
			try {
				if ((boardDisplay[getFarLeftValue()][getTopValue()] != 0)||(boardDisplay[getFarLeftValue()][getTopValue()+1] != 0)
						||(boardDisplay[getFarLeftValue()+2][getTopValue()+1] != 0)){
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
					if ((i == 0 && j == 1)||(i == 0 && j == 2)) {
						pieceBox[j][i] = 0;
					}
					else {
						pieceBox[j][i] = 3;
					}
					
				}
			}
			numberOfRotations++;
		}
	}
	
	
}
