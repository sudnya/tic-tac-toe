package tictactoe;

/** Represents the result of a tic-tac-toe game. All possible outcomes. */
public enum GameResult {
	/** 
	 * Indicates that the player (or first AI) won the match.
	 */
	PlayerWins, 
	
	/**
	 * Indicates that the player (or first AI) lost the match.
	 */
	PlayerLoses,
	
	/**
	 * Indicates that both players tied.
	 */
	Tie
}
