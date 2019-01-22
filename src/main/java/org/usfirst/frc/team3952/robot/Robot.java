package org.usfirst.frc.team3952.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.vision.VisionThread;

import org.usfirst.frc.team3952.robot.subsystems.BallHolder;
import org.usfirst.frc.team3952.robot.subsystems.Claw;
import org.usfirst.frc.team3952.robot.subsystems.Climber;
import org.usfirst.frc.team3952.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3952.robot.subsystems.Ladder;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.usfirst.frc.team3952.robot.commands.*;

public class Robot extends TimedRobot {
	public static Controller controller;
	public static DriveTrain drive;
	public static Ladder ladder;
	public static Claw claw;
	public static Climber climber;
	public static BallHolder ballholder;
	
	public static KarelTask task;
	
	public static int startMillis;
	private VisionThread visionThread;
	private UsbCamera camera;
	
	//[0][1] First blob center
	//[2][3] Second blob center
	//[4][5] Width/Height
	private static int[] autodropInfo = new int[2];
	private final Object imageLock = new Object();
	
	@Override
	public void robotInit() {
		RobotMap.init();
		controller = new Controller(new Joystick(0));
		drive = new DriveTrain();
		ladder = new Ladder();
		claw = new Claw();
		climber = new Climber();
		ballholder = new BallHolder();
		
		task = null;
		
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(160, 120); //(640, 480)
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
			if(pipeline.findContoursOutput().size() >= 2) {
				//find the two largest contour
				int a_index = 0;
				double a_area = 0;
				int b_index = 0;
				double b_area = 0;

				for(int i = 0; i < pipeline.findContoursOutput().size(); i++)
				{
					//reduce load on cpu repetition, increase ram usage however
					double area = Imgproc.contourArea(pipeline.findContoursOutput().get(i));
					if(area > a_area)
					{	
						a_index = i;
						a_area = area;
					}
				}

				for(int i = 0; i < pipeline.findContoursOutput().size(); i++)
				{
					if(i != a_index)
					{
						double area = Imgproc.contourArea(pipeline.findContoursOutput().get(i));
						if(area > b_area)
						{	
							b_index = i;
							b_area = area;
						}
					}
				}

				Moments m0 = Imgproc.moments(pipeline.findContoursOutput().get(a_index));
				int _x0 = (int)(m0.get_m10() / m0.get_m00());
				int _y0 = (int)(m0.get_m01() / m0.get_m00());

				Moments m1 = Imgproc.moments(pipeline.findContoursOutput().get(b_index));
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
	}

	@Override
	public void disabledInit() {}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public static int[] distanceToCenter()
	{
		return autodropInfo;
	}

	@Override
	public void autonomousInit() {
		double ax0, ax1;
		synchronized(imageLock) {
			ax0=autodropInfo[0];
			ax1=autodropInfo[1];
		}
		SmartDashboard.putNumber("x0", ax0);
		SmartDashboard.putNumber("x1", ax1);
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
	public void teleopInit() {}

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
			   claw.getCurrentCommandName().equals(claw.getDefaultCommandName()) && 
			   climber.getCurrentCommandName().equals(climber.getDefaultCommandName()) &&
			   ballholder.getCurrentCommandName().equals(ballholder.getDefaultCommandName());
	}
}
