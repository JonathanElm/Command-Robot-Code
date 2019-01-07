package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualClimber extends Command {

    public ManualClimber() {
        requires(Robot.climber);
        setInterruptible(true);
    }
    
    protected void initialize() {}
    
    protected void execute() {
    	if(Robot.controller.coil()) {
    		Robot.climber.climb();
    	} else {
    		Robot.climber.stop();
    	}
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    	Robot.climber.stop();
    }
    
    protected void interrupted() {
    	Robot.climber.stop();
    }
}
