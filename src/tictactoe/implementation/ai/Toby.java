package tictactoe.implementation.ai;

import tictactoe.AIType;
import tictactoe.PlayerType;
import tictactoe.implementation.engine.Board;
import tictactoe.implementation.engine.BoardGameEngine;

public class Toby extends BruteForceAI {

	public Toby(BoardGameEngine engine, AIType aiType, PlayerType playerType) {
		super(engine, aiType, playerType);
	}

	public double getBoardValueForAI(Board board) {
		if (board.isTied()) {
			return 2.0;
		}
		else if (board.isThereAWinner() && board.getWinner() == getAIPlayerType()) {
			return 1.0;
		}
		return 0.0;
	}

}
