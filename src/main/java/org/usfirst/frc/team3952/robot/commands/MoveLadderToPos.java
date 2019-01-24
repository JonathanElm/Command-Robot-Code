package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3952.robot.subsystems.*;
public class MoveLadderToPos extends Command {
    public static final double TIMEOUT = 8.0;

    //TODO edit values for real positions (after calibration?)
    public static final int[] POSITIONS = new int[] {200, 300, 400, 500, 600, 700};

    public int pos;
    public int dir;
    public int diff;

    private boolean finished = false;

    public MoveLadderToPos(int pos) {
        requires(Robot.ladder);
        setTimeout(TIMEOUT);
        setInterruptible(false);
        this.pos = Ladder.SWITCH_ENC[pos];
    }

    protected void initialize() {
        dir = pos - (int)Robot.ladder.encoder.getDistance();
    }

    protected void execute() {
        diff = (int)Robot.ladder.encoder.getDistance() - pos;
        if(dir * diff >= 0) {
            Robot.ladder.stop();
            Robot.ladder.pos = pos;
            finished = true;
        } else if(diff > 0) {
            Robot.ladder.extend();
        } else {
            Robot.ladder.retract();
        }
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
        Robot.ladder.stop();
    }

    protected void interrupted() {
        Robot.ladder.stop();
    }
}
