package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;
import org.usfirst.frc.team3952.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class ManualDrive extends Command {

	public Encoder[] encoders = new Encoder[] { RobotMap.frontLeftEncoder, RobotMap.frontRightEncoder, RobotMap.rearLeftEncoder, RobotMap.rearRightEncoder };
    
    public ManualDrive() {
        requires(Robot.drive);
        setInterruptible(true);
    }

    protected void initialize() {}

    double rotateBy = 0;

    protected void execute() {
    	double hor = Robot.maincontroller.getHorizontalMovement();
    	double lat = Robot.maincontroller.getLateralMovement();
        double rot = Robot.maincontroller.getRotation();

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
