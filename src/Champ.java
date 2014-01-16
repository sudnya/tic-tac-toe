// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : This is the Champ AI player class, who plays to win
// Comment : None

public class Champ extends AI {
	Champ(Engine e) {
		super(e);
	}


	public int getBoardValue(Board board, char symbol) {
		if (board.getWinner() == symbol) {
			return board.getSubtreeSize();
		}
		return 0;
	}
}
