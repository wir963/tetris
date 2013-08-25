package tetrisPieces;

/*
 * O tetris piece is a 2x2 square
 */
public class OTetrisPiece extends TetrisPiece {
	
	/* Default constructor
	 * Creates piece as a 2D array with each index either 0 or 2 depending on whether it represents a piece
	 * Keep track of piece using left and top value (x,y)
	 */
	public OTetrisPiece() {
		lengthOfPiece = 2;
		heightOfPiece = 2;
		pieceBox = new int[lengthOfPiece][heightOfPiece];
		
		for (int i = 0; i < heightOfPiece; i++) {
			for (int j = 0; j < lengthOfPiece; j++) {
				pieceBox[j][i] = 2;
			}
		}
		
		farLeftValue = 4;
		topValue = 0;
	}
	
	public void rotate(int[][] boardDisplay) {
		// do nothing because square block can't rotate
	}
	
}
