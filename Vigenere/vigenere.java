// Estefania Sanchez
// CIS 3360, Spring 2020


import java.io.*;
import java.util.*;
import java.lang.*;
public class vigenere
{
	// This method adds two characters together and returns the result as a
	// lowercase alphabetical character.
	public static char addCharacters(char key, char plain)
	{
		int k = (int) key - 'a';
		int p = (int) plain - 'a';
		int result = (k + p) % 26;
		return (char) ('a' + result);
	}

	// This method prints the arraylist passed to it in the the format required.
	// The format is 80 characters per line except for the last line
	public static void printArrayListWithFormatting(ArrayList list)
	{
		int len = list.size();
		int count = 0;
		for (int i = 0; i < len; i++)
		{
			if (count == 80)
			{
				System.out.println();
				count = 0;
			}
			System.out.print(list.get(i));
			count++;
		}
		System.out.println();
	}

	public static void main(String [] args) throws Exception
	{
		ArrayList<Character> vigenereKey = new ArrayList<Character>();
		ArrayList<Character> cipherText = new ArrayList<Character>();
		ArrayList<Character> plaintext = new ArrayList<Character>();;
		char current;
		File key = new File(args[0]);
		File plain = new File(args[1]);
		BufferedReader brKey = new BufferedReader(new FileReader(key));
		BufferedReader brPlain = new BufferedReader(new FileReader(plain));
		int i, keyLength, count = 0;

		// This while loop reads the first file holding the key and puts it into the
		// vigenerekey arraylist.
		while ((i = brKey.read()) != -1)
		{
			current = (char)i;
			if(Character.isLetter(current))
			{
				current = Character.toLowerCase(current);
				vigenereKey.add(current);
			}
		}

		// This method reads the second file holding the plaintext and puts it into
		// the plaintext arraylist
		while ((i = brPlain.read()) != -1)
		{
			current = (char)i;
			if(Character.isLetter(current))
			{
				current = Character.toLowerCase(current);
				plaintext.add(current);
				count++;
				if (count == 512)
				{
					break;
				}
			}
		}

		// This method pads the plaintext arraylist with 'x's until there are 512
		// characters in it.
		while (count < 512)
		{
			plaintext.add('x');
			count++;
		}

		count = 0;
		keyLength = vigenereKey.size();

		// This for loop performs the encryption and puts the encrypted characters
		// into the cipherText arraylist.
		for (int j = 0; j < 512; j++)
		{
			if (count == keyLength)
			{
				count = 0;
			}
			cipherText.add(addCharacters(vigenereKey.get(count), plaintext.get(j)));
			count++;
		}

		// The following prints out the output with the proper formatting.
		System.out.println("\n\nVigenere Key:\n");
		printArrayListWithFormatting(vigenereKey);

		System.out.println("\n\nPlaintext:\n");
		printArrayListWithFormatting(plaintext);

		System.out.println("\n\nCiphertext:\n");
		printArrayListWithFormatting(cipherText);

		brKey.close();
		brPlain.close();
		return;
	}
}
