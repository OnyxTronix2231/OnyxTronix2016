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

import FRC_Vision2016_newMethods_ft_team2231.newBinaryImage;
import FRC_Vision2016_newMethods_ft_team2231.newParticleAnalysisReport;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ParticleFilterCriteria2;
import com.ni.vision.NIVision.ParticleFilterOptions;
import com.ni.vision.NIVision.RGBValue;
import com.ni.vision.NIVision.ROI;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.HSLImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 *
 */
public class Vision extends Subsystem {
	
	AxisCamera camera;
    protected boolean isProcessing = true;
    private ADXL362 accelerometer = RobotMap.visionAccelerometer;
    protected PIDSourceType pidSourceType;
    /*Images*/
    HSLImage inputImage;
    Image processImage;
    // Particle Report of the binary image
    newParticleAnalysisReport particleReport = null;
    
    protected double distance = 0; // The distance between the robot and the target   
    private double buttomAngle = 0;//The angle between the target to the end of the picture
    private double targetAngle = 0; // The angle between the plumb to the y axis of the target and the distance between the target and the camera

    private final double VERTICLE_APERTURE_ANGLE = 36.12; // The the aperture angle of the robot (HORIZONTAL_APERTURE_ANGLE = 47)
    private final double ROBOT_HEIGHT =  0.92; // The height of the robot(m)
    private final double TARGET_HEIGHT = 2.34; // The height of the target(m)
    /*Ranges of the HSV threshold operation*/
    private final int HUE_LOW = 80;
    private final int HUE_HIGH = 125;
    private final int SATURATION_LOW = 50;
    private final int SATURATION_HIGH = 255;
    private final int VALUE_LOW = 0;
    private final int VALUE_HIGH = 255;   	
    	
    /*Limit values of the particle filter operation*/
    private final double MIN_AREA = 1000; //The min area(in pixels) of an object in the processing operation
    private final double MAX_AREA = 6000; // The max area(in pixels) of an object in the processing operation
    	
    /*In processing values*/
    double maxArea = 0;
    double newFrame = 0;
    newParticleAnalysisReport[] particleArray;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new ProcessImage());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean isProcessing() {
		return isProcessing;
	}

	public void startProcessing() {
		this.isProcessing = true;
	}	
	
	public void stopProcessing(){
		this.isProcessing = false;
	}

	public newParticleAnalysisReport imageProcessing() {
					
		try {
			inputImage = camera.getImage();
			processImage = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_U8, 0);
				
			/* Ranges for the HSV variables in the Threshold operation*/
			NIVision.Range hueRange = new NIVision.Range(HUE_LOW, HUE_HIGH);
			NIVision.Range saturationRange = new NIVision.Range(SATURATION_LOW, SATURATION_HIGH);
			NIVision.Range valueRange = new NIVision.Range(VALUE_LOW,VALUE_HIGH);
			
			/*The Threshold operation - converting simple image into a binary image of black and white*/
			try{
				NIVision.imaqColorThreshold(processImage, inputImage.image, 255, NIVision.ColorMode.HSV, hueRange, saturationRange,valueRange);
				NIVision.imaqWriteFile(processImage, "/home/lvuser/thresholdImage.jpg", new RGBValue(255, 255, 255, 255));
			}catch(Exception e){
				e.printStackTrace();
			}		
				
			/*First filter operation of the Threshold image - "Particle Filter"*/	
			NIVision.ParticleFilterCriteria2[] criteria = new ParticleFilterCriteria2[1];
			criteria[0] = new ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA, MIN_AREA, MAX_AREA, 0, 0);
			ParticleFilterOptions options = new ParticleFilterOptions(0, 0, 1);
			ROI roi = NIVision.imaqCreateROI();
			NIVision.imaqParticleFilter3(processImage, processImage, criteria[0], 1, options, roi);
			
			NIVision.imaqWriteFile(inputImage.image,"/home/lvuser/inputImage.jpg", new RGBValue(255, 255, 255, 255));
			NIVision.imaqWriteFile(processImage,"/home/lvuser/filterImage.jpg", new RGBValue(255, 255, 255, 255));
							
			newBinaryImage binaryImage = new newBinaryImage();
			binaryImage.image = processImage;
				
			/*Second filter operation of the Threshold image - "Remove Small Object"(manual)*/
			/*Sending a Particle Report of the largest object remaining*/
				
			System.out.println(binaryImage.getNumberParticles());
			if (binaryImage.getNumberParticles() > 0) {
				particleArray = binaryImage.getOrderedParticleAnalysisReports(binaryImage.getNumberParticles());				
				maxArea = particleArray[0].particleArea;
				particleReport = particleArray[0];
				for(newParticleAnalysisReport par : particleArray){
					if(maxArea < par.particleArea){
						maxArea = par.particleArea;
						particleReport = par;
					}
				}						
			}
				
			Thread.sleep(1000);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return particleReport;	
	}

	public double calculateDistance(newParticleAnalysisReport par) throws NIVisionException {
			
		/*Above the middle of the picture*/
		System.out.println("y of center of mass : "+par.center_mass_y);
		System.out.println("image height/2 : "+par.imageHeight/2);
		if(par.center_mass_y <= par.imageHeight/2) {
			System.out.println("above");
			
			buttomAngle = VERTICLE_APERTURE_ANGLE * (par.imageHeight - par.center_mass_y) / par.imageHeight;
			targetAngle = buttomAngle - VERTICLE_APERTURE_ANGLE/2 + calculateAngleToFloor();
		}
			
		/*Below the middle of the picture*/
		else {
			System.out.println("below");
			System.out.println(VERTICLE_APERTURE_ANGLE/2 * (par.imageHeight-par.center_mass_y) / (par.imageHeight/2));
			targetAngle = calculateAngleToFloor() - VERTICLE_APERTURE_ANGLE/2 + VERTICLE_APERTURE_ANGLE/2 * (par.imageHeight-par.center_mass_y) / (par.imageHeight/2);
		}
			
	 	targetAngle *= Math.PI / 180;// changing of the angle into radians
		return (TARGET_HEIGHT - ROBOT_HEIGHT) / Math.tan(targetAngle);		
	}

	public double calculateAreaRatio(newParticleAnalysisReport par) throws NIVisionException {
		 return par.particleArea / (par.boundingRectHeight * par.boundingRectWidth);
	}
		
	public double calculateAngleToFloor(){
		return Math.acos(accelerometer.getY());
	}
	
	public double getDistance(){
		return this.distance;
	}

	public newParticleAnalysisReport getReport() {
		return this.particleReport;
	}
	
	public class VisionPID extends Vision implements PIDSource, Runnable{
				
		//Executor.
		@Override
		public void run(){			
			camera = RobotMap.shooterCamera;
			while(isProcessing){
				this.distance = pidGet();
			}
		}	
		
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			pidSourceType = pidSource;
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return pidSourceType;
		}


		@Override
		public double pidGet() {
			particleReport = imageProcessing();
			try {
				this.distance = calculateDistance(particleReport);
				SmartDashboard.putNumber("Distance From Target", calculateAreaRatio(particleReport));
			} catch (NIVisionException e) {
				e.printStackTrace();
			}
		
			return this.distance;
		}
		
	}
}

