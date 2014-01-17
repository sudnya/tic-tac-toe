package tictactoe;

// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : This is the AI player class, implements all functions except 
//         the cost function that motivates each AI player (to win, to tie, random)
// Comment : Optimize possible subtree without enumerating all

public abstract class AI {
	public static AI create(GenericEngine e, String s, char symbol) throws ClassNotFoundException {
		if (s.equalsIgnoreCase("Champ"))
			return new Champ(e, symbol);
		if (s.equalsIgnoreCase( "Toby"))
			return new Toby(e, symbol);
		if (s.equalsIgnoreCase("George"))
			return new George(e, symbol);
		else {
			throw new ClassNotFoundException();
		}
	}
	
	AI(GenericEngine e, char symbol) {
		this.engine = e;
		this.aiSymbol = symbol;
	}
	
	public void move() {
		Board boardWithBestValue = getBoardWithBestValueForAI(engine.getBoard());
		assert(boardWithBestValue != null);
		// update the board
		engine.setBoard(boardWithBestValue);
	}
	
	public Board getBoardWithBestValueForAI(Board currentBoard) {
		// Enumerate possible moves
		Board[] possibleMoves = enumeratePossibleMoves(currentBoard, aiSymbol);
		
		// pick the move with the best value
		Board bestMove = null;
		double bestValue = Double.MIN_VALUE;
		
		for (int i = 0; i < possibleMoves.length; ++i) {
			if (possibleMoves[i] == null)
				break;
			
			// find the best player move (equivalently, the worst move for the AI)
			double value = computeMoveValueToAI(possibleMoves[i]);
			
			if (bestMove == null || value > bestValue) {
				bestMove  = possibleMoves[i];
				bestValue = value;
			}
		}
		assert(bestMove != null);
		return bestMove;
	}
	
	public double computeMoveValueToAI(Board board) {
		if (board.gameOver()) {
			return getBoardValueForAI(board);
		}

		Board bestBoardForPlayer = getBoardWithBestValueForPlayer(board);
		if (bestBoardForPlayer.gameOver()) {
			return getBoardValueForAI(bestBoardForPlayer);
		}
		
		Board bestBoardForAI = getBoardWithBestValueForAI(bestBoardForPlayer);
		return computeMoveValueToAI(bestBoardForAI);
	}
	
	public Board getBoardWithBestValueForPlayer(Board currentBoard) {
		// Enumerate possible moves
		Board[] possibleMoves = enumeratePossibleMoves(currentBoard, getPlayerSymbol());
		
		// pick the move with the best value
		Board bestMove = null;
		double bestValue = Double.MIN_VALUE;
		
		for (int i = 0; i < possibleMoves.length; ++i) {
			if (possibleMoves[i] == null)
				break;
			
			// find the best player move (equivalently, the worst move for the AI)
			double value = computeMoveValueToPlayer(possibleMoves[i]);
			if (bestMove == null || value > bestValue) {
				bestMove  = possibleMoves[i];
				bestValue = value;
			}
		}
		assert(bestMove != null);
		return bestMove;
	}
	
	public double computeMoveValueToPlayer(Board board) {
		if (board.gameOver()) {
			return getBoardValueForPlayer(board);
		}

		Board bestBoardForAI = getBoardWithBestValueForAI(board);

		if (bestBoardForAI.gameOver()) {
			return getBoardValueForPlayer(bestBoardForAI);
		}
		
		Board bestBoardForPlayer = getBoardWithBestValueForPlayer(bestBoardForAI);
		return computeMoveValueToPlayer(bestBoardForPlayer);
	}
	
	public double getBoardValueForPlayer(Board board) {
		if (board.getWinner() == getPlayerSymbol())
			return 2.0;
		else if (board.isTied())
			return 1.0;
		
		return 0.0;
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
	
	public boolean canAnyPlayerWinThisBoard(Board b, char symbol) {
		Board[] possibleMoves = enumeratePossibleMoves(b, symbol);
		
		for (int i = 0; i < possibleMoves.length; ++i) {
			if (possibleMoves[i] == null)
				continue;
			
			if (possibleMoves[i].getWinner() != ' ') {
				return true;
			}
			
			if (canAnyPlayerWinThisBoard(possibleMoves[i], engine.invertSymbol(symbol))) {
				return true;
			}
		}
		
		return false;
	}
	
	char getAISymbol() {
		return aiSymbol;
	}
	
	char getPlayerSymbol() {
		return engine.invertSymbol(getAISymbol());
	}
	
	// this will vary according to who the human player chooses to play against
	public abstract double getBoardValueForAI(Board board);

	protected GenericEngine engine;
	protected char aiSymbol;
}
