package org.usfirst.frc2231.OnyxTronix2016;

import edu.wpi.first.wpilibj.SpeedController;

public interface OnyxSpeedController extends SpeedController {

	void pidWrite(double output, boolean isStraight);
	
	default void pidWriteStraight(double output) {
		pidWrite(output, true);
	}
	
	default void pidWriteTurn(double output) {
		pidWrite(output, false);
	}

	void setControllers(SpeedController[] scs);
}