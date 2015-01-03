package info.ovhs.robotics.commands;

import info.ovhs.robotics.OI;
import info.ovhs.robotics.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveWithController extends Command {

	boolean TANK_DRIVE_MODE = false;
	long lastPressStartButton = 0;

	    public DriveWithController() {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
	    	
	    }

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    	lastPressStartButton = System.currentTimeMillis();
	    	
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	if (OI.isStartButtonPressed() && System.currentTimeMillis()-lastPressStartButton > 1000)
	            {
	                TANK_DRIVE_MODE = !TANK_DRIVE_MODE;
	                
	                // Update last button press time
	                lastPressStartButton = System.currentTimeMillis();
	            }
	    	if (TANK_DRIVE_MODE) {
	    		DriveTrain.instance.tankDrive();
	    	} else {
	    		DriveTrain.instance.arcadeDrive();
	    	}
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
