package org.usfirst.frc.team3952.robot.subsystems;

import org.usfirst.frc.team3952.robot.RobotMap;
import org.usfirst.frc.team3952.robot.commands.ManualBallHolder;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallHolder extends Subsystem {
	public static final double CLOCKWISE = 1;
	
	private DoubleSolenoid piston = RobotMap.solenoid;
	
	public boolean extended;
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualBallHolder());
    }
    
    public void shoot() {
        if(!extended)
        {
            piston.set(DoubleSolenoid.Value.kForward);
            extended = true;
        }
        stop();
    }

    public void retract() {
        if(extended)
        {
            piston.set(DoubleSolenoid.Value.kReverse);
            extended = false;
        }
        stop();
    }

    public void stop() {
        piston.set(DoubleSolenoid.Value.kOff);
    }
}

