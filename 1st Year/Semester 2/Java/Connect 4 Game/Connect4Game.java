/* SELF ASSESSMENT

Connect4Game class (35 marks)
My class creates references to the Connect 4 Grid and two Connect 4 Players:
Comment: My class creates a reference to the Connect4Grid2DArray class and to two connect 4 players.
It asks the user whether he/she would like to play/quit inside a loop. 
Comment: The user is asked if they would like to play inside a loop, the loop terminating when the user says no.
If the user decides to play then: 1. Connect4Grid2DArray is created using the Connect4Grid interface, 
2. the two players are initialised - must specify the type to be ConnectPlayer, and 
3. the game starts. 
Comment:
In the game, I ask the user where he/she would like to drop the piece. 
Comment: The user is asked which column they want to drop their piece in.
I perform checks by calling methods in the Connect4Grid interface. 
Comment: I check if the desired move is valid by calling the methods isColumnFull() and isValidColumn().
Finally a check is performed to determine a win. 
Comment: My code checks if a player has connected four either horizontally, vertically or diagonally, outputting who won if this is true.

Connect4Grid interface (10 marks)
I define all 7 methods within this interface.
Comment: All 7 methods are defined within this interface.

Connect4Grid2DArray class (25 marks) 
My class implements the Connect4Grid interface. 
Comment: My Connect4Grid2DArray class implements the Connect4Grid interface.
It creates a grid using a 2D array Implementation of the method to check whether the column to drop the piece is valid.
Comment: Yes - if the column is not within the size of the grid the method returns false.
It provides as implementation of the method to check whether the column to drop the piece is full.
Comment: Yes, if the column is full the user/computer will be asked to make a different move.
It provides as implementation of the method to drop the piece.
Comment: The method dropPiece is used to implement the dropping of the piece into the desired column.
It provides as implementation of the method to check whether there is a win.
Comment: If the user/computer has connected 4 pieces diagonally, vertically or horizontally, they win and an output message is displayed.

ConnectPlayer abstract class (10 marks)
My class provides at least one non-abstract method and at least one abstract method. 
Comment: The non-abstract method provided is getColour() and the abstract method is dropPiece.

C4HumanPlayer class (10 marks)
My class extends the ConnectPlayer class and overrides the abstract method(s). 
Comment: My C4HumanPlayer class extends the ConnectPlayer class and overrides the abstract method dropPiece. 
It provides the Human player functionality.
Comment: The human player is asked which column they want to enter their piece into and my code deals with this.

C4RandomAIPlayer class (10 marks)
My class extends the ConnectPlayer class and overrides the abstract method(s). 
Comment: My C4RandomAIPlayer class extends the ConnectPlayer class and overrides the abstract method dropPiece. 
It provides AI player functionality. 
Comment: The computer randomly keeps choosing a column to drop their piece into until the column is valid.

Total Marks out of 100:
 */

package connect4Game;

/*A class whose objects represent an executing Connect Four two-player game application. The class will allow a user
 * to start a new Connect Four game involving two players whose types are chosen by the user. This class consists of
 * a mainline which handles user input. The class creates a grid using the connect4Grid interface and a human player
 * and a computer player using the player abstract class.
 */
//object of this class represents a game.

import java.util.Scanner;

public class Connect4Game {

	boolean resetGrid = true;

	public void playGame(ConnectPlayer player1, ConnectPlayer player2, boolean gameOver) {
		Connect4Grid2DArray grid = new Connect4Grid2DArray();
		while(gameOver==false) {
			if(resetGrid) {
				grid.emptyGrid(); 
				resetGrid = false;
			}
			player1.dropPiece(player1, grid);
			if(grid.didLastPieceConnect4() || grid.isGridFull()) {
				gameOver = true;
				System.out.println("Game Over!");
			}
			else {
				player2.dropPiece(player2, grid);
				if(grid.didLastPieceConnect4() || grid.isGridFull()) {
					gameOver = true;
					System.out.println("Game Over!");
				}
			}
		}
	}
	//-----------------------------------------------------------------------------
	public static void main(String[] args) { 
		boolean newGame = true;
		do {
			System.out.println("Would you like to play a new game?");
			Scanner userInput = new Scanner(System.in);
			String input = userInput.nextLine();
			if(input.equalsIgnoreCase("Yes")) {
				newGame = true;
				Connect4Game game = new Connect4Game();
				System.out.println("Will player1 be a human or a computer? Enter 'human' or 'computer': ");
				String player1 = userInput.nextLine();	
				if(player1.equalsIgnoreCase("human")) {
					ConnectPlayer human = new C4HumanPlayer("white");
					System.out.println("Will player2 be a human or a computer? Enter 'human' or 'computer': ");
					String player2 = userInput.nextLine();
					if(player2.equalsIgnoreCase("human")) {
						ConnectPlayer human2 = new C4HumanPlayer("green");
						game.playGame(human, human2, !newGame);
					}
					else if (player2.equalsIgnoreCase("computer")) {
						ConnectPlayer computer2 = new C4RandomAIPlayer("black");
						game.playGame(human, computer2, !newGame);
					}
					else {
						System.out.println("Invalid entry. Start again.");
						newGame = false;
					}
				}
				else if(player1.equalsIgnoreCase("computer")) {
					ConnectPlayer computer = new C4RandomAIPlayer("white");
					System.out.println("Will player2 be a human or a computer? Enter 'human' or 'computer': ");
					String player2 = userInput.nextLine();
					if(player2.equalsIgnoreCase("human")) {
						ConnectPlayer human2 = new C4HumanPlayer("green");
						game.playGame(computer, human2, !newGame);
					}
					else if (player2.equalsIgnoreCase("computer")) {
						ConnectPlayer computer2 = new C4RandomAIPlayer("black");
						game.playGame(computer, computer2, !newGame);
					}
					else {
						System.out.println("Invalid entry. Start again.");
						newGame = false;
					}
				}
				else {
					System.out.println("Invalid entry. Start again.");
				}
			}
			else {
				System.out.println("Goodbye!");
				newGame = false;
			}
		} while(newGame);
	}
}
