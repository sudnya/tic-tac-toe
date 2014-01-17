package tictactoe;

// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : Toby AI player class, who plays to tie
// Comment : None

public class Toby extends AI {
	Toby(GenericEngine e, char symbol) {
		super(e, symbol);
	}

	
	public double getBoardValueForAI(Board board) {
		if (board.isTied()) {
			return 2.0;
		}
		else if (board.getWinner() == getAISymbol()) {
			return 1.0;
		}
		return 0.0;
	}

}
