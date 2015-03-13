package info.ovhs.robotics.commands.autonomous;

import info.ovhs.robotics.Robot;
import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Strafes the robot either left or right for a certain amount of time and power
 */
public class Strafe extends Command {
	private double initialTime;
	private double time;
	private double powerFrontLeft;
	private double powerFrontRight;
	private double powerRearRight;
	private double powerRearLeft;
	private boolean left;

	/**
	 * Strafes the robot right at full speed for 1.5 seconds
	 */
	public Strafe() {
		this(1, 1.5, true);
	}
	
	/**
	 * Strafes the robot right at full speed for a set amount of time
	 * @param time How long to strafe the robot (in seconds)
	 */
	public Strafe(double time) {
		this(1, time, true);
	}
	
	/**
	 * Strafes the robot right
	 * @param power What power to strafe the robot at
	 * @param time How long to strafe the robot (in seconds)
	 */
	public Strafe(double power, double time) {
		this(power, power, power, power, time, true);
	}
	
	public Strafe(double power, double time, boolean left) {
		this(power, power, power, power, time, left);
	}
	
	/**
	 * Strafes the robot
	 * @param power What power to strafe the robot at
	 * @param time How long to strafe the robot (in seconds)
	 * @param right Whether or not the robot is strafing right
	 */
    public Strafe(double powerFrontLeft, double powerFrontRight, double powerRearLeft, double powerRearRight, double time, boolean left) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.driveTrain);
    	this.time = time;
    	this.powerFrontLeft = powerFrontLeft;
    	this.powerFrontRight = powerFrontRight;
    	this.powerRearLeft = powerRearLeft;
    	this.powerRearRight = powerRearRight;
    	this.left = left;
    }

    /**
     *  Called just before this Command runs the first time
     */
    protected void initialize() {
    	Robot.print(this.toString());
    	this.initialTime = System.nanoTime();
    	if (!this.left) {
//    		RobotMap.frontLeftDriveSpeedController.set(this.powerFrontLeft);
//    		RobotMap.frontRightDriveSpeedController.set(-this.powerFrontRight);
//    		RobotMap.rearRightDriveSpeedController.set(this.powerRearRight);
//    		RobotMap.rearLeftDriveSpeedController.set(-this.powerRearLeft);
    		CommandBase.driveTrain.setFrontLeftMotor(this.powerFrontLeft);
    		CommandBase.driveTrain.setFrontRightMotor(-this.powerFrontRight);
    		CommandBase.driveTrain.setRearRightMotor(this.powerRearRight);
    		CommandBase.driveTrain.setRearLeftMotor(-this.powerRearLeft);
    	} else {
//    		RobotMap.frontLeftDriveSpeedController.set(-this.powerFrontLeft);
//    		RobotMap.frontRightDriveSpeedController.set(this.powerFrontRight);
//    		RobotMap.rearRightDriveSpeedController.set(-this.powerRearRight);
//    		RobotMap.rearLeftDriveSpeedController.set(this.powerRearLeft);    	
    		CommandBase.driveTrain.setFrontLeftMotor(-this.powerFrontLeft);
    		CommandBase.driveTrain.setFrontRightMotor(this.powerFrontRight);
    		CommandBase.driveTrain.setRearRightMotor(-this.powerRearRight);
    		CommandBase.driveTrain.setRearLeftMotor(this.powerRearLeft);
    	}
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    }

    /**
     *  Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return System.nanoTime() >= this.initialTime + this.time * Math.pow(10, 9);
    }

    /**
     *  Called once after isFinished returns true
     */
    protected void end() {
    	CommandBase.driveTrain.stopAllMotors();
    }

    /**
     *  Called when another command which requires one or more of the same subsystems is scheduled to run
     */
    protected void interrupted() {
    	this.end();
    }
    
    /**
     * String representation of command
     */
    public String toString() {
    	if (!this.left) {
    		return "Strafing right for " + this.time + "seconds";
    	} else {
    		return "Strafing left for " + this.time + "seconds";
    	}    
	}
}
