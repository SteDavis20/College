package eTestAge;

import java.util.Scanner;

public class E_Test_Age {

	public static final int MAX_TEENAGE_AGE = 19;
	public static final int MIN_TEENAGE_AGE = 13;
	public static final int PERCENTAGE_CONVERTER = 100;
	public static void main(String[] args) {

		double teenagers = 0;
		double percentageTeenagers = 0;
		double totalAge = 0;
		double numberOfAgesEntered = 0;
		double averageAge = 0;
		boolean finished = false;
		do
		{
			Scanner userInput = new Scanner (System.in);
			System.out.println("Please enter a person's age or enter 'quit'.");

			if (userInput.hasNextDouble())
			{
				double personsAge = userInput.nextDouble();
				numberOfAgesEntered++; 
				totalAge += personsAge;
				averageAge = (totalAge / numberOfAgesEntered);

				if (personsAge <= MAX_TEENAGE_AGE && personsAge >= MIN_TEENAGE_AGE)
				{
					teenagers++;
				}

				percentageTeenagers = (teenagers / numberOfAgesEntered) * PERCENTAGE_CONVERTER;
				System.out.println("The average age entered is " + averageAge + " and " 
						+ percentageTeenagers + "% of the ages correspond to teenagers.");

			}

			else if (userInput.hasNext("quit"))
			{
				finished = true;
				userInput.close();
			}

			else 
			{
				System.out.println("Invalid entry. Try again.");
			}
		}	while (!finished);

	}

}
