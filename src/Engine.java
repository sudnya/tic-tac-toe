// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : This is the game engine class that creates the 3 essential parts of the game, 
// 1. user input, 2. an AI player and 3. a 3x3 board
// Comment : support small x,o and have a way to convert them to capital letters
// maybe make the board dimensions configurable in the future?

import org.apache.commons.cli.CommandLine;  

public class Engine {
	Engine(CommandLine cmd) throws ClassNotFoundException {

		// we default to playing against the Champ who plays to win
		this.ai = AI.create(this, cmd.getOptionValue("t", "Champ"));
		this.board = new Board();
		this.ui = new UserInput(this);
		String symbol = cmd.getOptionValue("s", "X");
		
		if (symbol.equals("X")) {
			this.playerSymbol = 'X';
		} else if (symbol.equals("O")) {
			this.playerSymbol = 'O';
		} else {
			throw new IllegalArgumentException("Illegal player symbol " + symbol + ", use 'X' or 'O'.");
		}
		
	}
	
	public void run() {
		ui.run();
	}

	public Board getBoard() {
		return this.board;
	}
	
	public void setBoard(Board b) {
		this.board = b;
	}
	
	public AI getAI() {
		return this.ai;
	}
	
	public char getAISymbol() {
		return invertSymbol(playerSymbol);
	}
	
	public char invertSymbol(char symbol) {
		if (symbol == 'X')
			return 'O';
		return 'X';
	}
	
	// member variables
	private UserInput ui;
	private Board board;
	private AI ai;
	
	public char playerSymbol;
}
