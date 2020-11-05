/*
 * Write a program to determine if an entered date (day/month/year) is valid.
 * As part of the program write and use the following routines:
 *  - validDate() which takes a year, month and day and returns whether or not
 *  the date is valid
 *  - daysInMonth() which takes a month (between 1 and 12) and year, and returns
 *    the number of days in the month.
 *  - isLeapYear() which takes a year and return whether or not it is a leap year.
 */

// 4 functions, including main as 1

package valid_Date;

import java.util.Scanner;
public class Valid_Date {

	public static final int DAYS_IN_MOST_MONTHS = 31;
	public static final int DAYS_IN_APRIL_JUNE_SEPTEMBER_NOVEMBER = 30;
	public static void main(String[] args) {

		System.out.println("Please enter a date in the following format: dd mm yyyy e.g 03 08 1999");
		Scanner userInput = new Scanner(System.in);
		int day = userInput.nextInt();
		int month = userInput.nextInt();
		int year = userInput.nextInt();
		boolean isValidDate = validDate(year, month, day);
		if (isValidDate == true) {
			System.out.print("The date entered (" + day + "/" + month + "/" + year + ") is valid.");
		}
		else {
			System.out.println("The date entered (" + day + "/" + month + "/" + year + ") is invalid.");
		}
		userInput.close();
	}

	
	// Function 3
	public static boolean isLeapYear(int year) {
		boolean leapYear = false;
		if (year % 400 == 0) {
			leapYear = true;
		}
		else if (year % 100 != 0) {
			leapYear = false;
		}
		else if (year % 4 == 0){
			leapYear = true;
		}
		else {
			leapYear = false;
		}
		return leapYear;
	}
	
	
	// Function 2
	public static int daysInMonth(int month, int year) { 
		boolean leapYear = isLeapYear(year);      /*if you just type isLeapYear(year) then you are not SAVING the value 
													for the boolean. You enter isLeapYear(year) and not isLeapYear() because you 
		 									      need to take in the year. */
		int numberOfDaysInMonth = DAYS_IN_MOST_MONTHS;
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			numberOfDaysInMonth = DAYS_IN_APRIL_JUNE_SEPTEMBER_NOVEMBER;
		}
		else if (month == 2 && leapYear == false) {
			numberOfDaysInMonth = 28;
		}
		else if (month == 2 && leapYear == true) {
			numberOfDaysInMonth = 29;
		}
		else if (month < 1 || month > 12) {
			numberOfDaysInMonth = 0;
		}
		else {
			numberOfDaysInMonth = DAYS_IN_MOST_MONTHS;
		}
		return numberOfDaysInMonth;
	}
	
	// Function 1
	public static boolean validDate(int year, int month, int day) {
		boolean isValidDate = true;
		int numberOfDaysInMonth = daysInMonth(month, year);
		if ((day > numberOfDaysInMonth || day < 1) || month < 1 || month > 12 ) {
			isValidDate = false;
		}
		return isValidDate;
	}
}

