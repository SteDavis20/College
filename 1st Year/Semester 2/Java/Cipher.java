/* Write a Java program which repeatedly converts (user entered) plain text to cipher text using a substitution cipher (in which plain text letters are
 *  randomly assigned to cipher text letters), and then converts it back again (just to be sure that we can decrypt it).  Note that a Substitution Cipher
 *  replaces plaintext with cipher-text. The most common substitution ciphers replace single characters of plaintext with predefined single characters
 *  of cipher-text (e.g. the plain-text character `a' might be replaced by cipher text character 'q', 'b' might be replaced by 'x', 'c' by 'k' and so on).  
 *  Each plain-text character should be replaced by a different cipher-text character.

As part of your solution you must write and use at least the following functions/methods:

(i)    createCipher() which determines and returns the mapping from plain text to cipher text.  Each plain text character ('a' .. 'z', ' ') must be
 randomly assigned a cipher-text character;

(ii)   an encrypt() method which takes a plain text phrase (as an array of characters) & the cipher and returns an encrypted version of the phrase
 according to the substitution cipher;

(iii)  a decrypt() which takes an encrypted phrase (as an array of characters) & the cipher mapping and returns a plain text version of the phrase
 according to the substitution cipher

Hints:

A 27 element 1-D array (26 letters and the space character) can be used for the mapping where each element is the cipher-text character corresponding
to a particular letter.

Given a String called myString you can create an array of characters as follows:

o   char[] characterArray = myString.toCharArray();

To convert back to a String:

o  String anotherString = new String( characterArray );

To convert a String to lowercase:

o   String lowercaseString = myString.toLowerCase();
*/

package cipher;

public class Cipher {

	public static void main(String[] args) {
	
		
	}

	// Assign each letter in alphabet with a different, unique letter and show what the new character is that the original character has been assigned to
	public static String createCipher() {
		char[] subsitutionCipher = new char[27];
		
		// how to assign each letter in the alphabet to a different, unique and random character???
		
		String originalMessage = "";
		char[] characterArray = originalMessage.toCharArray();
		String mapping = "";
		return mapping;
	}
	
	public static String encrpyt(String[] plainTextPhrase, char[] cipher) {
		String[] encryptedVersion = new String[];
		return encryptedVersion;
	}
	
	public static String decrypt(String [] encryptedPhrase, String [] cipherMapping) {
		String plainTextVersion = "";
		return plainTextVersion;
	}
}
