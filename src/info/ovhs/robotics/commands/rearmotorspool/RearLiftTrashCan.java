package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;

/**
 * Lifts a trash can using the rear motor spool
 *
 */
public class RearLiftTrashCan extends CommandBase {
	
	/**
	 * Initial distance for the rear encoder
	 */
	public double initialDistance;
	/**
	 * Percent speed to move the rear motor spool while lifting the trash can 
	 */
	private double percentSpeed;
	
	
	/**
	 * Lifts trash can with rear motor spool at full speed
	 */
	public RearLiftTrashCan() {
		this(50);
	}
	
	/**
	 * Lift trash can with rear motor spool at a set speed
	 * 
	 * @param percentSpeed speed to lift trash can at
	 */
    public RearLiftTrashCan( double percentSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.rearMotorSpool);
    	this.percentSpeed = percentSpeed;
    }

    /**
     *  Called just before this Command runs the first time
     */
    protected void initialize() {
    	this.initialDistance = CommandBase.rearMotorSpool.encoder.getDistance();
    	
    	// Turn on motor
    	CommandBase.rearMotorSpool.forward(this.percentSpeed / 100 * Constants.Motors.MOTOR_MAX_OUTPUT);	
    } 

    /**
     *  Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    	// Nothing, let motor run for 50 times a second  
    }

    /**
     *  Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
    	// Command is done when encoder reaches certain amount of ticks 
    	return CommandBase.rearMotorSpool.encoder.getDistance() <= this.initialDistance - Constants.FieldElements.TOTE_HEIGHT_INCHES;
    }

    /**
     *  Called once after isFinished returns true
     */
    protected void end() {
    	// Turn motor off
    	CommandBase.rearMotorSpool.stop(); 
    }

    /**
     *  Called when another command which requires one or more of the same subsystems is scheduled to run
     */
    protected void interrupted() {
    	// Turn motor off 
    	CommandBase.rearMotorSpool.stop();
    }
}
