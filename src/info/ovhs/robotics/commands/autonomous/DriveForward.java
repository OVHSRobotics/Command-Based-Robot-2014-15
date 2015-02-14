package info.ovhs.robotics.commands.autonomous;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {
	private double initialTime;
	private double time;
	private double power;

    public DriveForward(double power, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.driveTrain);
    	this.time = time;
    	this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialTime = System.nanoTime();
    	CommandBase.driveTrain.driveStraight(this.power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.nanoTime() >= this.initialTime + this.time * Math.pow(10, 9);
    }

    // Called once after isFinished returns true
    protected void end() {
    	CommandBase.driveTrain.stopAllMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
