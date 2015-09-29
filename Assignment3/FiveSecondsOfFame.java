/* TODO: Replace these file comments with a description of what your program
 * does. This will be particularly important for this program, since you have
 * complete control over what your animation does!
 */
import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class FiveSecondsOfFame extends GraphicsProgram {
	/**Diameter of ball */
	private static final int DIAM_BALL = 30;
	/**Y velocity increased each cycly as a result of gravity*/
	private static final double GRAVITY = 3;
	/**Animation delay or pause time between ball movement*/
	private static final int DELAY = 50;
	/**X velocity*/
	private static final double X_VEL = 5;
	/**Y velocity reduced when it bounces*/
	private static final double BOUNCE_REDUCE = 0.9;
	/**Starting X and Y velocity*/
	private double xVel = X_VEL;
	private double yVel = 0.0;
	/**Private instance variable*/
	private GOval ball;
	/**Starting X and Y coordinates*/
	private static final double X_START = 0;
	private static final double Y_START = 0;
	
	public void run() {
		setup();
		while(ball.getX() < getWidth()) {
			moveBall();
			checkForCollision();
			pause(DELAY);
		}
	}
	/**Create and place ball*/
	private void setup() {
		ball = new GOval(X_START, Y_START, DIAM_BALL, DIAM_BALL);
		ball.setFilled(true);
		add(ball);
	}
	/**Move ball*/
	private void moveBall() {
		yVel += GRAVITY;
		ball.move(xVel, yVel);
	}
	/**Check if collision with floor and update velocity and position*/
	private void checkForCollision() {
		//determine if ball has dropped below floor
		if(ball.getY() > getHeight() - DIAM_BALL) {
			//change Y velocity to now bounce upward
			yVel = -yVel * BOUNCE_REDUCE;
			
			//assume bounce will move ball an amount above the floor equal to 
			//the amount it would have dropped below the floor
			double diff = ball.getY() - (getHeight() - DIAM_BALL);
			ball.move(0, -2 * diff);
		}
	}
}
