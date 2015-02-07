package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;


public class RearDropTote extends CommandBase {

	public double initialDistance;
	
    public RearDropTote() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.rearMotorSpool);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.initialDistance = CommandBase.rearMotorSpool.encoder.getDistance();
    	
    	// Turn the motor on
    	double percentSpeed = 100;
    	CommandBase.rearMotorSpool.backward(percentSpeed / 100 * -Constants.Motors.MOTOR_MAX_OUTPUT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// Encoder reaches threshold
    	return CommandBase.rearMotorSpool.encoder.getDistance() <= this.initialDistance - Constants.FieldElements.TOTE_HEIGHT_INCHES;
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
