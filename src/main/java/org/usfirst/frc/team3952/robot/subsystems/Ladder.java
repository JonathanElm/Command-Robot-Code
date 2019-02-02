package org.usfirst.frc.team3952.robot.subsystems;

import org.usfirst.frc.team3952.robot.RobotMap;
import org.usfirst.frc.team3952.robot.commands.ManualLadder;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Ladder extends Subsystem {
	public static final int FIXED_AMOUNT = 0;
	public static final double LADDER_EXTENDING_SPEED = 0.65;
	public static final double LADDER_RETRACTING_SPEED = 0.4;
		
	public Encoder encoder = RobotMap.ladderEncoder;
	
	
	public int pos = 0;
	
    public void initDefaultCommand() {
        setDefaultCommand(new ManualLadder());
    }
    
    public void extend() {
    	if(!RobotMap.ladderTopLimit.get()) {
    		RobotMap.ladder.set(LADDER_EXTENDING_SPEED);
    	} else {
    		RobotMap.ladder.set(0);
    	}
    }
    
    public void retract() {
    	if(!RobotMap.ladderBottomLimit.get()) {
    		RobotMap.ladder.set(-LADDER_RETRACTING_SPEED);   
    	} else {
    		RobotMap.ladder.set(0);
    		encoder.reset();
    	}
    }
    
    public void stop() {
    	RobotMap.ladder.set(0);
    }
}

