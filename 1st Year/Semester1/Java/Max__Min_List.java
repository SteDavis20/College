package max_min_list;

import java.util.Scanner;

public class Max__Min_List {

	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);
		int maxNumber, minNumber, currentNumber;
		
		System.out.print("Please enter a list of whole numbers separated by spaces and no commas e.g 1 2 5 7 89.");

		currentNumber = 0 ;
		minNumber = userInput.nextInt();
		maxNumber = minNumber;
		System.out.println("" + currentNumber + minNumber + maxNumber);
		while (userInput.hasNextInt()) 
		{ 
			currentNumber = userInput.nextInt(); //currentNumber = first number user entered, then 2nd, then 3rd etc.
			
			if (currentNumber < minNumber) 
			{
			currentNumber = minNumber;
			}
		
			else if (currentNumber > maxNumber) 
			{
			currentNumber = maxNumber;
			}
		}
		System.out.println("The max number is " + maxNumber + ", and the min number is " + minNumber);
		
		userInput.close();
	}

}
