package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;
import org.usfirst.frc.team3952.robot.subsystems.Ladder;

import edu.wpi.first.wpilibj.command.Command;

public class MoveLadderToPos extends Command {
	public static final double TIMEOUT = 8.0;
	
	public int pos;
	
    public MoveLadderToPos(int pos) {
        requires(Robot.ladder);
        setTimeout(TIMEOUT);
        setInterruptible(false);
        this.pos = pos;
    }

    protected void initialize() {}

    protected void execute() {
    	switch(pos) {
    		case 0: 
    			if(Robot.ladder.bottomLimit.get()) {
    				Robot.ladder.pos = 0;
    				Robot.ladder.encoder.reset();
    				Robot.ladder.stop();
    			} else {
    				Robot.ladder.retract();
    			}
    		break;
    		case 1:
    			int diff = Ladder.SWITCH_ENC - (int)Robot.ladder.encoder.getDistance();
    			if(Math.abs(diff) < 100) {
    				Robot.ladder.pos = 1;
    				Robot.ladder.stop();
    			} else if(diff > 0) {
    				Robot.ladder.extend();
    			} else {
    				Robot.ladder.retract();
    			}
    		break;
    		case 2: 
    			if(Robot.ladder.topLimit.get()) {
    				Robot.ladder.pos = 2;
    				Robot.ladder.stop();
    			} else {
    				Robot.ladder.extend();
    			}
    		break;
    	}
    }

    protected boolean isFinished() {
        return Robot.ladder.pos == pos;
    }

    protected void end() {
    	Robot.ladder.stop();
    }

    protected void interrupted() {
    	Robot.ladder.stop();
    }
}
