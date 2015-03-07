package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;

/**
 * Drops a trash can using the rear motor spool
 */

public class RearDropTrashCan extends CommandBase {

	/**
	 * Initial distance for the rear encoder
	 */
	private double initialDistance;
	/**
	 * Percent speed to move the rear motor spool when dropping the trash can
	 */
	private double percentSpeed;
	
	/**
	 * Drops a trash can at full speed
	 */
	public RearDropTrashCan() {
		this(50);
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

    /**
     *  Called just before this Command runs the first time
     *  
     *  <p>
     *  Starts dropping the trash can
     *  </p>
     */
    protected void initialize() {
    	this.initialDistance = CommandBase.rearMotorSpool.encoder.getDistance();
    
    	CommandBase.rearMotorSpool.backward(this.percentSpeed / 100 * -Constants.Motors.MOTOR_MAX_OUTPUT);
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     *  
     *  <p>
     *  Do nothing
     *  </p>
     */
    protected void execute() {
    	// Do nothing
    }

    /**
     *  Returns true when the rear motor spool is at the correct height
     */
    protected boolean isFinished() {
    	// Command is done when encoder reaches certain amount of ticks
    	return CommandBase.rearMotorSpool.encoder.getDistance() <= this.initialDistance - Constants.FieldElements.TOTE_HEIGHT_INCHES;
    }

    /**
     *  Called once after isFinished returns true
     *  
     *  <p>
     *  Stops the rear motor spool
     *  </p>
     */
    protected void end() {
    	// Turn motor off
    	CommandBase.rearMotorSpool.stop();
    }

    /**
     *  Called when another command which requires one or more of the same subsystems is scheduled to run
     *  <p>
     *  Stops the rear motor spool
     *  </p>
     */
    protected void interrupted() {
    	// Turn motor off
    	CommandBase.rearMotorSpool.stop();	
    }
}
