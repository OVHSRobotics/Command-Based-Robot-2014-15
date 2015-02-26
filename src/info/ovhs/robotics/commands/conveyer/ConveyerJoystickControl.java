package info.ovhs.robotics.commands.conveyer;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.OI;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Allows control of conveyer belt with joystick control (Uses Y axis on the operator control
 */
public class ConveyerJoystickControl extends Command {

	/**
	 * Allows control of conveyer belt with joystick control (Uses Y axis on the operator control
	 */
    public ConveyerJoystickControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.conveyerBelt);
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
    	CommandBase.conveyerBelt.setSpeed(OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.OperatorController.Axes.Y));
    	CommandBase.driveTrain.stopAllMotors();
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
    	CommandBase.conveyerBelt.stop();
    }

    /**
     *  Called when another command which requires one or more of the same subsystems is scheduled to run
     */
    protected void interrupted() {
    	this.end();
    }
}
