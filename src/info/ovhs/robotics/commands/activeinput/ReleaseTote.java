package info.ovhs.robotics.commands.activeinput;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReleaseTote extends Command {

	private double moveSpeed;
	
    public ReleaseTote() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(Constants.ActiveIntake.AUTO_MOVE_SPEED);
    }
    
    public ReleaseTote(double moveSpeed) {
    	requires(CommandBase.activeInput);
    	this.moveSpeed = moveSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CommandBase.activeInput.releaseTote(this.moveSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.interrupted();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	CommandBase.activeInput.stop();
    }
}
