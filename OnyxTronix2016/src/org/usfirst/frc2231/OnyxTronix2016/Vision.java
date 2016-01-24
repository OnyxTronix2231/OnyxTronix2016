package org.usfirst.frc2231.OnyxTronix2016;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.MeasurementType;
import com.ni.vision.NIVision.RGBValue;
import com.ni.vision.NIVision.RawData;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Vision {
	
	private double angleAperture;  // The the aperture angle of the robot
	private double angleAboveRobot; // The angle between the y axis of the robot and the camera
	private double robotsHeight; // The height of the robot
	private double targetsHeight; // The height of the target 
	private double distance; // 	The distance between the y axis of the target and the robot
	
	public Vision(){
		this.angleAperture = 0;
		this.angleAboveRobot = 0;
		this.robotsHeight = 0;
		this.targetsHeight = 0;
		this.distance = 0;
	}
	
	public Vision(double angleAperture, double angleAboveRobot, double robotsHeight, double targetsHeight){
		this.angleAperture = angleAperture;
		this.angleAboveRobot = angleAboveRobot;
		this.robotsHeight = robotsHeight;
		this.targetsHeight = targetsHeight;
		this.distance = 0;
		System.out.println(this.distance);
	}
	
	File file;
	FileWriter fileWriter;
	BufferedWriter bufferedWriter;
	
	double distanceFromTarget = 0; // The distance between the robot and the target
	double targetAngle = 0; // The angle between the plumb to the y axis of the target and the distance between the target and the camera 
	double newCamerasHeight = 0; // The new height of the image after cutting the height of the robot from the image
	double hypotenuse = 0; // "Yeter"

	NIVision.Range TOTE_HUE_RANGE = new NIVision.Range(100, 150);	//Default hue range for yellow tote
	NIVision.Range TOTE_SAT_RANGE = new NIVision.Range(0, 255);	//Default saturation range for yellow tote
	NIVision.Range TOTE_VAL_RANGE = new NIVision.Range(120, 255);	//Default value range for yellow tote

	AxisCamera camera;
	//Images
	BinaryImage binaryImage;
	Image processImage;

	
	public void activateDashboard() {
				
//		NIVision.imaqWriteBMPFile(processImage, "/home/lvuser/test.jpg", 50, new RGBValue(160, 160, 160, 0));
		
		//Put default values to SmartDashboard so fields will appear
//		TOTE_HUE_RANGE.minValue = (int)SmartDashboard.getNumber("Tote hue min");
//		TOTE_HUE_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote hue max");
//		TOTE_SAT_RANGE.minValue = (int)SmartDashboard.getNumber("Tote sat min");
//		TOTE_SAT_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote sat max");
//		TOTE_VAL_RANGE.minValue = (int)SmartDashboard.getNumber("Tote val min");
//		TOTE_VAL_RANGE.maxValue = (int )SmartDashboard.getNumber("Tote val max");
//		SmartDashboard.putNumber("Tote hue min", TOTE_HUE_RANGE.minValue);
//		SmartDashboard.putNumber("Tote hue max", TOTE_HUE_RANGE.maxValue);
//		SmartDashboard.putNumber("Tote sat min", TOTE_SAT_RANGE.minValue);
//		SmartDashboard.putNumber("Tote sat max", TOTE_SAT_RANGE.maxValue);
//		SmartDashboard.putNumber("Tote val min", TOTE_VAL_RANGE.minValue);
//		SmartDashboard.putNumber("Tote val max", TOTE_VAL_RANGE.maxValue);


		SmartDashboard.putNumber("Distance", distance);
		System.out.println(distance);
		
		//TOTE_HUE_RANGE.maxValue -= 10;
	}
	
	public void ImageProcessing() throws NIVisionException{
		System.out.println(this.distance);
		camera = RobotMap.shooterCamera;
		processImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_HSL, 0);
		//Threshold the image looking for yellow (tote color)
		processImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_HSL, 0);
		NIVision.imaqThreshold(binaryImage.image, processImage, (float)TOTE_SAT_RANGE.minValue, (float)TOTE_SAT_RANGE.maxValue, 160, (float)160);
		NIVision.ParticleFilterCriteria2 ParticleFilterCriteria = new NIVision.ParticleFilterCriteria2(MeasurementType.MT_AREA,0, 500,0,0);
		NIVision.ParticleFilterCriteria2[] PFCArray = new NIVision.ParticleFilterCriteria2[1];
		PFCArray[0] = ParticleFilterCriteria;
		binaryImage.particleFilter(PFCArray);
		binaryImage.removeSmallObjects(false, 5);
		binaryImage.getOrderedParticleAnalysisReports(1);
//		CameraServer.getInstance().setImage(binaryImage.image);
	}
	
	public void calculateDistance() throws NIVisionException{
//		newCamerasHeight = binaryImage.getHeight()*(angleAperture/2+angleAboveRobot)/angleAperture;	// Cutting what's below the plumb to the y axis of the target
//		targetAngle = targetsHeight*(angleAperture/2+angleAboveRobot)/newCamerasHeight;
//		hypotenuse = (targetsHeight-robotsHeight)/Math.sin(targetAngle);
//		this.distance = Math.sqrt(Math.pow(hypotenuse, 2) - Math.pow(targetsHeight-robotsHeight, 2));
		this.distance = 400;
//		CameraServer.getInstance().setImage(binaryImage.image);
	}
		
	public double getDistance() {
		return distance;
	}
	
	public void appendText(String textToAppend)
	{	
		byte[] content;
		try{
			file = new File("/home/lvuser/test.txt");
			if(!file.exists())
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
