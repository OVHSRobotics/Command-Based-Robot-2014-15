package info.ovhs.robotics.commands;

import info.ovhs.robotics.Robot;
import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.CommandBase;

/**
 *
 */
public class MecanumDriveGyro extends CommandBase {

    public MecanumDriveGyro() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.print(toString());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveTrain.mecanumDriveController(RobotMap.gyro1.getAngle());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public String toString() {
    	return "Driving mecanum style with xbox controller";
    }
}
