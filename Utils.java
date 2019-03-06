public class Utils { 	
	/**
	@param wordSize Longitud de la palabra secreta	

	@return Devuelve un array de "char" de longitud igual a wordSize y todas las casillas del array con '*'
	*/
	static char[] createEncryptedWord(int wordSize){
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