package play;

import org.apache.commons.cli.CommandLine;  
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.BasicParser;  
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;

import tictactoe.AIType;
import tictactoe.GameEngine;
import tictactoe.GameEngineFactory;
import tictactoe.PlayerType;

public class TicTacToe {
	public static void main(String[] args) {

		Options options = createCommandLineOptions();
		
		try {
			runGame(args, options);
		} catch (Exception e) {
			printHelpMessage(options);
		}
	}
	
	static private void runGame(String[] args, Options options) throws ParseException {
		GameEngine engine = createAndInitializeGameEngine(options, args);
				
		engine.run();
	}
	
	static private Options createCommandLineOptions() {
		Options options = new Options();

		options.addOption("h", "help", true, "Help, show options");
		options.addOption("s", "player-symbol", true, 
			"The symbol for the player to use: X or O (X goes first).");
		options.addOption("a", "ai-type", true,
			"The artificial intelligence to use (Champ (plays to win), Toby " +
			" (play to tie/draw), or George (plays randomly).");

		return options;
	}
	
	static private void printHelpMessage(Options options) {
		HelpFormatter helper = new HelpFormatter();
		helper.printHelp("TicTacToe [options]", options);
	}
	
	static private GameEngine createAndInitializeGameEngine(
		Options options, String[] args) throws ParseException {
		
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = parser.parse(options, args);
		
		GameEngine engine = GameEngineFactory.createGameEngine(
			getPlayerType(cmd), getAIType(cmd));
				
		return engine;
	}
	
	static private PlayerType getPlayerType(CommandLine cmd) {
		String s = cmd.getOptionValue("s", "X");
		
		if (s.equalsIgnoreCase("X")) {
			return PlayerType.X;
		} else if (s.equalsIgnoreCase("O")) {
			return PlayerType.O;
		} else {
			throw new IllegalArgumentException();
		}

	}
	
	static private AIType getAIType(CommandLine cmd) {
		String s = cmd.getOptionValue("a", "Champ");
		
		if (s.equalsIgnoreCase("Champ")) {
			return AIType.Champ;
		} else if (s.equalsIgnoreCase("Toby")) {
			return AIType.Toby;
		} else if (s.equalsIgnoreCase("George")) {
			return AIType.George;
		} else {
			throw new IllegalArgumentException();
		}

	}
	
}
