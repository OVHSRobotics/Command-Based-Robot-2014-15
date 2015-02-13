
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
 *
 */
public class DriveTrain extends Subsystem {
	
	protected static DriveTrain instance;
	
    SpeedController motorSpeedController1 = RobotMap.frontLeftDriveSpeedController;
    SpeedController motorSpeedController2 = RobotMap.rearLeftDriveSpeedController;
    SpeedController motorSpeedController3 = RobotMap.frontRightDriveSpeedController;
    SpeedController motorSpeedController4 = RobotMap.rearRightDriveSpeedController;
    RobotDrive drive = RobotMap.drive;
    
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
        drive.tankDrive(speedFactor, speedFactor);
    }

    /**
     * Stop driving.
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void stopAllMotors() {
        drive.stopMotor();
    }
    
    /**
     * Drive using the left joystick in arcade-style.
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void arcadeDriveController() {
        drive.arcadeDrive(OI.getLeftStickYAxis(), OI.getLeftStickXAxis());
    }

    /**
     * Drive using an xbox controller in tank style, left stick controlling the left side and right stick controlling the right side
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void tankDriveController() {
        drive.tankDrive(OI.getLeftStickYAxis(), OI.getRightStickYAxis());
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
    	if (Math.abs(OI.getRightStickXAxis()) <= Constants.OperatorControls.Controller.Deadzone.RIGHT_X) {
    		rotationRate = -RobotMap.robotGyro.getRate();
    	}
    	else {
    		rotationRate = OI.getRightStickXAxis();
    	}
    	
    	drive.mecanumDrive_Cartesian(OI.getLeftStickXAxis(), OI.getLeftStickYAxis(), rotationRate, gyroAngle);
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

