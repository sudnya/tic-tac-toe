package tictactoe.implementation.ai;

import tictactoe.AIType;
import tictactoe.PlayerType;
import tictactoe.implementation.engine.BoardGameEngine;

/** An abstract interface for an AI, reuse most of the implementation for all AI types */
public abstract class AI {
	
	/** The AI performs its next move */
	public abstract void move();
		
	/** Read only access to the player type */
	public PlayerType getAIPlayerType() {
		return playerType;
	}
	
	protected BoardGameEngine engine;
	protected AIType          aiType;
	protected PlayerType      playerType;

}
