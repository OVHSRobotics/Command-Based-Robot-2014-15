package info.ovhs.robotics.commands.conveyer;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the conveyer a set direction
 */
public class ConveyerMove extends Command {
	
	/**
	 * Whether or not the conveyer is moving forward
	 */
	boolean forward = true;

	/**
	 * Sets the conveyer to move forward
	 */
	public ConveyerMove() {
		this(true);
	}
	
	/**
	 * Sets the conveyer to move a set direction
	 * 
	 * @param forward Whether or not the conveyer is moving forward
	 */
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

    /**
     *  Called just before this Command runs the first time
     */
    protected void initialize() {
    	
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     *  
     *  <p>
     *  Moves the conveyer a set direction with a speed set in Constants
     *  </p>
     */
    protected void execute() {
    	if (this.forward) {
    		CommandBase.conveyerBelt.forward(Constants.ConveyerBelt.MANUAL_MOVE_SPEED);
    	} else if (!this.forward) {
    		CommandBase.conveyerBelt.backward(Constants.ConveyerBelt.MANUAL_MOVE_SPEED);
    	} else {

    	}
    }

    /**
     *  Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     *  Called once after isFinished returns true
     *  
     *  <p>
     *  Stops the motors
     *  </p>
     */
    protected void end() {
    	CommandBase.conveyerBelt.stop();
    }

    /**
     *  Called when another command which requires one or more of the same subsystems is scheduled to run
     *  
     *  <p>
     *  Stops the motors
     *  </p>
     */
    protected void interrupted() {
    	this.end();
    }
}
