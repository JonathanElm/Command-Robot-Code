package org.usfirst.frc.team3952.robot;

import org.usfirst.frc.team3952.robot.commands.*;

import edu.wpi.first.wpilibj.command.*;

public abstract class KarelTask {
	public final static double UNIT_DISTANCE = 3;
	
	protected byte facing = 0;
	/*
		- 0 North
		- 1 East
		- 2 South
		- 3 West
	*/
	
	public abstract void task();
	
	private void waitForRobot() {
		do {
			try {Thread.sleep(500);} catch(InterruptedException ignored) {}
		} while(!Robot.idle());
	}
	
	protected void move() {
		Scheduler.getInstance().add(new MoveForward(UNIT_DISTANCE));
		waitForRobot();
	}
	
	protected void turnLeft() {
		Scheduler.getInstance().add(new Turn(-90));
		facing--;
		if(facing < 0)
			facing = 3;
		waitForRobot();
	}
	
	protected void turnRight() {
		Scheduler.getInstance().add(new Turn(90));
		facing++;
		if(facing > 3)
			facing = 0;
		waitForRobot();
	}
	
	protected void pickBeeper() {
		// TODO
		waitForRobot();
	}
	
	protected void putBeeper() {
		// TODO
		waitForRobot();
	}
	
	protected boolean facingNorth() {
		return facing == 0;
	}
	
	protected boolean facingEast() {
		return facing == 1;
	}
	
	protected boolean facingSouth() {
		return facing == 2;
	}
	
	protected boolean facingWest() {
		return facing == 3;
	}
	
	protected boolean frontIsClear() {
		return true;
	}
}