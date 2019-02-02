package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;
import org.usfirst.frc.team3952.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3952.robot.subsystems.*;
public class MoveLadderToPos extends Command {
    public static final double TIMEOUT = 8.0;

    //TODO edit values for real positions (after calibration?)
    public static final int[] POSITIONS = new int[] {200, 300, 400, 500, 600, 700};

    //go to
    public double pos;
    //true: UP, false: DOWN
    public boolean dir;
    public int diff;

    private boolean finished = false;

    public MoveLadderToPos(int pos) {
        requires(Robot.ladder);
        setTimeout(TIMEOUT);
        setInterruptible(false);
        this.pos = POSITIONS[pos];
    }

    protected void initialize() {
        dir = (pos - Robot.ladder.encoder.getDistance()) > 0;
    }

    protected void execute() {
        if(dir)
            Robot.ladder.extend();
        else
            Robot.ladder.retract();
        //just in case it runs too fast for us
        dir = (pos - Robot.ladder.encoder.getDistance()) > 0;

       
    }

    protected boolean isFinished() {
        if(RobotMap.ladderTopLimit.get() || RobotMap.ladderBottomLimit.get())
            return true;
        if(Math.abs(pos - Robot.ladder.encoder.getDistance()) < 0.2)
        {
            ManualLadder.isMoving = false;
            return true;
        }
        return false;
    }

    protected void end() {
        Robot.ladder.stop();
    }

    protected void interrupted() {
        Robot.ladder.stop();
    }
}
