/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.command;

import edu.wpi.first.wpilibj.OnyxPIDOutput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * This class is designed to handle the case where there is a {@link Subsystem}
 * which uses a single {@link PIDController} almost constantly (for instance, an
 * elevator which attempts to stay at a constant height).
 *
 * <p>
 * It provides some convenience methods to run an internal {@link PIDController}
 * . It also allows access to the internal {@link PIDController} in order to
 * give total control to the programmer.
 * </p>
 *
 * @author Joe Grinstead
 */
public abstract class OnyxPIDSubsystem extends PIDSubsystem implements Sendable {

	public OnyxPIDSubsystem(String name, double p, double i, double d) {
		super(name, p, i, d);
	}
	/** The internal {@link PIDController} */
	public PIDController controller;
	/** An output which calls {@link PIDCommand#usePIDOutput(double)} */

	public OnyxPIDOutput output = new OnyxPIDOutput() {

		@Override
		public void pidWrite(double output, boolean isStraight) {

			usePIDOutput(output, isStraight);
		}

		@Override
		public void pidWrite(double output) {
			System.out.println(Thread.currentThread().getStackTrace());
		}
	};
	/** A source which calls {@link PIDCommand#returnPIDInput()} */
	public PIDSource source = new PIDSource() {
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		public double pidGet() {
			return returnPIDInput();
		}
	};
	protected abstract void usePIDOutput(double output2, boolean isStraight);
		// TODO Auto-generated method stub
		
	
}
