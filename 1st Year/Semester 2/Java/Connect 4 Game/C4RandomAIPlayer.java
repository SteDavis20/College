package connect4Game;

/* A class which extends the ConnectPlayer abstract class and whose objects represent Random AI Player
 * (a computer) which is involved in a Connect Four game.
 */
import java.util.Random;

public class C4RandomAIPlayer extends ConnectPlayer{
	
	private String colour;

	C4RandomAIPlayer(String colour) {
		super(colour);
	}

	@Override
	public void dropPiece(ConnectPlayer computer, Connect4Grid2DArray grid) {
		Random generator = new Random();
		int column = generator.nextInt(7);
		while (grid.isValidColumn(column)==false && grid.isColumnFull(column)==false) {
			column = generator.nextInt(7);
		}
		grid.dropPiece(computer, column);
		System.out.println("Computer Move: "+column);
	}
}
