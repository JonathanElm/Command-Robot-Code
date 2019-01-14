package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoAlign extends Command
{
    public static final int MIN_DISTANCE_FROM_TARGET = 10;

    private int cnt = 0;

    public AutoAlign()
    {
        requires(Robot.drive);
        setInterruptible(true);
    }

    protected void initialize() {}

    protected void execute()
    {
        //does stepping
        //if you want it to step farther, increase the 5 to something else
        //if you want it to step more often, decrease the 30 i guess
        if(cnt++ % 30 <= 5) {
            if(Robot.distanceToCenter()[0] > 0)
                Robot.drive.drive(0.7, 0, 0);
            else
                Robot.drive.drive(-0.7, 0, 0);
        } else {
            Robot.drive.stop();
        }
    }

    protected boolean isFinished()
    {
        //10 is stop distance
        if(Math.abs(Robot.distanceToCenter()[0]) < 10)
        {
            Robot.drive.stop();
            return true;
        }
        return false;
    }
}