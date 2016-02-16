/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package FRC_Vision2016_newMethods_ft_team2231;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.EllipseDescriptor;
import com.ni.vision.NIVision.CurveOptions;
import com.ni.vision.NIVision.ShapeDetectionOptions;
import com.ni.vision.NIVision.ROI;
import com.ni.vision.NIVision.DetectEllipsesResult;

import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 * A grey scale image represented at a byte per pixel.
 *$
 * @author dtjones
 */
public class newMonoImage extends newImageBase {

  /**
   * Create a new 0x0 image.
   */
  public newMonoImage() throws NIVisionException {
    super(NIVision.ImageType.IMAGE_U8);
  }

  public newMonoImage(newMonoImage sourceImage) {
    super(sourceImage);
  }

  public DetectEllipsesResult detectEllipses(EllipseDescriptor ellipseDescriptor,
      CurveOptions curveOptions, ShapeDetectionOptions shapeDetectionOptions, ROI roi)
      throws NIVisionException {
    return NIVision.imaqDetectEllipses(image, ellipseDescriptor, curveOptions,
        shapeDetectionOptions, roi);
  }

  public DetectEllipsesResult detectEllipses(EllipseDescriptor ellipseDescriptor)
      throws NIVisionException {
    return NIVision.imaqDetectEllipses(image, ellipseDescriptor, null, null, null);
  }
}
