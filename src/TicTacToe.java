// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : TicTacToe class that drives the game, it accepts user choices from a command line parser and calls
//         the game engine with those options
// Comment : I will need to figure out the coding style popular for java and clean this up accordingly


import org.apache.commons.cli.CommandLine;  
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.BasicParser;  
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;

public class TicTacToe {
	public static void main(String[] args) {
		
		   // create Options object
		   Options options = new Options();

		   // add option
		   options.addOption("h", "help", true, "Help, show options");
		   options.addOption("b", "begin", true, "Begin a new the game");
		   options.addOption("s", "player-symbol", true, "The symbol for the player to use: X or O (X goes first).");
		   options.addOption("t", "ai-type", true, "The artificial intelligence to use (Champ (plays to win), Toby (play to tie/draw), or George (plays randomly)");
		   // TODO:
		   options.addOption("w", "winnable", false, "Is winning possible?");
		   options.addOption("q", "quit", false, "Quit the game");
		   CommandLineParser parser = new BasicParser();
		   try {
			   CommandLine cmd = parser.parse(options, args);
			   
			   Engine engine = new Engine(cmd);
			   engine.run();
		   } catch (ParseException e) {
			    HelpFormatter helper = new HelpFormatter();
			   
			    helper.printHelp("TicTacToe [options]", options);
			    
				e.printStackTrace();
		   } catch (ClassNotFoundException e) {
			e.printStackTrace();
		   }
		   
		 }

}
