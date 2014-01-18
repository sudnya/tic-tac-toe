package tictactoe.implementation.ai;

import java.util.Random;

import tictactoe.AIType;
import tictactoe.PlayerType;
import tictactoe.implementation.engine.Board;
import tictactoe.implementation.engine.BoardGameEngine;

public class George extends BruteForceAI {

	public George(BoardGameEngine engine, AIType aiType, PlayerType playerType) {
		super(engine, aiType, playerType);
	}

	public double getBoardValueForAI(Board board) {
		return 0;
	}
	
	public double computeMoveValueToAI(Board board) {
		return random.nextInt(100);
	}
	
	static private Random random = new Random();
}
