/* TODO: Replace these file comments with a description of what your program
 * does.
 */
import acm.program.*;
import acm.util.RandomGenerator;

public class SaintPetersburgGame extends ConsoleProgram {
	
	private static final int TOTAL_WIN = 20;
	
	public void run() {
		startGaming();
	}
	
	private void startGaming(){
		int total = 0;
		int money = 1;
		int count = 0;
		while(total < TOTAL_WIN){
			side = rgen.nextBoolean();
			if(side == false){
				money *= 2;
			} else {
				total = total + money;
				println("This game, you earned " + money);
				println("Your total is " + total);
				count++;
			}
		}
		println("It took " + count + " games to earn $20");
	}
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private boolean side;                  //If side is false, it is head; if side is true, it is tail.
}
