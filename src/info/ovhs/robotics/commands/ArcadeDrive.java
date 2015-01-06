

package info.ovhs.robotics.commands;

import info.ovhs.robotics.OI;
import info.ovhs.robotics.Robot;
import info.ovhs.robotics.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class  ArcadeDrive extends Command {
	
	DriveTrain drivetrain = new DriveTrain();

    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

        
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drivetrain.arcadeDriveController();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (OI.xboxController.getRawButton(7));
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
