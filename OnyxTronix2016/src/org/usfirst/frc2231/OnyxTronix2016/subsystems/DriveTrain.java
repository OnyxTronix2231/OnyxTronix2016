// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc2231.OnyxTronix2016.subsystems;

import javax.activation.CommandMap;

import org.usfirst.frc2231.OnyxTronix2016.OnyxSpeedController;
import org.usfirst.frc2231.OnyxTronix2016.Robot;
import org.usfirst.frc2231.OnyxTronix2016.RobotMap;
import org.usfirst.frc2231.OnyxTronix2016.commands.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Encoder.IndexingType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.OnyxPIDSubsystem;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final Solenoid closeShifter = RobotMap.driveTrainCloseShifter;
    private final Solenoid openShifter = RobotMap.driveTrainOpenShifter;
    private final SpeedController leftCopy = RobotMap.driveTrainLeftCopy;
    private final Encoder leftEncoder = RobotMap.driveTrainLeftEncoder;
    private final PIDController leftPIDController = RobotMap.driveTrainLeftPIDController;
    private final Encoder rightEncoder = RobotMap.driveTrainRightEncoder;
    private final SpeedController rightCopy = RobotMap.driveTrainRightCopy;
    private final PIDController rightPIDController = RobotMap.driveTrainRightPIDController;
    private final SpeedController right = RobotMap.driveTrainRight;
    private final SpeedController left = RobotMap.driveTrainLeft;
    private final RobotDrive robotDrive21 = RobotMap.driveTrainRobotDrive21;
    private final PowerDistributionPanel pdp = RobotMap.driveTrainPdp;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final double ROBOT_RADIUS = 35;
	private final double WHEEL_RADIUS = 3;
	private final double INCH = 2.54;
	public final double WHEEL_PERIMETER = WHEEL_RADIUS * INCH * 2 * Math.PI;
	public final double ROBOT_PERIMETER = ROBOT_RADIUS * INCH * 2 * Math.PI;
	private final int DEFAULT_POV_VALUE = -1;
	private final int PULSE_SCALE = 360;
	// Initialize your subsystem here
	public DriveTrain() {
        RobotMap.driveTrainLeftEncoder.setDistancePerPulse(WHEEL_PERIMETER / PULSE_SCALE);
        RobotMap.driveTrainRightEncoder.setDistancePerPulse(WHEEL_PERIMETER / PULSE_SCALE);
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
	}
	
		
		
		
	

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveWithJoystick());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;

		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
        return leftEncoder.pidGet();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SOURCE
	}

	public void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		//System.out.println(Thread.currentThread().getStackTrace());
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
        left.pidWrite(output);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=OUTPUT
	}


	
	
	public void openShifters(boolean isOpen){
		openShifter.set(isOpen);
		closeShifter.set(!isOpen);
	}
	public int directionValue = 1;

	public void arcadeDrive(Joystick stick) {
		robotDrive21.arcadeDrive(
				directionValue * stick.getRawAxis(1),
				directionValue * stick.getRawAxis(4));

	}


	public void driveByDirection(double degrees) {

		if (degrees != DEFAULT_POV_VALUE) {
			double move = getMove(degrees);
			double rotate = getRotate(degrees);
			robotDrive21.arcadeDrive(rotate, move);
		}
	}

	public void turnByDegree(double degrees) {
		double divide = 360 / degrees;
		double turnPerimeter = (2 * Math.PI * ROBOT_RADIUS) / divide;
	}

	public double getRotate(double degrees) {
		int shift;
		double shiftedDegree;

		boolean signRotate = Math.floor(degrees / 180) % 2 == 0;
		shift = ((int) degrees) / 180;
		shiftedDegree = degrees - 180 * shift;
		if (signRotate) {
			return 1 - shiftedDegree / 90.0;
		} else {
			return -(1 - shiftedDegree / 90.0);
		}
	}

	public double getMove(double degrees) {
		return getRotate(degrees + 270.0);
	}

	public boolean isClockwiseRotationEfficient(double setpoint) {
		if (setpoint - RobotMap.driveTrainLeftEncoder.getDistance() > WHEEL_PERIMETER / 2) {
			return true;
		}
		return false;
	}

	private boolean isToleratable(boolean isLeft) {
		if(isLeft) {
//			leftPIDController.getSetpoint() - leftPIDController.
		} else {
			
		}
		return true;
	}
	
	public void driveByStraightPOVValue(double value) {
		int POV = Robot.oi.getdriveStick().getPOV();
		double moveValue = POV == -1 ? 0 : POV / 90 -1;  
		robotDrive21.arcadeDrive(moveValue , 0);
	}
}
