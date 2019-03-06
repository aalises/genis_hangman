import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hangman {

	public static void main(String[] args) {
		final int MAX_ERRORS = 10;	
		final String ERROR_FORMAT = "Format error: You must write one word without numbers nor special characters!!";
		Scanner in;
		boolean loser = false, winner = false;
		in = new Scanner(System.in);
		
		System.out.println("Write a word: ");
		String wordString = in.next();
		wordString = wordString.toLowerCase();
		
		Pattern pattern = Pattern.compile("[a-z]*");
		Matcher matcher = pattern.matcher(wordString);
	 
		if (!matcher.matches()) {
			System.out.println(ERROR_FORMAT);
			System.out.println("Game finished!!");
		}else {
			char[] word = wordString.toCharArray();
			int wordSize = word.length;
			
			char letter;
			int errors = 0;
		
			for(int i = 0;i<25;i++) System.out.println("");
			
			char[] encryptedWord = Utils.createEncryptedWord(wordSize);		
		    while(!loser && !winner) {
		    	System.out.println("Enter a letter: ");
		    	letter = Character.toLowerCase(in.next().charAt(0));
		    	
		    	if(!Utils.revealAsterisks(encryptedWord,word,letter)) errors +=1;	
		    	
		    	System.out.println(new String(encryptedWord) +" | Errors: "+errors);
		    	
		    	if(errors == MAX_ERRORS) loser = true;
		    	if(Utils.isRevealed(encryptedWord)) winner = true;
		    }
		    
		    if(winner) System.out.println("You win!!");
		    else System.out.println("Game over!!");
		    
		    in.close();
		}
	}
}