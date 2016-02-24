// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2231.OnyxTronix2016;

import java.nio.file.AccessMode;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANSpeedController.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.*;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.USBCamera;
import edu.wpi.first.wpilibj.vision.AxisCamera.Resolution;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController collectorCollectorSpinner;
    public static Solenoid collectorleftOpenSolenoid;
    public static Solenoid collectorrightOpenSolenoid;
    public static Solenoid collectorrightCloseSolenoid;
    public static Solenoid collectorleftCloseSolenoid;
    public static Compressor collectorCompressor;
    public static Solenoid driveTrainCloseShifter;
    public static Solenoid driveTrainOpenShifter;
    public static Ultrasonic driveTrainUltrasonic1;
    public static Ultrasonic driveTrainUltrasonic2;
    public static SpeedController driveTrainLeftCopy;
    public static Encoder driveTrainLeftEncoder;
    public static PIDController driveTrainLeftPIDController;
    public static Encoder driveTrainRightEncoder;
    public static SpeedController driveTrainRightCopy;
    public static PIDController driveTrainRightPIDController;
    public static SpeedController driveTrainRight;
    public static SpeedController driveTrainLeft;
    public static RobotDrive driveTrainRobotDrive21;
    public static Encoder shooterShooterRightEncoder;
    public static SpeedController shooterShooterRightMotor;
    public static PIDController shooterRightPIDController;
    public static SpeedController shooterShooterLeftMotor;
    public static Encoder shooterShooterLeftEncoder;
    public static PIDController shooterLeftPIDController;
    public static SpeedController climberSpeedController1;
    public static SpeedController climberSpeedController2;
    public static SpeedController reloaderReloaderWheel;
    public static DigitalInput reloaderisCollected;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static AxisCamera shooterCamera;
    public static USBCamera driveCamera; 
    public static ADXL362 visionAccelerometer;

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        collectorCollectorSpinner = new Talon(5);
        LiveWindow.addActuator("Collector", "CollectorSpinner", (Talon) collectorCollectorSpinner);
        
        collectorleftOpenSolenoid = new Solenoid(0, 4);
        LiveWindow.addActuator("Collector", "leftOpenSolenoid", collectorleftOpenSolenoid);
        
        collectorrightOpenSolenoid = new Solenoid(0, 3);
        LiveWindow.addActuator("Collector", "rightOpenSolenoid", collectorrightOpenSolenoid);
        
        collectorrightCloseSolenoid = new Solenoid(0, 2);
        LiveWindow.addActuator("Collector", "rightCloseSolenoid", collectorrightCloseSolenoid);
        
        collectorleftCloseSolenoid = new Solenoid(0, 5);
        LiveWindow.addActuator("Collector", "leftCloseSolenoid", collectorleftCloseSolenoid);
        
        collectorCompressor = new Compressor(0);
        
        
        driveTrainCloseShifter = new Solenoid(0, 0);
        LiveWindow.addActuator("DriveTrain", "CloseShifter", driveTrainCloseShifter);
        
        driveTrainOpenShifter = new Solenoid(0, 1);
        LiveWindow.addActuator("DriveTrain", "OpenShifter", driveTrainOpenShifter);
        
        driveTrainUltrasonic1 = new Ultrasonic(5, 4);
        LiveWindow.addSensor("DriveTrain", "Ultrasonic 1", driveTrainUltrasonic1);
        
        driveTrainUltrasonic2 = new Ultrasonic(7, 6);
        LiveWindow.addSensor("DriveTrain", "Ultrasonic 2", driveTrainUltrasonic2);
        
        driveTrainLeftCopy = new TalonSRX(18);
        LiveWindow.addActuator("DriveTrain", "LeftCopy", (TalonSRX) driveTrainLeftCopy);
        
        driveTrainLeftEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "LeftEncoder", driveTrainLeftEncoder);
        driveTrainLeftEncoder.setDistancePerPulse(1.0);
        driveTrainLeftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
        driveTrainLeftPIDController = new PIDController(0.07, 0.0, 0.03, 0.0, driveTrainLeftEncoder, driveTrainLeftCopy, 0.02);
        LiveWindow.addActuator("DriveTrain", "LeftPIDController", driveTrainLeftPIDController);
        driveTrainLeftPIDController.setContinuous(false);
        driveTrainLeftPIDController.setAbsoluteTolerance(10.0);

        driveTrainLeftPIDController.setOutputRange(-0.4, 0.4);
        driveTrainRightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveTrain", "RightEncoder", driveTrainRightEncoder);
        driveTrainRightEncoder.setDistancePerPulse(1.0);
        driveTrainRightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
        driveTrainRightCopy = new TalonSRX(19);
        LiveWindow.addActuator("DriveTrain", "RightCopy", (TalonSRX) driveTrainRightCopy);
        
        driveTrainRightPIDController = new PIDController(0.07, 0.0, 0.03, 0.0, driveTrainRightEncoder, driveTrainRightCopy, 0.02);
        LiveWindow.addActuator("DriveTrain", "RightPIDController", driveTrainRightPIDController);
        driveTrainRightPIDController.setContinuous(false);
        driveTrainRightPIDController.setAbsoluteTolerance(10.0);

        driveTrainRightPIDController.setOutputRange(-0.4, 0.4);
        driveTrainRight = new TalonSRX(1);
        LiveWindow.addActuator("DriveTrain", "Right", (TalonSRX) driveTrainRight);
        
        driveTrainLeft = new TalonSRX(0);
        LiveWindow.addActuator("DriveTrain", "Left", (TalonSRX) driveTrainLeft);
        
        driveTrainRobotDrive21 = new RobotDrive(driveTrainLeft, driveTrainRight);
        
        driveTrainRobotDrive21.setSafetyEnabled(true);
        driveTrainRobotDrive21.setExpiration(0.1);
        driveTrainRobotDrive21.setSensitivity(0.5);
        driveTrainRobotDrive21.setMaxOutput(1.0);

        shooterShooterRightEncoder = new Encoder(11, 12, false, EncodingType.k4X);
        LiveWindow.addSensor("Shooter", "ShooterRightEncoder", shooterShooterRightEncoder);
        shooterShooterRightEncoder.setDistancePerPulse(1.0);
        shooterShooterRightEncoder.setPIDSourceType(PIDSourceType.kRate);
        shooterShooterRightMotor = new Talon(4);
        LiveWindow.addActuator("Shooter", "ShooterRightMotor", (Talon) shooterShooterRightMotor);
        
        shooterRightPIDController = new PIDController(1.0, 0.0, 0.0, 0.0, shooterShooterRightEncoder, shooterShooterRightMotor, 0.02);
        LiveWindow.addActuator("Shooter", "RightPIDController", shooterRightPIDController);
        shooterRightPIDController.setContinuous(false);
        shooterRightPIDController.setAbsoluteTolerance(0.2);

        shooterRightPIDController.setOutputRange(-1.0, 1.0);
        shooterShooterLeftMotor = new Talon(3);
        LiveWindow.addActuator("Shooter", "ShooterLeftMotor", (Talon) shooterShooterLeftMotor);
        
        shooterShooterLeftEncoder = new Encoder(9, 10, false, EncodingType.k4X);
        LiveWindow.addSensor("Shooter", "ShooterLeftEncoder", shooterShooterLeftEncoder);
        shooterShooterLeftEncoder.setDistancePerPulse(1.0);
        shooterShooterLeftEncoder.setPIDSourceType(PIDSourceType.kRate);
        shooterLeftPIDController = new PIDController(1.0, 0.0, 0.0, 0.0, shooterShooterLeftEncoder, shooterShooterLeftMotor, 0.02);
        LiveWindow.addActuator("Shooter", "LeftPIDController", shooterLeftPIDController);
        shooterLeftPIDController.setContinuous(false);
        shooterLeftPIDController.setAbsoluteTolerance(0.2);

        shooterLeftPIDController.setOutputRange(-1.0, 1.0);
        climberSpeedController1 = new Talon(7);
        LiveWindow.addActuator("Climber", "Speed Controller 1", (Talon) climberSpeedController1);
        
        climberSpeedController2 = new Talon(6);
        LiveWindow.addActuator("Climber", "Speed Controller 2", (Talon) climberSpeedController2);
        
        reloaderReloaderWheel = new Talon(2);
        LiveWindow.addActuator("Reloader", "ReloaderWheel", (Talon) reloaderReloaderWheel);
        
        reloaderisCollected = new DigitalInput(8);
        LiveWindow.addSensor("Reloader", "isCollected", reloaderisCollected);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        visionAccelerometer = new ADXL362(null); //TODO Create a new Range for the accelerometer
        LiveWindow.addSensor("Vision", "Accelerometer", visionAccelerometer);
        
        shooterCamera = new AxisCamera("10.22.31.12");
        shooterCamera.writeResolution(Resolution.k640x480);
        driveTrainLeftCopy = driveTrainLeft;
        driveTrainRightCopy = driveTrainRight;
    }
}
	