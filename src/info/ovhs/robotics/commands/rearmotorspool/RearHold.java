package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.OI;
import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Keeps the rear motor spool in the same place (Does not use PID)
 */
public class RearHold extends Command {

	/**
	 * Keeps the rear motor spool in the same place (Does not use PID)
	 */
    public RearHold() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (CommandBase.rearMotorSpool);
    }

    /**
     *  Called just before this Command runs the first time
     *  
     *  <p>
     *  Stops the rear motor spool
     *  </p>
     */
    protected void initialize() {
    	CommandBase.rearMotorSpool.stop();
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     *  <p>
     *  Do nothing
     *  </p>
     */
    protected void execute() {
//    	if (RobotMap.robotLimitSwitch.get() && !CommandBase.rearMotorSpool.switchHasBeenHit) {
//    		OI.startControllerRumble(OI.xboxController);
//    		CommandBase.rearMotorSpool.switchHasBeenHit = true;
//    	} else {
//    		OI.stopControllerRumble(OI.xboxController);
//    	}
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
     *  Does nothing, motor is already stopped
     *  </p>
     *  
     */
    protected void end() {
    }

    /**
     *  Called when another command which requires one or more of the same subsystems is scheduled to run
     *  <p>
     *  Does nothing, motor is already stopped
     *  </p>
     */
    protected void interrupted() {
    }
}
