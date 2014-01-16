
public class Champ extends AI {
	Champ(Engine e) {
		super(e);
	}
	
	public int getBoardValue(Board board, char symbol) {
		if(board.getWinner() == symbol) {
			return board.getSubtreeSize();
		}
		
		return 0;
	}
}
