// Author: Sudnya Padalikar
// Date  : Jan 15 2014
// Brief : The test class which pits AI playes to play against each other

package unitTest;

import tictactoe.GenericEngine;

import org.apache.commons.cli.CommandLine;  
import org.apache.commons.cli.CommandLineParser;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.BasicParser;  
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;


public class TestDriver {
	public static void main(String[] args) {
	   // create Options object
	   Options options = new Options();

	   // add option
	   options.addOption("h", "help", true, "Help, show options");
	   options.addOption("i", "iterations", true, "The number of games to play for each combination of AIs.");
	   
	   String[] aiTypes = {"Champ", "George", "Toby"};
	   
	   CommandLineParser parser = new BasicParser();
	   try {
		   CommandLine cmd = parser.parse(options, args);
		   
		   int iterations = Integer.parseInt(cmd.getOptionValue("i", "100"));
		   
		   for (int player = 0; player != aiTypes.length; ++player) {
			   for (int opponent = 0; opponent != aiTypes.length; ++opponent) {
				   System.out.println("Playing " + aiTypes[player] + " (X) against " + aiTypes[opponent] + " (O)");
				   
				   GenericEngine engine = new TestEngine(aiTypes[player], aiTypes[opponent], iterations);
				   engine.run();
			   }
		   }
		   
	   } catch (ParseException e) {
		    HelpFormatter helper = new HelpFormatter();
		    helper.printHelp("TestDriver [options]", options);
			//e.printStackTrace();
	   } catch (ClassNotFoundException e) {
		    e.printStackTrace();
	   }
	   
	 }

}
