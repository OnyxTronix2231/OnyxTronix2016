package org.usfirst.frc2231.OnyxTronix2016;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import FRC_Vision2016_newMethods_ft_team2231.newBinaryImage;
import FRC_Vision2016_newMethods_ft_team2231.newParticleAnalysisReport;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.MeasureParticlesReport;
import com.ni.vision.NIVision.ParticleFilterCriteria2;
import com.ni.vision.NIVision.ParticleFilterOptions;
import com.ni.vision.NIVision.RGBValue;




import com.ni.vision.NIVision.ROI;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Vision extends Thread{

	private final double VERTICLE_APERTURE_ANGLE = 36.12; // The the aperture angle of the robot
	private final double HORIZONTAL_APERTURE_ANGLE = 47;
	private final double ANGLE_TO_FLOOR = 13; // The angle between the y axis of the robot and the camera
	private final double ROBOT_HEIGHT =  0.77; // The height of the robot(m)
	private final double TARGET_HEIGHT = 2.32; // The height of the target(m)
	
	AxisCamera camera;
	// Images
	HSLImage inputImage;
	Image processImage;

	// The analays report of the binary image
	//newParticleAnalysisReport par;
	
	File file;
	double buttomAngle = 0;//The angle between the target to the end of the picture
	double targetAngle = 0; // The angle between the plumb to the y axis of the target and the distance between the target and the camera
	double hypotenuse = 0; // "YETER"
	double distance = 0;// The distance between the y axis of the target and the robot
	/*Ranges */
	public final int HUE_LOW = 80;
	public final int HUE_HIGH = 125;
	public final int SATURATION_LOW = 30;
	public final int SATURATION_HIGH = 255;
	public final int VALUE_LOW = 0;
	public final int VALUE_HIGH = 255;
	
	double newFrame = 0;
	
	public final double MIN_AREA = 1000; //The min area(in pixels) of an object in the processing operation
	public final double MAX_AREA = 6000; // The max area(in pixels) of an object in the processing operation
	
	public double particleArea;
	
	public Vision() {
		camera = RobotMap.shooterCamera;
	}
	
	public void activateDashboard() {

	}
	
	public void imageProcessing() {
			
		try {
			inputImage = camera.getImage();
			processImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0);
			NIVision.Range hueRange = new NIVision.Range(HUE_LOW, HUE_HIGH);
			NIVision.Range saturationRange = new NIVision.Range(SATURATION_LOW, SATURATION_HIGH);
			NIVision.Range valueRange = new NIVision.Range(VALUE_LOW, VALUE_HIGH);	
			
			NIVision.imaqColorThreshold(processImage, inputImage.image, 255, NIVision.ColorMode.HSV, hueRange, saturationRange, valueRange);
			NIVision.imaqWriteFile(processImage, "/home/lvuser/thresholdImage.jpg", new RGBValue(255, 255, 255, 255));
	
			NIVision.ParticleFilterCriteria2[] criteria = new ParticleFilterCriteria2[1];
			criteria[0] = new ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA, MIN_AREA, MAX_AREA, 0, 0);
			ParticleFilterOptions options = new ParticleFilterOptions(0, 0, 1);
			ROI roi = NIVision.imaqCreateROI();
			NIVision.imaqParticleFilter3(processImage, processImage, criteria[0], 1, options, roi);
			
			NIVision.imaqWriteFile(inputImage.image, "/home/lvuser/inputImage.jpg", new RGBValue(255, 255, 255, 255));
			NIVision.imaqWriteFile(processImage, "/home/lvuser/filterImage.jpg", new RGBValue(255, 255, 255, 255));
			
			newBinaryImage binaryImage = new newBinaryImage();
			binaryImage.image = processImage;
			System.out.println(binaryImage.getNumberParticles());
			if(binaryImage.getNumberParticles() != 0){
				newParticleAnalysisReport par = binaryImage.getOrderedParticleAnalysisReports(1)[0];
				System.out.println(calculateDistance(par));
			}
			
			Thread.sleep(1000);
		
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}	

	public double calculateDistance(newParticleAnalysisReport par) throws NIVisionException {
		//Rigler's Version
		buttomAngle = VERTICLE_APERTURE_ANGLE*(par.imageHeight-par.center_mass_y)/par.imageHeight;
		targetAngle = buttomAngle - VERTICLE_APERTURE_ANGLE/2 + ANGLE_TO_FLOOR;
		targetAngle *= Math.PI/180;//changing of the angle to radians
		distance = (TARGET_HEIGHT-ROBOT_HEIGHT)/Math.tan(targetAngle);
		
		//Ziv's Version
//		newFrame = (VERTICLE_APERTURE_ANGLE/2+ANGLE_TO_FLOOR)/VERTICLE_APERTURE_ANGLE*par.imageHeight;
//		targetAngle = (VERTICLE_APERTURE_ANGLE/2+ANGLE_TO_FLOOR)*(newFrame-par.center_mass_y)/newFrame;
//		targetAngle *= Math.PI/180;
//		distance = (TARGET_HEIGHT-ROBOT_HEIGHT)/Math.tan(targetAngle);
		
		return distance;
	}
	
	public double calculateAreaRatio(newParticleAnalysisReport par) throws NIVisionException{
		
		return par.particleArea/(par.boundingRectHeight*par.boundingRectWidth);		
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
