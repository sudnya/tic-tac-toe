package tictactoe.implementation.engine;

import tictactoe.AIType;
import tictactoe.PlayerType;
import tictactoe.implementation.ai.AI;
import tictactoe.implementation.ai.AIFactory;
import tictactoe.implementation.ui.UserInput;

/** An implementation of the BoardGameEngine using a command line interface */
public class CommandLineGameEngine extends BoardGameEngine {
	public CommandLineGameEngine(PlayerType playerType, AIType aiType) {

		this.ai = AIFactory.createAI(this, aiType, PlayerType.invert(playerType));
		this.ui = new UserInput(this, playerType, this.ai);
	}
	
	public void run() {
		ui.run();
	}

	// member variables
	private UserInput ui;
	private AI        ai;
}
