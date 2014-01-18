package tictactoe.implementation.ai;

import tictactoe.AIType;
import tictactoe.PlayerType;
import tictactoe.implementation.engine.Board;
import tictactoe.implementation.engine.BoardGameEngine;
import tictactoe.implementation.engine.Cell;

public abstract class BruteForceAI extends AI {
	
	/** The cost function determines how 'good' a finished board is for the AI */
	public abstract double getBoardValueForAI(Board board);
	
	public BruteForceAI(BoardGameEngine engine, AIType aiType, PlayerType playerType) {
		this.engine     = engine;
		this.aiType     = aiType;
		this.playerType = playerType;
	}
	
	public void move() {
		Board boardWithBestValue = getBoardWithBestValueForAI(engine.getBoard());
		assert(boardWithBestValue != null);
		
		// update the board
		engine.setBoard(boardWithBestValue);
	}
	
	public Board getBoardWithBestValueForAI(Board currentBoard) {
		// Enumerate possible moves
		Board[] possibleMoves = currentBoard.enumeratePossibleMoves(
			Cell.convertPlayerToCell(getAIPlayerType()));
		
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
		Board[] possibleMoves = currentBoard.enumeratePossibleMoves(
			Cell.convertPlayerToCell(getPlayerPlayerType()));
		
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
	
	private double getBoardValueForPlayer(Board board) {
		if (board.isThereAWinner() && board.getWinner() == getPlayerPlayerType())
			return 2.0;
		else if (board.isTied())
			return 1.0;
		
		return 0.0;
	}
	
	private PlayerType getPlayerPlayerType() {
		return PlayerType.invert(getAIPlayerType());
	}
}
