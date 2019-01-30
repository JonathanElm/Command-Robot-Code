package org.usfirst.frc.team3952.robot.commands;

import 

public class MoveToWall extends Command {
    public double initialAngle;
	public double degrees;
	public long lastMillis;
	public boolean finished;

    public MoveToWall() {
        
    }
    
    protected void initialize() {
    	
    }
    
    protected void execute() {
    	
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
}