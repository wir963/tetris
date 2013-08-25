package main;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreBoard extends JPanel {
	
	private int scoreCount = 0;
	private JLabel score;
	
	ScoreBoard (){
		score = new JLabel();
		add(score);
		updateScore();
	}
	
	public void updateScore(){
		score.setText("Score: " + scoreCount);
	}
	
	public void updateScore(int addedScore){
		scoreCount += (addedScore*addedScore)*100;
		updateScore();
	}
	
	public void resetScore(){
		scoreCount = 0;
		updateScore();
	}
	
	public void setGameOver(){
		score.setText("Game Over");
	}

}
