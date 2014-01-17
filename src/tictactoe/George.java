package tictactoe;
// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : George AI player class, who plays randomly
// Comment : None

import java.util.Random;

public class George extends AI {
	George(GenericEngine e, char symbol) {
		super(e, symbol);
	}

	public double getBoardValueForAI(Board board) {
		return 0;
	}
	
	public double computeMoveValueToAI(Board board) {
		return random.nextInt(100);
	}
	
	static private Random random = new Random();
}
