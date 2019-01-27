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
        if(Robot.mainController.releaseDisc())
            Robot.discHolder.shoot();
        else if(Robot.mainController.grabDisc())
            Robot.discHolder.retract();
        else
            Robot.discHolder.stop();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.discHolder.stop();
    }

    protected void interrupted() {
    	Robot.discHolder.stop();
    }
}
