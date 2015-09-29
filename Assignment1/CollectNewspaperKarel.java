/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	
	// You fill in this part
	public void run(){
		moveToNewspaper();
		pickUp();
		moveBack();
	}
	
	/* Makes Karel moves to the newspaper
	 * Precondition: Karel is facing East at the start point.
	 * Postcondition: Karel is facing East at the newspaper.
	 */
	private void moveToNewspaper () {
		moveToWall();
		turnRight();
		while (leftIsBlocked()) {
			move();
		}
		turnLeft();
		move();
	}
	
	/* Makes Karel pick up the newspaper.
	 * Precondition: Karel is facing East at the newspaper.
	 * Postcondition: Karel is facing West at the newspaper point and with newspaper in bag.
	 */
	private void pickUp () {
		if(beepersPresent()) {
			pickBeeper();
		}
		turnAround();
	}
	
	/* Makes Karel move back to start point.
	 * Precondition: Karel is facing West at the newspaper point and with newspaper in bag.
	 * Postcondition: Karel is facing East at the start point.
	 */
	private void moveBack () {
		moveToWall();
		turnRight();
		moveToWall();
		turnRight();
	}
	
	private void moveToWall() {
		while (frontIsClear()) {
			move();
		}
	}

}
