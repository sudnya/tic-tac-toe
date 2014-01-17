package tictactoe;
// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : This is the game engine class that creates the 3 essential parts of the game, 
// 1. user input, 2. an AI player and 3. a 3x3 board
// Comment : support small x,o and have a way to convert them to capital letters
// maybe make the board dimensions configurable in the future?

import org.apache.commons.cli.CommandLine;  

public class Engine extends GenericEngine {
	Engine(CommandLine cmd) throws ClassNotFoundException {
		
		String symbol = cmd.getOptionValue("s", "X");
		char playerSymbol = 'X';
		
		if (symbol.equalsIgnoreCase("X")) {
			playerSymbol = 'X';
		} else if (symbol.equalsIgnoreCase("O")) {
			playerSymbol = 'O';
		} else {
			throw new IllegalArgumentException("Illegal player symbol " + symbol + ", use 'X' or 'O'.");
		}

		// we default to playing against the Champ who plays to win
		String opponent = cmd.getOptionValue("a", "Champ");
		System.out.println("AI type is " + opponent);
		this.ai = AI.create(this, opponent, invertSymbol(playerSymbol));

		this.ui = new UserInput(this, playerSymbol);

	}
	
	public void run() {
		ui.run();
	}
	
	public char getAISymbol() {
		return invertSymbol(this.ui.getPlayerSymbol());
	}

	
	// member variables
	private UserInput ui;
}
