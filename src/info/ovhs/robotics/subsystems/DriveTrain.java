
package info.ovhs.robotics.subsystems;

import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.*;
import info.ovhs.robotics.OI;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {
    Victor speedController1 = RobotMap.speedController1;
    Victor speedController2 = RobotMap.speedController2;
    //SpeedController speedController3 = RobotMap.speedController3;
    //SpeedController speedController4 = RobotMap.speedController4;
    RobotDrive drive = RobotMap.drive;
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
    	drive.mecanumDrive_Cartesian(OI.getLeftStickXAxis(), OI.getLeftStickYAxis(), OI.getRightStickXAxis(), 0);
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
    }
    

    


}

