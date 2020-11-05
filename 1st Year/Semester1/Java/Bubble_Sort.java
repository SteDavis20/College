package bubble_Sort;

import java.util.Arrays;

public class Bubble_Sort {

	public static void main(String[] args) {

		int[] array = {2,6,4,3,78,5,321};
		
		// amount of swaps is the amount of other numbers that you check to see if they're smaller/larger
		
		// sort in descending order: i.e {321, 78, 6, 5, 4, 3, 2}
		if (array != null) {
			int tempValue = 0;
			int index = 0;	// or int i = 0;
			for (index = 0; index<array.length; index++) {
				for (int amountOfSwaps = 0; amountOfSwaps < array.length; amountOfSwaps++) {
					if (array[index] > array[amountOfSwaps]) {
						tempValue = array[index];
						array[index] = array[amountOfSwaps];
						array[amountOfSwaps] = tempValue;
					}
				}
			}
		}
		
		int[] arrayTwo = {64, 34, 31, 3, 67};
		
		// sort in ascending order: i.e {3, 31, 34, 64, 67}		
		int temp = 0;	
		// amountOfSwaps = j = counter for swaps
		if (arrayTwo != null) {
			for (int index = 0; index<arrayTwo.length; index++) {
				for (int amountOfSwaps = 0; amountOfSwaps<arrayTwo.length; amountOfSwaps++) {
					if (arrayTwo[index] < arrayTwo[amountOfSwaps]) {
						temp = arrayTwo[index];
						arrayTwo[index] = arrayTwo[amountOfSwaps];
						arrayTwo[amountOfSwaps] = temp;
					}
				}
			}
		}
		// How to print out arrays to check:
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(arrayTwo));
	}

}
