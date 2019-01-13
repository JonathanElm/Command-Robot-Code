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
        if(cnt++ % 30 <= 5) {
            // System.out.println("AutoAlign: " + Robot.distanceToCenter()[0]);
            //TODO did i do it right???
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
        if(Math.abs(Robot.distanceToCenter()[0]) < 10)
        {
            Robot.drive.stop();
            return true;
        }
        return false;
    }
}