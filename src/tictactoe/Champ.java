package tictactoe;

// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : This is the Champ AI player class, who plays to win
// Comment : None

public class Champ extends AI {
	Champ(GenericEngine e, char symbol) {
		super(e, symbol);
	}


	public double getBoardValueForAI(Board board) {
		if (board.getWinner() == getAISymbol()) {
			//board.print();
			//System.out.println("Score is " + board.getSubtreeSize());
			
			return 2.0;
		}
		else if (board.isTied()) {
			return 1.0;
		}
		
		return 0.0;
	}
}
