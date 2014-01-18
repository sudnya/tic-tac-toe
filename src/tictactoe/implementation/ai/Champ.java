package tictactoe.implementation.ai;

import tictactoe.AIType;
import tictactoe.PlayerType;
import tictactoe.implementation.engine.Board;
import tictactoe.implementation.engine.BoardGameEngine;

public class Champ extends BruteForceAI {

	public Champ(BoardGameEngine engine, AIType aiType, PlayerType playerType) {
		super(engine, aiType, playerType);
	}
	
	public double getBoardValueForAI(Board board) {
		if (board.isThereAWinner() && board.getWinner() == getAIPlayerType()) {
			//board.print();
			//System.out.println("Score is " + board.getSubtreeSize());
			
			return 2.0;
			
		} else if (board.isTied()) {
			return 1.0;
		}
		
		return 0.0;
	}
}
