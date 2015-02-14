package info.ovhs.robotics.commands.conveyer;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;


public class DropTote extends CommandBase {

	private double initialDistance;
	
	private double percentSpeed;
	
	/**
	 * Drops tote at full speed
	 */
	public DropTote() {
		this(100);
	}
	
	/**
	 * Drops tote at a set speed
	 * 
	 * @param percentSpeed Speed to drop tote at
	 */
    public DropTote(double percentSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.conveyerBelt);
    	this.percentSpeed = percentSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.initialDistance = CommandBase.conveyerBelt.encoder.getDistance();
    	
    	// Turn the motor on
    	CommandBase.conveyerBelt.backward(this.percentSpeed / 100 * -Constants.Motors.MOTOR_MAX_OUTPUT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Encoder reaches threshold
    	return CommandBase.conveyerBelt.encoder.getDistance() <= this.initialDistance - Constants.FieldElements.TOTE_HEIGHT_INCHES;
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
