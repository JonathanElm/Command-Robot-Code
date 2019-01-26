package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;
import org.usfirst.frc.team3952.robot.RobotMap;
import org.usfirst.frc.team3952.robot.subsystems.BallHolder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class ManualBallHolder extends Command {
    
    public ManualBallHolder() {
        requires(Robot.ballholder);
        setInterruptible(true);
    }

    protected void initialize() {}

    double rotateBy = 0;

    protected void execute() {
        Robot.ballholder.shoot();
        Robot.ballholder.retract();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.drive.stop();
    }

    protected void interrupted() {
    	Robot.drive.stop();
    }
}
