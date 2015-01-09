package info.ovhs.robotics.commands;

import info.ovhs.robotics.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class ZeroControllerInputs extends Command {

    public ZeroControllerInputs() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.print("*** Prepare to zero controller inputs ... DO NOT TOUCH CONTROLLER BUTTONS OR JOYSTICKS");
    	for (int i = 3; i != 0; --i) {
    		Robot.print(" " + i + " seconds ...");
    		Timer.delay(1.0d);
    	}
    	Robot.print("*** Zeroing controller inputs");
    	Robot.oi.zeroAxisReadings();
    	Robot.print("*** Completed zeroing controller inputs");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
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
