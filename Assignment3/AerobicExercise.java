/* TODO: Replace these file comments with a description of what your program
 * does.
 */
import acm.program.*;

public class AerobicExercise extends ConsoleProgram {
	
	/*
	 * The minutes for maintain cardiovascular health per day.
	 */
	private static final int CARDIO_HEALTH = 30;

	/*
	 * The minutes for maintain low blood pressure per day.
	 */
	private static final int LOW_BLOOD = 40;
	
	public void run() {
		for(int i = 0; i < 7; i++) {
			int minutes = readInt("How many minutes of aerobic exercise did you do on day " + (i + 1) + "? ");
			checkQualifiedExercise(minutes);
		}
		showResult();
		
	}
	
	/*
	 * Method: checkQualifiedExercise()
	 * Check if the user qualified for the cardiovascular health and low blood pressure. 
	 * Give them recommendtion for how many exercises they need.
	 */
	
	private int cardio_count = 0;
	private int low_blood_count = 0;
	
	private void checkQualifiedExercise(int minutes) {
		//cardio_count = 0; //count how many days the user exercise more than 30 minutes
		//low_blood_count = 0; //count how many days the user exercise more than 40 minutes
		if(minutes >= LOW_BLOOD) {
			low_blood_count++;
			cardio_count++;
		} else if(minutes >= CARDIO_HEALTH){
			cardio_count++;
		}
	}
	
	/*
	 * Method: showResult()
	 * Show the result.
	 */
	private void showResult() {
		println("Cardiovascular Health");
		if(cardio_count >= 5){
			println("Great job! You've done enough exercise for cardiovascular health.");
		} else {
			println("You need 30 or more minutes of aerobic exercise on at least " + (5 - cardio_count) + " more day(s).");
		}
		println("Blood Pressure");
		if(low_blood_count >= 3) {
			println("Great job! You've done enough exercise to keep low blood pressure.");
		} else {
			println("You need 40 or more munutes of aerobic exercise on at least " + (3 - low_blood_count) + " more day(s).");
		}
	}
}
