package info.ovhs.robotics.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DropTote extends CommandBase {

    public DropTote() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// Turn the motor on
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Encoder reaches threshold
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Turn motor off
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// Turn motor off
    }
}
