package tictactoe;

/** The set of available player types */
public enum PlayerType {
	X, O;
	
	public static PlayerType invert(PlayerType type) {
		if (type == X) {
			return O;
		} else {
			return X;
		}
	}
}
