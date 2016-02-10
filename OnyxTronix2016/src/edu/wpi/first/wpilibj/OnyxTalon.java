/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2016. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

import org.usfirst.frc2231.OnyxTronix2016.OnyxSpeedController;

import edu.wpi.first.wpilibj.SafePWM;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tResourceType;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Cross the Road Electronics (CTRE) Talon and Talon SR Speed Controller
 */
public class OnyxTalon extends Talon implements OnyxSpeedController {

	private SpeedController[] m_controllers;

	public OnyxTalon(int channel) {
		super(channel);
	}

	public void setControllers(SpeedController[] controllers) {
		this.m_controllers = controllers;
	}

	public void pidWrite(double output, boolean isStright) {
		System.out.println(Thread.currentThread().getStackTrace());
		for (int i = 0; i < 2; i++) {
			if (i % 2 == 0 && !isStright) {
				m_controllers[i].set(-output);
			} else {
				m_controllers[i].set(output);
			}
		}
		// set(output);
	}
	/*
	@Override
	public void pidWrite(double output, boolean isStraight) {
		for (int i = 0; i < m_controllers.length; i++) {
			if (i % 2 == 0 && !isStraight) {
				m_controllers[i].pidWrite(-output);
			} else {
				m_controllers[i].pidWrite(output);
			}
		}

	}
	*/
}