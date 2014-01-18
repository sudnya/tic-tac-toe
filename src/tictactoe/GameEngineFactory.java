package tictactoe;

import tictactoe.implementation.engine.CommandLineGameEngine;

/** A factory for creating instances of the game engine */
public class GameEngineFactory {

	public static GameEngine createCommandLineGameEngine(PlayerType playerType, AIType aiType) {
		return new CommandLineGameEngine(playerType, aiType);
	}

}
