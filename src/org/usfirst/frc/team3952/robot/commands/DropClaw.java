package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DropClaw extends Command {
	public static final double TIMEOUT = 1.0;
	
	public long startMillis;
	
	public DropClaw() {
		requires(Robot.climber);
		setTimeout(TIMEOUT);
		setInterruptible(false);
	}

    protected void initialize() {
    	startMillis = System.currentTimeMillis();
    }

    protected void execute() {
    	Robot.climber.breakString();
    }

    protected boolean isFinished() {
        return System.currentTimeMillis() >= startMillis + 1000 * TIMEOUT;
    }

    protected void end() {
    	Robot.climber.stop();
    }
    
    protected void interrupted() {
    	Robot.climber.stop();
    }
}
