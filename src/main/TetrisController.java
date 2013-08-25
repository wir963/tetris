package main;
//
// Project 2
// Name: Wells Robinson
// E-mail: wir@georgetown.edu
// Instructor: Singh
// COSC 150
//
// In accordance with the class policies and Georgetown's Honor Code,
// I certify that, with the exceptions of the lecture notes and those
// items noted below, I have neither given nor received any assistance
// on this project.
//
// Used the answer to this StackOverflow question - http://stackoverflow.com/questions/2510159/can-i-add-a-component-to-a-specific-grid-cell-when-a-gridlayout-is-used
//
// Description: This is a Tetris Game with all of the normal rules. Everything works. It contains seven color-coded pieces
// of four blocks, which can rotate by pressing the up button. The left and right button move the piece in the respective direction.
// Pressing the down button speeds up the fall of the pieces.



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TetrisController extends JFrame {
	
	private TetrisBoard tboard;
	private ScoreBoard sboard;
	
	public TetrisController() {
		initUI();
	}
	
	public final void initUI(){ // final means this method cannot be overwritten
		
		setTitle("Tetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 800);
		setResizable(false);
		sboard = new ScoreBoard();
		tboard = new TetrisBoard(sboard);
		add (tboard);		
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TetrisController tetrisController = new TetrisController();
				tetrisController.setVisible(true);
			}
		});
	}
}
