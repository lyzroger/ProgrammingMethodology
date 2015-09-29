/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {
	    // You fill this in, along with any helper methods //
		nameField = new JTextField(20);
		nameField.setActionCommand("Graph");
		nameField.addActionListener(this);
		
		add(new JLabel("Name:"), NORTH);
		add(nameField, NORTH);
		
		add(new JButton("Graph"), NORTH);
		add(new JButton("Clear"), NORTH);
		
		dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
		
		canvas = new NameSurferGraph();
		add(canvas);
		
		addActionListeners();
	}
	
	/*used for NameSurferEntry testing
	public void run() {
		String line = readLine("!");
		NameSurferEntry name = new NameSurferEntry(line);
		println(name.toString());
		
	}
	*/
	
	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		if(e.getActionCommand().equals("Graph")) {
			//println(dataBase.findEntry(nameField.getText()).toString());
			canvas.addEntry(dataBase.findEntry(nameField.getText()));
		} else if(e.getActionCommand().equals("Clear")) {
			canvas.clear();
			canvas.update();
			
			
		}
	}
	
	/* instance variables */
	private JTextField nameField;
	private NameSurferDataBase dataBase;
	private NameSurferGraph canvas;
}
