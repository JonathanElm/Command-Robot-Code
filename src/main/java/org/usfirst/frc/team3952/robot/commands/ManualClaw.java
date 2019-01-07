package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualClaw extends Command {

    public ManualClaw() {
        requires(Robot.claw);
        setInterruptible(true);
    }

    protected void initialize() {}
    
    protected void execute() {
    	if(Robot.controller.unsafeOpenClaw()) {
    		Robot.claw.openUnsafe();
    	} else if(Robot.controller.unsafeCloseClaw()) {
    		Robot.claw.closeUnsafe();
    	} else {
    		Robot.claw.stop();
    	}
    }
    
    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    	Robot.claw.stop();
    }
    
    protected void interrupted() {
    	Robot.claw.stop();
    }
}
