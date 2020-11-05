/*
 * Write a program which, given two integers (from the user) will compute the
 * Greatest Common Divisor (GCD) of those numbers.  As part of your solution
 * write and use the following functions:
 *  - getGreatestCommonDivisor which takes to integers and returns their
 *    greatest common divisor.
 *  - getNextDivisor() which takes a number and a divisor (of that number)
 *    and returns the next highest divisor of the number.  If there is no
 *    such divisor -1 is returned.
 * Ensure that you handle all possible errors.
 */

package greatest_Common_Divisor;

import java.util.Scanner;

public class Greatest_Common_Divisor {

	public static void main(String[] args) {
		System.out.println("Please enter two integer values separated by a space: ");
		Scanner userInput = new Scanner(System.in);
		int inputOne = userInput.nextInt();
		int inputTwo = userInput.nextInt();
		int greatestCommonDivisor = getGreatestCommonDivisor(inputOne, inputTwo);
		System.out.println("The greatest common divisor of your two integers is: " + greatestCommonDivisor);
	}

	
	public static int getGreatestCommonDivisor(int numberOne, int numberTwo) {
		int greatestCommonDivisor = 1;
		int highestDivisorNumberOne = getNextDivisor(numberOne, 1);
		if (numberTwo % highestDivisorNumberOne == 0) {
			greatestCommonDivisor = highestDivisorNumberOne;
			
		}
		return greatestCommonDivisor;
	}
	
	public static int getNextDivisor(int number, int divisor) {
		int nextHighestDivisor = 1;
		if (number % divisor !=0) {
			nextHighestDivisor = -1;
		}
//		while (number % divisor != 0 && divisor <= number) {
//			divisor++;
//		}
		
		while (divisor <= number) {
			if (number % divisor == 0) {
				divisor = nextHighestDivisor;
			}
			divisor++;
		}
		nextHighestDivisor = divisor;
		
		return nextHighestDivisor;

	}
	
	
	
	
}
