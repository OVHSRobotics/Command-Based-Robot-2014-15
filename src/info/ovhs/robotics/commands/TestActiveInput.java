package info.ovhs.robotics.commands;

import info.ovhs.robotics.OI;
import info.ovhs.robotics.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestActiveInput extends Command {

    public TestActiveInput() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CommandBase.driveTrain.stopAllMotors();
    	RobotMap.testSpeedControllerLeft.set(OI.getXboxRightStickYAxis());
    	RobotMap.testSpeedControllerRight.set(OI.getXboxRightStickYAxis());
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	CommandBase.driveTrain.stopAllMotors();
    	RobotMap.testSpeedControllerLeft.set(OI.getXboxRightStickYAxis());
    	RobotMap.testSpeedControllerRight.set(OI.getXboxRightStickYAxis());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.testSpeedControllerLeft.set(0);
    	RobotMap.testSpeedControllerRight.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.testSpeedControllerLeft.set(0);
    }
}
