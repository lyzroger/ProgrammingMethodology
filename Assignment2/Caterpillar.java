/* TODO: Replace these file comments with a description of what your program
 * does.
 */
import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class Caterpillar extends GraphicsProgram {
	/* TODO: You are probably going to want to define some program constants here that
	 * control the size, number, spacing, and color of the body segments. When you do,
	 * delete this comment and replace it with your constants.
	 * 
	 * Remember to add comments describing what each constant does!
	 */	
	
	/*
	 * The width and height of the border of the circle.
	 */
	private static final double CIRCLE_BORDER_SIZE = 100;
	
	/*
	 * The total number of body segments.
	 */
	private static final int NUM_BODY_SEG = 6;
	
	/*
	 * The horizontal spacing.
	 */
	private static final double HORIZONTAL = 60;
	
	/*
	 * The vertical spacing.
	 */
	private static final double VERTICAL = 50;
	
	/*
	 * The color of body segment.
	 */
	private static final Color BODY_COLOR = new Color(3, 192, 60);
	
	/*
	 * The color of border of body segment.
	 */
	private static final Color BORDER_COLOR = new Color(6, 42, 120);
	
	public void run() {
		drawCaterpillar();
	}
	
	/*
	 * Method: drawCaterpillar()
	 * Draw a caterpillar.
	 */
	private void drawCaterpillar() {
		double x_init = 0;
		double y_init = getHeight() - CIRCLE_BORDER_SIZE;
		double y = 0;
		for(int i = 0; i < NUM_BODY_SEG; i++) {
			double x = x_init + i * HORIZONTAL;
			if((i % 2) == 0) {
				y = y_init;
			} else {
				y = y_init - VERTICAL;
			}
			GOval body_seg = new GOval(x, y, CIRCLE_BORDER_SIZE, CIRCLE_BORDER_SIZE);
			body_seg.setFilled(true);
			body_seg.setColor(BORDER_COLOR);
			body_seg.setFillColor(BODY_COLOR);
			add(body_seg);
		}
		
	}
}
