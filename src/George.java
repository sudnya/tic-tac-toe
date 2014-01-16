
import java.util.Random;

public class George extends AI {
	George(Engine e) {
		super(e);
	}

	public int getBoardValue(Board board, char symbol) {
		return 0;
	}
	
	public int computeValue(Board board, char symbol, char originalSymbol) {
		return random.nextInt();
	}
	
	static private Random random = new Random();
}
