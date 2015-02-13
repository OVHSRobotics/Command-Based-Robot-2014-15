

package info.ovhs.robotics.commands.drive;

import info.ovhs.robotics.OI;
import info.ovhs.robotics.Robot;
import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.CommandBase;

/**
 *
 */
public class  MecanumDrive extends CommandBase {
	
    public MecanumDrive() {
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
    	CommandBase.driveTrain.mecanumDriveController();
//    	RobotMap.drive.mecanumDrive_Cartesian(OI.getLeftStickXAxis(), OI.getLeftStickYAxis(), OI.getRightStickXAxis(), 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
