package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
<<<<<<< HEAD
public class RearLiftTote extends CommandBase {
=======
public class RearLiftTote extends Command {
	
	public double initialDistance;
>>>>>>> origin/master

    public RearLiftTote() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.rearMotorSpool);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.initialDistance = CommandBase.rearMotorSpool.encoder.getDistance();
    	
    	// Turn on motor
    	double percentSpeed = 100;
    	CommandBase.rearMotorSpool.forward(percentSpeed / 100 * Constants.MotorConstants.MOTOR_MAX_OUTPUT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Do nothing
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Command is done when encoder reaches certain amount of ticks
    	return CommandBase.rearMotorSpool.encoder.getDistance() <= this.initialDistance - Constants.TOTE_HEIGHT_INCHES;
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Turn motor off
    	CommandBase.rearMotorSpool.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// Turn motor off
    	CommandBase.rearMotorSpool.stop();
    }
}