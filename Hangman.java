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
			
			char[] encryptedWord = createEncryptedWord(wordSize);		
		    while(!loser && !winner) {
		    	System.out.println("Enter a letter: ");
		    	letter = Character.toLowerCase(in.next().charAt(0));
		    	
		    	if(!revealAsterisks(encryptedWord,word,letter)) errors +=1;	
		    	
		    	System.out.println(new String(encryptedWord) +" | Errors: "+errors);
		    	
		    	if(errors == MAX_ERRORS) loser = true;
		    	if(isRevealed(encryptedWord)) winner = true;
		    }
		    
		    if(winner) System.out.println("You win!!");
		    else System.out.println("Game over!!");
		    
		    in.close();
		}
	}
	
	/**
	@param wordSize Longitud de la palabra secreta	

	@return Devuelve un array de "char" de longitud igual a wordSize y todas las casillas del array con '*'
	*/
	static char[] createEncryptedWord(int wordSize){
		
		//TODO

		char[] encryptedAsterisks=new char[wordSize];
		
		char asteriskChar='*';
		for(int i = 0;i<wordSize;i++) {
			encryptedAsterisks[i]=asteriskChar;
		}
		return encryptedAsterisks;		
	}
	
	/**
	@param encryptedWord Palabra con asteriscos alla donde no se ha adivinado y sin donde si se ha adivinado	

	@return Devuelve true si todas las casillas encryptedWord son diferentes a * y false en caso contrario
	*/
	static boolean isRevealed(char[] encryptedWord) {
		
		//TODO
		
		boolean words=false;
		int contadorFalse=0;	
		int encryptedWordSize = encryptedWord.length;
		char asteriskChar='*';
		
		for(int i = 0;i<encryptedWordSize;i++){
			
			if (encryptedWord[i]!=asteriskChar){
				if(i==encryptedWordSize-1 && contadorFalse == 0){
				words=true;
				}
			}else{
					words=false;
					contadorFalse = 1;
				}
		}
		return words;		
	}
	
	/**
	 * 
	 * @param encryptedWord Palabra encriptada con las letras no adivinadas aun con * (Java pasa las arrays por referencia)
	 * @param word Palabra sin encriptar
	 * @param letter Caracter/letra que ha introducido el jugador
	 * @return Devuelve true si la letra existe en la palabra y no ha sido ya revelada. En caso contrario devuelve false.
	 */	
	static boolean revealAsterisks(char[] encryptedWord,char[] word,char letter) {
		
		//TODO
		
		boolean letterGame=true;
		int contadorTrue=0;
		int encryptedWordSize = encryptedWord.length;
		
		for(int i = 0;i<encryptedWordSize;i++){
			if(word[i]==letter && encryptedWord[i]!=letter){
				encryptedWord[i]=letter;
				contadorTrue=1;
			} 
		}
			if(contadorTrue==0){
				letterGame=false;
			}	
		return letterGame;
	}
}