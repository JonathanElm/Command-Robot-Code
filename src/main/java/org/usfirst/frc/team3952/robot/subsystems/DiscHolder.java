package org.usfirst.frc.team3952.robot.subsystems;

import org.usfirst.frc.team3952.robot.RobotMap;
import org.usfirst.frc.team3952.robot.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DiscHolder extends Subsystem {	
    private DoubleSolenoid piston = RobotMap.discSolenoid;
    
	
	public boolean extended;
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ManualDiscHolder());
    }
    
    public void shoot() {
        if(!extended)
        {
            piston.set(DoubleSolenoid.Value.kForward);
            extended = true;
        }
    }

    public void retract() {
        if(extended)
        {
            piston.set(DoubleSolenoid.Value.kReverse);
            extended = false;
        }
    }

    public void stop() {
        piston.set(DoubleSolenoid.Value.kOff);
    }
}