/* TODO: Replace these file comments with a description of what your program
 * does.
 */
import acm.graphics.*;
import acm.program.*;

public class Pyramid extends GraphicsProgram {
	/** Width of each brick in pixels */
	private static final double BRICK_WIDTH = 30;

	/** Height of each brick in pixels */
	private static final double BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		buildPyramid();
	}
	
	/* Method: buildPyramid()
	 * Draw the brick pyramid.
	 */
	private void buildPyramid(){
		/*
		 * The coordinates of the lower-left brick
		 */
		double x_base = (getWidth() - BRICKS_IN_BASE * BRICK_WIDTH) / 2;
		double y_base = getHeight() - BRICK_HEIGHT;
		
		/*
		 * Draw the 1st brick in line i + 1.
		 * x coordinate increased by half of the brick width and y coordinate decreased by one brick height.
		 */
		for(int i = 0; i < BRICKS_IN_BASE; i++){
			double x = x_base + ((i * BRICK_WIDTH) / 2);
			double y = y_base - (i * BRICK_HEIGHT);
			GRect rect1 = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT); 
			add(rect1);
			/*
			 * Draw the remaining bricks in line i + 1.
			 * x coordinate increased by one brick width and y coordinate remain the same.
			 */
			for(int j = 1; j < (BRICKS_IN_BASE - i); j++){           
				x = x + BRICK_WIDTH;
				GRect rect2 = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				add(rect2);
			}
		}
	}
}

