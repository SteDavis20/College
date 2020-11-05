/* Write a Java class Swap which swaps around two numbers
 * to be inputted by the user without using a third
 * variable.

*/
package swap;

import java.util.Scanner;

public class Swap {

	private int number1;
	private int number2;
	
	public int getNumber1() {
		return number1;
	}
	public void setNumber1(int number) {
		number1 = number;
	}
	
	public int getNumber2() {
		return number2;
	}
	public void setNumber2(int number) {
		number2 = number;
	}
	
	public static void main(String[] args) {
		Swap input1 = new Swap();
		Swap input2 = new Swap();
		Scanner userInput = new Scanner(System.in);
		System.out.println("Please enter two numbers separated by spaces: ");
		input1.setNumber2(userInput.nextInt());
		input2.setNumber1(userInput.nextInt());
		System.out.println("Your first number is: " + input2.getNumber1() + 
				" and your second number is: " + input1.getNumber1());
	}
}
