// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : This class stores the board and it's state
// Comment : configurable dimensions?

import java.util.Arrays;

public class Board {
	Board() {
		this.board = new char[3][3];
		this.reset();
	}
	
	Board(Board b) {
		// copy over current with new board state
		this.board = new char[3][3];

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.board[i][j] = b.board[i][j];
			}
		}
	}
	
	public void reset() {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.board[i][j] = ' ';
			}
		}
	}
	
	public void setPosition(char c, int x, int y) {
		if (!isCharacterAllowed(c)) {
			throw new IllegalArgumentException();
		}
		
		if (!isLocationEmpty(x,y)) {
			throw new IllegalArgumentException();
		}
		// note we use coordinate system with 0,0 at top left
		board[y][x] = c;
	}
	
	public boolean isCharacterAllowed(char c) {
		if (c == ' ' || c == 'X' || c == 'O')
			return true;
		return false;
	}
	
	public boolean isLocationEmpty(int x, int y) {
		if (board[y][x] == ' ')
			return true;
		return false;
	}
	
	public void print() {
		System.out.println(Arrays.toString(board[0]));
		System.out.println(Arrays.toString(board[1]));
		System.out.println(Arrays.toString(board[2]));
	}
	
	public boolean gameOver() {
		return getWinner() != ' ' || isTied();
	}
		
	public char getWinner() {
		//check rows
		for (int i = 0; i < 3; ++i) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
				return board[i][0];
			}
		}
		//check columns
		for (int i = 0; i < 3; ++i) {
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
				return board[0][i];
			}
		}
		//check diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ')
			return board[0][0];
		if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != ' ')
			return board[2][0];
		
		return ' ';
	}
	
	public boolean isTied() {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (isLocationEmpty(i, j)) {
					return false;
				}
			}
		}
		return getWinner() == ' ';
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
	
	// TODO: There must be a library factorial function to use instead of this one!
	static public int factorial(int n) {
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
	
	private char [][] board;
}
