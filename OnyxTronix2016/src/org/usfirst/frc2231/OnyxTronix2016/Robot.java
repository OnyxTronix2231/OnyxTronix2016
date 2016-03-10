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

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2231.OnyxTronix2016.commands.AutoCycle;
import org.usfirst.frc2231.OnyxTronix2016.commands.AutoLoaded;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_ManualTimeouts;
import org.usfirst.frc2231.OnyxTronix2016.commands.DriveByDistance;
import org.usfirst.frc2231.OnyxTronix2016.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    CameraServer server;
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Collector collector;
    public static DriveTrain driveTrain;
    public static Shooter shooter;
    public static Climber climber;
    public static Reloader reloader;
//    public static Vision vision;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public double distanceFromTarget;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	RobotMap.init();
    	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        collector = new Collector();
        driveTrain = new DriveTrain();
        shooter = new Shooter();
        climber = new Climber();
        reloader = new Reloader();
//        vision = new Vision();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
      // vision.writeImage();
        server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam2");
    }
    
    public void operatorControl() {
    	
    }
    
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	//Robot.vision.setProcessing(false);
    	Scheduler.getInstance().removeAll();

    }

    public void disabledPeriodic() {
		//System.out.println(Thread.currentThread().isAlive());
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autonomousCommand = new Autonomous_ManualTimeouts();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        Scheduler.getInstance().removeAll();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //SmartDashboard.putString("default command", Robot.driveTrain.getCurrentCommand().toString());
//        SmartDashboard.putString("Distance - Left", Math.floor(RobotMap.driveTrainLeftEncoder.getDistance() * 1000) / 1000 + "");
//        SmartDashboard.putString("Rate - Left", Math.floor(RobotMap.driveTrainLeftEncoder.getRate() * 1000) / 1000 + "");
//        SmartDashboard.putString("DistancePerPulse - Left", Math.floor(RobotMap.driveTrainLeftEncoder.getDistance() / RobotMap.driveTrainLeftEncoder.get() * 1000) / 1000 + "");
//        SmartDashboard.putString("Distance - Right", Math.floor(RobotMap.driveTrainRightEncoder.getDistance() * 1000) / 1000 + "");
//        SmartDashboard.putString("Rate - Right", Math.floor(RobotMap.driveTrainRightEncoder.getRate() * 1000) / 1000 + "");
//        SmartDashboard.putString("DistancePerPulse - Right", Math.floor(RobotMap.driveTrainRightEncoder.getDistance() / RobotMap.driveTrainRightEncoder.get() * 1000) / 1000 + "");
//        SmartDashboard.putString("running command: DriveTrain", driveTrain.getCurrentCommand() + "");
//        SmartDashboard.putString("running command: Reloader", reloader.getCurrentCommand() + "");
//        SmartDashboard.putString("running command: Collector", collector.getCurrentCommand() + "");
//        SmartDashboard.putString("running command: Shooter", shooter.getCurrentCommand() + "");
//		SmartDashboard.putNumber("DriveTrainLeftCopy:", RobotMap.driveTrainLeftCopy.get());
//		SmartDashboard.putNumber("Drvw2iveTrainRightCopy:", RobotMap.driveTrainRightCopy.get());
		SmartDashboard.putNumber("Shooter Rate - Encoder(Left)", RobotMap.shooterShooterLeftEncoder.getRate());
//		SmartDashboard.putNumber("Shooter PID Rate ", RobotMap.shooterLeftPIDController.getSetpoint());
//		SmartDashboard.putString("Pdp Temp: ", RobotMap.driveTrainPdp.getTemperature() + "");
//		
//		SmartDashboard.putBoolean("Is ready to shoot: ", Robot.shooter.isReadyToShoot());
        
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
