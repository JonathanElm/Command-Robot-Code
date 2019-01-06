package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command {
	public double initialAngle;
	public double degrees;
	public long lastMillis;
	public boolean finished;

    public Turn(double degrees) {
        requires(Robot.drive);
        setInterruptible(false);
        this.degrees = degrees;
    }
    
    protected void initialize() {
    	initialAngle = Robot.drive.gyro.getAngle();
    	lastMillis = System.currentTimeMillis();
    }
    
    protected void execute() {
    	long nowMillis = System.currentTimeMillis();
    	if(differenceAngle(Robot.drive.gyro.getAngle() + Robot.drive.gyro.getRate() * (nowMillis - lastMillis) / 1000, initialAngle + degrees) < 7.0) {
    		Robot.drive.stop();
    		finished = true;
    	} else if(degrees < 0) {
    		Robot.drive.drive(0,  0,  -0.5);
    	} else if(degrees > 0) {
    		Robot.drive.drive(0, 0, 0.5);
    	}
    	lastMillis = nowMillis;
    }
    
    protected boolean isFinished() {
        return finished;
    }
    
    protected void end() {
    	Robot.drive.stop();
    }
    
    protected void interrupted() {
    	Robot.drive.stop();
    }
    
    private static double differenceAngle(double a1, double a2) {
    	return Math.abs(a1 % 360 - a2 % 360);
    }
}
