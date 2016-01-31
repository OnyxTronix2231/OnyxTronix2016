package org.usfirst.frc2231.OnyxTronix2016;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.omg.CORBA.VersionSpecHelper;
import org.usfirst.frc2231.OnyxTronix2016.commands.PrintToDebug;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.MeasurementType;
import com.ni.vision.NIVision.ParticleFilterCriteria;
import com.ni.vision.NIVision.ParticleFilterCriteria2;
import com.ni.vision.NIVision.ParticleFilterOptions;
import com.ni.vision.NIVision.ParticleReport;
import com.ni.vision.NIVision.RGBValue;
import com.ni.vision.NIVision.ROI;
import com.ni.vision.NIVision.ThresholdData;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.image.ImageBase;
import edu.wpi.first.wpilibj.image.MonoImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.image.RGBImage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Vision extends Thread{

	private double angleAperture; // The the aperture angle of the robot
	private double angleAboveRobot; // The angle between the y axis of the robot and the camera
	private int robotHeight; // The height of the robot
	private double targetsHeight; // The height of the target
	
	AxisCamera camera;
	// Images
	HSLImage inputImage;
	Image processImage;
	int counter = 0;
	// The analays report of the binary image
	ParticleAnalysisReport par;
	
	File file;
	double targetAngle = 0; // The angle between the plumb to the y axis of the target and the distance between the target and the camera
	int newCamerasHeight = 0; // The new height of the image after cutting the height of the robot from the image
	double hypotenuse = 0; // "Yeter"
	double distance = 0;// The distance between the y axis of the target and the robot
	
	public final int HUE_LOW = 0; // Low value of the "Hue" field in HSV
	public final int HUE_HIGH = 255; // High value of the "Hue" field in HSV
	public final int SATURATION_LOW = 105; // Low value of the "Saturation" field in HSV
	public final int SATURATION_HIGH = 255; // High value of the "Saturation" field in HSV
	public final int VALUE_LOW = 235; // Low value of the "Value" field in HSV
	public final int VALUE_HIGH = 255; // High value of the "Value" field in HSV
	public final double MIN_CONVEX_HULL_AREA = 2500; // The area(in pixels) of an object in the processing operation
	
	public Vision() {
		this.angleAperture = 0;
		this.angleAboveRobot = 0;
		this.robotHeight = 0;
		this.targetsHeight = 0;
		camera = RobotMap.shooterCamera;
	}
	
	public void activateDashboard() {

	}
	
	
	public void imageProcessing() {
//		while(true){
			try {
				inputImage = camera.getImage();			
				processImage = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
//			
				NIVision.imaqReadFile(inputImage.image, "/home/lvuser/vis.jpg");
			
				ParticleFilterCriteria2 [] criteria = new ParticleFilterCriteria2[1];
				criteria[0] = new ParticleFilterCriteria2(NIVision.MeasurementType.MT_CONVEX_HULL_AREA, 0, MIN_CONVEX_HULL_AREA, 0, 0);
				ParticleFilterOptions options = new ParticleFilterOptions(1, 0, 0);
				ROI roi = NIVision.imaqCreateROI();
			
				NIVision.Range hueRange = new NIVision.Range(HUE_LOW, HUE_HIGH);
				NIVision.Range saturationRange = new NIVision.Range(SATURATION_LOW, SATURATION_HIGH);
				NIVision.Range valueRange = new NIVision.Range(VALUE_LOW, VALUE_HIGH);
	        
				NIVision.imaqColorThreshold(processImage, inputImage.image, 255, NIVision.ColorMode.HSV, hueRange, saturationRange, valueRange);
//				NIVision.imaqParticleFilter3(processImage, processImage, criteria[0], 1, options, roi);
			
//	        	NIVision.imaqWriteFile(processImage, "/home/lvuser/processedImage.jpg", new RGBValue(255, 255, 255, 255));
//	       
//				Thread.sleep(500);
			} catch(Exception e) {
				e.printStackTrace();
			}
//		}	
	}	

	public double calculateDistance() throws NIVisionException {
		
		newCamerasHeight = (int) (par.imageHeight*(angleAperture/2+angleAboveRobot)/angleAperture); // Cutting what's below the plumb to the y axis of the target
		robotHeight = par.imageHeight-newCamerasHeight;
		targetsHeight = 2*par.boundingRectTop-par.boundingRectHeight-par.center_mass_y-robotHeight;
		targetAngle = targetsHeight*(angleAperture/2+angleAboveRobot)/newCamerasHeight; // targetHeight isn't final
		hypotenuse = (targetsHeight-robotHeight)/Math.sin(targetAngle);
		distance = Math.sqrt(Math.pow(hypotenuse, 2)-Math.pow(targetsHeight-robotHeight, 2));

		return this.distance;
	}

	public void appendText(String textToAppend) {
		
		byte[] content;
		try {
			file = new File("/home/lvuser/test.txt");
			if (!file.exists())
				file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			content = textToAppend.getBytes();
			fos.write(content);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
