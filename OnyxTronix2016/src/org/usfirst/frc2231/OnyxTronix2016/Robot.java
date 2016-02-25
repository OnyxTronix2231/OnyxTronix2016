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

import java.io.ObjectOutputStream.PutField;
import java.lang.reflect.Array;
import java.util.Arrays;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2231.OnyxTronix2016.commands.AutoCycle;
import org.usfirst.frc2231.OnyxTronix2016.commands.AutoLoaded;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_ChevalDeFrise;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_Gate;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_LowBar;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_Moat;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_Ramparts;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_Rockwall;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_RoughTerrain;
import org.usfirst.frc2231.OnyxTronix2016.commands.CenterByVision;
import org.usfirst.frc2231.OnyxTronix2016.commands.ProcessImage;
import org.usfirst.frc2231.OnyxTronix2016.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	SendableChooser obstacles;
	SendableChooser positions;
	CommandGroup OrderedAutonomous;
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Collector collector;
    public static DriveTrain driveTrain;
    public static Shooter shooter;
    public static Climber climber;
    public static Reloader reloader;
    public static Vision vision;

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
        vision = new Vision();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        obstacles = new SendableChooser();
        obstacles.addDefault("LowBar", new Autonomous_LowBar());
        obstacles.addObject("Gate", new Autonomous_Gate());
        obstacles.addObject("ChevalDeFrise", new Autonomous_ChevalDeFrise()); // The four "FlipFlops"
        obstacles.addObject("Moat", new Autonomous_Moat()); // The "U" Channel
        obstacles.addObject("Ramparts", new Autonomous_Ramparts());
        obstacles.addObject("RockWall", new Autonomous_Rockwall());      
        obstacles.addObject("RoughTerrain", new Autonomous_RoughTerrain());  
        
        positions = new SendableChooser(); 
        positions.addDefault("1", new Integer(1));
        positions.addObject("2", new Integer(2));
        positions.addObject("3", new Integer(3));
        positions.addObject("4", new Integer(4));
        positions.addObject("5", new Integer(5));
        SmartDashboard.putData("Positions : ",positions);
        
        SmartDashboard.putData("Obstacle : ",obstacles);
        
        OrderedAutonomous = new CommandGroup();

    }
    
    public void operatorControl() {
    	
    }
    
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	Robot.vision.stopProcessing();
    	Scheduler.getInstance().removeAll();

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putString("auto status", "OK");
        for(Obstacles.Obstacle obstacle : Obstacles.Obstacle.values()) {
        	int[] possiblePositions = obstacle.getPositions();
        	Arrays.sort(possiblePositions);
        	if(obstacle.getCommand().getClass().equals(obstacles.getSelected().getClass()) && 
        			Arrays.binarySearch(possiblePositions, (Integer)positions.getSelected()) < 0) {
        		SmartDashboard.putString("auto status", "INVALID AUTONOMOUS CHOSEN");
        	}	
        }
//    	if ((obstacles.getSelected().getClass().equals(Autonomous_LowBar.class) && !positions.getSelected().equals("1")) ||
//    			(obstacles.getSelected().getClass().equals(Autonomous_Gate.class) && !positions.getSelected().equals("3")) || 
//    			(obstacles.getSelected().getClass().equals(Autonomous_ChevalDeFrise.class) && !positions.getSelected().equals("3"))) {
//			SmartDashboard.putString("auto status", "INVALID AUTONOMOUS CHOSEN");
//		} else {
//			SmartDashboard.putString("auto status", "OK");
//		}
    }

    public void autonomousInit() {
    	
    	OrderedAutonomous.addSequential((Command) obstacles.getSelected());
    	OrderedAutonomous.addSequential((Command) positions.getSelected());
    	OrderedAutonomous.addParallel(new ProcessImage());
    	OrderedAutonomous.addParallel(new CenterByVision());
//    	OrderedAutonomous.addSequential(command);//TODO fix isFinished method in the vision's commands
       if (OrderedAutonomous != null) OrderedAutonomous.start();
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
        if (OrderedAutonomous != null) OrderedAutonomous.cancel();
        Scheduler.getInstance().removeAll();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //SmartDashboard.putString("default command", Robot.driveTrain.getCurrentCommand().toString());
        SmartDashboard.putString("Distance - Left", Math.floor(RobotMap.driveTrainLeftEncoder.getDistance() * 1000) / 1000 + "");
        SmartDashboard.putString("Rate - Left", Math.floor(RobotMap.driveTrainLeftEncoder.getRate() * 1000) / 1000 + "");
        SmartDashboard.putString("DistancePerPulse - Left", Math.floor(RobotMap.driveTrainLeftEncoder.getDistance() / RobotMap.driveTrainLeftEncoder.get() * 1000) / 1000 + "");
        SmartDashboard.putString("Distance - Right", Math.floor(RobotMap.driveTrainRightEncoder.getDistance() * 1000) / 1000 + "");
        SmartDashboard.putString("Rate - Right", Math.floor(RobotMap.driveTrainRightEncoder.getRate() * 1000) / 1000 + "");
        SmartDashboard.putString("DistancePerPulse - Right", Math.floor(RobotMap.driveTrainRightEncoder.getDistance() / RobotMap.driveTrainRightEncoder.get() * 1000) / 1000 + "");
        SmartDashboard.putString("running command: DriveTrain", driveTrain.getCurrentCommand() + "");
        SmartDashboard.putString("running command: Reloader", reloader.getCurrentCommand() + "");
        SmartDashboard.putString("running command: Collector", collector.getCurrentCommand() + "");
        SmartDashboard.putString("running command: Shooter", shooter.getCurrentCommand() + "");
		SmartDashboard.putNumber("DriveTrainLeftCopy:", RobotMap.driveTrainLeftCopy.get());
		SmartDashboard.putNumber("DriveTrainRightCopy:", RobotMap.driveTrainRightCopy.get());
		SmartDashboard.putNumber("Shooter Rate - Encoder(Left)", RobotMap.shooterShooterLeftEncoder.getRate());
		SmartDashboard.putNumber("Shooter PID Rate ", RobotMap.shooterLeftPIDController.getSetpoint());
		
		
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
