package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ManualLadder extends Command {

    public static boolean isMoving = false;

    public ManualLadder() {
        requires(Robot.ladder);
        setInterruptible(true);
    }

    protected void initialize() {}

    protected void execute() {
    	if(Robot.mainController.extendLadder()) {
			Robot.ladder.extend();
		} else if(Robot.ladderController.retractLadder()) {
			Robot.ladder.retract();
		} else {
			Robot.ladder.stop();
        }
        
        //change 5 for levels, if you do change the Controller config too
        for(int i = 0; i < 5; i++)
        {
            if(Robot.mainController.goToLadder(i+1) && !isMoving)
            {
                Scheduler.getInstance().add(new MoveLadderToPos(i));
                break;
            }
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.ladder.stop();
    }
    
    protected void interrupted() {
    	Robot.ladder.stop();
    }
}
