/* TODO: Replace these file comments with a description of what your program
 * does.
 */
import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int number = readInt("Enter a number: ");
		hailStone(number);
	}
	
	private void hailStone(int number) {
		int count = 0; //Used to count how many steps to reach 1
		while(number != 1){
			count++;
			if((number % 2) == 0){
				println(number + " is even, so I take half: " + (number / 2));
				number = number / 2;
			} else {
				println(number + " is odd, so I make 3n+1: " + (3 * number + 1));
				number = 3 * number + 1;
			}
		}
		println("The process took " + count + " to reach 1");
	}
}

