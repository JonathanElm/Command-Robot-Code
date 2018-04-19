package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3952.robot.subsystems.Claw;
import org.usfirst.frc.team3952.robot.subsystems.Climber;
import org.usfirst.frc.team3952.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3952.robot.subsystems.Ladder;
import org.usfirst.frc.team3952.robot.commands.*;

public class Robot extends TimedRobot {
	public static Controller controller;
	public static DriveTrain drive;
	public static Ladder ladder;
	public static Claw claw;
	public static Climber climber;
	
	public static int startMillis;
	
	@Override
	public void robotInit() {
		RobotMap.init();
		controller = new Controller(new Joystick(0));
		drive = new DriveTrain();
		ladder = new Ladder();
		claw = new Claw();
		climber = new Climber();
	}

	@Override
	public void disabledInit() {}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void autonomousInit() {
		Scheduler.getInstance().add(new MoveForward(1));
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
	public void testPeriodic() {}
}
