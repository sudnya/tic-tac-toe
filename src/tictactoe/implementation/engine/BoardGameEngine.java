package tictactoe.implementation.engine;

import tictactoe.GameEngine;

/** Implements common engine functionality */
public abstract class BoardGameEngine implements GameEngine {
	
	/** The constructor creates a board for the engine to play on */
	public BoardGameEngine() {
		this.board = new Board();
	}
	
	/** Read access to the board */
	public Board getBoard() {
		return board;
	}
	
	/** Reset the board to a blank state */
	public void restart() {
		board.reset();
	}
	
	/** Write access to the board */
	public void setBoard(Board b) {
		board = b;
	}

	// member variables
	protected Board board;

}
