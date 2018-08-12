package org.usfirst.frc.team3952.robot.subsystems;

import org.usfirst.frc.team3952.robot.RobotMap;
import org.usfirst.frc.team3952.robot.commands.ManualClimber;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	public static final double CLIMB_SPEED = -0.5;
	public static final double BREAK_STRING_SPEED = -0.3;
	
	private Talon motor = RobotMap.coiler;
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualClimber());
    }
    
    public void climb() {
    	motor.set(CLIMB_SPEED);
    }
    
    public void breakString() {
    	motor.set(BREAK_STRING_SPEED);
    }
    
    public void stop() {
    	motor.set(0);
    }
}

