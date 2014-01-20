package tictactoe;

/** The set of available player types */
public enum PlayerType {
	/**
	 * The 'X' symbol in a tic-tac-toe game.  This player goes first.
	 */
	X, 
	
	/**
	 * The 'O' symbol in a tic-tac-toe game.  This player goes second.
	 */
	O;
	
	/**
	 * A helper function to quickly determine the opponent of a given player.
	 * 
	 * @param type A PlayerType.
	 * @return     The opposite type.
	 */
	public static PlayerType invert(PlayerType type) {
		if (type == X) {
			return O;
		} else {
			return X;
		}
	}
}
