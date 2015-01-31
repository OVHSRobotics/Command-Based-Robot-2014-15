
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
	
    SpeedController motorSpeedController1 = RobotMap.motorSpeedController1;
    SpeedController motorSpeedController2 = RobotMap.motorSpeedController2;
    SpeedController motorSpeedController3 = RobotMap.motorSpeedController3;
    SpeedController motorSpeedController4 = RobotMap.motorSpeedController4;
    RobotDrive drive = RobotMap.drive;
    
    public static DriveTrain getInstance() {
    	if (DriveTrain.instance == null) {
    		DriveTrain.instance = new DriveTrain();
    	}
    	
    	return DriveTrain.instance;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
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
     * 
     * <p>
     * If safety is on, motor output cannot exceed value set in constants
     * </p>
     * 
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void arcadeDriveController() {
    	if (Constants.JOYSTICK_SAFETY) {
    		drive.arcadeDrive(OI.getLeftStickYAxisSafe(), OI.getLeftStickXAxisSafe());
    	} else {
    		drive.arcadeDrive(OI.getLeftStickYAxis(), OI.getLeftStickXAxis());
    	}   
    }

    /**
     * Drive using an xbox controller in tank style, left stick controlling the left side and right stick controlling the right side.
     * 
     * <p>
     * If safety is on, motor output cannot exceed value set in constants
     * </p>
     * 
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void tankDriveController() {
    	if (Constants.JOYSTICK_SAFETY) {
    		drive.tankDrive(OI.getLeftStickXAxisSafe(), OI.getRightStickXAxisSafe());
    	} else {
        drive.tankDrive(OI.getLeftStickYAxis(), OI.getRightStickYAxis());
    	}
    }
    
    /**
     * Drive using an xbox controller in Cartesian Mecanum Style, left stick controlling linear motion and right stick x axis controlling rotation
     * 
     * <p>
     * If safety is on, motor output cannot exceed value set in constants
     * </p>
     * 
     * <p>
     * This can be called within commands.
     * </p> 
     */
    public void mecanumDriveController() {
    	if (Constants.JOYSTICK_SAFETY) {
    		drive.mecanumDrive_Cartesian(OI.getLeftStickXAxisSafe(), OI.getLeftStickYAxisSafe(), OI.getRightStickXAxisSafe(), 0);
    	} else {
    		drive.mecanumDrive_Cartesian(OI.getLeftStickXAxis(), OI.getLeftStickYAxis(), OI.getRightStickXAxis(), 0);
    	}
    }
    
    /**
     * Drive using an xbox controller in Cartesian Mecanum Style using a gyro if present
     *     
     * <p>
     * If safety is on, motor output cannot exceed value set in constants
     * </p>
     * 
     * <p>
     * This can be called within commands.
     * </p>
     *
     * @param gyroAngle The angle that the gyro is currently at
     */
    public void mecanumDriveController(double gyroAngle) {
    	if (Constants.JOYSTICK_SAFETY) {
    		drive.mecanumDrive_Cartesian(OI.getLeftStickXAxisSafe(), OI.getLeftStickYAxisSafe(), OI.getRightStickXAxisSafe(), gyroAngle);
    	} else {
    		drive.mecanumDrive_Cartesian(OI.getLeftStickXAxis(), OI.getLeftStickYAxis(), OI.getRightStickXAxis(), gyroAngle);
    	}   
	}
    
    /**
     * Change the drive style for the default command
     */
    public void changeDefaultDriveStyle() {
    	if (getDefaultCommand() instanceof TankDrive){
    		setDefaultCommand(new MecanumDrive());
    	}
    	
    	if (getDefaultCommand() instanceof ArcadeDrive) {
    		setDefaultCommand(new TankDrive());
    	}
    	
    	if (getDefaultCommand() instanceof MecanumDrive) {
    		setDefaultCommand(new ArcadeDrive());
    	}
    	
    	getDefaultCommand().start();
    }

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	
}

