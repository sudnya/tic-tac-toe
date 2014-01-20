package tictactoe;

import tictactoe.implementation.engine.AIVsAIGameEngine;
import tictactoe.implementation.engine.CommandLineGameEngine;

/** A factory for creating instances of the game engine. */
public class GameEngineFactory {

	/** 
	 * Creates a new game engine with the specified player type (X or O) and AI type.
	 * 
	 * This interface assumes that a human player will face off against an AI.
	 * 
	 * @param playerType  Selects the player symbol (X or O).  This determines whether or not the player moves first.
	 * @param aiType      The choice of AI to play against (Toby, George, or Champ).  Each AI will use a different strategy.
	 * @return            Returns the result of the game from the perspective of the player (Win, Loss, Tie).
	 */
	public static GameEngine createGameEngine(PlayerType playerType, AIType aiType) {
		return new CommandLineGameEngine(playerType, aiType);
	}
	
	/**
	 * Creates a new game engine with the specified AI types.
	 * 
	 * This interface assumes that two AIs will play against each other.
	 * 
	 * @param aiPlayerType   The choice of the first AI (Champ, George, or Toby).  This AI plays the X symbol and moves first.
	 * @param aiOpponentType The choice of the second AI (Champ, George, or Toby). This AI plays the O symbol and moves second.
	 * @return               Returns the result of the game from the perspective of the first AI (Win, Loss, Tie).
	 */
	public static GameEngine createGameEngine(AIType aiPlayerType, AIType aiOpponentType) {
		return new AIVsAIGameEngine(aiPlayerType, aiOpponentType);
	}
		

}
