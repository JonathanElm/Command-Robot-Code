package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;
import org.usfirst.frc.team3952.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Teleop extends Command {
    public Teleop() {
    	setInterruptible(false);
    }

    protected void initialize() {}

    protected void execute() {
    	
    	
    	
    	
    	
    }

    protected boolean isFinished() {
    	return false;
    }
    
    protected void end() {
    	RobotMap.drive.driveCartesian(0, 0, 0);
    	Robot.ladder.stop();
    	Robot.claw.stop();
    	Robot.climber.stop();
    }
    
    protected void interrupted() {
    	RobotMap.drive.driveCartesian(0, 0, 0);
    	Robot.ladder.stop();
    	Robot.claw.stop();
    	Robot.climber.stop();
    }
}
