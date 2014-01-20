// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : The test class which pits AI playes to play against each other

package unitTest;

import tictactoe.AIType;
import tictactoe.GameEngine;
import tictactoe.GameEngineFactory;

import org.apache.commons.cli.CommandLine;  
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.BasicParser;  
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;


public class TestDriver {
	public static void main(String[] args) {

		Options options = createCommandLineOptions();
		
		try {
			runTest(options, args);
		} catch (Exception e) {
			printHelpMessage(options);
		}
	}
	
	static private void runTest(Options options, String[] args) throws ParseException {
		
		int iterations = getIterationCount(options, args);
		
		AIType[] aiTypes = {AIType.Champ, AIType.Toby, AIType.George};
		
		for (int player = 0; player != aiTypes.length; ++player) {
			for (int opponent = 0; opponent != aiTypes.length; ++opponent) {
				
				System.out.println("Playing " + aiTypes[player] +
					" (X) against " + aiTypes[opponent] + " (O)");
				
				int wins   = 0;
				int losses = 0;
				int ties   = 0;
				
				for (int i = 0; i < iterations; ++i) {
					GameEngine engine = GameEngineFactory.createGameEngine(aiTypes[player], aiTypes[opponent]);
					
					switch(engine.run()) {
						case PlayerWins: {
							++wins;
							break;
						} case PlayerLoses: {
							++losses;
							break;
						} case Tie: {
							++ties;
							break;
						} default:
					}
				}
				
				System.out.println(" wins:   " + wins  );
				System.out.println(" losses: " + losses);
				System.out.println(" ties:   " + ties  );

			}
		}
		
	}
	
	static private Options createCommandLineOptions() {
		Options options = new Options();
		
		options.addOption("h", "help", true, "Help, show options");
		options.addOption("i", "iterations", true,
			"The number of games to play for each combination of AIs.");

		return options;
	}
	
	static private void printHelpMessage(Options options) {
		HelpFormatter helper = new HelpFormatter();
		helper.printHelp("TestTicTacToe [options]", options);
	}
	
	static private int getIterationCount(
		Options options, String[] args) throws ParseException {
		
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = parser.parse(options, args);
		
		int iterations = Integer.parseInt(cmd.getOptionValue("i", "10"));

		return iterations;
	}

}
