package info.ovhs.robotics.commands.conveyer;

import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ConveyerMove extends Command {
	
	boolean forward = true;

    public ConveyerMove(boolean forward) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.conveyerBelt);
    	if (forward) {
    		this.forward = true;
    	} else {
    		this.forward = false;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (this.forward) {
    		CommandBase.conveyerBelt.forward(1);
    	} else if (!this.forward) {
    		CommandBase.conveyerBelt.backward(1);
    	} else {

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
