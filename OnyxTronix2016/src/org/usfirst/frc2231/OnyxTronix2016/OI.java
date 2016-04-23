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

import org.usfirst.frc2231.OnyxTronix2016.commands.AutoCollect;
import org.usfirst.frc2231.OnyxTronix2016.commands.AutoCycle;
import org.usfirst.frc2231.OnyxTronix2016.commands.AutoShoot;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_ChevalDeFrise;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_Gate;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_LowBar;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_Moat;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_Ramparts;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_Rockwall;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_RoughTerrain;
import org.usfirst.frc2231.OnyxTronix2016.commands.CalculateDistanceByVision;
import org.usfirst.frc2231.OnyxTronix2016.commands.CenterByVision;
import org.usfirst.frc2231.OnyxTronix2016.commands.Autonomous_ManualTimeouts;
import org.usfirst.frc2231.OnyxTronix2016.commands.CloseCollector;
import org.usfirst.frc2231.OnyxTronix2016.commands.CloseShifters;
import org.usfirst.frc2231.OnyxTronix2016.commands.DriveByDistance;
import org.usfirst.frc2231.OnyxTronix2016.commands.DriveByTimeout;
import org.usfirst.frc2231.OnyxTronix2016.commands.DriveWithJoystick;
import org.usfirst.frc2231.OnyxTronix2016.commands.OpenCollector;
import org.usfirst.frc2231.OnyxTronix2016.commands.OpenShifters;
import org.usfirst.frc2231.OnyxTronix2016.commands.ReloadBackward;
import org.usfirst.frc2231.OnyxTronix2016.commands.ShootAtSpeed;
import org.usfirst.frc2231.OnyxTronix2016.commands.ShootByTimeout;
import org.usfirst.frc2231.OnyxTronix2016.commands.ShooterSpeedByPOV;
import org.usfirst.frc2231.OnyxTronix2016.commands.StartCompressor;
import org.usfirst.frc2231.OnyxTronix2016.commands.StartEmergency;
import org.usfirst.frc2231.OnyxTronix2016.commands.StartReloader;
import org.usfirst.frc2231.OnyxTronix2016.commands.StopCompressor;
import org.usfirst.frc2231.OnyxTronix2016.commands.StopEmergency;
import org.usfirst.frc2231.OnyxTronix2016.commands.StopReloader;
import org.usfirst.frc2231.OnyxTronix2016.commands.StopRoll;
import org.usfirst.frc2231.OnyxTronix2016.commands.StopShooter;
import org.usfirst.frc2231.OnyxTronix2016.commands.SwitchDirection;
import org.usfirst.frc2231.OnyxTronix2016.commands.TurnByDegrees;
import org.usfirst.frc2231.OnyxTronix2016.commands.UnloadBall;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton calculateDistanceByVision;
    public JoystickButton shootByVision;
    public JoystickButton shifterOpen;
    public JoystickButton shifterClose;
    public JoystickButton startCopmressor;
    public JoystickButton stopCompressor;
    public JoystickButton left90;
    public JoystickButton right90;
    public JoystickButton centerOnTarget;
    public Joystick driveStick;
    public JoystickButton unloadTheBall;
    public JoystickButton collectorOpen;
    public JoystickButton collectorClose;
    public JoystickButton emergencyStart;
    public JoystickButton emergencyStop;
    public JoystickButton prepareShooterWheels;
    public Joystick buttonStick;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        buttonStick = new Joystick(1);
        
        prepareShooterWheels = new JoystickButton(buttonStick, 5);
        prepareShooterWheels.whileHeld(new ShootAtSpeed(1));
        emergencyStop = new JoystickButton(buttonStick, 7);
        emergencyStop.whenPressed(new StopEmergency());
        emergencyStart = new JoystickButton(buttonStick, 8);
        emergencyStart.whenPressed(new StartEmergency());
        collectorClose = new JoystickButton(buttonStick, 2);
        collectorClose.whileHeld(new CloseCollector());
        collectorOpen = new JoystickButton(buttonStick, 3);
        collectorOpen.whileHeld(new OpenCollector());
        unloadTheBall = new JoystickButton(buttonStick, 4);
        unloadTheBall.whileHeld(new UnloadBall());
        driveStick = new Joystick(0);
        
        centerOnTarget = new JoystickButton(driveStick, 1);
        centerOnTarget.whenPressed(new CenterByVision(32));
        right90 = new JoystickButton(driveStick, 2);
        right90.whenPressed(new TurnByDegrees(-90));
        left90 = new JoystickButton(driveStick, 3);
        left90.whenPressed(new TurnByDegrees(90));
        stopCompressor = new JoystickButton(driveStick, 7);
        stopCompressor.whenPressed(new StopCompressor());
        startCopmressor = new JoystickButton(driveStick, 8);
        startCopmressor.whenPressed(new StartCompressor());
        shifterClose = new JoystickButton(driveStick, 6);
        shifterClose.whileHeld(new CloseShifters());
        shifterOpen = new JoystickButton(driveStick, 5);
        shifterOpen.whileHeld(new OpenShifters());
        calculateDistanceByVision = new JoystickButton(driveStick, 4);
        calculateDistanceByVision.whileHeld(new CalculateDistanceByVision()); 


        // SmartDashboard Buttons
        SmartDashboard.putData("OpenShifters", new OpenShifters());
        SmartDashboard.putData("CloseShifters", new CloseShifters());
        SmartDashboard.putData("Autonomous_ManualTimeouts", new Autonomous_ManualTimeouts());
        SmartDashboard.putData("StopRoll", new StopRoll());
        SmartDashboard.putData("DriveWithJoystick", new DriveWithJoystick());
        SmartDashboard.putData("AutoCollect", new AutoCollect());
        SmartDashboard.putData("SwitchDirection", new SwitchDirection());
        SmartDashboard.putData("UnloadBall", new UnloadBall());
        SmartDashboard.putData("StartReloader", new StartReloader());
        SmartDashboard.putData("StopReloader", new StopReloader());
        SmartDashboard.putData("OpenCollector", new OpenCollector());
        SmartDashboard.putData("CloseCollector", new CloseCollector());
        SmartDashboard.putData("StopShooter", new StopShooter());
        SmartDashboard.putData("AutoCycle", new AutoCycle());
        SmartDashboard.putData("Autonomous_RoughTerrain", new Autonomous_RoughTerrain());
        SmartDashboard.putData("ReloadBackward", new ReloadBackward());
        SmartDashboard.putData("AutoShoot", new AutoShoot());
        SmartDashboard.putData("StopEmergency", new StopEmergency());
        SmartDashboard.putData("StartEmergency", new StartEmergency());
        SmartDashboard.putData("ShootByTimeout", new ShootByTimeout());
        SmartDashboard.putData("Autonomous_ChevalDeFrise", new Autonomous_ChevalDeFrise());
        SmartDashboard.putData("DriveByDistance: fullSpeed", new DriveByDistance(1));
        SmartDashboard.putData("Autonomous_Gate", new Autonomous_Gate());
        SmartDashboard.putData("Autonomous_LowBar", new Autonomous_LowBar());
        SmartDashboard.putData("Autonomous_Moat", new Autonomous_Moat());
        SmartDashboard.putData("Autonomous_Ramparts", new Autonomous_Ramparts());
        SmartDashboard.putData("Autonomous_Rockwall", new Autonomous_Rockwall());
        SmartDashboard.putData("CenterByVision: center", new CenterByVision(0.8));
        SmartDashboard.putData("ShooterSpeedByPOV", new ShooterSpeedByPOV());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getdriveStick() {
        return driveStick;
    }

    public Joystick getbuttonStick() {
        return buttonStick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

