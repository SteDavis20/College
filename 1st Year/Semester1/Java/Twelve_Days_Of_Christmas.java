/* SELF ASSESSMENT
 1. Did I use easy-to-understand meaningful, properly formatted, variable names and CONSTANTS?
        Mark out of 10: 10
        Comment:   Yes, I did not abbreviate any of my variable names/constants and formatted them all appropriately.
 2. Did I implement the getVerse function correctly and in a manner that can be understood (5 marks for function definition, 5 marks for function call and 15 marks for function implementation)?
       Mark out of 25: 25
        Comment:  Yes, my function definition is clear and easy to understand, I successfully called and implemented my function. 
 3. Did I implement the getChristmasGift function correctly using a switch statement and in a manner that can be understood (5 marks for function definition, 5 marks for function call and 15 marks for function implementation)?
       Mark out of 25: 25
        Comment:  Yes, I used an appropriate switch statement in my function, gave it an easy to understand name and called and implemented my function successfully.
 4. Did I implement the getOrdinalString function correctly using if or conditional operators and in a manner that can be understood (5 marks for function definition, 5 marks for function call and 15 marks for function implementation)?
       Mark out of 25: 25
        Comment:  This function uses if statements as requested, is well defined and is called and implemented as expected.
 5. Does the program produce the output correctly?
       Mark out of 10: 10
        Comment:  Yes, the output is the output as requested in the question.
 6. How well did I complete this self-assessment?
        Mark out of 5: 5
        Comment: I believe I provided ample feedback through my comments under each question.
 Total Mark out of 100 (Add all the previous marks): 100
*/ 

package twelve_Days_Of_Christmas_Functions;

public class Twelve_Days_Of_Christmas {

	public static final String FIRST_GIFT = "a partridge in a pear tree\n";
	public static final String SECOND_GIFT = "Two turtle doves\nand ";
	public static final String THIRD_GIFT = "Three French hens\n";
	public static final String FOURTH_GIFT = "Four calling birds\n";
	public static final String FIFTH_GIFT = "Five gold rings\n";
	public static final String SIXTH_GIFT = "Six geese a laying\n";
	public static final String SEVENTH_GIFT = "Seven swans a swimming\n";
	public static final String EIGHTH_GIFT = "Eigth mades a milking\n";
	public static final String NINETH_GIFT = "Nine ladies dancing\n";
	public static final String TENTH_GIFT = "Ten lords a leaping\n";
	public static final String ELEVENTH_GIFT = "Eleven pipers piping\n";
	public static final String TWELVETH_GIFT = "Twelve drummers drumming\n";

	public static void main(String[] args) { 
		for (int i = 1; i <= 12; i++) {
			String lyrics = getVerse(i); 
			String verseString = getOrdinalString(i);
			String firstTwoLines = ("On the " + verseString + " day of Christmas\nMy true love gave to me:");
			System.out.println(firstTwoLines);
			System.out.println(lyrics);
		}
	}

	public static String getVerse(int verseNumber) {
		String lyrics = "";
		for (int i = 1; i <= verseNumber; i++) {
			lyrics = getChristmasGift(i) + lyrics;		
		}
		return lyrics;
	}

	public static String getChristmasGift(int verseNumber) {
		String gift = "";
		switch (verseNumber) {
		case 1: 
			gift = FIRST_GIFT;
			break;
		case 2: 
			gift = SECOND_GIFT;
			break;
		case 3: 
			gift = THIRD_GIFT;
			break;
		case 4: 
			gift = FOURTH_GIFT;
			break;
		case 5: 
			gift = FIFTH_GIFT;
			break;
		case 6: 
			gift = SIXTH_GIFT;
			break;
		case 7: 
			gift = SEVENTH_GIFT;
			break;
		case 8: 
			gift = EIGHTH_GIFT;
			break;
		case 9: 
			gift = NINETH_GIFT;
			break;
		case 10: 
			gift = TENTH_GIFT;
			break;
		case 11: 
			gift = ELEVENTH_GIFT;
			break;
		case 12: 
			gift = TWELVETH_GIFT;
			break;
		default:
		}
		return gift;
	}

	public static String getOrdinalString(int verseNumber) {
		String verseString ="";
		if (verseNumber == 1) {
			verseString = "first";
		}
		else if (verseNumber == 2) {
			verseString = "second";
		}
		else if (verseNumber == 3) {
			verseString = "third";
		}
		else if (verseNumber == 4) {
			verseString = "fourth";
		}
		else if (verseNumber == 5) {
			verseString = "fifth";
		}
		else if (verseNumber == 6) {
			verseString = "sixth";
		}
		else if (verseNumber == 7) {
			verseString = "seventh";
		}
		else if (verseNumber == 8) {
			verseString = "eighth";
		}
		else if (verseNumber == 9) {
			verseString = "nineth";
		}
		else if (verseNumber == 10) {
			verseString = "tenth";
		}
		else if (verseNumber == 11) {
			verseString = "eleventh";
		}
		else if (verseNumber == 12) {
			verseString = "twelveth";
		}
		return verseString;
	}
}




