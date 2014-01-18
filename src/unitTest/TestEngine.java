package unitTest;

import tictactoe.AIType;
import tictactoe.PlayerType;
import tictactoe.implementation.ai.AI;
import tictactoe.implementation.ai.AIFactory;
import tictactoe.implementation.engine.BoardGameEngine;

public class TestEngine extends BoardGameEngine {
	public TestEngine(AIType player, AIType opponent, int iterations)  {

		this.testIterations = iterations;
		
		this.playerAI   = AIFactory.createAI(this, player,   PlayerType.X);
		this.opponentAI = AIFactory.createAI(this, opponent, PlayerType.O);
	}

	public void run() {
		int playerWins  = 0;
		int playerLoses = 0;
		int playerTies  = 0;
		
		for (int i = 0; i < testIterations; ++i) {
			restart();
			
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
				++playerWins;
			} else if (getBoard().isThereAWinner() && getBoard().getWinner() == getOpponentAIType()) {
				++playerLoses;
			} else {
				++playerTies;
			}
		}
		
		System.out.println(" wins:   " + playerWins );
		System.out.println(" losses: " + playerLoses);
		System.out.println(" ties:   " + playerTies );
	}
	
	public PlayerType getPlayerAIType() {
		return playerAI.getAIPlayerType();
	}
	
	public PlayerType getOpponentAIType() {
		return opponentAI.getAIPlayerType();
	}
	
	private AI playerAI;
	private AI opponentAI;
	
	private int testIterations;

}
