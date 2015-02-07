package info.ovhs.robotics.commands.conveyer;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;

public class LiftTrashCan extends CommandBase {

	private double initialDistance;
	
    public LiftTrashCan() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.conveyerBelt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.initialDistance = CommandBase.conveyerBelt.encoder.getDistance();
    	// Turn on motor
    	double percentSpeed = 100;
    	CommandBase.conveyerBelt.forward(percentSpeed / 100 * Constants.Motors.MOTOR_MAX_OUTPUT);	
    } 

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Nothing, let motor run for 50 times a second  
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Command is done when encoder reaches certain amount of ticks 
        return CommandBase.conveyerBelt.encoder.getDistance() <= this.initialDistance + Constants.FieldElements.TRASHCAN_HEIGHT_INCHES;
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Turn motor off
    	CommandBase.conveyerBelt.stop(); 
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// Turn motor off 
    	CommandBase.conveyerBelt.stop();
    }
}
