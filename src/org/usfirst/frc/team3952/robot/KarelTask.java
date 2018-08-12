package org.usfirst.frc.team3952.robot;

import org.usfirst.frc.team3952.robot.commands.*;

import edu.wpi.first.wpilibj.command.*;

public abstract class KarelTask {
	public final static double UNIT_DISTANCE = 3;
	
	protected boolean facingNorth = true, 
					  facingEast = false, 
					  facingSouth = false, 
					  facingWest = false;
	
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
		if(facingNorth) {
			facingNorth = false;
			facingWest = true;
		} else if(facingWest) {
			facingWest = false;
			facingSouth = true;
		} else if(facingSouth) {
			facingSouth = false;
			facingEast = true;
		} else if(facingEast) {
			facingEast = false;
			facingNorth = true;
		}
		waitForRobot();
	}
	
	protected void turnRight() {
		Scheduler.getInstance().add(new Turn(90));
		if(facingNorth) {
			facingNorth = false;
			facingEast = true;
		} else if(facingWest) {
			facingWest = false;
			facingNorth = true;
		} else if(facingSouth) {
			facingSouth = false;
			facingWest = true;
		} else if(facingEast) {
			facingEast = false;
			facingSouth = true;
		}
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
		return facingNorth;
	}
	
	protected boolean facingEast() {
		return facingEast;
	}
	
	protected boolean facingSouth() {
		return facingSouth;
	}
	
	protected boolean facingWest() {
		return facingWest;
	}
	
	protected boolean frontIsClear() {
		// TODO
		return true;
	}
}