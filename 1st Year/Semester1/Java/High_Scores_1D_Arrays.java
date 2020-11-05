/*
 * Write a program to maintain a list of the high scores obtained in a game.  The program should first ask the user
 * how many scores they want to maintain and then repeatedly accept new scores from the user and should add the
 * score to the list of high scores (in the appropriate position) if it is higher than any of the existing high scores.  
 * You must include the following functions:

initialiseHighScores () which sets all high scores to zero.

printHighScores() which prints the high scores in the format: “The high scores are 345, 300, 234”, for all existing 
high scores in the list (remember that sometimes it won’t be full).

higherThan() which takes the high scores and a new score and returns whether the passed score is higher than
any of those in the high score list.

insertScore() which takes the current high score list  and a new score and updates it by inserting the new score at
the appropriate position in the list
 */

package highScores_1D_Arrays;

import java.util.Scanner;

public class High_Scores_1D_Arrays {

	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);
		System.out.println("How many scores do you want to maintain?");
		int scores = userInput.nextInt();
		double[] highScores = new double[scores];
		// highScores = initialiseHighScores(highScores);
		boolean finished = false;
		do
		{
			System.out.println("Please enter a new score: (or enter quit if you wish to exit.)");
			if (userInput.hasNextDouble()) {
				double newScore = userInput.nextDouble();
			}
			else if (userInput.hasNext ("quit")) {
				System.out.println("Goodbye.");
				finished = true;
			}
			else {
				System.out.println("Invalid entry. Please try again.");
			}
		} while (finished == false);
	}
	//DONE
	public static void initialiseHighScores(double[] highScores){
		for (int index = 0; index<highScores.length; index++) {
			highScores[index]=0;
		}
	}
	//DONE in descending order
	public static void printHighScores(double[] highScores) {
		double temp = 0;
		for (int index = 0; index<highScores.length; index++) {
			for (int amountOfSwaps = highScores.length-1; amountOfSwaps>0; amountOfSwaps--) {
				if (highScores[index] < highScores[index++]) {
					temp = highScores[index++];
					highScores[index++] = highScores[index];
					highScores[index] = temp;
				}
			}
		}
		String stringHighScores = "The high scores are: " + highScores;
		System.out.print(stringHighScores);
	}
	//DONE
	public static boolean higherThan(double[] highScores, double newScore) {
		boolean higher = false;
		int index = 0;
		for (index = 0; index<highScores.length; index++) {
			if (newScore > highScores[index]) {
				higher = true;
			}
		}
		return higher;
	}

	// Adding new score to list and inserting in the correct ranked position
	public static void insertScore(double[] highScores, double newScore) {
		boolean run = true;
		while (run) {
			double [] updatedHighScores = new double[highScores.length+1];
			System.arraycopy(highScores, 0, updatedHighScores, 0, highScores.length);
			updatedHighScores[updatedHighScores.length-1] = newScore;
			boolean higher = higherThan(highScores, newScore);
			double temp = 0;
			if (higher == true) {
				for (int index = 0; index<highScores.length; index++) {
					for (int amountOfSwaps = updatedHighScores.length-1; amountOfSwaps>0; amountOfSwaps++) { 
						temp = updatedHighScores[index];
						updatedHighScores[index++] = updatedHighScores[index];
						updatedHighScores[index] = temp;
					}
				}
			}
		}
	}
}
