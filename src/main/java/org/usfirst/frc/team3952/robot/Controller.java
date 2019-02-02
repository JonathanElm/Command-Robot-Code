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

    public Joystick joystick;

    // MAP(manualExtendLadder, manualRetractLadder, ball drop, ball retract, grab, release)
    final static int[] SIDEWINDER_MAP = {3, 4, 1, 2, 7, 6, 7};

    // MAP(literally every level of the ladder)
    // used to be known as the BAD_CONTROLLER
    final static int[] LUCAS_CONTROLLER = {1, 2, 3, 4, 5};

    int[] currentController;

    public Controller(Joystick joystick, boolean isMain) {
        this.joystick = joystick;
        if(isMain)
            currentController = SIDEWINDER_MAP;
        else
            currentController = LUCAS_CONTROLLER;
    }

    public double getHorizontalMovement() {
        double x = joystick.getX();
        return Math.abs(x) >= deadzone ? k * Math.signum(x) * (Math.log(Math.abs(x) + 1 - deadzone) + c) : 0;
    }

    public double getLateralMovement() {
        double y = -joystick.getY();
        return Math.abs(y) >= deadzone ? k * Math.signum(y) * (Math.log(Math.abs(y) + 1 - deadzone) + c) : 0;
    }

    public double getRotation() {
        double t = joystick.getZ();
        return Math.abs(t) >= deadzoneT ? kT * Math.signum(t) * (Math.log(Math.abs(t) + 1 - deadzoneT) + cT) : 0;
    }

    public boolean extendLadder() {
        return joystick.getRawButton(currentController[0]);
    }

    public boolean retractLadder() {
        return joystick.getRawButton(currentController[1]);
    }

    public boolean shootBallHolder() {
        return joystick.getRawButton(currentController[2]);
    }

    public boolean retractBallHolder() {
        return joystick.getRawButton(currentController[3]);
    }

    public boolean grabDisc() {
        return joystick.getRawButton(currentController[4]);
    }

    public boolean releaseDisc() {
        return joystick.getRawButton(currentController[5]);
    }

    public boolean clawDeploy(){
        return joystick.getRawButton(currentController[6]);
    }
    //For the bad/Lucas controller
    public boolean goToLadder(int level)
    {
        return joystick.getRawButton(level);
    }

    
}