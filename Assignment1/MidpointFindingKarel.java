/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	// You fill in this part
	public void run() {
		findWidth();
		turnAround();
		moveToMid();
		putBeeper();
	}
	
	/* Makes Karel find out the width of 1st row of the world.
	 * Precondition: Karel is facing East at the start point.
	 * Postcondition: Karel is facing East at the end of 1st row.
	 */
	int width = 0;
	private void findWidth() {
		width = 0;
		while(frontIsClear()) {
			move();
			width++;
		}
	}
	
	/* Makes Karel move to mid point of 1st row.
	 * Precondition: Karel is facing East at the end of 1st row.
	 * Postcondition: Karel is facing West at the mid point of 1st row.
	 */
	private void moveToMid() {
		for(int i = 0; i < width/2; i++) {
			move();
		}
	}

}
