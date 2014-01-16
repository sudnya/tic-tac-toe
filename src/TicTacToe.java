
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

		   // add t option
		   options.addOption("s", "player-symbol", true, "The symbol for the player to use: X or O (X goes first).");
		   options.addOption("t", "ai-type", true, "The artificial intelligence to use (Champ, George, or Toby)");
		
		   CommandLineParser parser = new BasicParser();
		   try 
		   {
			   CommandLine cmd = parser.parse(options, args);
			   
			   Engine engine = new Engine(cmd);
			   engine.run();
		   }
		   catch (ParseException e)
		   {
			    HelpFormatter helper = new HelpFormatter();
			   
			    helper.printHelp("TacTacToe [options]", options);
			    
			   // TODO Auto-generated catch block
				e.printStackTrace();
		   }
		   catch (ClassNotFoundException e)
		   {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
		   
		 }

}
