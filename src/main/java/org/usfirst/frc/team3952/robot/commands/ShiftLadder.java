package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;
import org.usfirst.frc.team3952.robot.subsystems.Ladder;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftLadder extends Command {
   public static final double TIMEOUT = 8.0;

   public boolean shift;
   public int pos;
   public int dir;
   public int initialPos;
   public int finalPos;
   public int diff;
   private boolean finished = false;

   public ShiftLadder (boolean shift) {
       requires(Robot.ladder);
       setTimeout(TIMEOUT);
       setInterruptible(false);
       this.shift = shift;
   }

   protected void initialize() {
       initialPos =  (int) Robot.ladder.encoder.getDistance();
       if(shift) {
           finalPos = initialPos + Ladder.FIXED_AMOUNT;
       } else {
           finalPos = initialPos - Ladder.FIXED_AMOUNT;
       }
       dir = finalPos - (int)Robot.ladder.encoder.getDistance();
   }

   protected void execute() {
       diff = finalPos - (int) Robot.ladder.encoder.getDistance();
       if(dir * diff <= 0) {
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

