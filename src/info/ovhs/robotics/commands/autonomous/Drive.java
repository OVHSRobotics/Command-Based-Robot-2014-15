package info.ovhs.robotics.commands.autonomous;

import info.ovhs.robotics.Robot;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
	private double initialTime;
	private double time;
	private double power;
	private boolean forward;
	
	/**
	 * Drives the robot forward at full speed for 3 seconds
	 */
	public Drive() {
		this(1, 1.5, true);
	}
	
	/**
	 * Drives the robot forward at full speed for a set amount of time
	 * @param time How long to drive the robot (in seconds)
	 */
	public Drive(double time) {
		this(1, time, true);
	}
	
	/**
	 * Drives the robot forward
	 * @param power What power to drive the robot at
	 * @param time How long to drive the robot (in seconds)
	 */
	public Drive(double power, double time) {
		this(power, time, true);
	}
	
	/**
	 * Drives the robot
	 * @param power What power to drive the robot at
	 * @param time How long to drive the robot (in seconds)
	 * @param forward Whether or not the robot is driving forward
	 */
    public Drive(double power, double time, boolean forward) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.driveTrain);
    	this.time = time;
    	this.power = power;
    	this.forward = forward;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.print(this.toString());
    	this.initialTime = System.nanoTime();
    	if (!forward) {
    		CommandBase.driveTrain.driveStraight(-this.power);
    	} else {
    		CommandBase.driveTrain.driveStraight(this.power);
    	}
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
    	this.end();
    }
    
    public String toString() {
    	if (this.forward) {
    		return "Driving forward for " + this.time + "seconds at " + (this.power * 100) + "percent power";
    	} else {
    		return "Driving backward for " + this.time + "seconds at " + (this.power * 100) + "percent power";
    	}
    }
}
