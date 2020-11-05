/* Write a java program which uses the Sieve of Eratosthenes to compute the prime numbers from 2 up to a given number N
 * which is entered by the user. As part of your solution, you must use an array to represent the sequence of numbers
 * (which may contain both crossed out and non-crossed out numbers). Use the following functions: (Check photo on phone).
 */

/* SELF ASSESSMENT 
   1.  createSequence:
Did I use the correct method definition?
Mark out of 5: 5
Comment: Yes, I used the correct method return type, header and parameters.
Did I create an array of size n (passed as the parameter) and initialise it?
Mark out of 5: 5
Comment: Yes, my array is the size of the number the user inputs (n) which is passed into the function as a parameter. My array is initialised here too.
Did I return the correct item?
Mark out of 5: 5
Comment: Yes, my method returns an array from 2 to n (user's input) inclusive.

   2.  crossOutMultiples
Did I use the correct method definition?
Mark out of 5: 5
Comment: Yes, I used the correct method return type, header and parameters.

Did I ensure the parameters are not null and one of them is a valid index into the array
Mark out of 2: 2
Comment: Yes, I have accounted for these possible errors.

Did I loop through the array using the correct multiple?
Mark out of 5: 5
Comment: Yes, my array checks for multiples of the required numbers.
Did I cross out correct items in the array that were not already crossed out?
Mark out of 3: 3
Comment: Yes, if a number in the array was a multiple of the passed in number excluding the number itself it was crossed out.


   3.  sieve   
Did I have the correct function definition?
Mark out of 5: 5
Comment: Yes, my function header, return type and parameters are correct.
Did I make calls to other methods?
Mark out of 5: 5
Comment: Yes, I called the createSequence method and the crossOutHigherMultiples method.      
Did I return an array with all non-prime numbers are crossed out?
Mark out of 2: 2
Comment: Yes, my code outputs the required result.


   4.  sequenceTostring  
Did I have the correct function definition? 
Mark out of 5: 5
Comment: Yes, my method has the correct function header, return type and parameters.


Did I ensure the parameter to be used is not null?
Mark out of 3: 3
Comment: Yes, my code accounts for this potential error.


Did I Loop through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets? 
Mark out of 10: 10
Comment: Yes, prime numbers remain normal and multiples(non-prime numbers) are crossed out accordingly.  

   5.  nonCrossedOutSubseqToString  
Did I have the correct function definition
Mark out of 5: 5
Comment: Yes, my method header, return type and parameters are correct.        

Did I ensure the parameter to be used is not null?  
Mark out of 3: 3
Comment: Yes, my code accounts for this potential error.


Did I loop through the array updating the String variable with just the non-crossed out numbers? 
Mark out of 5: 5
Comment: Yes, this method prints out just the prime numbers (nonCrossedOut numbers). 
   6.  main  
Did I ask  the user for input n and handles input errors?  
Mark out of 5: 5
Comments: Yes. If the input is <2 or if the input is not a number then the user is prompted to try again.


Did I make calls to other methods (at least one)?
Mark out of 5: 5
Comment: Yes, I call the createSequence, the sieve method, and the sequenceToString method. 


Did I print the output as shown in the question?  
Mark out of 5: 5
Comment:  Yes, my output is exactly as shown in the question.


   7.  Overall
Is my code indented correctly?
Mark out of 4: 4
Comments: Yes, I believe my code is indented appropriately.


Do my variable names make sense?
Mark out of 4: 4
Comments: Yes, I did not abbreviate my variable names to avoid confusion.


Do my variable names, method names and class name follow the Java coding standard
Mark out of 4: 4
Comments: Yes, where appropriate I use lowerCamelCase, for classes they begin with a capital letter.
      Total Mark out of 100 (Add all the previous marks): 100
 */


package sieve_Of_Eratosthenes;

import java.util.Scanner;
import java.security.KeyStore.Entry;
import java.util.Arrays;		// need this to print out array(s)

public class Sieve_Of_Eratosthenes {

	public static final int TWO = 2;
	public static final int NEGATIVE_CONVERTER = -1;
	public static void main(String[] args) {

		System.out.println("Please enter a positive integer whose value is at least 2: ");
		Scanner userInput = new Scanner(System.in);
		boolean validEntry = false;
		while(validEntry == false) {
			if (userInput.hasNextInt()) {
				int number = userInput.nextInt();
				if(number>=TWO) {
					int[] originalArray = createSequence(number);
					sequenceToString(originalArray);
					sieve(number);
					validEntry = true;
				}
				else {
					System.out.println("Invalid entry. Try again.");
				}
			}
			else {
				System.out.println("Invalid entry. Try again.");
			}
		}
	}
	public static int[] createSequence(int integer) {
		int[] sequence = new int[integer-1];
		int nextNumber = 2;
		for(int index = 0; index < sequence.length; index++) {
			sequence[index] = nextNumber;
			nextNumber++;
		}
		return sequence;
	}
	public static int[] crossOutHigherMultiples(int[] sequenceOfAllNumbers, int n) {
		if(sequenceOfAllNumbers!=null && n >= 2 && n<=sequenceOfAllNumbers.length+1) {
			int i = 0;
			for (i = 0; i<=sequenceOfAllNumbers.length-1; i++ ) {
				if (sequenceOfAllNumbers[i] % n == 0 && sequenceOfAllNumbers[i] > 0 && sequenceOfAllNumbers[i] != n) {
					sequenceOfAllNumbers[i] *= NEGATIVE_CONVERTER;
				}			
			}
		}
		return sequenceOfAllNumbers;		
	}
	public static int[] sieve(int N) {
		int[] array = createSequence(N);		// N-1 is done in createSequence function so don't do here (would = N-2)
		for (int i=0; i<array.length-1; i++) {
			if(array[i]>0 && array[i]<Math.sqrt(N)) {
				array = crossOutHigherMultiples(array, array[i]);
				sequenceToString(array);
			}	
		}
		notCrossedOutSubseqToString(array);
		return array;
	}
	public static String sequenceToString(int[] sequenceOfAllNumbers) {
		String stringRepresentationIncludingCrossedOut = "";
		if(sequenceOfAllNumbers!=null) {
			for (int i = 0; i<=sequenceOfAllNumbers.length-1; i++) {
				if (sequenceOfAllNumbers[i] == TWO) {
					stringRepresentationIncludingCrossedOut += TWO; 
				}
				else if (sequenceOfAllNumbers[i] < 0) {
					stringRepresentationIncludingCrossedOut += ", " + "[" + Math.abs(sequenceOfAllNumbers[i]) + "]";
				}
				else {
					stringRepresentationIncludingCrossedOut += ", " + sequenceOfAllNumbers[i];
				}
			}
		}
		System.out.println(stringRepresentationIncludingCrossedOut);
		return stringRepresentationIncludingCrossedOut;
	}
	public static String notCrossedOutSubseqToString(int[] sequenceOfAllNumbers) {
		String stringRepresentationOfPrimes = "";
		if(sequenceOfAllNumbers!=null) {
			int i = 0;
			for (i = 0; i<=sequenceOfAllNumbers.length-1; i++) {
				if (sequenceOfAllNumbers[i] == TWO) {
					stringRepresentationOfPrimes += TWO;
				}
				else if (sequenceOfAllNumbers[i]>0) {
					stringRepresentationOfPrimes += ", " + sequenceOfAllNumbers[i];
				}
			}
		}
		System.out.println(stringRepresentationOfPrimes);
		return stringRepresentationOfPrimes;	
	}
}