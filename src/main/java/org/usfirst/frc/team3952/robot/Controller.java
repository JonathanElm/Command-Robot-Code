package org.usfirst.frc.team3952.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller {
    public double c = 0.1;
    public double deadzone = 0.2;
    public double max = 0.8;
    public double k = (max - c) / Math.log(2 - deadzone);

    public double cT = 0.08;
    public double deadzoneT = 0.08;
    public double maxT = 0.4;
    public double kT = (maxT - cT) / Math.log(2 - deadzoneT);

    public Joystick mainJoystick;

    public Joystick subJoystick;

    // MAP(manualExtendLadder, manualRetractLadder, ball drop, ball retract, grab, release)
    final static int[] SIDEWINDER_MAP = {3, 4, 1, 2, 7, 6};

    // MAP(literally every level of the ladder)
    // used to be known as the BAD_CONTROLLER
    final static int[] LUCAS_CONTROLLER = {1, 2, 3, 4, 5, 6};

    int[] mainController;

    int[] subController;

    public Controller(boolean sWinderIsMain) {
       
        if(sWinderIsMain) {
            mainController = SIDEWINDER_MAP;
            subController = LUCAS_CONTROLLER;
            this.mainJoystick = new Joystick(0);
            this.subJoystick = new Joystick(1);
        }else {
            subController = SIDEWINDER_MAP;
            mainController = LUCAS_CONTROLLER;
            this.subJoystick = new Joystick(0);
            this.mainJoystick = new Joystick(1);
             }
    }



    public double getHorizontalMovement() {
        double x = mainJoystick.getX();
        return Math.abs(x) >= deadzone ? k * Math.signum(x) * (Math.log(Math.abs(x) + 1 - deadzone) + c) : 0;
    }

    public double getLateralMovement() {
        double y = -mainJoystick.getY();
        return Math.abs(y) >= deadzone ? k * Math.signum(y) * (Math.log(Math.abs(y) + 1 - deadzone) + c) : 0;
    }

    public double getRotation() {
        double t = mainJoystick.getZ();
        return Math.abs(t) >= deadzoneT ? kT * Math.signum(t) * (Math.log(Math.abs(t) + 1 - deadzoneT) + cT) : 0;
    }

    public boolean extendLadder() {
        return subJoystick.getRawButton(subController[0]);
    }

    public boolean retractLadder() {
        return subJoystick.getRawButton(subController[1]);
    }

    public boolean shootBallHolder() {
        return subJoystick.getRawButton(subController[2]);
    }

    public boolean retractBallHolder() {
        return subJoystick.getRawButton(subController[3]);
    }

    public boolean grabDisc() {
        return subJoystick.getRawButton(subController[4]);
    }

    public boolean releaseDisc() {
        return subJoystick.getRawButton(subController[5]);
    }

    //For the bad/Lucas controller
    public boolean goToLadder(int level)
    {
        return subJoystick.getRawButton(level);
    }

    
}
