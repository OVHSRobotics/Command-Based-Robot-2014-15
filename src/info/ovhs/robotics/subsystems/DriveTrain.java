
package info.ovhs.robotics.subsystems;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.drive.ArcadeDrive;
import info.ovhs.robotics.commands.drive.MecanumDrive;
import info.ovhs.robotics.commands.drive.TankDrive;
import info.ovhs.robotics.OI;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/** 
 * Drive Train Subsystem on the robot
 */
public class DriveTrain extends Subsystem {
	
	/**
	 * Instance of the Drive Train Subsystem
	 */
	protected static DriveTrain instance;
	
	/**
	 * Speed Controller for the front left drive motor
	 */
    SpeedController frontLeftDriveMotorSpeedController = RobotMap.frontLeftDriveSpeedController;
    /**
     * Speed Controller for the rear left drive motor
     */
    SpeedController rearLeftDriveMotorSpeedController = RobotMap.rearLeftDriveSpeedController;
    /**
     * Speed Controller for the front right drive motor
     */
    SpeedController frontRightDriveMotorSpeedController = RobotMap.frontRightDriveSpeedController;
    /**
     * Speed Controller for the rear right drive motor
     */
    SpeedController rearRightDriveMotorSpeedController = RobotMap.rearRightDriveSpeedController;
    /**
     * RobotDrive Drive Motor Configuration
     */
    RobotDrive drive = RobotMap.drive;
    
    /**
     * Gets the instance of the DriveTrain Subsystem and creates one if there isn't one already
     * @return instance of DriveTrain Subsystem
     */
    public static DriveTrain getInstance() {
    	if (DriveTrain.instance == null) {
    		DriveTrain.instance = new DriveTrain();
    	}
    	
    	return DriveTrain.instance;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MecanumDrive());
    	setDefaultCommand(new MecanumDrive());
    }
    
    /**
     * Drive forward or backward.
     * <p>
     * This can be called within commands.
     * </p>
     * 
     * @param speedFactor the fraction of full-speed to drive, ranging from -1.0 (backward at full power) to 1.0 (forward at full
     *        power)
     */
    public void driveStraight( double speedFactor ) {
    	drive.mecanumDrive_Cartesian(0, -speedFactor, 0, 0);
    }
    
    public void setFrontRightMotor(double speed) {
    	this.frontRightDriveMotorSpeedController.set(speed);
    }
    
    public void setFrontLeftMotor(double speed) {
    	this.frontLeftDriveMotorSpeedController.set(speed);
    }
    
    public void setRearRightMotor(double speed) {
    	this.rearRightDriveMotorSpeedController.set(speed);
    }
    
    public void setRearLeftMotor(double speed) {
    	this.rearLeftDriveMotorSpeedController.set(speed);
    }
    
    /**
     * Strafes the robot to the left
     * @param speedFactor How fast to strafe
     */
    public void strafeLeft( double speedFactor) {
    	drive.mecanumDrive_Cartesian(speedFactor, 0, 0, 0);
    }
    
    /**
     * Strafes the robot to the right
     * @param speedFactor How fast to strafe
     */
    public void strafeRight( double speedFactor) {
    	drive.mecanumDrive_Cartesian(-speedFactor, 0, 0, 0);
    }
    
    /**
     * Moves the robot at 45 degrees relative at a specified speed
     * 
     * @param speedFactor Speed to move robot at
     */
    public void moveAt45DegreeAngle( double speedFactor) {
    	drive.mecanumDrive_Cartesian(-speedFactor, -speedFactor, 0, 0);
    }
    
    /**
     * Moves the robot at 135 degrees relative at a specified speed
     * 
     * @param speedFactor Speed to move robot at
     */    
    public void moveAt135DegreeAngle( double speedFactor) {
    	drive.mecanumDrive_Cartesian(speedFactor, -speedFactor, 0, 0);
    }
    
    /**
     * Moves the robot at 225 degrees relative at a specified speed
     * 
     * @param speedFactor Speed to move robot at
     */
    public void moveAt225DegreeAngle( double speedFactor) {
    	drive.mecanumDrive_Cartesian(speedFactor, speedFactor, 0, 0);
    }
    
    /**
     * Moves the robot at 315 degrees relative at a specified speed
     * 
     * @param speedFactor Speed to move robot at
     */
    public void moveAt315DegreeAngle( double speedFactor) {
    	drive.mecanumDrive_Cartesian(-speedFactor, speedFactor, 0, 0);
    }
    
    /**
     * Moves the robot at a specified relative angle at a specified speed
     * @param speedFactor Speed for robot to move at
     * @param angle Relative angle for robot to move at
     */
    public void moveAtAngle( double speedFactor, double angle) {
    	drive.mecanumDrive_Polar(-speedFactor, angle, 0);
    }

    /**
     * Stop driving.
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void stopAllMotors() {
        RobotMap.frontLeftDriveSpeedController.set(0);
        RobotMap.frontRightDriveSpeedController.set(0);
        RobotMap.rearRightDriveSpeedController.set(0);
        RobotMap.rearLeftDriveSpeedController.set(0);
    }
    
    /**
     * Drive using the left joystick in arcade-style.
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void arcadeDriveController() {
        drive.arcadeDrive(OI.getXboxLeftStickYAxis(), OI.getXboxLeftStickXAxis());
    }

    /**
     * Drive using an xbox controller in tank style, left stick controlling the left side and right stick controlling the right side
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void tankDriveController() {
        drive.tankDrive(OI.getXboxLeftStickYAxis(), OI.getXboxRightStickYAxis());
    }
    
    /**
     * Drive using an xbox controller in Cartesian Mecanum Style, left stick controlling linear motion and right stick x axis controlling rotation
     * <p>
     * This can be called within commands.
     * </p> 
     */
    public void mecanumDriveController() {
    	this.mecanumDriveController(0);
    }
    
    /**
     * Drive using an xbox controller in Cartesian Mecanum Style using a gyro if present
     * <p>
     * This can be called within commands.
     * </p>
     * 
     * @param gyroAngle The angle that the gyro is currently at
     */
    public void mecanumDriveController(double gyroAngle) {
    	double rotationRate = 0;
    	if (Math.abs(OI.getXboxRightStickXAxis()) <= Constants.OperatorControls.Controller.Xbox.Deadzone.RIGHT_X) {
//    		rotationRate = -RobotMap.robotGyro.getRate();
    		rotationRate = OI.getXboxRightStickXAxis();
    	}
    	else {
    		rotationRate = OI.getXboxRightStickXAxis();
    	}
    	rotationRate = OI.getXboxRightStickXAxis();
    	
    	drive.mecanumDrive_Cartesian(OI.getXboxLeftStickXAxis(), OI.getXboxLeftStickYAxis(), rotationRate, gyroAngle);
    	//drive.mecanumDrive_Cartesian(OI.getLeftStickXAxis(), OI.getLeftStickYAxis(), OI.getRightStickXAxis(), 0);
    }
    
    /**
     * Change the drive style for the default command
     */
    public void changeDefaultDriveStyle() {
    	if (getDefaultCommand() instanceof TankDrive){
    		setDefaultCommand(new MecanumDrive());
    	}
    	
    	else if (getDefaultCommand() instanceof ArcadeDrive) {
    		setDefaultCommand(new TankDrive());
    	}
    	
    	else if (getDefaultCommand() instanceof MecanumDrive) {
    		setDefaultCommand(new ArcadeDrive());
    	}
    	
    	getDefaultCommand().start();
    }

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	
}

