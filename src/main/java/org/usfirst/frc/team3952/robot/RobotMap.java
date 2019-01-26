package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;

public class RobotMap {
	public static final int REAR_RIGHT_WHEEL_PORT = 1;
	public static final int REAR_LEFT_WHEEL_PORT = 0;
	public static final int FRONT_RIGHT_WHEEL_PORT = 3;
	public static final int FRONT_LEFT_WHEEL_PORT = 2;
	public static final int LADDER_PORT = 6;
	public static final int COILER_PORT = 4;
	public static final int CLAW_PORT = 5;
	
	public static final int REAR_RIGHT_ENCODER_PORT_1 = 2;
	public static final int REAR_RIGHT_ENCODER_PORT_2 = 3;
	public static final int REAR_LEFT_ENCODER_PORT_1 = 0;
	public static final int REAR_LEFT_ENCODER_PORT_2 = 1;

	public static final int FRONT_RIGHT_ENCODER_PORT_1 = 4;
	public static final int FRONT_RIGHT_ENCODER_PORT_2 = 5;
	public static final int FRONT_LEFT_ENCODER_PORT_1 = 6;
	public static final int FRONT_LEFT_ENCODER_PORT_2 = 7;

	public static final int LADDER_ENCODER_PORT_1 = 8;
	public static final int LADDER_ENCODER_PORT_2 = 9;
	public static final int LADDER_TOP_LIMIT_PORT = 6;
	public static final int LADDER_BOTTOM_LIMIT_PORT = 7;
	public static final int CLAW_OPENING_LIMIT_PORT = 8;
	public static final int CLAW_CLOSING_LIMIT_PORT = 9;

	public static final int SOLENOID_PORT_1 = 0;
	public static final int SOLENOID_PORT_2 = 1;
	
	public static Talon frontLeftWheel;
	public static Talon frontRightWheel;
	public static Talon rearLeftWheel;
	public static Talon rearRightWheel;
	public static MecanumDrive drive;
	public static Encoder frontLeftEncoder;
	public static Encoder frontRightEncoder;
	public static Encoder rearLeftEncoder;
	public static Encoder rearRightEncoder;
	public static ADXRS450_Gyro gyro;
	public static Talon ladder;
	public static Talon coiler;
	public static Talon claw;
	public static Encoder ladderEncoder;
	public static DigitalInput ladderTopLimit;
	public static DigitalInput ladderBottomLimit;
	public static DigitalInput clawOpeningLimit;
	public static DigitalInput clawClosingLimit;
	public static DoubleSolenoid solenoid;
	
	public static void init() {
		frontLeftWheel = new Talon(FRONT_LEFT_WHEEL_PORT);
		frontRightWheel = new Talon(FRONT_RIGHT_WHEEL_PORT);
		rearLeftWheel = new Talon(REAR_LEFT_WHEEL_PORT);
		rearRightWheel = new Talon(REAR_RIGHT_WHEEL_PORT);
		drive = new MecanumDrive(frontLeftWheel, frontRightWheel, rearLeftWheel, rearRightWheel);

		frontLeftEncoder = new Encoder(FRONT_LEFT_ENCODER_PORT_1,FRONT_LEFT_ENCODER_PORT_2, false, Encoder.EncodingType.k1X);
		frontLeftEncoder.setDistancePerPulse(-0.007266115676069 * 2);
		frontRightEncoder = new Encoder(FRONT_RIGHT_ENCODER_PORT_1, FRONT_RIGHT_ENCODER_PORT_2, false, Encoder.EncodingType.k1X);
		frontRightEncoder.setDistancePerPulse(-0.007604813285879 * 2);
		
		rearLeftEncoder = new Encoder(REAR_LEFT_ENCODER_PORT_1, REAR_LEFT_ENCODER_PORT_2, false, Encoder.EncodingType.k1X);
		//rearLeftEncoder.setDistancePerPulse(0.00677);
		rearRightEncoder = new Encoder(REAR_RIGHT_ENCODER_PORT_1, REAR_RIGHT_ENCODER_PORT_2, false, Encoder.EncodingType.k1X);
		//rearRightEncoder.setDistancePerPulse(0.0078);

		gyro = new ADXRS450_Gyro();
		ladder = new Talon(LADDER_PORT);
		coiler = new Talon(COILER_PORT);
		claw = new Talon(CLAW_PORT);
		ladderEncoder = new Encoder(LADDER_ENCODER_PORT_1, LADDER_ENCODER_PORT_2, false, Encoder.EncodingType.k2X);
		//ladderTopLimit = new DigitalInput(LADDER_TOP_LIMIT_PORT);
		//ladderBottomLimit = new DigitalInput(LADDER_BOTTOM_LIMIT_PORT);
		//clawOpeningLimit = new DigitalInput(CLAW_OPENING_LIMIT_PORT);
		//clawClosingLimit = new DigitalInput(CLAW_CLOSING_LIMIT_PORT);
		//TODO yes
		//solenoid = new DoubleSolenoid(SOLENOID_PORT_1, SOLENOID_PORT_2);
	}
}
