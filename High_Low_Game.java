/*
 * Write a program to support the High-Low game:
 * Give a user 5 tries to guess a number between 0 and 20 (inclusive).  For each incorrect
 * guess tell the user if the guess is too big or too small.  At the end tell them how
 * many tries it took them (if they got it right) or  what the number was (if they didn’t
 * guess it correctly).
 */

package high_Low_Game;

import java.util.Scanner;
import java.util.Random;

public class High_Low_Game {

	public static final int NUMBER_OF_GUESSES_ALLOWED = 5;
	public static final int MIN_VALUE = 0;
	public static final int MAX_VALUE = 20;
	
	public static void main(String[] args) {
		
		Random generator = new Random();
		int number = generator.nextInt(21);
		Scanner userInput = new Scanner(System.in);
		System.out.print("Guess a number between 0 and 20 inclusive: ");
		int userGuess = 0;
		int guessesUsed = 0;
		
		for (guessesUsed = 0; guessesUsed<NUMBER_OF_GUESSES_ALLOWED; guessesUsed++)
		{
		userGuess = userInput.nextInt();
			if (userGuess == number)
			{
				System.out.println("Well done, you guessed correctly. It took you " + guessesUsed + " tries.");
				guessesUsed = 5;
			}
			
			else if (userGuess < number)
			{
				System.out.println("Too low. Aim higher.");
			}
			
			else if (userGuess > number)
			{
				System.out.println("Too high. Aim lower.");
			}
		}
		if (userGuess != number)
		{
			System.out.println("Hard luck. You have ran out of guesses. The correct answer was " + number);
		}
		userInput.close();
	}

}
