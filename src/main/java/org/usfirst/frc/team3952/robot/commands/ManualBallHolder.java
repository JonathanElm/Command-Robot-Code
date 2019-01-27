package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualBallHolder extends Command {
    
    public ManualBallHolder() {
        requires(Robot.ballHolder);
        setInterruptible(true);
    }

    protected void initialize() {}

    double rotateBy = 0;

    protected void execute() {
        if(Robot.mainController.shootBallHolder())
            Robot.ballHolder.shoot();
        else if(Robot.mainController.retractBallHolder())
            Robot.ballHolder.retract();
        else
            Robot.ballHolder.stop();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.ballHolder.stop();
    }

    protected void interrupted() {
    	Robot.ballHolder.stop();
    }
}
