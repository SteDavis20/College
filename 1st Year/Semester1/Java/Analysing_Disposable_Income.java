/* SELF ASSESSMENT
   1. Did I use easy-to-understand meaningful variable and CONSTANT names? 
       Mark out of 10: 10
       Comment: Yes, all of my variable and CONSTANT names are understandable and I did not use abbreviations.
   2. Did I format the variable and CONSTANT names properly (in lowerCamelCase and UPPERCASE_WITH_UNDERSCORES)?
       Mark out of 10: 10
       Comment: Yes, my variable names are in lowerCamelCase and my CONSTANT names are in UPPERCASE_WITH_UNDERSCORES.
   3. Did I indent the code appropriately? 
       Mark out of 10: 9
       Comment:Yes, I indented my code very well but I feel it could be a bit neater.
   4. Did I read the input correctly from the user using appropriate questions?
       Mark out of 15: 15
       Comment: Yes, my questions are clear and understandable for the user to answer.
   5. Did I computer the disposable income and disposable income percentage correctly, and output it in the correct format?
       Mark out of 15: 15
       Comment: Yes, my output is exactly as it should be.
  6. Did I use an appropriate series of if statements to generate the income analysis to the user?
       Mark out of 25: 25
       Comment: Yes, my if statements provide the required income analysis.
   7. Did I provide the correct output for each possibility in an easy to read format?
       Mark out of 10: 10
       Comment: All of my outputs are correct and they are easy to read.
   8. How well did I complete this self-assessment?
       Mark out of 5: 5
       Comment: I answered every question with detail.
   Total Mark out of 100 (Add all the previous marks): 99
 */

package analysing_disposable_income;	

import java.util.Scanner;

public class Analysing_Disposable_Income {

	public static final double TAX_RATE = 0.35;

	public static final double LESS_THAN_OR_EQUAL_TO_NO_DISPOSABLE_INCOME = 0;

	public static final double MUCH_LESS_THAN_THE_AVERAGE_MONTHLY_DISPOSABLE_INCOME = 250;

	public static final double AVERAGE_MONTHLY_DISPOSABLE_INCOME = 500;

	public static final double MUCH_MORE_THAN_THE_AVERAGE_MONTHLY_DISPOSABLE_INCOME = 750;

	public static void main(String[] args) {	

		double monthlyGrossIncome, monthlyIncomeAfterTax, monthlyCostOfAccommodation, monthlyCostOfTravel, 
							monthlyCostOfFood, monthlyDisposableIncome, monthlyDisposableIncomePercentageOfGrossIncome;

		System.out.println("Please answer the following questions using numbers only. \n" );

		Scanner userInput = new Scanner (System.in);						
		System.out.println("What is your monthly income before tax?");
		monthlyGrossIncome = userInput.nextDouble();

		monthlyIncomeAfterTax = monthlyGrossIncome - (monthlyGrossIncome * TAX_RATE);

		System.out.println("What are your monthly costs for accommodation?");
		monthlyCostOfAccommodation = userInput.nextDouble();

		System.out.println("What are your monthly costs for travel?");
		monthlyCostOfTravel = userInput.nextDouble();

		System.out.println("What are your monthly costs for food?");
		monthlyCostOfFood = userInput.nextDouble();

		monthlyDisposableIncome = monthlyIncomeAfterTax - (monthlyCostOfAccommodation + monthlyCostOfTravel + monthlyCostOfFood);
		monthlyDisposableIncomePercentageOfGrossIncome = (monthlyDisposableIncome / monthlyGrossIncome) * 100;
		System.out.println("Your monthly disposable income is: " + monthlyDisposableIncome + ", which is " + monthlyDisposableIncomePercentageOfGrossIncome + "% of your salary.");

		if (monthlyDisposableIncome <= 0) {
			System.out.println("You have no disposable income.");
		}

		else if (monthlyDisposableIncome < MUCH_LESS_THAN_THE_AVERAGE_MONTHLY_DISPOSABLE_INCOME) {
			System.out.println("You have much less than the average monthly disposable income.");
		}

		else if (monthlyDisposableIncome < AVERAGE_MONTHLY_DISPOSABLE_INCOME) {
			System.out.println("You have less than the average monthly disposable income.");
		}

		else if (monthlyDisposableIncome == AVERAGE_MONTHLY_DISPOSABLE_INCOME) {
			System.out.println("You have the average monthly disposable income.");
		}

		else if (monthlyDisposableIncome > MUCH_MORE_THAN_THE_AVERAGE_MONTHLY_DISPOSABLE_INCOME) {
			System.out.println("You have much more than the average monthly disposable income.");
		}

		else {
			System.out.println("You have more than the average monthly disposable income.");
		}

		userInput.close();
	}

}