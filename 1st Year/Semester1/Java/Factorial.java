/*
 * Write a program to calculate factorial of some number (i.e. number!).
 * For example 4! = 1*2*3*4 = 24
 */

package factorial;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
	
	System.out.println("Please enter a number so I can output it's factorial.");
	Scanner userInput = new Scanner(System.in);	
	int number = userInput.nextInt();
	int count = 1;
	int factorial = 1;

	for (count = 1; count<=number; count++)
	{
		factorial = factorial * count;
	}
	
	System.out.println("" + number + "! = " + factorial);
	userInput.close();
	}

}

// with 32! I get a negative value? double can store larger numbers than int.