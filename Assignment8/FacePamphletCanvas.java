/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
		GLabel message = new GLabel(msg);
		message.setFont(MESSAGE_FONT);
		double x = (getWidth() - message.getWidth()) / 2.0;
		double y = getHeight() - BOTTOM_MESSAGE_MARGIN;
		message.setLocation(x, y);
		add(message);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// You fill this in
		removeAll();
		if(profile != null) {
			//add name on the canvas
			GLabel name = new GLabel(profile.getName());
			name.setFont(PROFILE_NAME_FONT);
			name.setColor(Color.BLUE);
			double x_name = LEFT_MARGIN;
			double y_name = TOP_MARGIN + name.getHeight();
			name.setLocation(x_name, y_name);
			add(name);
			//add image on the canvas
			double x_image = x_name;
			double y_image = y_name + IMAGE_MARGIN;
			GRect noImage = null;
			GLabel noImageLabel = null;
			if(profile.getImage() == null) {
				noImage = new GRect(x_image, y_image, IMAGE_WIDTH, IMAGE_HEIGHT);
				add(noImage);
				noImageLabel = new GLabel("No Image");
				double x = (IMAGE_WIDTH - noImageLabel.getWidth()) / 2.0;
				double y = y_image + (IMAGE_HEIGHT + noImageLabel.getHeight()) / 2.0;
				noImageLabel.setFont(PROFILE_IMAGE_FONT);
				noImageLabel.setLocation(x, y);
				add(noImageLabel);
			} else {
				//remove(noImage);
				//remove(noImageLabel);
				GImage image = profile.getImage();
				image.setLocation(x_image, y_image);
				image.scale(IMAGE_WIDTH / image.getWidth(), IMAGE_HEIGHT / image.getHeight());
				add(image);
			}
			//add status on the canvas
			String status = "No current status";
			if(!profile.getStatus().equals("")) {
				status = profile.getName() + " is " + profile.getStatus();
			}
			GLabel statusLabel = new GLabel(status);
			statusLabel.setFont(PROFILE_STATUS_FONT);
			double x_status = x_name;
			double y_status = y_image + IMAGE_HEIGHT + STATUS_MARGIN + statusLabel.getHeight();
			statusLabel.setLocation(x_status, y_status);
			add(statusLabel);
			
			//add friends on the canvas
			//add friend label
			GLabel friend = new GLabel("Friends:");
			double x_friend = (getWidth() - friend.getWidth()) / 2.0;
			double y_friend = y_image;
			friend.setFont(PROFILE_FRIEND_LABEL_FONT);
			friend.setLocation(x_friend, y_friend);
			add(friend);
			//add friends
			Iterator<String> it = profile.getFriends();
			int i = 1;
			while(it.hasNext()) {
				GLabel friends = new GLabel(it.next());
				friends.setFont(PROFILE_FRIEND_FONT);
				friends.setLocation(x_friend, (1.5 * i * friends.getHeight() + y_friend));
				i++;
				add(friends);
			}
		}
	}
}
