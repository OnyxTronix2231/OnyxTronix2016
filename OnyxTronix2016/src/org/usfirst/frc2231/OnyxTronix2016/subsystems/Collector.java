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

import org.usfirst.frc2231.OnyxTronix2016.RobotMap;
import org.usfirst.frc2231.OnyxTronix2016.commands.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Collector extends Subsystem {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController collectorSpinner = RobotMap.collectorCollectorSpinner;
    private final Solenoid leftOpenSolenoid = RobotMap.collectorleftOpenSolenoid;
    private final Solenoid rightOpenSolenoid = RobotMap.collectorrightOpenSolenoid;
    private final Solenoid rightCloseSolenoid = RobotMap.collectorrightCloseSolenoid;
    private final Solenoid leftCloseSolenoid = RobotMap.collectorleftCloseSolenoid;
    private final Compressor compressor = RobotMap.collectorCompressor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public boolean isCollectorOpen = leftOpenSolenoid.get();
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void openCollector(boolean isOpening) {
		leftCloseSolenoid.set(isOpening);
		leftOpenSolenoid.set(!isOpening);
		
		rightOpenSolenoid.set(!isOpening);
		rightCloseSolenoid.set(isOpening);
	}

	public void openCollector() {
		isCollectorOpen = true;
		openCollector(true);
	}

	public void closeCollector() {
		isCollectorOpen = false;
		openCollector(false);
	}

	public void stopCompressor() {
		compressor.stop();
	}

	public void startCompressor() {
		compressor.start();
	}

	public void roll(double speed) {
		collectorSpinner.set(speed);
	}
}
