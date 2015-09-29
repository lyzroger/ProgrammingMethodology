/* TODO: Replace these file comments with a description of what your program
 * does.
 */
import acm.program.*;
import acm.util.*;

import java.io.*;
import java.util.*;

public class LicensePlateGame extends ConsoleProgram {
	private static final String DICTIONARY_FILE = "dictionary.txt";
	
	private BufferedReader openFile(String name) {
		BufferedReader rd = null;
		while(rd == null) {
			try {
				rd = new BufferedReader(new FileReader(name));
			} catch(IOException ex) {
				println("File does not exist!");
			}
		}
		return rd;
	}
	
	private void readFile(ArrayList list) {
		try{
			BufferedReader rd = openFile(DICTIONARY_FILE);
			while(true) {
				String line = rd.readLine();
				if(line == null) break;
				list.add(line);
			}
			rd.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
		
	}
	
	public void run() {
		ArrayList<String> dictionary = new ArrayList<String>();
		ArrayList<String> words = new ArrayList<String>();
		readFile(dictionary);
		while(true) {
			String letters = readLine("Enter license plate letters: ");
			String letters_low = "";  //all letters lower case. initialized as null. Do not use " " to initialize! If so, the first element is space. 
			if(isThreeLetters(letters)) {
				//change letter to lower case, if it is upper case.
				for(int i = 0; i < letters.length(); i++) {
					letters_low += Character.toLowerCase(letters.charAt(i));
				}
				getWords(dictionary, words, letters_low);
				for(String temp : words) {
					println(temp);
				}
				words.clear();
			} else {
				println("Please enter three letters.");
			}
		}
	}
	
	private boolean isThreeLetters(String letters) {
		boolean result = false;
		int count = 0;
		for(int i = 0; i < 3; i++){
			if(letters.charAt(i) < 'A' || (letters.charAt(i) > 'Z' && letters.charAt(i) < 'a') || letters.charAt(i) > 'z') break;
			count++;
		}
		if(letters.length() == 3 && count == 3) {
			result = true;
		} 
		return result;
	}
	
	private void getWords(ArrayList dic, ArrayList list, String letters) {
		int[] indexs = new int[3];
		for(int i = 0; i < dic.size(); i++) {
			for(int j = 0; j < 3; j++) {
				if(j == 0) {
					indexs[j] = ((String) dic.get(i)).indexOf(letters.charAt(j));
					if(indexs[j] == -1) break;
				} else {
					indexs[j] = ((String) dic.get(i)).indexOf(letters.charAt(j), indexs[j - 1]);
					if(indexs[j] == -1) break;
				}
			}
			
			if((indexs[0] < indexs[1]) && (indexs[1] < indexs[2])) {
				list.add(dic.get(i));
			}
			Arrays.fill(indexs, 0); //set all values of the array 0.
		}
	}
}
