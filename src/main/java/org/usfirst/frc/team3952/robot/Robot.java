package org.usfirst.frc.team3952.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.vision.VisionThread;

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
	
	public static KarelTask task;
	
	public static int startMillis;
	private VisionThread visionThread;
	private UsbCamera camera;
	
	private double x0;
	private double x1;
	private final Object imageLock = new Object();
	
	@Override
	public void robotInit() {
		RobotMap.init();
		controller = new Controller(new Joystick(0));
		drive = new DriveTrain();
		ladder = new Ladder();
		claw = new Claw();
		climber = new Climber();
		
		task = null;
		
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 480); //(160, 120)
		//https://wpilib.screenstepslive.com/s/currentCS/m/vision/l/674733-using-generated-code-in-a-robot-program
		visionThread = new VisionThread(camera, new GripPipeline(this), pipeline -> {
			if(pipeline.findContoursOutput().size() == 2) {
				//only if it finds 2 clumps
				Moments m0 = Imgproc.moments(pipeline.findContoursOutput().get(0));
				int _x0 = (int)(m0.get_m10() / m0.get_m00());
				
				Moments m1 = Imgproc.moments(pipeline.findContoursOutput().get(1));
				int _x1 = (int)(m1.get_m10()/ m0.get_m00());
				
				synchronized(imageLock) {
					x0 = _x0;
					x1 = _x1;
				}
				
			}
		});
		//visionThread.start();
		
	}

	@Override
	public void disabledInit() {}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void autonomousInit() {
		double ax0, ax1;
		synchronized(imageLock) {
			ax0=x0;
			ax1=x1;
		}
		SmartDashboard.putNumber("x0", ax0);
		SmartDashboard.putNumber("x1", ax1);
		CommandGroup auto = new CommandGroup();
		auto.addSequential(new MoveForward(1));
		auto.addSequential(new Turn(90));
		auto.addSequential(new MoveForward(1));
		auto.addSequential(new Turn(90));
		auto.addSequential(new MoveForward(1));
		auto.addSequential(new Turn(90));
		auto.addSequential(new MoveForward(1));
		auto.addSequential(new Turn(90));
		auto.addSequential(new MoveForward(1));
		//Scheduler.getInstance().add(auto);
		auto.close();
	}
	
	@Override
	public void autonomousPeriodic() {
		if(task != null) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					task.task();
				}
			}).start();
			task = null;
		}
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void testPeriodic() {}
	
	public static boolean idle() {
		return drive.getCurrentCommandName().equals(drive.getDefaultCommandName()) && 
			   ladder.getCurrentCommandName().equals(ladder.getDefaultCommandName()) && 
			   claw.getCurrentCommandName().equals(claw.getDefaultCommandName()) && 
			   climber.getCurrentCommandName().equals(climber.getDefaultCommandName());
	}
}
