package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import tetrisPieces.ITetrisPiece;
import tetrisPieces.JTetrisPiece;
import tetrisPieces.LTetrisPiece;
import tetrisPieces.OTetrisPiece;
import tetrisPieces.STetrisPiece;
import tetrisPieces.TTetrisPiece;
import tetrisPieces.TetrisPiece;
import tetrisPieces.ZTetrisPiece;


public class TetrisBoard extends JPanel implements ActionListener {
	
	private Timer timer;
	final private int  boardHeight = 22;
	final private int boardWidth = 10;
	final private int numberOfDifferentPieces = 7;
	private int[][] boardDisplay;
	private TetrisPiece activePiece;
	private JPanel board;
	private JPanel[][] panelHolder;
	private ScoreBoard sboard;
	
	private JButton restartButton, startButton;
	protected boolean gameOver = false;
	
	/*
	 * Default constructor for the TetrisBoard class
	 */
	public TetrisBoard(ScoreBoard s) {
		sboard = s;
		initUI();
	}
	
	/* Takes no input parameters and has no return values
	 * 
	 */
	public final void initUI() {
		board = new JPanel();// new JPanel for the board itself
		add(board);
		board.setLayout(new GridLayout(boardHeight, boardWidth, 2, 2));// # of rows = boardHeight, # of columns = boardWidth
		
		boardDisplay = new int[boardWidth][boardHeight];//x = width of board/ # of columns; y = height of board/ # of rows
		
		panelHolder = new JPanel[boardWidth][boardHeight];// 2D array of JPanels used to display the color of each block - idea from SO question (see README)
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				panelHolder[j][i] = new JPanel();
				board.add(panelHolder[j][i]);
			}
		}
		board.setPreferredSize(new Dimension(boardWidth*30-boardWidth-2, boardHeight*30-boardHeight-2));
		board.setBorder(BorderFactory.createLineBorder(Color.black));
		add(sboard);
		
		initControls();
		
		initKeyBindings();
		disableKeyBindings();
		timer = new Timer(400, this);
	}
	
	private void start(){
		enableKeyBindings();
		
		startNewPieceMovement();
		
        // A Swing timer fires an action event after a delay
        // In this case, there is a .4 second (400 millisecond) delay and then the 
        // action listener for the timer event will execute
        
        timer.start();
        // use timer.setDelay(# of milliseconds) to change the delay on the timer
	}
	
	private void stop(){
		// disable key bindings
		// pause/ disable piece movement by clock
		disableKeyBindings();
		timer.stop();
	}
	
	private void restart(){
		resetBoard();
		// wipe back of board
		start();

	}
	
	private void resetBoard(){
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				boardDisplay[j][i] = 0;
			}
		}
		// set score to zero
		sboard.resetScore();
	}
	
	private void initControls(){
		restartButton = new JButton("Restart");
		
		restartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				// hack to refocus and make sure key bindings still work
				remove(restartButton);
				add(restartButton);
				if (!gameOver){// if the game isn't over
					stop();
				}
				
				restart();
			}
		});
		
		startButton = new JButton("Start");
		
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				start();
				remove(startButton);
				add(restartButton);
				// hack to get restart button to show up
				sboard.updateScore(1);
				sboard.resetScore();
			}
		});
		
		add(startButton);
		
		
	}

	
	/* Takes no input parameters and has no return values
	 * Starts a new piece movement
	 */
	private void startNewPieceMovement() {
		pickNextPiece();
		// check here if the game is over
		if (checkIfPieceCanEnter()) {
			activePiece.display( panelHolder );
			repaint();
		}
		else {
			stop();
			sboard.setGameOver();
		}
	}
	
	/* Takes no input parameters and returns a boolean
	 * true if there is room on the top of the board for the piece 
	 * and false if there isn't
	 */
	private boolean checkIfPieceCanEnter() {
		for (int i = 4; i < 8; i++){
			for (int j = 0; j < 2; j++){
				if (boardDisplay[i][j] != 0){
					return false;
				}
			}
		}
		return true;
	}
	
	/* Takes no input parameters and has no return value
	 * Generates a random number and decides the type of the next piece
	 * depending on the random number
	 */
	private void pickNextPiece() {
		Random random = new Random();
		int pickedNumber = random.nextInt(numberOfDifferentPieces);
		switch(pickedNumber) {
		case 0: activePiece = new ITetrisPiece();
			break;
		case 1: activePiece = new OTetrisPiece();
			break;
		case 2: activePiece = new JTetrisPiece();
			break;
		case 3: activePiece = new LTetrisPiece();
			break;
		case 4: activePiece = new STetrisPiece();
			break;
		case 5: activePiece = new ZTetrisPiece();
			break;
		case 6: activePiece = new TTetrisPiece();
			break;
		}
	}
	
	/* 
	 * Recolors the board based on the boardDisplay 2D array
	 * Then displays the "current" or "falling" piece
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
    public void paint(Graphics g) {
        super.paint(g);
         
        recolorBoard();
        if (activePiece != null){
        	activePiece.display(panelHolder);
        }
        
        g.dispose();
    }

    /* 
     * Results from the timer in initUI
     * Takes an action event as an input parameters and no return value
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
 
        if (activePiece.moveDown( boardDisplay) == false) {// if the piece is "stuck"
        	checkEffectedRowsForCompletion();// see if any rows are full and need TBE
        	startNewPieceMovement();// starts a new piece
        }
        repaint();  
    }
    
    /* Takes no input parameters and returns nothing
     * Loops through the rows affected by the latest insertion
     * and sees if any are full; if they are, then it eliminates 
     * those pieces and updates the scoreCount
     */
    private void checkEffectedRowsForCompletion() {
    	ArrayList<Integer> intList = new ArrayList<Integer>();
    	for (int i = 0; i < activePiece.getHeight(); i++) {// loops through the rows affected by the piece placement
    		for (int j = 0; j < boardWidth; j++) {// loops through every block in the row
    			if (boardDisplay[j][i+activePiece.getTopValue()] == 0)
    				break;// break the loop if a block == 0
    			if (j == (boardWidth-1)){// if j reaches this value then the entire row is full
    				intList.add(i+activePiece.getTopValue());// add the row number to the list
    			}
    		}
    	}
    	// now have a list of the rows that are full and need to be eliminated
    	for (int k = 0; k < intList.size(); k++) {// for each row that needs to be eliminated
    		for (int n = intList.get(k); n > 0; n--) {// loops through all the rows above it the row TBE
    			for (int m = 0; m < boardWidth; m++){// for each block in the row
    				boardDisplay[m][n] = boardDisplay[m][n-1];// assign to the block the value of the block above it
    			}
    		}
    		for (int l = 0; l < boardWidth; l++){// for each block in the very top row
				boardDisplay[l][0] = 0;// assign it value of 0
			}
    	}
    	sboard.updateScore(intList.size());
    	repaint();
    }
    
    
    /* Method recolors the board depending on the values in
     * the 2D array of integers (called the boardDisplay)
     * Takes no input parameters and returns no values
     */
    private void recolorBoard() {
    	for (int i = 0; i < boardHeight; i++) {// loops through every column
    		for (int j = 0; j < boardWidth; j++) {// loops through every block on the column
    			switch(boardDisplay[j][i]) {
    			case 0: panelHolder[j][i].setBackground(Color.white);
    				break;
    			case 1: panelHolder[j][i].setBackground(Color.cyan);
					break;
    			case 2: panelHolder[j][i].setBackground(Color.yellow);
					break;
    			case 3: panelHolder[j][i].setBackground(Color.blue);
					break;
    			case 4: panelHolder[j][i].setBackground(Color.orange);
					break;
    			case 5: panelHolder[j][i].setBackground(Color.green);
					break;
    			case 6: panelHolder[j][i].setBackground(Color.red);
					break;
    			case 7: panelHolder[j][i].setBackground(Color.pink);
					break;
    			}
    		}
    	}
    }
    
    public void disableKeyBindings(){
    	rightButtonAction.setEnabled(false);
    	leftButtonAction.setEnabled(false);
    	upButtonAction.setEnabled(false);
    	downButtonAction.setEnabled(false);
    }
    
    public void enableKeyBindings() {
    	rightButtonAction.setEnabled(true);
    	leftButtonAction.setEnabled(true);
    	upButtonAction.setEnabled(true);
    	downButtonAction.setEnabled(true);
    }
    
	public void initKeyBindings(){
		// For key bindings, add to input map and action map
		getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightButton");
		getActionMap().put("rightButton", rightButtonAction);
		
		getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftButton");
		getActionMap().put("leftButton", leftButtonAction);
		
		getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downButton");
		getActionMap().put("downButton", downButtonAction);
		
		getInputMap().put(KeyStroke.getKeyStroke("UP"), "upButton");
		getActionMap().put("upButton", upButtonAction);
	}
	
	/*
	 * Defines action inner class for the press of the right key
	 */
	Action rightButtonAction = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			activePiece.moveRight(boardDisplay);
			repaint();
		}
	};
	
	/*
	 * Defines action inner class for the press of the left key
	 */
	Action leftButtonAction = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			activePiece.moveLeft(boardDisplay);
			repaint();
		}
	};
	
	/*
	 * Defines action inner class for the press of the down key
	 */
	Action downButtonAction = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			if (activePiece.moveDown( boardDisplay) == false) {
				checkEffectedRowsForCompletion();
	        	startNewPieceMovement();
	        }
	        repaint();  
		}
	};
	
	/*
	 * Defines action inner class for the press of the up key
	 */
	Action upButtonAction = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
			activePiece.rotate(boardDisplay);

	        repaint();  
		}
	};
    
	
}
