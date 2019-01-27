package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualDiscHolder extends Command {
    
    public ManualDiscHolder() {
        requires(Robot.discHolder);
        setInterruptible(true);
    }

    protected void initialize() {}

    double rotateBy = 0;

    protected void execute() {
        Robot.discHolder.shoot();
        Robot.discHolder.retract();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.drive.stop();
    }

    protected void interrupted() {
    	Robot.drive.stop();
    }
}
