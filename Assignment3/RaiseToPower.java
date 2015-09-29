/* TODO: Replace these file comments with a description of what your program
 * does.
 */
import acm.program.*;

public class RaiseToPower extends ConsoleProgram {
	public void run() {
		/* Sit in a loop reading bases and exponents and printing out the values
		 * produced by raising the base to the exponent.
		 */
		while (true) {
			double base  = readDouble("Enter base: ");
			int exponent = readInt("Enter exponent: ");
			println(base + " ^ " + exponent + " = " + raiseToPower(base, exponent));
		}
	}
	
	private double raiseToPower(double base, int exponent) {
		/* Method: raiseToPower()
		 * Calculate the exponential for both positive and negative exponent
		 */
		double result = 1.0;
		if(exponent > 0){                                       //positive exponent
			for(int i = 1; i <= exponent; i++){
				result = base * raiseToPower(base, i - 1);
				//result *= base;
			}
			return result;
		} else {                                                //negative exponent
			exponent = -1 * exponent;
			for(int i = 1; i <= exponent; i++){
				result = base * raiseToPower(base, i - 1);
			}
			return (1 / result);
		}
		
	}
}
