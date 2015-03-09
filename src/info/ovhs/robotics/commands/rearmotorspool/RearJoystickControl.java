package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.OI;
import info.ovhs.robotics.Robot;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses the Z-Axis on the operator joystick to control the rear motor spool
 */
public class RearJoystickControl extends Command {

	private int joystickToUse;
	private int joystickAxisToUse;
	
	/**
	 * Uses the Z-Axis on the operator joystick to control the rear motor spool
	 */
    public RearJoystickControl(int joystickToUse, int joystickAxisToUse) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.rearMotorSpool);
    	this.joystickToUse = joystickToUse;
    	this.joystickAxisToUse = joystickAxisToUse;
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
    		CommandBase.rearMotorSpool.setSpeed(OI.xboxController.getRawAxis(this.joystickAxisToUse));
    	case 1: 
    		CommandBase.rearMotorSpool.setSpeed(OI.operatorController.getRawAxis(this.joystickAxisToUse));
    		break;
    	default:
    		Robot.print("Invalid Number as parameter for axis");
    	}
    	//CommandBase.rearMotorSpool.setSpeed(OI.operatorControllerOne.getRawAxis(Constants.OperatorControls.Controller.OperatorController1.Axes.Z));
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
