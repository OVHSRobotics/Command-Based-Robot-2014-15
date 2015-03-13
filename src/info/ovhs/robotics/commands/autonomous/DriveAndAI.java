package info.ovhs.robotics.commands.autonomous;

import info.ovhs.robotics.Robot;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives a certain distance, for a certain time, 
 * a certain direction, depending on the parameters and the constructor
 */
public class DriveAndAI extends Command {
	private double initialTime;
	private double time;
	private double powerFrontRight, powerFrontLeft, powerRearRight, powerRearLeft;
	private boolean forward;
	private boolean AiIn;
	private double AiSpeed;
	
	/**
	 * Drives the robot forward at full speed for 3 seconds
	 */
	public DriveAndAI() {
		this(1, 1, 1, 1, 1.5, true, 1, true);
	}
	
	/**
	 * Drives the robot forward at full speed for a set amount of time
	 * @param time How long to drive the robot (in seconds)
	 */
	public DriveAndAI(double time) {
		this(1, 1, 1, 1, time, true, 1, true);
	}
	
	/**
	 * Drives the robot forward
	 * @param power What power to drive the robot at
	 * @param time How long to drive the robot (in seconds)
	 */
	public DriveAndAI(double power, double time) {
		this(power, power, power, power, time, true, 1,  true);
	}
	
	public DriveAndAI(double power, double time, boolean forward) {
		this(power, power, power, power, time, forward, 1, true);
	}
	
	public DriveAndAI(double power, double time, boolean forward, boolean AiIn) {
		this(power, power, power, power, time, forward, 1, AiIn);
	}
	
	public DriveAndAI(double power, double time, boolean forward, double AiSpeed, boolean AiIn) {
		this(power, power, power, power, time, forward, 1, AiIn);
	}
	
	/**
	 * Drives the robot
	 * @param power What power to drive the robot at
	 * @param time How long to drive the robot (in seconds)
	 * @param forward Whether or not the robot is driving forward
	 */
    public DriveAndAI(double powerFrontLeft, double powerRearLeft, double powerFrontRight, double powerRearRight, double time, boolean forward, double AiSpeed, boolean AiIn) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.driveTrain);
    	requires(CommandBase.activeInput);
    	this.time = time;
    	this.powerFrontLeft = powerFrontLeft;
    	this.powerRearLeft = powerRearLeft;
    	this.powerFrontRight = powerFrontRight;
    	this.powerRearRight = powerRearRight;
    	this.forward = forward;
    	this.AiIn = AiIn;
    	this.AiSpeed = AiSpeed;
    }

    /**
     *  Called just before this Command runs the first time
     */
    protected void initialize() {
    	Robot.print(this.toString());
    	this.initialTime = System.nanoTime();
    	if (!forward) {
    		if (this.AiIn) {
    			//    		RobotMap.frontLeftDriveSpeedController.set(-this.powerFrontLeft);
    			//    		RobotMap.frontRightDriveSpeedController.set(-this.powerFrontRight);
    			//    		RobotMap.rearRightDriveSpeedController.set(-this.powerRearRight);
    			//    		RobotMap.rearLeftDriveSpeedController.set(-this.powerRearLeft);
    			CommandBase.driveTrain.setFrontLeftMotor(-this.powerFrontLeft);
    			CommandBase.driveTrain.setFrontRightMotor(-this.powerFrontRight);
    			CommandBase.driveTrain.setRearRightMotor(-this.powerRearRight);
    			CommandBase.driveTrain.setRearLeftMotor(-this.powerRearLeft);
    			CommandBase.activeInput.suckInTote(this.AiSpeed);
    		} else {
    			CommandBase.driveTrain.setFrontLeftMotor(-this.powerFrontLeft);
        		CommandBase.driveTrain.setFrontRightMotor(-this.powerFrontRight);
        		CommandBase.driveTrain.setRearRightMotor(-this.powerRearRight);
        		CommandBase.driveTrain.setRearLeftMotor(-this.powerRearLeft);
        		CommandBase.activeInput.releaseTote(this.AiSpeed);
    		}
    	} else {
    		if (this.AiIn) {
    			//    		RobotMap.frontLeftDriveSpeedController.set(-this.powerFrontLeft);
    			//    		RobotMap.frontRightDriveSpeedController.set(-this.powerFrontRight);
    			//    		RobotMap.rearRightDriveSpeedController.set(-this.powerRearRight);
    			//    		RobotMap.rearLeftDriveSpeedController.set(-this.powerRearLeft);
    			CommandBase.driveTrain.setFrontLeftMotor(-this.powerFrontLeft);
    			CommandBase.driveTrain.setFrontRightMotor(-this.powerFrontRight);
    			CommandBase.driveTrain.setRearRightMotor(-this.powerRearRight);
    			CommandBase.driveTrain.setRearLeftMotor(-this.powerRearLeft);
    			CommandBase.activeInput.suckInTote(this.AiSpeed);
    		} else {
    			CommandBase.driveTrain.setFrontLeftMotor(-this.powerFrontLeft);
        		CommandBase.driveTrain.setFrontRightMotor(-this.powerFrontRight);
        		CommandBase.driveTrain.setRearRightMotor(-this.powerRearRight);
        		CommandBase.driveTrain.setRearLeftMotor(-this.powerRearLeft);
        		CommandBase.activeInput.releaseTote(this.AiSpeed);
    		}
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
    		if (this.AiIn) {
    			return "Driving forwards for " + this.time + " seconds with the Auto Intake moving in";
    		} else {
    			return "Driving forwards for " + this.time + " seconds with the Auto Intake moving out";    			
    		}
    	} else {
    		if (this.AiIn) {
    			return "Driving backwards for " + this.time + " seconds with the Auto Intake moving in";
    		} else {
    			return "Driving backwards for " + this.time + " seconds with the Auto Intake moving out";    			
    		}    	}
    }
}
