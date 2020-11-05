/*An interface with the following (abstract) methods:
 * public void emptyGrid();
 * public String toString();
 * public boolean isValidColumn(int column);
 * public boolean isColumnFull(int column);
 * public void dropPiece(ConnectPlayer player, int column);
 * public boolean didLastPieceConnect4();
 * public boolean isGridFull();   
 */

package connect4Game;

public interface Connect4Grid {

	public abstract void emptyGrid();
	public abstract String toString();
	public abstract boolean isValidColumn(int column);
	public abstract boolean isColumnFull(int column);
	public abstract void dropPiece(ConnectPlayer player, int column);
	public abstract boolean didLastPieceConnect4();
	public abstract boolean isGridFull(); 
}
