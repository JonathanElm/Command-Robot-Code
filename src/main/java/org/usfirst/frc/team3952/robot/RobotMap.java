package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;

public class RobotMap {

	//PWM
	public static final int REAR_LEFT_WHEEL_PORT = 0;
	public static final int REAR_RIGHT_WHEEL_PORT = 1;
	public static final int FRONT_LEFT_WHEEL_PORT = 2;
	public static final int FRONT_RIGHT_WHEEL_PORT = 3;
	public static final int LADDER_PORT = 4;
	public static final int ROBO_CLAW_ENABLER_PORT = 5;
	
	//DIO
	//public static final int REAR_RIGHT_ENCODER_PORT_1 = 2;
	//public static final int REAR_RIGHT_ENCODER_PORT_2 = 3;
	//public static final int REAR_LEFT_ENCODER_PORT_1 = 0;
	//public static final int REAR_LEFT_ENCODER_PORT_2 = 1;
	
	//blame electronics/mech for messing up left and right
	public static final int FRONT_RIGHT_ENCODER_PORT_1 = 0;
	public static final int FRONT_RIGHT_ENCODER_PORT_2 = 1;
	public static final int FRONT_LEFT_ENCODER_PORT_1 = 2;
	public static final int FRONT_LEFT_ENCODER_PORT_2 = 3;

	public static final int LADDER_ENCODER_PORT_1 = 4;
	public static final int LADDER_ENCODER_PORT_2 = 5;
	public static final int LADDER_TOP_LIMIT_PORT = 6;
	public static final int LADDER_BOTTOM_LIMIT_PORT = 7;

	//PNEUMATIC BOARD (PCM)
	public static final int BALL_SOLENOID_PORT_1 = 0;
	public static final int BALL_SOLENOID_PORT_2 = 1;
	public static final int DISC_SOLENOID_PORT_1 = 2;
	public static final int DISC_SOLENOID_PORT_2 = 3;
	
	//ANALOG IN
	public static final int QTI_SENSOR_PORT = 0;
	public static final int ULTRASONIC_PORT = 1;
	
	public static Spark frontLeftWheel;
	public static Spark frontRightWheel;
	public static Spark rearLeftWheel;
	public static Spark rearRightWheel;
	public static MecanumDrive drive;
	public static Encoder frontLeftEncoder;
	public static Encoder frontRightEncoder;
	public static Encoder rearLeftEncoder;
	public static Encoder rearRightEncoder;
	public static ADXRS450_Gyro gyro;
	public static PWMTalonSRX ladder;
	public static Encoder ladderEncoder;
	public static DigitalInput ladderTopLimit;
	public static DigitalInput ladderBottomLimit;
	public static DoubleSolenoid ballSolenoid;
	public static DoubleSolenoid discSolenoid;
	public static AnalogInput qtiSensor;
	public static AnalogInput ultrasonicSensor;
	public static Servo clawDeploy;
	public static DigitalInput limitSwitch;
	
	
	public static void init() {
		frontLeftWheel = new Spark(FRONT_LEFT_WHEEL_PORT);
		frontRightWheel = new Spark(FRONT_RIGHT_WHEEL_PORT);
		rearLeftWheel = new Spark(REAR_LEFT_WHEEL_PORT);
		rearRightWheel = new Spark(REAR_RIGHT_WHEEL_PORT);
		drive = new MecanumDrive(frontLeftWheel, frontRightWheel, rearLeftWheel, rearRightWheel);
		clawDeploy = new Servo(ROBO_CLAW_ENABLER_PORT);

		frontLeftEncoder = new Encoder(FRONT_LEFT_ENCODER_PORT_1,FRONT_LEFT_ENCODER_PORT_2, false, Encoder.EncodingType.k1X);
		frontLeftEncoder.setDistancePerPulse(-0.007266115676069);
		frontRightEncoder = new Encoder(FRONT_RIGHT_ENCODER_PORT_1, FRONT_RIGHT_ENCODER_PORT_2, false, Encoder.EncodingType.k1X);
		frontRightEncoder.setDistancePerPulse(-0.007604813285879);

		ladderTopLimit = new DigitalInput(LADDER_TOP_LIMIT_PORT);
		ladderBottomLimit = new DigitalInput(LADDER_BOTTOM_LIMIT_PORT);


		/*
		rearLeftEncoder = new Encoder(REAR_LEFT_ENCODER_PORT_1, REAR_LEFT_ENCODER_PORT_2, false, Encoder.EncodingType.k1X);
		rearLeftEncoder.setDistancePerPulse(-0.007266115676069);
		rearRightEncoder = new Encoder(REAR_RIGHT_ENCODER_PORT_1, REAR_RIGHT_ENCODER_PORT_2, false, Encoder.EncodingType.k1X);
		rearRightEncoder.setDistancePerPulse(-0.007604813285879);
		*/


		gyro = new ADXRS450_Gyro();
		ladder = new PWMTalonSRX(LADDER_PORT);
		ladderEncoder = new Encoder(LADDER_ENCODER_PORT_1, LADDER_ENCODER_PORT_2, false, Encoder.EncodingType.k2X);
		ballSolenoid = new DoubleSolenoid(BALL_SOLENOID_PORT_1, BALL_SOLENOID_PORT_2);
		discSolenoid = new DoubleSolenoid(DISC_SOLENOID_PORT_1, DISC_SOLENOID_PORT_2);
		qtiSensor = new AnalogInput(QTI_SENSOR_PORT);
		ultrasonicSensor = new AnalogInput(ULTRASONIC_PORT);
	}
}
