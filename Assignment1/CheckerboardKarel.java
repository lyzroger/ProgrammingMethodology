/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	// You fill in this part
	public void run() {
		checkWidth();
		putFirstRow();
		while(leftIsClear()) {
			moveToNextRow();
			putOneRow();
		}
	}
	
	/* Makes Karel check the width of the world
	 * and then return to start point
	 * Precondition: Karel is facing East at the start point.
	 * Postcondition: Karel is facing East at the start point.
	 */
	int width = 0;
	private void checkWidth() {
		width = 0;
		while(frontIsClear()) {
			move();
			width++;
		}
		backToWall();
	}
	/* Makes Karel complete the 1st row and return back to start point
	 * Precondition: Karel is facing East at the start point.
	 * Postcondition: Karel is facing East at the start point, and the 1st row is completed.
	 */
	private void putFirstRow() {
		for(int i = 0; i < width; i++) {
			if((i % 2) == 0) {
				putBeeper();
			}
			move();
		}
		if((width % 2) == 0) {
			putBeeper();
		}
		backToWall();
	}
	
	/* Makes Karel move to next row above
	 * Preconditon: Karel is facing East at the leftmost point of that row.
	 * Postcondition: Karel is facing East at the leftmost point of next row,
	 * and get ready to complete this row.
	 */
	private void moveToNextRow() {
		turnLeft();
		move();
		turnRight();
	}
	
	/* Makes Karel check if there has a beeper at right.
	 * Precondition: Karel is facing East
	 * Postcondition: Karel is facing East
	 */
	private boolean rightHasBeeper() {
		turnRight();
		move();
		if(beepersPresent()) {
			turnAround();
			move();
			turnRight();
			return true;
		} else {
			turnAround();
			move();
			turnRight();
			return false;
		}
	}
	
	/* Makes Karel complete the following rows,
	 * and return back to leftmost point of that row
	 * Precondition: Karel is facing East at the leftmost point of that row.
	 * Postcondition: Karel is facing East at the leftmost point of that row,
	 * and complete that row.
	 */
	private void putOneRow() {
		while(frontIsClear()) {
			if(rightHasBeeper()) {
				move();
			} else {
				putBeeper();
				move();
			}
		}
		if(!rightHasBeeper()) {
			putBeeper();
		}
		backToWall();
	}
	
	private void backToWall(){
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnAround();
	}
}
