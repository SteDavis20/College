//Collections in Java

package arrayListCollection;

import java.util.ArrayList;

public class ArrayListCollection
{ 
	public static void main(String[] args)
	{
		ArrayList<String> items = new ArrayList<String>();	// declare ArrayList

		items.add("red"); // Append/add an item to the list
		items.add(0, "yellow"); // Insert yellow at index 0

		display(items, "\nList contents:");		// %n and \n both go onto a new line.

		items.add("green"); // Add green to the end of the list
		display(items, "%nList with one new element:");

		items.remove(1); // Remove the second item from list

		System.out.printf("\"red\" is %sin the list%n",
				items.contains("red") ? "": "not ");

		System.out.printf("Size: %s%n", items.size());	// items.size() does not work without "%s%n" bit
		// Without the "f" in "printf", print statement does not work.
	}

	public static void display(ArrayList<String> items, String header)
	{
		System.out.printf(header);

		for(String item: items) // Enhanced for statement	???
			System.out.printf(" %s", item);

		System.out.println();
	}
}