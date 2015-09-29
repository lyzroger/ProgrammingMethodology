/* TODO: Replace these file comments with a description of what your program
 * does.
 */
import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class IllusoryContours extends GraphicsProgram {
	/* The default width and height of the window. These constants will tell Java to
	 * create a window whose size is *approximately* given by these dimensions. You should
	 * not directly use these constants in your program; instead, use getWidth() and
	 * getHeight(), which return the *exact* width and height of the window.
	 */
	public static final int APPLICATION_WIDTH = 300;
	public static final int APPLICATION_HEIGHT = 300;
	
	private double x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0, x4 = 0, y4 = 0;
	
	public void run() {
		
		calculateCoordinate();
		drawCircle(x1, y1);
		drawCircle(x2, y2);
		drawCircle(x3, y3);
		drawCircle(x4, y4);
		drawSquare();
	}
	/* 
	 * Method: calculateCoordinate()
	 * Get the coordinates of the four circles
	 */
	private void calculateCoordinate() {
		x1 = 0;
		y1 = 0;
		x2 = getWidth() - getWidth() / 3.0;
		y2 = 0;
		x3 = 0;
		y3 = getHeight() - getHeight() / 3.0;
		x4 = getWidth() - getWidth() / 3.0;
		y4 = getHeight() - getHeight() / 3.0;
	}
	/*
	 * Method: drawCircle()
	 * Draw a circle with coordinates (x,y) and set the color black
	 */
	private void drawCircle(double x, double y) {
		double width = getWidth() / 3.0;
		double height = getHeight() / 3.0;
		GOval circle = new GOval(x, y, width, height);
		circle.setFilled(true);
		circle.setColor(Color.BLACK);
		add(circle);
	}
	/*
	 * Method: drawSquare()
	 * Draw the square with coordinate at the center of the up left circle and set the color white.
	 */
	private void drawSquare() {
		double width = 2 * (getWidth() / 3.0);
		double height = 2 * (getHeight() / 3.0);
		double x5 = getWidth() / 6.0;
		double y5 = getHeight() / 6.0;
		GRect square = new GRect(x5, y5, width, height);
		square.setFilled(true);
		square.setColor(Color.WHITE);
		add(square);
	}
}
