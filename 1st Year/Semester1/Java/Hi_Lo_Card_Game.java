/* SELF ASSESSMENT
   1. Did I use appropriate CONSTANTS instead of numbers within the code?
       Mark out of 5: 5
       Comment: Yes, I assigned constants for Jack, Queen, King and Ace instead of numbers.
   2. Did I use easy-to-understand, meaningful CONSTANT names formatted correctly in UPPERCASE?
       Mark out of 5: Yes
       Comment: My constants are all in capitals and easy to understand.
   3. Did I use easy-to-understand meaningful variable names?
       Mark out of 10: 10
       Comment: Yes, I did not abbreviate any variable names in order to make my code clear for anybody to read.
   4. Did I format the variable names properly (in lowerCamelCase)?
       Mark out of 5: 5
       Comment: Yes, all of my variables are in lowerCamelCase.
   5. Did I indent the code appropriately?
       Mark out of 10: 10
       Comment: Yes, my code is indented as it should be.
   6. Did I use an appropriate loop to allow the user to enter their guesses until they win or lose?
       Mark out of 20: 20
       Comment: Yes, the user repeatedly guesses whether the next card will be higher, lower or equal to until they get 4 correct answers in a row.
   7. Did I check the input to ensure that invalid input was handled appropriately?
       Mark out of 10: 10
       Comment: Yes, if the user enters an invalid input then the code asks the user to try again.
   8. Did I generate the cards properly using random number generation (assuming all cards are equally likely each time)?
       Mark out of 10: 10
       Comment: Yes, each card has an equal chance of being chosen and each card is randomly chosen.
   9. Did I output the cards correctly as 2, 3, 4, ... 9, 10, Jack, Queen, King?
       Mark out of 10: 10
       Comment: Yes, using Strings I outputted all the cards correctly.
   10. Did I report whether the user won or lost the game before the program finished?
       Mark out of 10: 10
       Comment: Yes, before the loop finishes, the code informs the user whether they won or not.
   11. How well did I complete this self-assessment?
       Mark out of 5: 5
       Comment: I believe I provided efficient feedback via the comments.
   Total Mark out of 100 (Add all the previous marks): 100
*/

package hi_Lo_Card_Game;

import java.util.Random;
import java.util.Scanner;

public class Hi_Lo_Card_Game {

	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	public static final int ACE = 14;

	public static void main(String[] args) {

		Random randomGenerator = new Random();
		int currentRandomCard = randomGenerator.nextInt(13);
		currentRandomCard += 2;
		int nextRandomCard = 0;
		int correctAnswers = 0;
		
		String currentCardName = "";
		if (currentRandomCard == JACK)
		{
			currentCardName = "Jack";
		}
		else if (currentRandomCard == QUEEN)
		{
			currentCardName = "Queen";
		}
		else if (currentRandomCard == KING)
		{
			currentCardName = "King";
		}
		else if (currentRandomCard == ACE)
		{
			currentCardName = "Ace";
		}
		else
		{
			currentCardName = "" + currentRandomCard;
		}

		System.out.println("The card is a " + currentCardName + ". "
				+ "\nDo you think the next card will be higher, lower or equal? (Answers are case sensitive.)");

		boolean finished = false;
		do
		{
			nextRandomCard = randomGenerator.nextInt(13);
			nextRandomCard += 2;
			Scanner userInput = new Scanner(System.in);


			if (userInput.hasNext("higher"))
			{
				if (nextRandomCard > currentRandomCard)
				{
					correctAnswers += 1;
				}
				else
				{
					correctAnswers = 0;
				}
			}

			else if (userInput.hasNext("lower"))
			{
				if (nextRandomCard < currentRandomCard)
				{
					correctAnswers += 1;
				}
				else 
				{
					correctAnswers = 0;
				}

			}

			else if (userInput.hasNext("equal"))
			{
				if (currentRandomCard == nextRandomCard)
				{
					correctAnswers += 1;
				}
				else 
				{
					correctAnswers = 0;
				}
			}

			else
			{
				System.out.println("Invalid entry. Try again.");
			}

			if (correctAnswers == 4)
			{
				finished = true;
				System.out.println("The card is a " + nextRandomCard + "." + "\nWell done, you won.");
				userInput.close();
			}

			String nextCardName = "";
			if (nextRandomCard == JACK)
			{
				nextCardName = "Jack";
			}
			else if (nextRandomCard == QUEEN)
			{
				nextCardName = "Queen";
			}
			else if (nextRandomCard == KING)
			{
				nextCardName = "King";
			}
			else if (nextRandomCard == ACE)
			{
				nextCardName = "Ace";
			}
			else
			{
				nextCardName = "" + nextRandomCard;
			}
		
			if (correctAnswers < 4 )
			{
				System.out.println("The card is a " + nextCardName + ".\nDo you think the next card will be higher"
					+ ", lower or equal? (Answers are case sensitive.)");
			} 
			
			currentRandomCard = nextRandomCard;

		}	while (!finished);

	}

}
