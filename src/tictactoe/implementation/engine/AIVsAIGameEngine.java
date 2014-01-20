package tictactoe.implementation.engine;

import tictactoe.AIType;
import tictactoe.GameResult;
import tictactoe.PlayerType;
import tictactoe.implementation.ai.AI;
import tictactoe.implementation.ai.AIFactory;

public class AIVsAIGameEngine extends BoardGameEngine {
	public AIVsAIGameEngine(AIType player, AIType opponent)  {

		this.playerAI   = AIFactory.createAI(this, player,   PlayerType.X);
		this.opponentAI = AIFactory.createAI(this, opponent, PlayerType.O);
	}

	public GameResult run() {
		
		while(true)
		{
			playerAI.move();
			
			if (getBoard().gameOver()) {
				break;
			}
			
			opponentAI.move();

			if (getBoard().gameOver()) {
				break;
			}
		}
		
		if (getBoard().isThereAWinner() && getBoard().getWinner() == getPlayerAIType()) {
			return GameResult.PlayerWins;
		} else if (getBoard().isThereAWinner() && getBoard().getWinner() == getOpponentAIType()) {
			return GameResult.PlayerLoses;
		} else {
			return GameResult.Tie;
		}
	}
	
	public PlayerType getPlayerAIType() {
		return playerAI.getAIPlayerType();
	}
	
	public PlayerType getOpponentAIType() {
		return opponentAI.getAIPlayerType();
	}
	
	private AI playerAI;
	private AI opponentAI;

}
