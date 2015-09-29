/* TODO: Replace these file comments with a description of what your program
 * does.
 */
import acm.program.*;
import acm.util.*;

import java.io.*;
import java.util.*;

public class CSVParser extends ConsoleProgram {
	
	//private static final int COL_NUM = 3;
	private int col_num;
	
	public void run() {
		/* This starter code is designed to test a fieldsIn method that breaks a
		 * line from a CSV file apart into tokens and returns an ArrayList of them.
		 * You don't have to implement this program this way, but we strongly
		 * recommend it.
		 * 
		 * TODO: Delete this run method and replace it with one that prompts the
		 * user for a filename and column number, calls the extractColumn
		 * method that you'll be writing, and prints out all the cells in that
		 * column.
		 */
		while (true) {
			/*
			String line = readLine("Enter a CSV-formatted line of data: ");
			ArrayList<String> fields = fieldsIn(line);
			
			for (int i = 0; i < fields.size(); i++) {
				println("Field " + i + ": [" + fields.get(i) + "]");
			}
			*/
			BufferedReader br = openFile("Please enter the file name: ");
			int col = readInt("Please enter the colunm number: ");
			ArrayList<String> fields = extractColumn(br, col);
			for(String temp : fields) {
				println(temp);
			}
			
		}
	}
	
	/**
	 * Given a line from a CSV file, parses each field out of the line and returns
	 * an ArrayList containing all of them.
	 * 
	 * @param line The line of the file to parse.
	 * @return A list of all the fields in that line.
	 */
	private ArrayList<String> fieldsIn(String line) {
		ArrayList<String> oneRow = new ArrayList<String>();
		ArrayList<Integer> indexOfQuote = new ArrayList<Integer>();
		ArrayList<Integer> indexOfComma = new ArrayList<Integer>();
		//String noQuotes = "";
		for(int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == '"') {
				indexOfQuote.add(i);
			}
		}
		
		for(int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == ',') {
				indexOfComma.add(i);
			}
		}
		
		if(indexOfQuote.size() == 0) { //if no quotes, split the String using comma.
			splitUsingComma(indexOfComma, oneRow, line);
		} else { 
			//if has quotes, remove the index of comma that between two quotes. 
			for(int i = 0; i < indexOfQuote.size(); i = i + 2) {
				for(int j = 0; j < indexOfComma.size(); j++) {
					if((indexOfComma.get(j) > indexOfQuote.get(i)) && (indexOfComma.get(j) < indexOfQuote.get(i + 1))) {
						indexOfComma.remove(j);
					}
				}
			}
			/*And store the String into a new String without quotes.
			//The index of commas of the new String are different with that of the original String!!!
			//So we should use both commas and quotes as splitters. It is easier than changing index of commas!
			for(int i = 0; i <= indexOfQuote.size(); i++) {
				if(i == 0) {
					noQuotes += line.substring(i, indexOfQuote.get(i));
				} else if(i == indexOfQuote.size()) {
					noQuotes += line.substring(indexOfQuote.get(i - 1) + 1, line.length());
				} else {
					noQuotes += line.substring(indexOfQuote.get(i - 1) + 1, indexOfQuote.get(i));
				}
			}
			*/
			col_num = indexOfComma.size() + 1;
			for(int temp : indexOfQuote) {
				indexOfComma.add(temp);
			}
			Collections.sort(indexOfComma);
			//split the String using both the remaining commas and quotes.
			splitUsingComma(indexOfComma, oneRow, line);
		}
		return oneRow;
	}
	
	private ArrayList<String> splitUsingComma(ArrayList<Integer> indexOfComma, ArrayList<String> oneRow, String line) {
		for(int i = 0; i <= indexOfComma.size(); i++) {
			if(i == 0) {
				if(!line.substring(i, indexOfComma.get(i)).equals("")) {
					oneRow.add(line.substring(i, indexOfComma.get(i)));
				}
			} else if(i == indexOfComma.size()) {
				if(!line.substring(indexOfComma.get(i - 1) + 1, line.length()).equals("")) {
					oneRow.add(line.substring(indexOfComma.get(i - 1) + 1, line.length()));
				}
			} else {
				if(!line.substring(indexOfComma.get(i - 1) + 1, indexOfComma.get(i)).equals("")) {
					oneRow.add(line.substring(indexOfComma.get(i - 1) + 1, indexOfComma.get(i)));
				}
			}
		}
		return oneRow;
	}
	
	/**
	 * Given the name of a CSV file and a column index, returns the contents of
	 * that column in the CSV file as an ArrayList.
	 * <p>
	 * This method can assume that the file to read is properly formatted and
	 * that the specified column index is in-bounds. However, the method
	 * cannot necessarily assume that the file exists. If the specified file
	 * does not exist, this method should return null as a sentinel value.
	 * 
	 * @param filename The name of the file to open.
	 * @param columnIndex The index of the column to extract.
	 * @return A list of all the fields in the indicated column.
	 */
	private ArrayList<String> extractColumn(BufferedReader br, int columnIndex) {
		// TODO: Replace this comment with your implementation of extractColumn.
		// We recommend that you start by implementing fieldsIn and only then
		// moving on to this method.
		ArrayList<String> allRows = new ArrayList<String>();
		ArrayList<String> allCols = new ArrayList<String>();
		try{
			while(true) {
				String line = br.readLine();
				if(line == null) break;
				for(String temp : fieldsIn(line)) {
					allRows.add(temp);
				}
			}
			br.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
		
		for(int i = columnIndex; i < allRows.size(); i = i + col_num ) {
			allCols.add(allRows.get(i));
		}
		return allCols;
	}
	
	private BufferedReader openFile(String filename) {
		BufferedReader br = null;
		while(br == null) {
			try{
				String name = readLine(filename);
				br = new BufferedReader(new FileReader(name));
			} catch(IOException ex) {
				println("File does not exist!");
			}
		}
		return br;
	}
}
