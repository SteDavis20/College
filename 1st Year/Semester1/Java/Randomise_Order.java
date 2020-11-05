package ranomise_Order;

import java.util.Random;

public class Randomise_Order {

	public static void main(String[] args) {

		int[] array = {3,5,2,7,86};
		
		// to randomise order
		if (array != null) {
			int index = 0;
			Random generator = new Random();
			for (index = 0; index<array.length; index++) {
				int otherIndex = generator.nextInt(array.length);
				int temp = array[index];
				array[index] = array[otherIndex];
				array[otherIndex] = temp;	
			}
		}
	}

}


// to print an array:
// System.out.print(Arrays.toString(array));