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
    	double hor = Robot.controller.getHorizontalMovement();
    	double lat = Robot.controller.getLateralMovement();
        double rot = Robot.controller.getRotation();

        //Calibration (to hardcode)
        //Change true to false to calibrate
        if(true)
        {
            Robot.drive.drive(hor, lat, rot);
            return;
        }
        else if (lat < 0.4) return;

        Robot.drive.drive(0, 1, rot + rotateBy);
        
        if(Math.abs(RobotMap.frontLeftEncoder.getDistance() - RobotMap.rearLeftEncoder.getDistance()) > 5)
            System.out.println("Probably a hardware problem on the left side of wheels; detected a distance of "
            + (RobotMap.frontLeftEncoder.getDistance() - RobotMap.frontLeftEncoder.getDistance()));
        if(Math.abs(RobotMap.frontLeftEncoder.getDistance() - RobotMap.frontLeftEncoder.getDistance()) > 5)
            System.out.println("Probably a hardware problem on the right side of wheels; detected a distance of "
            + (RobotMap.frontRightEncoder.getDistance() - RobotMap.rearRightEncoder.getDistance()));

        RobotMap.frontLeftEncoder.reset();
        RobotMap.frontRightEncoder.reset();
        RobotMap.rearLeftEncoder.reset();
        RobotMap.rearRightEncoder.reset();

        if(RobotMap.frontLeftEncoder.getDistance() - RobotMap.frontRightEncoder.getDistance() > 5)
            rotateBy += 0.01;
        else if(RobotMap.frontLeftEncoder.getDistance() - RobotMap.frontRightEncoder.getDistance() < -5)
            rotateBy -= 0.01;

        System.out.println("Rotation Factor (forwards): " + rotateBy);
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
