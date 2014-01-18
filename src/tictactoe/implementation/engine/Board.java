package tictactoe.implementation.engine;

import java.util.Arrays;

import tictactoe.PlayerType;

public class Board {
	Board() {
		this.board = new Cell[3][3];
		this.reset();
	}
	
	Board(Board b) {
		// deep copy
		this.board = new Cell[3][3];

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.board[i][j] = b.board[i][j];
			}
		}
	}
	
	public void reset() {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.board[i][j] = Cell.Empty;
			}
		}
	}
	
	public void setPosition(PlayerType playerType, int x, int y) {
		setPosition(Cell.convertPlayerToCell(playerType), x, y);
	}
	
	public void setPosition(Cell c, int x, int y) {
		if (!isLocationEmpty(x,y)) {
			throw new IllegalArgumentException();
		}
		// note we use coordinate system with 0,0 at top left
		board[y][x] = c;
	}
	
	public boolean isLocationEmpty(int x, int y) {
		if (board[y][x] == Cell.Empty)
			return true;
		return false;
	}
	
	public void print() {
		System.out.println(Arrays.toString(board[0]));
		System.out.println(Arrays.toString(board[1]));
		System.out.println(Arrays.toString(board[2]));
	}
	
	public boolean gameOver() {
		return isThereAWinner() || isTied();
	}
	
	public boolean isThereAWinner() {
		return getCellFromWinningLine() != Cell.Empty;
	}
	
	public PlayerType getWinner() {
		Cell winner = getCellFromWinningLine();
		
		assert(winner != Cell.Empty);
		
		if(winner == Cell.O) {
			return PlayerType.O;
		} else {
			return PlayerType.X;
		}
	}
	
	public Cell getCellFromWinningLine() {
		//check rows
		for (int i = 0; i < 3; ++i) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != Cell.Empty) {
				return board[i][0];
			}
		}
		//check columns
		for (int i = 0; i < 3; ++i) {
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != Cell.Empty) {
				return board[0][i];
			}
		}
		//check diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != Cell.Empty)
			return board[0][0];
		if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != Cell.Empty)
			return board[2][0];
		
		return Cell.Empty;
	}
	
	public boolean isTied() {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (isLocationEmpty(i, j)) {
					return false;
				}
			}
		}
		
		return !isThereAWinner();
	}
	
	public int getSubtreeSize() {
		// we want to win as early as possible (bigger subtree)
		int freeCells = countFreeCells();
		return factorial(freeCells);
	}
	
	public int countFreeCells() {
		int count = 0;
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (isLocationEmpty(i, j)) {
					count += 1;
				}
			}
		}
		return count;
	}
	
	public boolean canAnyPlayerWin(PlayerType playerType) {
		return canAnyPlayerWin(this, Cell.convertPlayerToCell(playerType));
	}
	
	public boolean canAnyPlayerWin(Board b, Cell cell) {
		Board[] possibleMoves = b.enumeratePossibleMoves(cell);
		
		for (int i = 0; i < possibleMoves.length; ++i) {
			if (possibleMoves[i] == null)
				break;
			
			if (possibleMoves[i].isThereAWinner()) {
				return true;
			}
			
			if (canAnyPlayerWin(possibleMoves[i], Cell.invert(cell))) {
				return true;
			}
		}
		
		return false;
	}
	
	public Board[] enumeratePossibleMoves(Cell symbol) {
		// Could place the symbol on any open space
		Board[] moves = new Board[9];
		int nextMove = 0;

		for (int x = 0; x < 3; ++x) {
			for (int y = 0; y < 3; ++y) {
				if (isLocationEmpty(x, y)) {
					Board newBoard = new Board(this);
					newBoard.setPosition(symbol, x, y);
					moves[nextMove++] = newBoard;
				}
			}
		}
		return moves;
	}

	// TODO: There must be a library factorial function to use instead of this one!
	static public int factorial(int n) {
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
	
	private Cell[][] board;
}
