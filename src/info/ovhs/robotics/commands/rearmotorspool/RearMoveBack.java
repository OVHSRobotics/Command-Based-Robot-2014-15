package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Manual move of the rear motor spool a specified direction
 */
public class RearMoveBack extends Command {
	
	
	/**
	 * Manual move of the rear motor spool a set direction
	 * @param forward whether or not to move forward
	 */
    public RearMoveBack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.rearMotorSpool);
    }

    /**
     *  Called just before this Command runs the first time
     */
    protected void initialize() {
    	
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    	
    	if (RobotMap.robotLimitSwitch.get()) {
    		CommandBase.rearMotorSpool.stop();
    	} else {
    		CommandBase.rearMotorSpool.backward(Constants.RearMotorSpool.MANUAL_MOVE_SPEED_DOWN);
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
     */
    protected void end() {
    }

    /**
     *  Called when another command which requires one or more of the same subsystems is scheduled to run
     */
    protected void interrupted() {
    }
}
