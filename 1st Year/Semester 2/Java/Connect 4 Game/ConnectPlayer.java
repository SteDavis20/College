package connect4Game;

/*An abstract class that provides the operations (methods) which any type of Connect Four Player 
 * class must override.
 */

public abstract class ConnectPlayer {
	
	private String colour;
	
	public ConnectPlayer(String colour) {
		this.colour=colour;
	}
	
	public abstract void dropPiece(ConnectPlayer player, Connect4Grid2DArray grid);

	public String getColour() {
		return colour;
	}
}
