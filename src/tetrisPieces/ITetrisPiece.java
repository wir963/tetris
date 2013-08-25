package tetrisPieces;

/* 
 * This is the tetris piece that looks like an sideways I
 * One row by four columns
 */
public class ITetrisPiece extends TetrisPiece {
	
	int numberOfRotations = 0;
	
	/* Default constructor
	 * Creates piece as a 2D array with each index either 0 or 1 depending on whether it represents a piece
	 * Keep track of piece using left and top value (x,y)
	 */
	public ITetrisPiece() {
		
		lengthOfPiece = 4;
		heightOfPiece = 1;
		pieceBox = new int[lengthOfPiece][heightOfPiece];
		
		for (int i = 0; i < heightOfPiece; i++) {
			for (int j = 0; j < lengthOfPiece; j++) {
				pieceBox[j][i] = 1;
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
		if (numberOfRotations % 2 == 0) {// this means conversion from horizontal to vertical
			for (int i = -1; i < 3; i++) {// rotate centered around second to the left block
				try {
					if (boardDisplay[getFarLeftValue()+1][getTopValue()+i] != 0)
						return;
				}
				catch (ArrayIndexOutOfBoundsException e) {
					return;
				}
			}
			// this means the rotate didn't violate the rules
			farLeftValue++;// increment the far left value
			topValue--;// decrement the top value
			lengthOfPiece = 1;
			heightOfPiece = 4;
			pieceBox = new int[lengthOfPiece][heightOfPiece];
			
			for (int i = 0; i < heightOfPiece; i++) {
				for (int j = 0; j < lengthOfPiece; j++) {
					pieceBox[j][i] = 1;
				}
			}
			numberOfRotations++;
		}
		else { //numberOfRotations % 2 == 1 => conversion from vertical to horizontal
			for (int i = -1; i < 3; i++) {// rotate centered around second to the top block
				try {
					if (boardDisplay[getFarLeftValue()+i][getTopValue()-1] != 0)
						return;
				}
				catch (ArrayIndexOutOfBoundsException e) {
					return;
				}
			}
			farLeftValue--;
			topValue++;
			lengthOfPiece = 4;
			heightOfPiece = 1;
			pieceBox = new int[lengthOfPiece][heightOfPiece];
			
			for (int i = 0; i < heightOfPiece; i++) {
				for (int j = 0; j < lengthOfPiece; j++) {
					pieceBox[j][i] = 1;
				}
			}
			numberOfRotations++;
		}
	}

}
