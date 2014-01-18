package tictactoe.implementation.engine;

import tictactoe.PlayerType;

/** A possible state of a cell on a Board */
public enum Cell {
	Empty, X, O;
	
	static Cell invert(Cell cell) {
		if (cell == X) {
			return O;
		} else if (cell == O) {
			return X;
		} else {
			return Empty;
		}
	}
	
	public static Cell convertPlayerToCell(PlayerType playerType) {
		if (playerType == PlayerType.O) {
			return Cell.O;
		} else {
			return Cell.X;			
		}
	}
	
	public String toString() {
		if (this == X) {
			return "X";
		} else if (this == O) {
			return "O";
		} else {
			return " ";
		}
		
	}

}
