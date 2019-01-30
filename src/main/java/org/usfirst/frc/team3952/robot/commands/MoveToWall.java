package org.usfirst.frc.team3952.robot.commands;

import org.usfirst.frc.team3952.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3952.robot.RobotMap;



public class MoveToWall extends Command{

    //change constant according to tests, default 3.5
    public static final double constant = 3.5;
    public double distanceToWall;
    public double distanceToStop;
    public boolean finished;
    double [] sonicData;
    

    public double getDistance(){
         
        distanceToWall = 0;
        // the longer the loop the more accurate? i dont really know
        // lucas told me to find le average between 10? Thats why this is here
        for (int i = 0; i<10; i++){
            sonicData [i] = RobotMap.ultrasonicSensor.getVoltage();
        }

        for (int i = 0; i<sonicData.length; i++) {
            distanceToWall += sonicData[i];
        }

       return distanceToWall/10;

    }
	
	public MoveToWall() {
        requires(Robot.drive);
		setInterruptible(false);
	}

    protected void initialize() {
    	finished = false;
    }
    
    protected void execute() {

    	if (getDistance() > distanceToStop ) {
            //change 0.5 for speed
            Robot.drive.drive(0,0.5,0);
        } else {
            finished = true;
        }
    		
    }

    
    
    protected boolean isFinished() {
        return finished;
    }
    
    protected void end() {
    	Robot.drive.stop();
    }
    
    protected void interrupted() {
    	Robot.drive.stop();
    }
    
}