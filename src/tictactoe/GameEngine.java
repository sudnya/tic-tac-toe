package tictactoe;

/** An abstract interface for playing a tic-tac-toe game */
public interface GameEngine {
	
	/** Runs the game until completion.
	 * 
	 *  @return Returns the outcome of the game as a GameResult.
	 */
	public GameResult run();
	
}
