// Dice and Wallet Classes are used from TCD blackboard.

/* SELF ASSESSMENT 

1. ResolveBet

I have correctly defined ResolveBet which takes the bet type (String) and the Wallet object, and a void return type [Mark out of 7: 7].
Comment: My definition of resolveBet method is as should be.
My program presents the amount of cash in the wallet and asks the user how much he/she would like to bet [Mark out of 8: 8].
Comment: After presenting the amount of cash the user has my program prompts the user how much they want to bet.
My program ensures the bet amount is not greater than the cash in the wallet [Mark out of 5: 5].
Comment: If the bet amount is greater than the cash in the wallet my program prompts the user to try again.
My program creates three Dice objects, rolls them and creates a total variable with a summation of the roll values returned [Mark out of 15: 15]..
Comment: 3 dice objects are created in my program, they are rolled and their total is stored in a variable, the sum is outputted.
My program determines the winnings by comparing the bet type with the total and comparing the bet type with the dice faces for the triple bet [Mark out of 20: 20].
Comment: Bet type is compared with total and the bet type is compared with the dice faces for the triple bet.
My program outputs the results (win or loss) and adds the winnings to the wallet if user wins or removes the bet amount from the wallet if the user loses [Mark out of 10: 10].
Comment: Code outputs win or loss accordingly and wallet is updated successfully.

2. Main

I ask the user for the amount of cash he/she has, create a Wallet object and put this cash into it [Mark out of 15: 15]
Comment: User is prompted to enter cash, wallet object is created and cash is put into the wallet object.
My program loops continuously until the user either enters quit or the cash in the wallet is 0 [Mark out of 5: 5]
Comment: Program does not end until user enters quit or cash equals 0.
I ask the user to enter any of the four bet types or quit [Mark out of 5: 5].
Comment: The user can enter any of the four bet type, or quit to finish.
My program calls resolveBet for each bet type entered [Mark out of 5: 5].
Comment: After each successful bet, the resolveBet method is called.
At the end of the game my program presents a summary message regarding winnings and losses [Mark out of 5: 5]
Comment: When user enters quit or when cash in wallet = 0 a summary is presented.

 Total Mark out of 100 (Add all the previous marks): 100
 */

package chuck_A_Luck_Game;

import java.util.Scanner;

public class CasinoGame {

	public static void main(String[] args) {
		double cash = 0;
		boolean retryCash = false;
		Wallet wallet = new Wallet();
		// Asks user for initial cash amount, creates wallet object and puts cash into wallet.
		do {
			System.out.println("Please enter the amount of cash you have: ");
			Scanner userInput = new Scanner(System.in);
			if (userInput.hasNextDouble()) {
				cash = userInput.nextDouble();
				if (cash > 0) {
					retryCash = false;
					wallet.put(cash);
					System.out.println("Current Balance: " + wallet.check());
					// Allow the user to play continuously placing any of 4 bets, until quit is entered or cash == 0
					boolean finished = false;
					String betType = "";
					while ((finished == false) && (wallet.check()!= 0)) {
						System.out.println("Please enter a bet or enter 'quit' to finish: ");
						Scanner scanner = new Scanner(System.in);
						betType = scanner.nextLine();
						boolean retryBet = false;
						if(betType.equalsIgnoreCase("quit")) {		
							finished = true;
						}
						else if (betType.equalsIgnoreCase("Triple")) {
							betType = "Triple";
						}
						else if (betType.equalsIgnoreCase("Field")) {
							betType = "Field";
						}
						else if (betType.equalsIgnoreCase("High")) {
							betType = "High";
						}
						else if (betType.equalsIgnoreCase("Low")) {
							betType = "Low";
						}
						else {
							System.out.println("Invalid bet/entry. Please try again: ");
							retryBet = true;
						}
						if (finished == false && retryBet == false) {
							resolveBet(betType, wallet);
						}
					}
				}
				else {
					System.out.println("Invalid entry. Please try again: ");
					retryCash = true;
				}
			}
			else {
				System.out.println("Invalid entry. Please try again: ");
				retryCash = true;
			}
		} while (retryCash == true);
		// call resolveBet method for each bet placed by the user.
		System.out.println("You started with: " + cash + 
				". You now have: " + wallet.check() + ".");
	}

	public static void resolveBet(String betType, Wallet wallet) {
		// Display amount of cash in the wallet. Ask user how much they want to bet.
		boolean retry = true;
		do {
			System.out.println("Your current balance is: " + wallet.check() + ". How much would you like to bet? ");
			Scanner userInput = new Scanner(System.in);

			if (userInput.hasNextDouble()) {
				double bet =  userInput.nextDouble();
				System.out.println("Bet amount: " + bet);
				retry = false;

				// Ensure the bet amount is <= cash in the wallet.
				if (wallet.get(bet)==false) {
					System.out.println("Invalid bet. You have insufficient funds to place this bet.");
					retry = true;
				}
				System.out.println("Current cash: " + wallet.check());

				if (retry == false) {
					// Checks to see if bet is successful by creating 3 Dice objects.
					Dice dice1 = new Dice();
					Dice dice2 = new Dice();
					Dice dice3 = new Dice();

					// Roll the 3 dice. Compare roll output with bet type.
					int roll1 = dice1.roll();
					int roll2 = dice2.roll();
					int roll3 = dice3.roll();
					int sum = roll1+roll2+roll3;
					System.out.println("Roll 1: " + roll1);
					System.out.println("Roll 2: " + roll2);
					System.out.println("Roll 3: " + roll3);

					// triple condition
					boolean triple = false;
					if(dice1.topFace()==dice2.topFace() && dice2.topFace()==dice3.topFace() && dice1.topFace() != 1 && dice1.topFace() != 6) {
						triple = true;
					}
					//if(roll1==roll2 && roll2==roll3 && roll1 != 1 && roll1 != 6) {
					//	triple = true;
					//}
					if (betType == "triple" || betType == "Triple") {
						if (triple == true) {
							System.out.println("Win.");
							wallet.put(bet+(bet*30));
						}
						else {
							System.out.println("Loss.");
						}
					}
					// Field condition
					if (betType == "field" || betType == "Field") {
						if (sum < 8 || sum > 12) {
							System.out.println("Win");
							wallet.put((bet*2));
						}
						else {
							System.out.println("Loss");
						}
					}

					// High condition
					if (betType == "High" || betType == "high") {
						if (sum > 10 && triple == false) {
							System.out.println("Win.");
							wallet.put((bet*2));
						}
						else {
							System.out.println("Loss.");
						}
					}

					// Low condition
					if (betType == "Low" || betType == "low") {
						if (sum < 11 && triple == false) {
							System.out.println("Win.");
							wallet.put((bet*2));
						}
						else {
							System.out.println("Loss.");
						}
					}
					System.out.println("Current cash: " + wallet.check());
				}
			}
			else {
				System.out.println("Invalid entry. Please try again: ");
				retry=true;

			}
		} while (retry == true);
	}
}
