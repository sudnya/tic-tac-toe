
import org.apache.commons.cli.CommandLine;  
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.BasicParser;  
import org.apache.commons.cli.ParseException;

public class TicTacToe {
	public static void main(String[] args) {
		
		   // create Options object
		   Options options = new Options();

		   // add t option
		   options.addOption("t", false, "display current time");
		
		   CommandLineParser parser = new BasicParser();
		   try 
		   {
			   CommandLine cmd = parser.parse(options, args);
		   }
		   catch (ParseException e)
		   {
				// TODO Auto-generated catch block
				e.printStackTrace();
		   }
		
		   System.out.println("Hello World");
		   System.out.println("1. Start a new game.");
		   System.out.println("2. Quit a game.");
		   System.out.println("3. Select an opponent.");     
		   System.out.println("4. Complete a move.");     
		   System.out.println("5. Determine if there is a winner or if a game is not winnable.");
		 }
}
