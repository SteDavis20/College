/* Write a program to allow a user to play a game of Rock-Paper-Scissors.  The user should be allowed to play a fixed number of times (say 5) and each time must be told if they win, lose or draw.  At the end a final score should be given.  Note that a for loop MUST be used.

In the game the user and the computer both have to choose one of Rock, Paper or Scissors.  If they choose the same object then it is a draw.  If not then one of them has won:  Rock beats Scissors, Scissors beats Paper and Paper beats Rock.

Sample input and output (User input is highlighted in bold and underlined):

Enter 1 (for Rock) or 2 (for Paper) or 3 (for Scissors): 1
You won this round as I chose Scissors
Enter 1 (for Rock) or 2 (for Paper) or 3 (for Scissors): 2
You won this round as I chose Rock
Enter 1 (for Rock) or 2 (for Paper) or 3 (for Scissors): 1
This round was a draw as I chose Rock too
Enter 1 (for Rock) or 2 (for Paper) or 3 (for Scissors): 3
You lost this round as I chose Rock
Enter 1 (for Rock) or 2 (for Paper) or 3 (for Scissors): 3
You lost this round as I chose Rock
The final score was Computer: 2 User: 2
 */

/* SELF ASSESSMENT
1. Did I use appropriate easy-to-understand, meaningful variables and CONSTANTS within the code? 
    Mark out of 10: 10
    Comment: Yes, I did not abbreviate any of my variable names or constant names making them easy to read.
2. Did I format the variable and CONSTANT names appropriate (in lowerCamelCase and UPPERCASE)?
    Mark out of 5: 5
    Comment: Yes, all of my variable names are in lowerCamelCase and all of my constant names are in UPPERCASE.
3. Did I generate the computer's choice in each game correctly using a Random number generator?
    Mark out of 10: 10
    Comment: Yes, I used a Random number generator and from running my code several times the computer's choice appears to be random every time.
4. Did I input the user's choice in each game correctly?
    Mark out of 10: 10
    Comment: Yes, when building my code I included a system.out.println(); line to output the user's input after each input and the user's choice was inputed correctly each time.
5. Did I correctly compare the choices and update the score appropriately?
    Mark out of 20: 20
    Comment: Yes, every time the computer and the user choose the same number the code outputs a draw and this does not alter the score. When either the computer or user win a round their score increases by 1.
6. Did I inform the user of who won each game (and why) correctly?
    Mark out of 10: 10
    Comment: Yes, after each round my code informs the user of who won the round and what the user and the computer chose.
7. Did I use an appropriate for loop to allow the player to play 5 games?  There should be only one loop.
    Mark out of 20: 20
    Comment: Yes, I used one for loop and had all my if, else-if statements inside this loop.
8. Did I output the final scores correctly after the 5 games were played. 
    Mark out of 10: 10
    Comment: Yes, by adding the user score and the computer score it can be seen that it adds up to 5 except for when there is one or more draws, where the total of the two scores will add up to less than 0.
9. How well did I complete this self-assessment?
    Mark out of 5: 5
    Comment: Very well. I believe I provided plenty of feedback and comments for each question.
Total Mark out of 100 (Add all the previous marks): 100
 */

package rock_Paper_Scissors;

import java.util.Scanner;
import java.util.Random;

public class Rock_Paper_Scissors {

	public static final int NUMBER_OF_PLAYS = 5;
	public static final int ROCK = 1;
	public static final int PAPER = 2;
	public static final int SCISSORS = 3;

	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);

		System.out.println("Let's play a game of rock, paper, scissors.");
		int count, userChoice;
		int userScore = 0;
		int computerScore = 0;

			for (count = 0; (count < NUMBER_OF_PLAYS); count++) {
				Random generator = new Random();
				int randomComputerChoice = generator.nextInt(3) +1;
				System.out.println("Enter 1 for Rock, 2 for Paper, or 3 for Scissors:");
				userChoice = userInput.nextInt();

				if (randomComputerChoice == userChoice ) {
					System.out.println("This round was a draw as I also chose " + userChoice + ".");
				}

				if (randomComputerChoice == ROCK && userChoice == PAPER) {
					System.out.println("You won this round as I chose Rock.");
					userScore ++;
				}
				else if (randomComputerChoice == ROCK && userChoice == SCISSORS) {
					System.out.println("You lost this round as I chose Rock.");
					computerScore ++;
				}

				if (randomComputerChoice == PAPER && userChoice == ROCK) {
					System.out.println("You lost this round as I chose Paper.");
					computerScore ++;
				}

				else if (randomComputerChoice == PAPER && userChoice == SCISSORS) {
					System.out.println("You won this round as I chose Paper.");
					userScore ++;
				}

				if (randomComputerChoice == SCISSORS && userChoice == ROCK) {
					System.out.println("You won this round as I chose Scissors.");
					userScore ++;
				}

				else if (randomComputerChoice == SCISSORS && userChoice == PAPER) {
					System.out.println("You lost this round as I chose Scissors.");
					computerScore ++;
				}
			}
			System.out.println("Final score: Computer: " + computerScore + ", User: " + userScore);

		userInput.close();
	}

}







