/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
	public NameSurferEntry(String line) {
		// You fill this in //
		int[] indexOfSpaces = new int[NDECADES]; //indexes of spaces in the line.
		int count = 0;
		ranks = new int[NDECADES];
		for(int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == ' ') {
				indexOfSpaces[count] = i;
				count++;
			}
		}
		name = line.substring(0, indexOfSpaces[0]);
		for(int i = 0; i < NDECADES; i++) {
			if(i < NDECADES -1) {
				ranks[i] = Integer.parseInt(line.substring(indexOfSpaces[i] + 1, indexOfSpaces[i + 1]));
			} else {
				ranks[i] = Integer.parseInt(line.substring(indexOfSpaces[i] + 1));
			}
			
		}
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		//int index = (decade - START_DECADE) / 10;
		return ranks[decade];
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		String allRanks = Integer.toString(ranks[0]);
		for(int i = 1; i < NDECADES; i++) {
			allRanks = allRanks + " " + Integer.toString(ranks[i]);
		}
		return (name + " [" + allRanks + "]");		
	}
	
	/*instance variables*/
	private String name;
	private int[] ranks;
}

