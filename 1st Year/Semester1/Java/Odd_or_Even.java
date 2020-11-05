package odd_Or_Even;

import java.util.Scanner;

public class Odd_or_Even {

	public static void main(String[] args) {
		
	Scanner userInput = new Scanner(System.in);
	System.out.println("Please enter an integer: ");
	int number = userInput.nextInt();
	
	boolean odd = (number % 2 == 1);
	
	if (odd)
	{
		System.out.println("The number " + number + " is odd.");
	}
	
	else
	{
		System.out.println("The number " + number + " is even.");
	}
	
	userInput.close();
	}

}
