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

    protected void execute() {
    	double hor = Robot.controller.getHorizontalMovement();
    	double lat = Robot.controller.getLateralMovement();
        double rot = Robot.controller.getRotation();
        
        //calibration
        /*
        if(hor <= 0.1 && rot <= 0.1 && lat >= 0.5)
		{
            double maxDistance = 0;
            for(Encoder e : encoders)
                if(e.getDistance() > maxDistance)
                    maxDistance = e.getDistance();
            if(RobotMap.frontLeftEncoder.getDistance() - RobotMap.frontRightEncoder.getDistance() < -10 &&
            RobotMap.rearLeftEncoder.getDistance() - RobotMap.rearRightEncoder.getDistance() < -10)
                rot += 0.2;
            else if(RobotMap.frontLeftEncoder.getDistance() - RobotMap.frontRightEncoder.getDistance() > 10 &&
            RobotMap.rearLeftEncoder.getDistance() - RobotMap.rearRightEncoder.getDistance() > 10)
                rot -= 0.2;
		}
        */
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
