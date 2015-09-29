/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	// You fill in this part
	public void run () {
		repairAll ();
	}
	
	/* Makes Karel repair all stone mason.
	 * Precondition: Karel is facing East at the start point.
	 * Postcondition: Karel is facing East at the rightmost point of 1st row,
	 * and repaired all the stone mason.
	 */
	private void repairAll () {
		while (frontIsClear()) {
			repairOne();
			for (int i = 0; i < 4; i++) {
				move();
			}
		}
		repairOne();
	}
	
	/* Makes Karel repair one stone mason.
	 * Precondition: Karel is facing East at the lowest point of one column.
	 * Postcondition: Karel is facing East at the lowest point of one column,
	 * and repaired that column.
	 */
	private void repairOne () {
		turnLeft();
		while (frontIsClear()) {
			if(noBeepersPresent()) {
				putBeeper();
			}
			move();
		}
		if(noBeepersPresent()) {
			putBeeper();
		}
		turnAround();
		moveToWall();
		turnLeft();
	}
	
	private void moveToWall () {
		while (frontIsClear()) {
			move();
		}
	}

}
