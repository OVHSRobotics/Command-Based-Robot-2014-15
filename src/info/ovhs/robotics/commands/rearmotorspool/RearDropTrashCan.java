package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;

/**
 *
 */

public class RearDropTrashCan extends CommandBase {

	
	private double initialDistance;

	private double percentSpeed;
	
	/**
	 * Drops a trash can at full speed
	 */
	public RearDropTrashCan() {
		this(100);
	}

	/**
	 * Drops a trash can at a set speed
	 * 
	 * @param percentSpeed Speed to drop trash can at
	 */
    public RearDropTrashCan(double percentSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.rearMotorSpool);
    	this.percentSpeed = percentSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.initialDistance = CommandBase.rearMotorSpool.encoder.getDistance();
    
    	CommandBase.rearMotorSpool.backward(this.percentSpeed / 100 * -Constants.Motors.MOTOR_MAX_OUTPUT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Do nothing
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Command is done when encoder reaches certain amount of ticks
    	return CommandBase.rearMotorSpool.encoder.getDistance() <= this.initialDistance - Constants.FieldElements.TOTE_HEIGHT_INCHES;
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Turn motor off
    	CommandBase.rearMotorSpool.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// Turn motor off
    	CommandBase.rearMotorSpool.stop();	
    }
}
