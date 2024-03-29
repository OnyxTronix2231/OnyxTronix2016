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

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2231.OnyxTronix2016.Robot;
import org.usfirst.frc2231.OnyxTronix2016.RobotMap;
import org.usfirst.frc2231.OnyxTronix2016.StaticMembers;

/**
 *
 */
public class ShootAtSpeed extends Command {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	private final double SENSITIVITY_VALUE = 0.8;
	private double m_speed;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public ShootAtSpeed(double speed) {

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		m_speed = speed;
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		m_speed = Robot.shooter.speed;
		RobotMap.shooterLeftPIDController.enable();
        RobotMap.shooterRightPIDController.enable();
        RobotMap.shooterRightPIDController.setSetpoint(-m_speed);
        RobotMap.shooterLeftPIDController.setSetpoint(-m_speed);
        RobotMap.shooterShooterLeftEncoder.reset();
        RobotMap.shooterShooterRightEncoder.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(RobotMap.shooterLeftPIDController.onTarget()){
			Robot.shooter.isReady = true;
		}
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.shooter.isReady = false;
		RobotMap.shooterLeftPIDController.disable();
		RobotMap.shooterRightPIDController.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		RobotMap.shooterLeftPIDController.disable();
		RobotMap.shooterRightPIDController.disable();
	}
}
