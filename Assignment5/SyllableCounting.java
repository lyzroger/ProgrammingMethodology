/* TODO: Replace these file comments with a description of what your program
 * does.
 */
import acm.program.*;

public class SyllableCounting extends ConsoleProgram {
	public void run() {
		/* Repeatedly prompt the user for a word and print out the estimated
		 * number of syllables in that word.
		 */
		while (true) {
			String word = readLine("Enter a single word: ");
			println("  Syllable count: " + syllablesIn(word));
		}
	}
	
	/**
	 * Given a word, estimates the number of syllables in that word according to the
	 * heuristic specified in the handout.
	 * 
	 * @param word A string containing a single word. 
	 * @return An estimate of the number of syllables in that word.
	 */
	private int syllablesIn(String word) {
		int count = 0;
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u' || word.charAt(i) == 'y' || word.charAt(i) == 'A' || word.charAt(i) == 'E' || word.charAt(i) == 'I' || word.charAt(i) == 'O' || word.charAt(i) == 'U' || word.charAt(i) == 'Y') {
				count++;
				if((word.charAt(i) != 'e' || i != word.length() - 1) && (i > 0) && (word.charAt(i - 1) == 'a' || word.charAt(i - 1) == 'e' || word.charAt(i - 1) == 'i' || word.charAt(i - 1) == 'o' || word.charAt(i - 1) == 'u' || word.charAt(i - 1) == 'y' || word.charAt(i - 1) == 'A' || word.charAt(i - 1) == 'E' || word.charAt(i - 1) == 'I' || word.charAt(i - 1) == 'O' || word.charAt(i - 1) == 'U' || word.charAt(i - 1) == 'Y')) {
					count--;
				}
				if((word.charAt(i) == 'e') && (i == word.length() - 1)) {
					if(count > 1){
						count--;
					}
				}
			}
		}
		return count;
	}
}
