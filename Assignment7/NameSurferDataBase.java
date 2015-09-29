import java.io.*;
import java.util.*;

import acm.util.*;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		// You fill this in //
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while(true) {
				String line = br.readLine();
				if(line == null) break;
				NameSurferEntry entry = new NameSurferEntry(line);
				data.put(entry.getName(), entry);
			}
			br.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		String name_new = "";
		for(int i = 0; i < name.length(); i++) {
			if(i > 0) {
				if(name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') {
					name_new = name_new + (char)(name.charAt(i) + 32);
				} else {
					name_new = name_new + name.charAt(i);
				}
			} else {
				if(name.charAt(i) >= 'a' && name.charAt(i) <= 'z') {
					name_new = name_new + (char)(name.charAt(i) - 32);
				} else {
					name_new = name_new + name.charAt(i);
				}
			}
		}
		return data.get(name_new);
		
	}
	
	private HashMap<String, NameSurferEntry> data = new HashMap<String, NameSurferEntry>();
}

