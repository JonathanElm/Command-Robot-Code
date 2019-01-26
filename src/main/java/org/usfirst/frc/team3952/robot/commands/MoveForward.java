package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MoveForward extends Command {
	public double initialDistance;
	public double distance;
	public boolean finished;
	
	public MoveForward(double distance) {
		requires(Robot.drive);
		setInterruptible(false);
		this.distance = distance;
	}

    protected void initialize() {
    	initialDistance = (Robot.drive.front_left.getDistance() + Robot.drive.front_right.getDistance()) / 2;
    }
    
    protected void execute() {
    	double currentDistance = (Robot.drive.front_left.getDistance() + Robot.drive.front_right.getDistance()) / 2;
    	if(currentDistance >= initialDistance + distance - 0.01) {
    		Robot.drive.stop();
    		finished = true;
    	} else {
    		Robot.drive.drive(0, 0.3, -0.015);
		}		
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
