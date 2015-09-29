/* TODO: Replace these file comments with a description of what your program
 * does.
 */
import acm.program.*;
import acm.graphics.*;

public class BoxesIllusion extends GraphicsProgram {
	/* The number of rows and columns in the grid, respectively. */
	private static final int NUM_ROWS = 5;
	private static final int NUM_COLS = 6;
	
	/* The width and height of each box. */
	private static final double BOX_SIZE = 40;
	
	/* The horizontal and vertical spacing between the boxes. */
	private static final double BOX_SPACING = 10;
	
	/* The default width and height of the window. These constants will tell Java to
	 * create a window whose size is *approximately* given by these dimensions. You should
	 * not directly use these constants in your program; instead, use getWidth() and
	 * getHeight().
	 */
	public static final int APPLICATION_WIDTH = 350;
	public static final int APPLICATION_HEIGHT = 300;

	public void run() {
		drawBoxes();
	}
	
	private void drawBoxes() {
		/*
		 * Get the size of the entire figure.
		 */
		double width = (NUM_COLS * BOX_SIZE) + ((NUM_COLS - 1) * BOX_SPACING);
		double height = (NUM_ROWS * BOX_SIZE) + ((NUM_ROWS - 1) * BOX_SPACING);
		/*
		 * Get the initial coordinates of the entire figure.
		 */
		double x_init = ( getWidth() - width ) / 2.0;
		double y_init = ( getHeight() - height ) / 2.0;
		/*
		 * Use two for loops to draw the illusion boxes.
		 */
		for(int i = 0; i < NUM_ROWS; i++) {
			for(int j = 0; j < NUM_COLS; j++) {
				double x = x_init + j * (BOX_SIZE + BOX_SPACING);
				double y = y_init + i * (BOX_SIZE + BOX_SPACING);
				GRect box = new GRect(x, y, BOX_SIZE, BOX_SIZE);
				box.setFilled(true);
				add(box);
			}
		}
	}
}
