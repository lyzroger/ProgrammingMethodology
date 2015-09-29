/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;
	
	/** Offset of the start instruction up from the bottom */
	private static final int INSTR_Y_OFFSET = 250;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	
	/** X and Y Velocity*/
	private static final double X_VELOCITY = 9.0;
	private static final double Y_VELOCITY = 9.0;
	

	public void run() {
		/* You fill this in, along with any subsidiary methods */
		
		addMouseListeners();
		createPaddle();
		//createBall();
		startInstruction();
		//initialVxVy();
		addBricks();
		//waitForClick();
		
		for(turnLeft = NTURNS; turnLeft > 0; turnLeft--) {
			showTurnLeft();
			createBall();
			initialVxVy();
			waitForClick();
			while(gameOver() == false) {
				moveBall();
				checkCollision();
				pause(30);
			}
			remove(ball);
			remove(turn_left);
			if(turnLeft > 1 && brickCount > 0) {
				startInstruction();
				//waitForClick();
			}
			if(brickCount == 0) break;
		}
		showResult();
		
	}
	
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	
	private int turnLeft;
	/**Show how many turns left*/
	private GLabel turn_left;
	private void showTurnLeft() {
		turn_left = new GLabel("Turn Left: " + turnLeft,0, BRICK_Y_OFFSET / 2);
		add(turn_left);
	}
	
	/**Create the paddle initially at the center of the bottom.*/
	private GRect paddle;
	private void createPaddle() {
		double paddle_x_init = (getWidth() - PADDLE_WIDTH) / 2;
		double paddle_y_init = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		paddle = new GRect(paddle_x_init, paddle_y_init, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		add(paddle);
	}
	
	/**Reposition the paddle in response to the mouse moving.*/
	public void mouseMoved(MouseEvent paddle_control) {
		/*Make the center of the paddle follow the mouse movement*/
		double newX = paddle_control.getX() - paddle.getWidth() / 2.0;
		double newY = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		/* if mouse position is btn 0 and half width of paddle, the paddle position should be 0
		 * if mouse position is btn (window width - half width of paddle) and window width, the 
		 * paddle position should be (window width - paddle width)
		 */
		if(paddle_control.getX() <= (paddle.getWidth() / 2.0)) {
			newX = 0;
		} else if (paddle_control.getX() >= (getWidth() - paddle.getWidth() / 2.0)) {
			newX = getWidth() - PADDLE_WIDTH;
		}
		paddle.setLocation(newX, newY);
	}
	
	/**Create a ball at the center of the window*/
	private GOval ball;
	private void createBall() {
		double ball_x_init = (getWidth() - BALL_RADIUS) / 2.0;
		double ball_y_init = (getHeight() - BALL_RADIUS) / 2.0;
		ball = new GOval(ball_x_init, ball_y_init, BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		add(ball);
	}
	
	/*Velocity of Ball*/
	private double vx, vy;
	/**Initialize vx and vy*/
	private void initialVxVy() {
		RandomGenerator rgen = RandomGenerator.getInstance();
		vx = rgen.nextDouble(1.0, X_VELOCITY);
		if(rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
		vy = Y_VELOCITY;
	}
	/**Ball bounces off the wall*/
	private void moveBall() {
		ball.move(vx, vy);
	}
	
	/**Check for collision*/
	private void checkCollision() {
		bottomTopCollision();
		leftRightCollision();
		paddleCollision();
		bricksCollision();
	}
	
	/**Check collision with the bottom or the top*/
	private void bottomTopCollision() {
		if((ball.getY() > getHeight() - BALL_RADIUS) || (ball.getY() < 0)) {
			vy = -vy;
			bounceClip.play();
		}
	}
		
	/**Check collision with the left and right*/
	private void leftRightCollision() {
		if((ball.getX() < 0) || (ball.getX() > getWidth() - BALL_RADIUS)) {
			vx = -vx;
			bounceClip.play();
		}
	}
	
	/**Get the colliding object*/
	private GObject getCollidingObjectBrick() {
		GObject collidingObject;
		if(getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS)) != null) {
			collidingObject = getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS));
		} else if (getElementAt((ball.getX() + BALL_RADIUS), (ball.getY() + BALL_RADIUS)) != null) {
			collidingObject = getElementAt((ball.getX() + BALL_RADIUS), (ball.getY() + BALL_RADIUS));
		} else if(getElementAt(ball.getX(), ball.getY()) != null){
			collidingObject = getElementAt(ball.getX(), ball.getY());
		} else if(getElementAt((ball.getX() + BALL_RADIUS), ball.getY()) != null) {
			collidingObject = getElementAt((ball.getX() + BALL_RADIUS), ball.getY());
		} else {
			collidingObject = null;
		}
		return collidingObject;
	}
	
	/**Check collision with paddle*/
	private void bricksCollision() {
		GObject collider = getCollidingObjectBrick();
		if((collider != null) && (collider != turn_left) && (collider != paddle)) {
			vy = -vy; 
			remove(collider);
			brickCount--;
			bounceClip.play();
		}
	}
	
	/**Get the colliding object*/
	private GObject getCollidingObjectPaddle() {
		GObject collidingObject;
		if(getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS)) != null) {
			collidingObject = getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS));
		} else if (getElementAt((ball.getX() + BALL_RADIUS), (ball.getY() + BALL_RADIUS)) != null) {
			collidingObject = getElementAt((ball.getX() + BALL_RADIUS), (ball.getY() + BALL_RADIUS));
		} else {
			collidingObject = null;
		}
		return collidingObject;
	}
	
	/**Check collision with paddle*/
	private void paddleCollision() {
		GObject collider = getCollidingObjectPaddle();
		if(collider == paddle) {
			vy = -vy;
			ball.move(vx, -BALL_RADIUS);
			bounceClip.play();
		}
	}
	
	
	/**Start drop the ball after clicking the mouse*/
	public void mouseClicked(MouseEvent ball_start) {
		remove(start_instr);
	}
	
	/**Show the instruction how to start the game*/
	private GLabel start_instr;
	private void startInstruction() {
		start_instr = new GLabel("Click the mouse to start.");
		double instr_x = (getWidth() - start_instr.getWidth()) / 2.0;
		double instr_y = getHeight() - INSTR_Y_OFFSET;
		start_instr.setLocation(instr_x, instr_y);
		add(start_instr);
	}
		
	/**Game Result shown*/
	private GLabel gameOverInstr;
	private void showResult() {
		if(brickCount == 0) {
			gameOverInstr = new GLabel("You win!!!");
		} else {
			gameOverInstr = new GLabel("You lose!");
		}
		double instr_x = (getWidth() - gameOverInstr.getWidth()) / 2.0;
		double instr_y = getHeight() - INSTR_Y_OFFSET;
		gameOverInstr.setLocation(instr_x, instr_y);
		add(gameOverInstr);
	}
	
	//private int turnLeft;
	/**Game Over conditions*/
	private boolean gameOver(){
		boolean over = false;
		if((ball.getY() > getHeight() - BALL_RADIUS) || (brickCount == 0)) {
			over = true;
		}
		return over;
	}
	
	/**Add bricks*/
	private int brickCount = 0;
	private GRect brick;
	private void addBricks() {
		double width = NBRICKS_PER_ROW * BRICK_WIDTH + (NBRICKS_PER_ROW - 1) * BRICK_SEP;
		double brick_x_init = (getWidth() - width) / 2.0;
		double brick_y_init = BRICK_Y_OFFSET;
		for(int i = 0; i < NBRICK_ROWS; i++) {
			for(int j = 0; j < NBRICKS_PER_ROW; j++) {
				double x = brick_x_init + j * (BRICK_WIDTH + BRICK_SEP);
				double y = brick_y_init + i * (BRICK_HEIGHT + BRICK_SEP);
				brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				if((i == 0) || (i == 1)) {
					brick.setColor(Color.RED);
				} else if((i == 2) || (i == 3)) {
					brick.setColor(Color.ORANGE);
				} else if((i == 4) || (i == 5)) {
					brick.setColor(Color.YELLOW);
				} else if((i == 6) || (i == 7)) {
					brick.setColor(Color.GREEN);
				} else if((i == 8) || (i == 9)) {
					brick.setColor(Color.CYAN);
				}
				add(brick);
				brickCount++;
			}
		}
	}
	

}
