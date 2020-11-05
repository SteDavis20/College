/* SELF ASSESSMENT 

1. readDictionary
- I have the correct method definition [Mark out of 5:5] 
- Comment: My method has the correct name, return type and parameter, and performs its desired task.
- My method reads the words from the "words.txt" file. [Mark out of 5:5]
- Comment: My method successfully reads each and every word from the words.txt file.
- It returns the contents from "words.txt" in a String array or an ArrayList. [Mark out of 5:5]
- Comment: My readDictionary method returns the contents of the file in an ArrayList.

2. readWordList
- I have the correct method definition [Mark out of 5:5]
- Comment: My method has the correct name and return type. My method readWordList performs its desired task.
- My method reads the words provided (which are separated by commas, saves them to an array or ArrayList of String references and returns it. [Mark out of 5:5]
- Comment: My method converts the large list String into sub-strings (each) word and saves them as indexes into an ArrayList and returns this ArrayList.

3. isUniqueList
- I have the correct method definition [Mark out of 5:5]
- Comment: My method has the correct name, return type and parameter, and performs its desired task.
- My method compares each word in the array with the rest of the words in the list. [Mark out of 5:5]
- Comment: Each word is compared with every other word in the list using two for loops.
- Exits the loop when a non-unique word is found. [Mark out of 5:5]
- Comment: As soon as 2 words are equal, the loop is exited.
- Returns true is all the words are unique and false otherwise. [Mark out of 5:5]
- Comment: If the list is unique, true is returned, otherwise false is returned.

4. isEnglishWord
- I have the correct method definition [Mark out of 5:5]
- Comment: My method has the correct name, return type and parameter, and performs its desired task.
- My method uses the binarySearch method in Arrays library class. [Mark out of 3:3]
- Comment: To search for the word in the list of English words my method uses the binarySearch function from the Arrays library class.
- Returns true if the binarySearch method return a value >= 0, otherwise false is returned. [Mark out of 2:2]
- Comment: If the value is < 0, false is returned, otherwise, true is returned.

5. isDifferentByOne
- I have the correct method definition [Mark out of 5:5]
- Comment: My method has the correct name, return type and parameter, and performs its desired task.
- My method loops through the length of a words comparing characters at the same position in both words searching for one difference. [Mark out of 10:10]
- Comment: If there is a difference, the count is incremented by 1. My method compares the character of each string at the same position.

6. isWordChain
- I have the correct method definition [Mark out of 5:5]
- Comment: My method has the correct name, return type and parameter, and performs its desired task.
- My method calls isUniqueList, isEnglishWord and isDifferentByOne methods and prints the appropriate message [Mark out of 10:10]
- Comment: My method calls these 3 functions

7. main
- Reads all the words from file words.txt into an array or an ArrayList using the any of the Java.IO classes covered in lectures [Mark out of 10:10]
- Comment: My main line successfully reads all the words into an ArrayList using the Java.IO Classes.
- Asks the user for input and calls isWordChain [Mark out of 5:5]
- Comment: User is prompted for input and isWordChain method is called.

 Total Mark out of 100 (Add all the previous marks): 100
*/

package lewisCarrollsPuzzle;

import java.util.ArrayList;
import java.util.Arrays;		// to use binarySearch() method.
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.*;
import java.util.Scanner;

public class LewisCarroll {

	public static final String FILE = "C:\\Users\\Brendan\\Desktop\\words.txt";

	public static ArrayList<String> readDictionary(File file) {
		ArrayList<String> words= new ArrayList<String>();
		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String line;
			while((line = br.readLine())!=null) {
				words.add(line);
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File was not found.");
		}
		catch (IOException e) {
			System.out.println("Data cannot be read. Potential security breach.");
		}
		return words;
	}

	public static ArrayList<String> readWordList() {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> userWords = new ArrayList<String>(0);
		try {
			String list = scanner.nextLine();
			String[] words = list.split(",\\s*");
			if (list.equalsIgnoreCase("")) {
				System.out.println("Thank you for your input. Goodbye.");
//				System.out.println(""+userWords);
			}
			else {
				for(int i=0; i<words.length; i++) {
					userWords.add(words[i]);					
				}
			}
		}
		catch (Exception e) {
			System.out.println("Invalid entry.");
		}
		System.out.println("User list: "+userWords);
		return userWords;
	}

	public static boolean isUniqueList(ArrayList<String> list) {
		boolean isUnique = true;
		for(int i=0; ((i<list.size())); i++) {
			for(int j=0; ((j<list.size())); j++) {
				if((list.get(i)).equalsIgnoreCase(list.get(j)) && (i!=j)) {
					isUnique = false;
					return isUnique;
				}
			}	
		}
		return isUnique;
	}

	public static boolean isEnglishWord(String string) {
		boolean isEnglishWord = false;
		File file = new File(FILE);
		ArrayList<String> words = new ArrayList<String>();
		words = readDictionary(file);

		int findIndex = Collections.binarySearch(words, string);
		if (findIndex>=0) {
			isEnglishWord = true;
			return isEnglishWord;
		}
		else {
			return isEnglishWord;
		}
	}

	public static boolean isDifferentByOne(String string1, String string2) {
		boolean hasCriteria = true;
		int count = 0;
		if (string1.length()==string2.length()) {
			for(int i=0; i<string1.length(); i++) {
				if((string1.charAt(i))!=(string2.charAt(i))) {
					count++;
				}
				if(count>1) {
					hasCriteria = false;
					return hasCriteria;
				}
			}
		}
		else {
			//System.out.println("Strings are not the same length.");
			hasCriteria = false;
		}
		if (count==0) {
			hasCriteria = false;
		}
		return hasCriteria;
	}


	public static boolean isWordChain(ArrayList<String> list) {
		boolean isWordChain = true;
		if(isUniqueList(list)==false) {
			isWordChain = false;
			//System.out.println("List is not unique.");
			return isWordChain;
		}
		for(int i=0; i<list.size(); i++) {
			if(isEnglishWord(list.get(i)) == false) {
				isWordChain = false;
				// System.out.println(""+list.get(i)+" is not an English word");
				return isWordChain;
			}
		}
		int j=1;
		for(int i=0; j<list.size(); i++) {
			if(isDifferentByOne(list.get(i),list.get(j)) == false) {
				isWordChain = false;
				// System.out.println(""+list.get(i)+" and "+list.get(j)+" do not differ by only 1 character/are not the same length.");
				return isWordChain;
			}
			j++;
		}
		return isWordChain;
	}

	//----------------------------------------------------------------------------------------------------------------

	public static void main (String[] args) {	
		boolean finished = false;
		do {
			ArrayList<String> userWords = new ArrayList<String>(0);
			System.out.println("Please enter a comma separated list of words, with a space after each comma or enter an empty list to quit: ");
			userWords = readWordList();
			if (userWords.isEmpty()==true) {
				finished = true;
			}
			if(finished==false) {
				if(isWordChain(userWords)==false) {
					System.out.println("Not a valid chain of words from Lewis Carroll's word-links game.");
				}
				else {
					System.out.println("Valid chain of words from Lewis Carroll's word-links game.");
				}
			}
		} while(finished==false);
	}			
}
