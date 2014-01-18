package tictactoe.implementation.ai;

import tictactoe.AIType;
import tictactoe.PlayerType;
import tictactoe.implementation.engine.BoardGameEngine;

/** A factory for creating AIs */
public class AIFactory {
	public static AI createAI(BoardGameEngine engine, AIType aiType, PlayerType playerType) {
		if (aiType == AIType.Champ) {
			return new Champ(engine, aiType, playerType);
		} else if (aiType == AIType.Toby) {
			return new Toby(engine, aiType, playerType);
		} else {
			return new George(engine, aiType, playerType);
		}
	}
}
