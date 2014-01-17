// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : TicTacToe class that drives the game, it accepts user choices from a command line parser and calls
//         the game engine with those options
// Comment : I will need to figure out the coding style popular for java and clean this up accordingly

package play;

import org.apache.commons.cli.CommandLine;  
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.BasicParser;  
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;

import tictactoe.GenericEngine;

public class TicTacToe {
	public static void main(String[] args) {
		
		   // create Options object
		   Options options = new Options();

		   // add option
		   options.addOption("h", "help", true, "Help, show options");
		   options.addOption("s", "player-symbol", true, "The symbol for the player to use: X or O (X goes first).");
		   options.addOption("a", "ai-type", true, "The artificial intelligence to use (Champ (plays to win), Toby (play to tie/draw), or George (plays randomly).");

		   CommandLineParser parser = new BasicParser();
		   try {
			   CommandLine cmd = parser.parse(options, args);
			   GenericEngine engine = GenericEngine.create(cmd, "play");
			   engine.run();
		   } catch (ParseException e) {
			    HelpFormatter helper = new HelpFormatter();
			    helper.printHelp("TicTacToe [options]", options);
				//e.printStackTrace();
		   } catch (ClassNotFoundException e) {
			   e.printStackTrace();
		   }
		   
		 }

}
