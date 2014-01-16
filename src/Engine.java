import org.apache.commons.cli.CommandLine;  

public class Engine {
	Engine(CommandLine cmd) throws ClassNotFoundException {
		this.ai = AI.create(this, cmd.getOptionValue("t", "Champ"));
		this.board = new Board();
		this.ui = new UserInput(this);
		String symbol = cmd.getOptionValue("s", "X");
		
		if(symbol.equals("X")) {
			this.playerSymbol = 'X';
		}
		else if (symbol.equals("O")) {
			this.playerSymbol = 'O';
		}
		else {
			throw new IllegalArgumentException("Illegal player symbol " + symbol + ", use 'X' or 'O'.");
		}
	}
	
	public void run() {
		ui.run();
	}

	public Board getBoard() {
		return this.board;
	}
	
	public void setBoard(Board b) {
		this.board = b;
	}
	
	public AI getAI() {
		return this.ai;
	}
	
	public char getAISymbol() {
		return invertSymbol(playerSymbol);
	}
	
	public char invertSymbol(char symbol) {
		if (symbol == 'X')
			return 'O';
		return 'X';
		
	}
	private UserInput ui;
	private Board board;
	private AI ai;
	
	public char playerSymbol;
}
