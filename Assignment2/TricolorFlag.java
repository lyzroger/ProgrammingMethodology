/* TODO: Replace these file comments with a description of what your program
 * does.
 */
import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class TricolorFlag extends GraphicsProgram {
	/* The width and height of the flag. Many flags have an aspect ratio of 3:2 and
	 * our choice of constants here assumes this. Feel free to change these constants
	 * if you're drawing a flag with a different aspect ratio.
	 */
	private static final double FLAG_WIDTH  = 300;
	private static final double FLAG_HEIGHT = 200;
	
	public void run() {
		drawTricolorFlag();
		addGlabel();
	}
	
	/*
	 * Method: drawTricolorFlag()
	 * Get the coordinates of three rects and set their colors.
	 */
	private void drawTricolorFlag() {
		double x1 = (getWidth() - FLAG_WIDTH) / 2.0;
		double y = (getHeight() - FLAG_HEIGHT) / 2.0;
		double x2 = x1 + FLAG_WIDTH / 3.0;
		double x3 = x2 + FLAG_WIDTH / 3.0;
		GRect rect1 = new GRect(x1, y, FLAG_WIDTH / 3.0, FLAG_HEIGHT);
		GRect rect2 = new GRect(x2, y, FLAG_WIDTH / 3.0, FLAG_HEIGHT);
		GRect rect3 = new GRect(x3, y, FLAG_WIDTH / 3.0, FLAG_HEIGHT);
		rect1.setFilled(true);
		rect1.setColor(Color.RED);
		rect2.setFilled(true);
		rect2.setColor(Color.BLUE);
		rect3.setFilled(true);
		rect3.setColor(Color.GREEN);
		add(rect1);
		add(rect2);
		add(rect3);
	}
	
	/*
	 * Method: addGlabel()
	 * Add a GLabel at the right-bottom corner of the canvas.
	 */
	private void addGlabel() {
		GLabel name = new GLabel("Flag of Tricolor");
		double x = getWidth() - name.getWidth();
		double y = getHeight() - name.getHeight();
		name.setLocation(x, y);
		add(name);
	}
}
