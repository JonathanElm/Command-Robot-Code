package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3952.robot.RobotMap;


// Tolerable within 11.6347742 in. - 16.5 in.

public class MoveToWall extends Command {  
     //change constant according to tests, 
    public double v2in = 41.552765;
    public boolean finished = false;
	
	public MoveToWall() {
        requires(Robot.drive);
		setInterruptible(false);
	}

    protected void initialize() {}
    
    protected void execute() {
        //System.out.println(RobotMap.ultrasonicSensor.getVoltage() * v2in);
        /* actuall code
        if (getDistance() > distanceToStop ) {
            //change 0.5 for speed
            Robot.drive.drive(0,0.5,0);
        } else {
            finished = true;
        }
        */
        
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
