package info.ovhs.robotics.commands.conveyer;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.OI;
import info.ovhs.robotics.Robot;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Allows control of conveyer belt with joystick control (Uses Y axis on the operator control
 */
public class ConveyerJoystickControl extends Command {

	private int joystickToUse;
	private int joystickAxisToUse;
	
	/**
	 * Allows control of conveyer belt with joystick control using an axis set in as a parameter
	 */
    public ConveyerJoystickControl(int joystickToUse, int joystickAxisToUse) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.conveyerBelt);
    	this.joystickToUse = joystickToUse;
    	this.joystickAxisToUse = joystickAxisToUse;
    }

    /**
     * Conveyer Joystick Control with y axis on operator controller
     */
    public ConveyerJoystickControl() {
    	this(Constants.Ports.Joystick.OPERATOR_CONTROLLER, Constants.OperatorControls.Controller.OperatorController.Axes.Y);
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
    	switch (this.joystickToUse){
    	case 0:
    		CommandBase.conveyerBelt.setSpeed(OI.xboxController.getRawAxis(this.joystickAxisToUse));
    	case 1: 
    		CommandBase.conveyerBelt.setSpeed(OI.operatorController.getRawAxis(-this.joystickAxisToUse));
    		break;
    	default:
    		Robot.print("Invalid Number as parameter for axis");
    	}
//    	CommandBase.conveyerBelt.setSpeed(OI.operatorControllerOne.getRawAxis(Constants.OperatorControls.Controller.OperatorController1.Axes.Y));
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
