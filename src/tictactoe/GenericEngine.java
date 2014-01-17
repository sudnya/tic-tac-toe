package tictactoe;
import org.apache.commons.cli.CommandLine;

// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : This is the generic game engine class 


public abstract class GenericEngine {
	public static GenericEngine create(CommandLine cmd, String s) throws ClassNotFoundException {
		if (s.equalsIgnoreCase( "play"))
			return new Engine(cmd);
		else {
			throw new ClassNotFoundException();
		}
	}
	
	public GenericEngine() {
		this.board = new Board();
	}
	
	public abstract void run();
	
	public Board getBoard() {
		return board;
	}
	
	public void restart() {
		board.reset();
	}
	
	public void setBoard(Board b) {
		board = b;
	}
	
	public AI getAI() {
		return ai;
	}

	public abstract char getAISymbol();
	
	public char getPlayerSymbol() {
		return invertSymbol(getAISymbol());
	}
	
	public char invertSymbol(char symbol) {
		if (symbol == 'X')
			return 'O';
		return 'X';
	}
	
	// member variables
	protected Board board;
	protected AI ai;

}
