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

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2231.OnyxTronix2016.Robot;
import org.usfirst.frc2231.OnyxTronix2016.RobotMap;

/**
 *
 */
public class TurnByDegrees extends Command {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	private double m_setpoint;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public TurnByDegrees(double setpoint) {
		
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		m_setpoint = setpoint;

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
//		m_setpoint = m_setpoint % Robot.driveTrain.ROBOT_PERIMETER;
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=INITIALIZE
		RobotMap.driveTrainLeftPIDController.enable();
        RobotMap.driveTrainLeftPIDController.setSetpoint(m_setpoint);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=INITIALIZE
        RobotMap.driveTrainRightPIDController.enable();
        RobotMap.driveTrainRightPIDController.setSetpoint(-m_setpoint);
        RobotMap.driveTrainRightEncoder.reset();
        RobotMap.driveTrainLeftEncoder.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		return Math.abs(Robot.driveTrain.getSetpoint() - Robot.driveTrain.getPosition()) <=  1;
		return RobotMap.driveTrainLeftPIDController.onTarget() && RobotMap.driveTrainRightPIDController.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.driveTrainLeftPIDController.disable();
		RobotMap.driveTrainRightPIDController.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		RobotMap.driveTrainLeftPIDController.disable();
		RobotMap.driveTrainRightPIDController.disable();
	}
}