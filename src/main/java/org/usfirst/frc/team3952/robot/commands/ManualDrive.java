package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualDrive extends Command {
    public ManualDrive() {
        requires(Robot.drive);
        setInterruptible(true);
    }

    protected void initialize() {}

    protected void execute() {
    	double hor = Robot.controller.getHorizontalMovement();
    	double lat = Robot.controller.getLateralMovement();
    	double rot = Robot.controller.getRotation();
    	Robot.drive.drive(hor, lat, rot);
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
