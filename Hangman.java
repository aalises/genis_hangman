import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hangman {

	public static void main(String[] args) {
		final int MAX_ERRORS = 10, NUM_SPACES = 25;	
		final String FORMAT_ERROR = "Format error: No numbers or special characters allowed";

		Scanner in;
		boolean loser = false, winner = false;
			
		System.out.println("Write a word: ");

		in = new Scanner(System.in);
		String wordString = in.next().toLowerCase();
			 
		//Finish the game due to an error format if the input word does not match
		if (!Utils.validateWord(wordString)) {
			System.out.println(String.format("%s, Game finished!!", FORMAT_ERROR));
			in.close();
			return;
		}

		char[] word = wordString.toCharArray();
		int wordSize = word.length;
			
		char letter;
		int errors = 0;
		
		Utils.printSpaces(NUM_SPACES); //Print some padding spaces
		char[] encryptedWord = Utils.createEncryptedWord(wordSize);	
	
		while(!(loser || winner)) {
		  System.out.println("Enter a letter: ");
			letter = Character.toLowerCase(in.next().charAt(0));
			
			if(!Utils.validateWord(Character.toString(letter))){
				System.out.println(FORMAT_ERROR);
				continue;
			} 

			if(!Utils.revealAsterisks(encryptedWord,word,letter)) errors +=1;	
		  System.out.println(new String(encryptedWord) +" | Errors: " + errors);
			
			loser = errors == MAX_ERRORS;
		  winner = Utils.isRevealed(encryptedWord);
		}
		System.out.println(winner ? "You win!!" : "Game over!!");
		in.close();
	}
}