
public class Toby extends AI {
	Toby(Engine e) {
		super(e);
	}

	
	public int getBoardValue(Board board, char symbol) {
		if(board.isTied()) {
			return board.getSubtreeSize();
		}
		
		return 0;
	}

}
