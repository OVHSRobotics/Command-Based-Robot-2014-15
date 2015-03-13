package info.ovhs.robotics.commands.autonomous;

import info.ovhs.robotics.Robot;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives a certain distance, for a certain time, 
 * a certain direction, depending on the parameters and the constructor
 */
public class Drive extends Command {
	private double initialTime;
	private double time;
	private double powerFrontRight, powerFrontLeft, powerRearRight, powerRearLeft;
	private boolean forward;
	
	/**
	 * Drives the robot forward at full speed for 3 seconds
	 */
	public Drive() {
		this(1, 1, 1, 1, 1.5, true);
	}
	
	/**
	 * Drives the robot forward at full speed for a set amount of time
	 * @param time How long to drive the robot (in seconds)
	 */
	public Drive(double time) {
		this(1, 1, 1, 1, time, true);
	}
	
	/**
	 * Drives the robot forward
	 * @param power What power to drive the robot at
	 * @param time How long to drive the robot (in seconds)
	 */
	public Drive(double power, double time) {
		this(power, power, power, power, time, true);
	}
	
	public Drive(double power, double time, boolean forward) {
		this(power, power, power, power, time, forward);
	}
	
	/**
	 * Drives the robot
	 * @param power What power to drive the robot at
	 * @param time How long to drive the robot (in seconds)
	 * @param forward Whether or not the robot is driving forward
	 */
    public Drive(double powerFrontLeft, double powerRearLeft, double powerFrontRight, double powerRearRight, double time, boolean forward) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.driveTrain);
    	this.time = time;
    	this.powerFrontLeft = powerFrontLeft;
    	this.powerRearLeft = powerRearLeft;
    	this.powerFrontRight = powerFrontRight;
    	this.powerRearRight = powerRearRight;
    	this.forward = forward;
    }

    /**
     *  Called just before this Command runs the first time
     */
    protected void initialize() {
    	Robot.print(this.toString());
    	this.initialTime = System.nanoTime();
    	if (!forward) {
//    		RobotMap.frontLeftDriveSpeedController.set(-this.powerFrontLeft);
//    		RobotMap.frontRightDriveSpeedController.set(-this.powerFrontRight);
//    		RobotMap.rearRightDriveSpeedController.set(-this.powerRearRight);
//    		RobotMap.rearLeftDriveSpeedController.set(-this.powerRearLeft);
    		CommandBase.driveTrain.setFrontLeftMotor(-this.powerFrontLeft);
    		CommandBase.driveTrain.setFrontRightMotor(-this.powerFrontRight);
    		CommandBase.driveTrain.setRearRightMotor(-this.powerRearRight);
    		CommandBase.driveTrain.setRearLeftMotor(-this.powerRearLeft);
    	} else {
//    		RobotMap.frontLeftDriveSpeedController.set(this.powerFrontLeft);
//    		RobotMap.frontRightDriveSpeedController.set(this.powerFrontRight);
//    		RobotMap.rearRightDriveSpeedController.set(this.powerRearRight);
//    		RobotMap.rearLeftDriveSpeedController.set(this.powerRearLeft);
    		CommandBase.driveTrain.setFrontLeftMotor(this.powerFrontLeft);
    		CommandBase.driveTrain.setFrontRightMotor(this.powerFrontRight);
    		CommandBase.driveTrain.setRearRightMotor(this.powerRearRight);
    		CommandBase.driveTrain.setRearLeftMotor(this.powerRearLeft);
    	}
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    	Robot.print(this.toString());
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
     * The String representation of the command
     */
    public String toString() {
    	if (this.forward) {
    		return "Driving forward for " + this.time + "seconds";
    	} else {
    		return "Driving backward for " + this.time + "seconds";
    	}
    }
}
