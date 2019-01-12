package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoAlign extends Command
{
    public static final int MIN_DISTANCE_FROM_TARGET = 10;

    public AutoAlign()
    {
        setTimeout(5);
        setInterruptible(true);
    }

    protected void initialize() {}

    protected void execute()
    {
        //TODO did i do it right???
        if(Robot.distanceToCenter()[0] > 0)
            Robot.drive.drive(0.5, 0, 0);
        else
            Robot.drive.drive(-0.5, 0, 0);
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