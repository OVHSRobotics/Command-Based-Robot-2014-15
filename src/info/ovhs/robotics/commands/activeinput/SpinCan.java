package info.ovhs.robotics.commands.activeinput;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinCan extends Command {
	
	private double moveSpeed;

	public SpinCan() {
		this(Constants.ActiveIntake.SPIN_MOVE_SPEED);
	}
	
    public SpinCan(double moveSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.activeInput);
    	this.moveSpeed = moveSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CommandBase.activeInput.spinCan(moveSpeed);
    	
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
    	CommandBase.activeInput.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
