package connect4Game;

/*A class which extends the ConnectPlayer abstract class and whose objects represent a human player
 * (a user) who is involved in a Connect Four game.
 */
import java.util.Scanner;

public class C4HumanPlayer extends ConnectPlayer{

	private String colour;

	C4HumanPlayer(String colour) {
		super(colour);	
	}

	@Override
	public void dropPiece(ConnectPlayer player, Connect4Grid2DArray grid) {
		Scanner scanner = new Scanner(System.in);
		boolean successfulMove = false;
		do {
			System.out.println("Please enter the column you want to drop your piece in: ");
			int playerColumn = scanner.nextInt();
			if((grid.isValidColumn(playerColumn)==true) && (grid.isColumnFull(playerColumn)==false)) {
				grid.dropPiece(player, playerColumn);
				successfulMove = true;
			}
			else {
				System.out.println("Invalid move. Try again.");
			}
		} while(successfulMove == false);
	}
}
