package org.usfirst.frc2231.OnyxTronix2016.commands;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrintToDebug extends Command {

	private String message;
	File file;
	
	public void debugText(String textToAppend)
	{	
		byte[] content;
		try{
			file = new File("/home/lvuser/test.txt");
			if(file.exists())
				file.delete();
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file, true);
			content = textToAppend.getBytes();
			fos.write(content);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public PrintToDebug(String message) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.message = message;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	debugText(this.message);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
