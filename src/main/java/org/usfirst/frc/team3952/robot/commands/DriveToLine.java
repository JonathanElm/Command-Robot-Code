package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;
import org.usfirst.frc.team3952.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Command;

public class DriveToLine extends Command
{
    static final int LIGHT_THRESHOLD = 100;
    AnalogInput sensor = RobotMap.qtiSensor;

    public DriveToLine()
    {
        requires(Robot.drive);
        setTimeout(10);
        setInterruptible(false);
    }

    protected void execute()
    {
        //Change 0.5 for faster drive (but not too fast to skip line)
        Robot.drive.drive(0, 0.5, 0);
    }

    protected boolean isFinished()
    {
        return sensor.getValue() < LIGHT_THRESHOLD;
    }
}