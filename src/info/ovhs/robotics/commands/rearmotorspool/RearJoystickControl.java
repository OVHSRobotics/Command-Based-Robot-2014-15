package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.OI;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses the Z-Axis on the operator joystick to control the rear motor spool
 */
public class RearJoystickControl extends Command {

	/**
	 * Uses the Z-Axis on the operator joystick to control the rear motor spool
	 */
    public RearJoystickControl() {
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
    	CommandBase.rearMotorSpool.setSpeed(OI.operatorController.getRawAxis(Constants.OperatorControls.Controller.OperatorController.Axes.Z));
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
    	CommandBase.rearMotorSpool.setSpeed(0);
    }

    /**
     *  Called when another command which requires one or more of the same subsystems is scheduled to run
     */
    protected void interrupted() {
    	this.end();
    }
}
