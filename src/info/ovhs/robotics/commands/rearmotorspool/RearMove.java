package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.OI;
import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Manual move of the rear motor spool a specified direction
 */
public class RearMove extends Command {
	
	/**
	 * Whether or not the rear motor spool is moving forward
	 */
	boolean forward = true;
	boolean switchHasBeenHit = false;

	/**
	 * Manual move of the rear motor spool a set direction
	 * @param forward whether or not to move forward
	 */
    public RearMove(boolean forward) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.rearMotorSpool);
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
     */
    protected void execute() {
    	if (this.forward) {
    		CommandBase.rearMotorSpool.forward(Constants.RearMotorSpool.MANUAL_MOVE_SPEED);
    		this.switchHasBeenHit = false;
    	} else if (!this.forward && RobotMap.robotLimitSwitch.get()) {
    		CommandBase.rearMotorSpool.backward(0);
    	} else if (!this.forward) {
    		CommandBase.rearMotorSpool.backward(Constants.RearMotorSpool.MANUAL_MOVE_SPEED);
    	} else {
    		//Impossible case
    	}
    	if (RobotMap.robotLimitSwitch.get() && !this.switchHasBeenHit) {
    		OI.xboxController.setRumble(RumbleType.kLeftRumble, 1);
    		OI.xboxController.setRumble(RumbleType.kRightRumble, 1);
    		this.switchHasBeenHit = true;
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
