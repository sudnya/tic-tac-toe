package tictactoe.implementation.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tictactoe.GameResult;
import tictactoe.PlayerType;
import tictactoe.implementation.ai.AI;
import tictactoe.implementation.engine.BoardGameEngine;

public class UserInput {
	public UserInput(BoardGameEngine e, PlayerType playerType, AI ai) {

		this.engine     = e;
		this.playerType = playerType;
		this.ai         = ai;
	}
	
	public GameResult run() {
		// when human player chooses 'O'
		if (aiMovesFirst()) {
			ai.move();
		}

		while (true) {
			if (!checkBoard()) {
				break;
			}
			
			String input = getUserInput();
			
			if (isQuit(input))
				break;
			
			if (isWin(input)) {
				isWinnable();
				engine.getBoard().print();
				continue;
			}
			
			if (isRestart(input)) {
				engine.restart();
				engine.getBoard().print();
				continue;
			}
			
			if (validateInput(input)) {
				if (processMove(input)) {
					if (!checkBoard())
						break;

					ai.move();
				}
			} else {
				printHelpMessage();
			}

			engine.getBoard().print();
		}
		
		return getGameResult();
	}
	
	private boolean checkBoard() {
        if (engine.getBoard().gameOver()) {
			System.out.println("The game is over. The final board is:");

			engine.getBoard().print();
			
			switch (getGameResult()) {
				case PlayerWins: {
					System.out.println("You win!");
					break;
				} case PlayerLoses: {
					System.out.println("You lose!");
					break;
				} case Tie: {
					System.out.println("An even match!");
					break;
				} default:
			}

			return false;
		}
		// continue playing
		return true;
	}
	
	private GameResult getGameResult() {
		assert(engine.getBoard().gameOver());
		
		if (engine.getBoard().isTied()) {
			return GameResult.Tie;
		} else if (engine.getBoard().getWinner() == playerType) {
			return GameResult.PlayerWins;
		} else {
			return GameResult.PlayerLoses;
		}
	}
	
	private boolean aiMovesFirst() {
		// only if human player chooses 'O' since
		// http://en.wikipedia.org/wiki/Tic_tac_toe#Number_of_terminal_positions 
		// says that X starts
		return playerType == PlayerType.O;
	}
	
	private String getUserInput() {
		System.out.print("Please enter which location to move: ");
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
		if (input.trim().equalsIgnoreCase("quit") || input.trim().equalsIgnoreCase("q"))
			return true;
		return false;
	}

	private boolean isWin(String input) {
		if (input.trim().equalsIgnoreCase("win") || input.trim().equalsIgnoreCase("w"))
			return true;
		return false;
	}

	private boolean isRestart(String input) {
		if (input.trim().equalsIgnoreCase("restart") || input.trim().equalsIgnoreCase("r")) {
			return true;
		} else {
			return false;
		}
	}
	
	private void isWinnable() {
		// iterate over all possibilities and check if not tie
		if (engine.getBoard().canAnyPlayerWin(playerType)) {
			System.out.println("It is still possible for this game to be won (and not a tie)");
		} else {
			System.out.println("Neither player can win this game, a tie is inevitable.");
		}
	}
	private boolean validateInput(String input) {
		if (!input.contains(",")) {
			return false;
		}

		if (input.split(",").length != 2) {
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
			System.out.println("Invalid move '" + input + "', position is already occupied, try again!");
			return false;
		}

		engine.getBoard().setPosition(playerType, x, y);	
		
		return true;
	}
	
	private void printHelpMessage() {
        System.out.println("Possible options....");
        System.out.println(" (r)estart - reset the board and start over.");
        System.out.println(" (w)in     - is it possible for either player to win the game?");
        System.out.println(" (q)uit    - exit the game.");
        System.out.println(" ");
        System.out.println(" OR ");
        System.out.println(" ");
        System.out.println("Please enter your next move.  A move should be of the form 'x, y'.");
        System.out.println("For example, '0,0' will set the upper left box.");
	}
		
	private BoardGameEngine engine;
	private PlayerType      playerType;
	private AI              ai;
}
