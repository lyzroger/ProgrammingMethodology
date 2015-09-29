/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// You fill this in
		nameField = new JTextField(TEXT_FIELD_SIZE);
		statusField = new JTextField(TEXT_FIELD_SIZE);
		picField = new JTextField(TEXT_FIELD_SIZE);
		friendField = new JTextField(TEXT_FIELD_SIZE);
		
		statusField.setActionCommand("Change Status");
		picField.setActionCommand("Change Picture");
		friendField.setActionCommand("Add Friend");
		statusField.addActionListener(this);
		picField.addActionListener(this);
		friendField.addActionListener(this);
		
		add(new JLabel("Name "), NORTH);
		add(nameField, NORTH);
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		
		add(statusField, WEST);
		add(new JButton("Change Status"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		add(picField, WEST);
		add(new JButton("Change Picture"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		add(friendField, WEST);
		add(new JButton("Add Friend"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT),WEST);
		
		addActionListeners();
		
		FPDatabase = new FacePamphletDatabase();
		
		canvas = new FacePamphletCanvas();
		add(canvas);
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	if(e.getActionCommand().equals("Add") && !nameField.getText().equals("")) {
    		//println("Add: " + nameField.getText());
    		if(FPDatabase.containsProfile(nameField.getText())) {
    			currentProfile = FPDatabase.getProfile(nameField.getText());
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("A profile with the name " + nameField.getText() + " already exits");
    			//println("Add: profile for " + nameField.getText() + " already exists: " + currentProfile);
    		} else {
    			currentProfile = new FacePamphletProfile(nameField.getText());
    			FPDatabase.addProfile(currentProfile);
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("New profile created");
    			//println("Add: new profile: " + currentProfile);
    		}
    		/*
    		if(currentProfile == null) {
    			println("--> No Current profile");
    		} else {
    			println("--> Current profile: " + currentProfile);
    		}
    		*/
    	} else if(e.getActionCommand().equals("Delete") && !nameField.getText().equals("")) {
    		//println("Delete: " + nameField.getText());
    		if(FPDatabase.containsProfile(nameField.getText())) {
    			FPDatabase.deleteProfile(nameField.getText());
    			currentProfile = null;
    			//println("Delete: profile of " + nameField.getText() + " deleted");
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Profile of " + nameField.getText() + " deleted");
    		} else {
    			currentProfile = null;
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("A profile with the name " + nameField.getText() + " does not exist");
    			//println("Delete: profile with name " + nameField.getText() + " does not exist");
    		}
    		/*
    		if(currentProfile == null) {
    			println("--> No Current profile");
    		} else {
    			println("--> Current profile: " + currentProfile);
    		}
    		*/
    	} else if(e.getActionCommand().equals("Lookup") && !nameField.getText().equals("")) {
    		//println("Lookup: " + nameField.getText());
    		if(FPDatabase.containsProfile(nameField.getText())) {
    			currentProfile = FPDatabase.getProfile(nameField.getText());
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Displaying " + nameField.getText());
    			//println("Lookup: " + currentProfile);
    		} else {
    			currentProfile = null;
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("A profile with the name " + nameField.getText() + " does not exist");
    			//println("Lookup: profile with name " + nameField.getText() + " does not exist");
    		}
    		/*
    		if(currentProfile == null) {
    			println("--> No Current profile");
    		} else {
    			println("--> Current profile: " + currentProfile);
    		}
    		*/
    	} else if(e.getActionCommand().equals("Change Status") && !statusField.getText().equals("")) {
    		//println("Change Status: " + statusField.getText());
    		if(currentProfile != null) {
    			currentProfile.setStatus(statusField.getText());
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Status updated to " + statusField.getText());
    			//println("Status updated to " + statusField.getText());
    			//println("--> Current profile: " + currentProfile);
    		} else {
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Please select a profile to change status");
    			//println("Please select a profile to change status");
    			//println("--> No Current profile");
    		}
    	} else if(e.getActionCommand().equals("Change Picture") && !picField.getText().equals("")) {
    		//println("Change Picture: " + picField.getText());
    		if(currentProfile != null) {
    			GImage image = null;
    			try {
    				image = new GImage(picField.getText());
    			} catch (ErrorException ex) {
    				canvas.displayProfile(null);
    				canvas.showMessage("Unable to open image file: " + picField.getText());
    			}
    			if(image != null) {
    				currentProfile.setImage(image);
    				canvas.displayProfile(currentProfile);
    				canvas.showMessage("Picture updated");
    				//println("Picture updated");
    				
    			}
    		} else {
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Please select a profile to change picture");
    			//println("Please select a profile to change image");
    			//println("--> No Current profile");
    		}
    	} else if(e.getActionCommand().equals("Add Friend") && !friendField.getText().equals("")) {
    		//println("Add Friend: " + friendField.getText());
    		if(currentProfile != null) {
    			//FacePamphletDatabase FPDatabaseFriend = new FacePamphletDatabase();
    			boolean databaseContain = FPDatabase.containsProfile(friendField.getText());
    			if(databaseContain) {
    				if(!friendField.getText().equals(currentProfile.getName())) {
    					FacePamphletProfile friendsProfile = FPDatabase.getProfile(friendField.getText());
        				boolean addSuccess = currentProfile.addFriend(friendField.getText());
        				String currentName = currentProfile.getName();
        				if(addSuccess) {
        					//println(friendField.getText() + " added as a friend");
        					friendsProfile.addFriend(currentName);
        					canvas.displayProfile(currentProfile);
        					canvas.showMessage(friendField.getText() + " added as a friend");
        				} else {
        					canvas.displayProfile(currentProfile);
        					canvas.showMessage(currentName + " already has " + friendField.getText() + " as a friend");
        					//println("Friend already exists");
        				}
    				} else {
    					canvas.displayProfile(currentProfile);
    					canvas.showMessage("Hey, you cannot add yourself as a friend");
    				}
    				
    			} else {
    				canvas.displayProfile(null);
    				canvas.showMessage(friendField.getText() + " does not exist");
    				//println("Profile you want to add is not exist");
    			}
    			//println("--> Current profile: " + currentProfile);
    		} else {
    			canvas.displayProfile(currentProfile);
    			canvas.showMessage("Please select a profile to add friend");
    			//println("Please select a profile to add friend");
    			//println("--> No Current profile");
    		}
    	}
	}
    
    private JTextField nameField;
    private JTextField statusField;
    private JTextField picField;
    private JTextField friendField;
    private FacePamphletDatabase FPDatabase;
    private FacePamphletProfile currentProfile = null;
    private FacePamphletCanvas canvas;

}
