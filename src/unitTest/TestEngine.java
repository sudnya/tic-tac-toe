package unitTest;

import org.apache.commons.cli.CommandLine;

import tictactoe.AI;
import tictactoe.GenericEngine;

public class TestEngine extends GenericEngine {
		TestEngine(String player, String opponent, int iterations) throws ClassNotFoundException {

			this.testIterations = iterations;
			
			this.playerAI = AI.create(this, player, getPlayerSymbol());
			this.ai = AI.create(this, opponent, getAISymbol());

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
					
					if (getBoard().gameOver())
						break;
					
					ai.move();

					if (getBoard().gameOver())
						break;
				}
				
				if (getBoard().getWinner() == getPlayerSymbol())
					++playerWins;
				else if (getBoard().getWinner() == getAISymbol())
					++playerLoses;
				else
					++playerTies;
			}
			
			System.out.println(" wins:   " + playerWins);
			System.out.println(" losses: " + playerLoses);
			System.out.println(" ties:   " + playerTies);
		}
		
		public char getAISymbol() {
			return 'O';
		}
		
		private AI playerAI;
		private int testIterations;

}
