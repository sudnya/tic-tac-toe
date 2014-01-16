// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : Toby AI player class, who plays to tie
// Comment : None

public class Toby extends AI {
	Toby(Engine e) {
		super(e);
	}

	
	public int getBoardValue(Board board, char symbol) {
		if (board.isTied()) {
			return board.getSubtreeSize();
		}
		return 0;
	}

}
