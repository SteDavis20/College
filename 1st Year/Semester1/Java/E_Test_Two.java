package eTest_Two;

import java.util.Scanner;

public class E_Test_Two {

	public static final int CONSTANT_TWO = 2;
	public static void main(String[] args) {
		
		boolean finished = false;	
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter an integer: ");
		int integer = input.nextInt();
		int[] startArray = new int[1];
		startArray[0] = integer;
		do
		{
			Scanner userInput = new Scanner(System.in);
			System.out.println("Enter an integer to add to the array (or 'quit' to finish):");
			if (userInput.hasNext("quit")) {
				finished = true;
			}
			else if (userInput.hasNextInt()) {
				int userSuppliedInteger = userInput.nextInt();
				int[] array = addIntToArray(userSuppliedInteger, startArray);
				int minimumValue = minimumValue(array);
				boolean palindromic = palindromic(array);
				String stringPalindromic = "";
				if (palindromic == true) {
					stringPalindromic = "palindromic.";
				}
				else {
					stringPalindromic = "not palindromic.";
				}
				System.out.println("The minimum value is " + minimumValue + ", and the values are " + stringPalindromic);
			}
			else {
				System.out.println("Invalid entry. Please try again.");
			}
		}	while (finished == false);
	}

	public static int minimumValue(int[] array) {
		int minimumValue;
		if (array != null) {
			minimumValue = array[1];
			int index = 0;
			for (index = 0; index<array.length; index++) {
				if (array[index] < minimumValue) {
					minimumValue = array[index];
				}
			}
		}
		else {
			minimumValue = 0;
		}
		return minimumValue;
	}
	
	public static boolean palindromic(int[] array) {
		boolean palindromic = false;

		int n = array.length;
		for (int index = 0; index<(array.length / 2); index++) {
			if (array[index] == array[index + n]) {
				palindromic = true;
			}
			else {
				palindromic = false;
			}
			n -= CONSTANT_TWO;
	}
		return palindromic;
	}
	
	public static int[] addIntToArray(int integer, int[] array) {
		int[] updatedArray = new int[array.length+1];
		System.arraycopy(array, 0, updatedArray, 0, array.length);
		updatedArray[updatedArray.length-1] = integer;
		return updatedArray;
	}
}







