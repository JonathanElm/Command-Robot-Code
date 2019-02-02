package org.usfirst.frc.team3952.robot;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3952.robot.subsystems.BallHolder;
import org.usfirst.frc.team3952.robot.subsystems.DiscHolder;
import org.usfirst.frc.team3952.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3952.robot.subsystems.Ladder;

import org.usfirst.frc.team3952.robot.commands.*;

public class Robot extends TimedRobot {
	public static Controller mainController;
	public static Controller ladderController;
	public static DriveTrain drive;
	public static Ladder ladder;
	public static BallHolder ballHolder;
	public static DiscHolder discHolder;
	public static NetworkTableInstance ntinst;
	public static NetworkTable ntable;

	public static NetworkTableEntry autoAlignX;
	public static NetworkTableEntry autoAlignY;
	
	
	
	public static int startMillis;
	//private VisionThread visionThread;
	//private UsbCamera camera;
	public static Subsystem sensor;
	
	@Override
	public void robotInit() {
		RobotMap.init();
		mainController = new Controller(new Joystick(0), true);
		ladderController = new Controller(new Joystick(1), false);
		drive = new DriveTrain();
		ladder = new Ladder();
		ballHolder = new BallHolder();
		discHolder = new DiscHolder();
		ntinst = NetworkTableInstance.getDefault();
		ntable = ntinst.getTable("datatable");
		autoAlignX = ntable.getEntry("movex");
		autoAlignY = ntable.getEntry("movey");

		
		/*
		Handled by co-processor.

		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(160, 120); //(640, 480)
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
			ArrayList<MatOfPoint> contours = pipeline.findContoursOutput();
			if(contours.size() >= 2) {
				//find the two largest contour

				contours.sort((a, b) -> (int) (Imgproc.contourArea(a) - Imgproc.contourArea(b)));

				Moments m0 = Imgproc.moments(contours.get(contours.size()-1));
				int _x0 = (int)(m0.get_m10() / m0.get_m00());
				int _y0 = (int)(m0.get_m01() / m0.get_m00());

				Moments m1 = Imgproc.moments(contours.get(contours.size()-2));
				int _x1 = (int)(m1.get_m10() / m1.get_m00());
				int _y1 = (int)(m1.get_m01() / m1.get_m00());

				int width = pipeline.cvErodeOutput().cols();
				int height = pipeline.cvErodeOutput().rows();
				
				synchronized(imageLock) {
					//autodropInfo = new int[] {distance_x, distance_y};
					int center_x = width/2;
					int mid_x = (_x0 + _x1)/2;
					int distance_x = mid_x - center_x;

					int center_y = height/2;
					int mid_y = (_y0 + _y1)/2;
					int distance_y = mid_y - center_y;

					autodropInfo = new int[] {distance_x, distance_y};
				}
			}
		});
		visionThread.start();
		*/
	}

	@Override
	public void disabledInit() {}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public static int[] distanceToCenter()
	{
		return new int[] {autoAlignX.getNumber(0).intValue(), autoAlignY.getNumber(0).intValue()};
	}

	@Override
	public void autonomousInit() {
		CommandGroup auto = new CommandGroup();
		auto.addSequential(new AutoAlign());
		Scheduler.getInstance().add(auto);
		auto.close();
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Scheduler.getInstance().run();
		
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void testInit() {}
	
	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public static boolean idle() {
		return drive.getCurrentCommandName().equals(drive.getDefaultCommandName()) && 
			   ladder.getCurrentCommandName().equals(ladder.getDefaultCommandName()) && 
			   ballHolder.getCurrentCommandName().equals(ballHolder.getDefaultCommandName()) &&
			   discHolder.getCurrentCommandName().equals(discHolder.getDefaultCommandName());
	}
}
