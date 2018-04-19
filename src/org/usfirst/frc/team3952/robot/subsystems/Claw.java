package org.usfirst.frc.team3952.robot.subsystems;

import org.usfirst.frc.team3952.robot.Robot;
import org.usfirst.frc.team3952.robot.RobotMap;
import org.usfirst.frc.team3952.robot.commands.ManualClaw;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {
	public static final double CLOCKWISE = 1;
	
	private Talon motor = RobotMap.claw;
	
	public DigitalInput openingLimit = RobotMap.clawOpeningLimit;
	public DigitalInput closingLimit = RobotMap.clawClosingLimit;
	
	public boolean opened;
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualClaw());
    }
    
    public void open() {
    	if(openingLimit.get()) {
    		motor.set(-CLOCKWISE);
    	} else {
    		motor.set(0);
    		opened = true;
    	}
    }
    
    public void openUnsafe() {
    	motor.set(-CLOCKWISE * 0.8);
    }
    
    public void close() {
    	if(closingLimit.get() && System.currentTimeMillis() - Robot.startMillis <= 300) {
    		motor.set(CLOCKWISE);
    	} else {
    		motor.set(0);
    		opened = false;
    	}
    }
    
    public void closeUnsafe() {
    	motor.set(CLOCKWISE * 0.8);
    }
    
    public void stop() {
    	motor.set(0);
    }
}

