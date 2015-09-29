/* This program is used to calculate the value of a quadratic equation where a is not zero.
 * 
 */
import acm.program.*;

public class QuadraticFormula extends ConsoleProgram {
	public void run() {
		int a = readInt("Enter a: ");
		int b = readInt("Enter b: ");
		int c = readInt("Enter c: ");
		calculateQuadraticEquation(a, b, c);
	}
	
	private void calculateQuadraticEquation(int a, int b, int c) {
		double discriminant = (double) b * b - 4 * a * c;
		double y = Math.sqrt(discriminant);
		if (y > 0) {
			double x1 = (-b + y)/(2 * a);
			double x2 = (-b - y)/(2 * a);
			println("There are two roots.");
			println("One root is " + x1);
			println("The other is " + x2);
		} else if(y == 0) {
			double x = -b/(2 * a);
			println("There is one root: " + x);
		}
		else {
			println("There are no real roots.");
		}
	}
}