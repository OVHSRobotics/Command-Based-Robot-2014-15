package info.ovhs.robotics.commands;

import info.ovhs.robotics.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class ZeroControllerInputs extends Command {

	private long startTime;
	private boolean isFinished;
	private int zeroingTimeSeconds = 3;
	
    public ZeroControllerInputs() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.isFinished = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.print("*** Preparing to zero controller inputs ... DO NOT TOUCH CONTROLLER BUTTONS OR JOYSTICKS");
    	this.startTime = System.nanoTime();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if ((System.nanoTime() - startTime) / Math.pow(10, 9) < this.zeroingTimeSeconds)
    	{
    		return;
    	}
    	
    	Robot.print("*** Zeroing controller inputs");
    	Robot.oi.zeroAxisReadings();
    	Robot.print("*** Completed zeroing controller inputs");
    	this.isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }
    
    public String toString() {
    	return "Zero controller inputs";
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
