// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2231.OnyxTronix2016.commands;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2231.OnyxTronix2016.Robot;
import org.usfirst.frc2231.OnyxTronix2016.RobotMap;
import org.usfirst.frc2231.OnyxTronix2016.subsystems.Shooter;

/**
 *
 */
public class CenterByVision extends Command {

	private static final int EXTENDED_TOLERANCE_RANGE = 60;
	private static final double MINIMUM_SPEED = 0.1;
	private static final double TOLERANCE = 3;
	public double currentAreaRatio;
	public double lastAreaRatio;
	final double ratioSensetivity = 0.8;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	double m_setpoint;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public CenterByVision(double setpoint) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
    	m_setpoint = Robot.setPoint;
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(!Robot.vision.isProcessing()){
    		Robot.vision.startProcessing();
        	Thread t = new Thread(Robot.vision.new VisionPID());
        	t.start();
    	}
    	RobotMap.VisionLeftPIDController.enable();
    	RobotMap.VisionRightPIDController.enable();
    	Robot.vision.setPIDSourceType(PIDSourceType.kRate);
    	Robot.vision.setPIDSourceType(PIDSourceType.kRate);
    	RobotMap.VisionLeftPIDController.setSetpoint(m_setpoint);
    	RobotMap.VisionRightPIDController.setSetpoint(m_setpoint);
    	Robot.shooter.setReady(false);
    	Robot.vision.refreshValues();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		System.out.println("Error: " + RobotMap.VisionRightPIDController.getError());

      /*
    	double startingSpeed = SmartDashboard.getNumber("PID OutputRange: ");
        double  currentSpeed = startingSpeed;
    	double error = Math.abs(RobotMap.VisionRightPIDController.getError());
    	double slope = (startingSpeed - MINIMUM_SPEED) /  EXTENDED_TOLERANCE_RANGE;
    	double currentOutputRange = 0;
    	m_setpoint = Robot.setPoint;
    	if(error > TOLERANCE + EXTENDED_TOLERANCE_RANGE){
    		currentOutputRange = startingSpeed;
    		Robot.vision.timeStart = System.currentTimeMillis();
    	}else if(error > TOLERANCE){
    		currentOutputRange = slope * error + MINIMUM_SPEED;
    		System.out.println("error: " + error + " Wide Tolerance: " + (TOLERANCE + EXTENDED_TOLERANCE_RANGE)  + "if: " + (error < TOLERANCE + EXTENDED_TOLERANCE_RANGE) + " speed: "+ currentOutputRange);
        	Robot.vision.timeStart = System.currentTimeMillis();
    	}
    	RobotMap.VisionLeftPIDController.setOutputRange(-currentOutputRange, currentOutputRange);
    	RobotMap.VisionRightPIDController.setOutputRange(-currentOutputRange, currentOutputRange);
    	System.out.println("current output range: " + currentOutputRange);
    */
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	/*
    	System.out.println("Left Vision Controller: current value: " + Robot.vision.getDistanceFromCenter() + ", difference: " + RobotMap.VisionLeftPIDController.getError() + ", is on target: " + RobotMap.VisionLeftPIDController.onTarget());
    	System.out.println("Right Vision Controller: current value: " + Robot.vision.getDistanceFromCenter() + ", difference: " + RobotMap.VisionRightPIDController.getError() + ", is on target: " + RobotMap.VisionRightPIDController.onTarget());
    	long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - Robot.vision.timeStart;
		System.out.println("time delta: " + tDelta / 1000.0);
//    	return RobotMap.VisionLeftPIDController.onTarget() && RobotMap.VisionRightPIDController.onTarget();
    	if(tDelta /1000.0 >= 0.8 && Math.abs(RobotMap.VisionRightPIDController.getError()) < TOLERANCE){
    		return true;
    	}
    	*/
    	if(Math.abs(RobotMap.VisionRightPIDController.getError()) < TOLERANCE){
    		System.out.println("On target: " + RobotMap.VisionRightPIDController.getError());
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.vision.stopProcessing();
		RobotMap.VisionLeftPIDController.disable();
		RobotMap.VisionRightPIDController.disable();
		Robot.shooter.setReady(true);
		System.out.println("centered by vision");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.vision.stopProcessing();
		RobotMap.VisionLeftPIDController.disable();
		RobotMap.VisionRightPIDController.disable();
    }
}
