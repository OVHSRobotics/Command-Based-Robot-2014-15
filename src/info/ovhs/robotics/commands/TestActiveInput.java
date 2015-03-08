package info.ovhs.robotics.commands;

import info.ovhs.robotics.OI;
import info.ovhs.robotics.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the active input arms based on the joystick input from the xbox controller's right stick's Y-axis
 * DEPRECATED, Use Active Input Subsystem
 */
@Deprecated
public class TestActiveInput extends Command {

    public TestActiveInput() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CommandBase.driveTrain.stopAllMotors();
    	RobotMap.activeInputSpeedControllerLeft.set(OI.getXboxRightStickYAxis());
    	RobotMap.activeInputSpeedControllerRight.set(OI.getXboxRightStickYAxis());
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	CommandBase.driveTrain.stopAllMotors();
    	RobotMap.activeInputSpeedControllerLeft.set(OI.getXboxRightStickYAxis());
    	RobotMap.activeInputSpeedControllerRight.set(OI.getXboxRightStickYAxis());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.activeInputSpeedControllerLeft.set(0);
    	RobotMap.activeInputSpeedControllerRight.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.activeInputSpeedControllerLeft.set(0);
    }
}
