package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualLadder extends Command {
    public ManualLadder() {
        requires(Robot.ladder);
        setInterruptible(true);
    }

    protected void initialize() {}
    
    protected void execute() {
    	if(Robot.mainController.extendLadder()) {
			Robot.ladder.extend();
		} else if(Robot.mainController.retractLadder()) {
			Robot.ladder.retract();
		} else {
			Robot.ladder.stop();
		}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.ladder.stop();
    }
    
    protected void interrupted() {
    	Robot.ladder.stop();
    }
}
