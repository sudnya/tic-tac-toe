// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : This is the AI player class, implements all functions except 
//         the cost function that motivates each AI player (to win, to tie, random)
// Comment : Optimize possible subtree without enumerating all

public abstract class AI {
	public static AI create(Engine e, String s) throws ClassNotFoundException {
		if (s == "Champ")
			return new Champ(e);
		if (s == "Toby")
			return new Toby(e);
		if (s == "George")
			return new George(e);
		else {
			throw new ClassNotFoundException();
		}
	}
	
	AI(Engine e) {
		this.engine = e;
	}
	
	public void move() {
		Board boardWithBestValue = getBoardWithBestValue(engine.getBoard(), engine.getAISymbol());
		// update the board
		engine.setBoard(boardWithBestValue);
	}
	
	public Board getBoardWithBestValue(Board currentBoard, char nextSymbol) {
		// Enumerate possible moves
		Board[] possibleMoves = enumeratePossibleMoves(currentBoard, nextSymbol);
		
		// pick the move with the best value
		Board bestMove = null;
		int bestValue = -1;
		
		for (int i = 0; i < possibleMoves.length; ++i) {
			if (possibleMoves[i] == null)
				continue;
			
			int value = computeValue(possibleMoves[i], engine.invertSymbol(nextSymbol), nextSymbol);
			
			if (value > bestValue) {
				bestMove  = possibleMoves[i];
				bestValue = value;
			}
		}
		
		assert(bestMove != null);
		return bestMove;
	}
	
	public Board[] enumeratePossibleMoves(Board board, char symbol) {
		// Could place the symbol on any open space
		Board[] moves = new Board[9];
		int nextMove = 0;

		for (int x = 0; x < 3; ++x) {
			for (int y = 0; y < 3; ++y) {
				if (board.isLocationEmpty(x, y)) {
					Board newBoard = new Board(board);
					newBoard.setPosition(symbol, x, y);
					moves[nextMove++] = newBoard;
				}
			}
		}
		return moves;
	}
	
	public int computeValue(Board board, char symbol, char originalSymbol) {
		if (board.gameOver()) {
			return getBoardValue(board, originalSymbol);
		}

		// get the total value by summing up the value of all possible moves
		Board[] possibleMoves = enumeratePossibleMoves(board, symbol);		
		int totalValue = 0;
		for (int i = 0; i < possibleMoves.length; ++i) {
			if(possibleMoves[i] == null)
				continue;

			totalValue += computeValue(possibleMoves[i], engine.invertSymbol(symbol), originalSymbol);
		}		
		return totalValue;
	}
	
	// this will vary according to who the human player chooses to play against
	public abstract int getBoardValue(Board board, char symbol);

	private Engine engine;
}
