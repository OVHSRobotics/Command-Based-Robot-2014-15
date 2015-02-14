

package info.ovhs.robotics.commands.drive;

import info.ovhs.robotics.Robot;
import info.ovhs.robotics.commands.CommandBase;

/**
 *
 */
public class  ArcadeDrive extends CommandBase {
	
    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        requires(CommandBase.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.print(toString());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	CommandBase.driveTrain.arcadeDriveController();
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
    
    public String toString() {
    	return "Drive arcade-style with left stick on xbox controller";
    }
}
