package org.usfirst.frc2231.OnyxTronix2016.commands;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Range;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CameraCommand extends Command {
	int session;
	Image raw;
	NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);

	protected void initialize() {
		raw = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		session = NIVision.IMAQdxOpenCamera("cam0",
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session);
		NIVision.IMAQdxStartAcquisition(session);
	}

	protected void execute() {
		NIVision.IMAQdxGrab(session, raw, 1);
		NIVision.imaqColorThreshold(raw, raw, 1, NIVision.ColorMode.HSI,
				new Range(0, 255), new Range(0, 255), new Range(200, 255));
		NIVision.imaqDrawShapeOnImage(raw, raw, rect, DrawMode.DRAW_VALUE,
				ShapeMode.SHAPE_OVAL, 0.0f);
		CameraServer.getInstance().setImage(raw);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		NIVision.IMAQdxStopAcquisition(session);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}