package org.usfirst.frc.team3952.robot.subsystems;

import org.usfirst.frc.team3952.robot.RobotMap;
import org.usfirst.frc.team3952.robot.commands.ManualDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class DriveTrain extends Subsystem {
	private MecanumDrive drive = RobotMap.drive;
	
	public Encoder front_left = RobotMap.frontLeftEncoder;
	public Encoder front_right = RobotMap.frontRightEncoder;
	
	public Encoder right_left = RobotMap.rearLeftEncoder;
	public Encoder right_right = RobotMap.rearRightEncoder;

	public ADXRS450_Gyro gyro = RobotMap.gyro;
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualDrive());
    }
    
    public void drive(double hor, double lat, double rot) {
    	drive.driveCartesian(lat, hor, rot);
    }
    
    public void stop() {
    	drive.driveCartesian(0,  0,  0);
    }
}

