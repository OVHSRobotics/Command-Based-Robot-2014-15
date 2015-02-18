package info.ovhs.robotics.commands;

import info.ovhs.robotics.Robot;
import info.ovhs.robotics.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetEncoders extends Command {
	
	private long initialTime;
	private int secondsWaited = 0;
	private int secondsToWait = 2;

    public ResetEncoders() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (CommandBase.conveyerBelt);
    	requires (CommandBase.rearMotorSpool);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.initialTime = System.nanoTime();
    	Robot.print("DO NOT MOVE CONVEYER OR REAR MOTOR SPOOL; ENCODERS RESETTING");
    	Robot.print("Resetting in " + this.secondsToWait + " seconds");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (secondsToWait - (int)((System.nanoTime() - this.initialTime) / Math.pow(10, 9)) > this.secondsWaited) {
    		Robot.print("Resetting in " + (secondsToWait - this.secondsWaited - 1) + " seconds");
    		this.secondsWaited = secondsToWait - (int)((System.nanoTime() - this.initialTime) / Math.pow(10, 9));
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.nanoTime() - this.initialTime > this.secondsToWait * Math.pow(10, 9);
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.resetEncoders();
    	Robot.print("Finished resetting encoders");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.print("Resetting encoders was interrupted");
    }
}
