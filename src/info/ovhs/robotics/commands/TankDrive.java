

package info.ovhs.robotics.commands;

import info.ovhs.robotics.OI;
import info.ovhs.robotics.Robot;
import edu.wpi.first.wpilibj.command.Command;
import info.ovhs.robotics.subsystems.DriveTrain;

/**
 *
 */
public class  TankDrive extends Command {

	DriveTrain drivetrain = new DriveTrain();
	
    public TankDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.print(toString());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drivetrain.tankDriveController();
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
    	return "Drive tank-style on the xbox controller";
    }
}
