import java.io.*;

public class UserInput {
	UserInput(Engine e) {
		this.engine = e;
	}
	
	public void run() {
		if (aiMovesFirst()) {
			engine.getAI().move();
		}
			
		while (true) {
			if(!checkBoard()) {
				break;
			}
			String input = getUserInput();
			if (isQuit(input))
				break;
			
			if (validateInput(input)) {
				if(processMove(input)) {
					if(!checkBoard()) {
						break;
					}
					engine.getAI().move();
				}
			} else {
				printHelpMessage();
			}
			
			engine.getBoard().print();
		}
	}
	
	private boolean checkBoard() {
		if (engine.getBoard().gameOver()) {
			System.out.println("The game is over.  The final board is:");

			engine.getBoard().print();
			
			if(engine.getBoard().isTied()) {
				System.out.println("An even match!");
			}
			else if (engine.getBoard().getWinner() == engine.playerSymbol) {
				System.out.println("You win!");
			}
			else {
				System.out.println("You lose!");
			}
			
			return false;
		}
		
		return true;
	}
	
	private boolean aiMovesFirst() {
		return engine.playerSymbol == 'O';
	}
	
	private String getUserInput() {
		//  prompt the user to enter their name
	    System.out.print("Please enter command: ");

	      //  open up standard input
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	      String input = null;

	      //  readLine() method
	      try {
	    	  input = br.readLine();
	      } catch (IOException ioe) {
	         System.out.println("IO error trying to read your input!");
	         System.exit(1);
	      }
	      return input;
	}
	
	private boolean isQuit(String input) {
		if (input == "quit" || input == "q")
			return true;
		return false;
	}
	
	private boolean validateInput(String input) {
		if(!input.contains(",")) {
			return false;
		}
		
		if(input.split(",").length != 2) {
			return false;
		}
		
		return true;
	}
	
	private boolean processMove(String input) {
		String[] components = input.split(",");
		
		assert components.length == 2;
		
		int x = Integer.parseInt(components[0].trim());
		int y = Integer.parseInt(components[1].trim());
		
		if (!engine.getBoard().isLocationEmpty(x, y)) {
			System.out.println("Invalid move '" + input + "', position is already occupied, try again:");
			return false;
		}
		
		engine.getBoard().setPosition(engine.playerSymbol, x, y);
		
		return true;
	}
	
	private void printHelpMessage() {
		   System.out.println("Please enter your next move.  A move should be of the form 'row,column'.  For example, '0,0' will set the upper left box.");
		
	}
	
	
	private Engine engine;
}