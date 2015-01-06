
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
    SpeedController speedController1 = RobotMap.speedController1;
    SpeedController speedController2 = RobotMap.speedController2;
    //SpeedController speedController3 = RobotMap.speedController3;
    //SpeedController speedController4 = RobotMap.speedController4;
    RobotDrive drive = RobotMap.drive;

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void mecanumDriveController() {
    	drive.mecanumDrive_Cartesian(OI.getLeftStickXAxis(), OI.getLeftStickYAxis(), OI.getRightStickXAxis(), 0);
    }
    public void tankDriveController() {
    	drive.tankDrive(OI.getLeftStickYAxis(), OI.getRightStickYAxis());
    }
    public void arcadeDriveController() {
    	drive.arcadeDrive(OI.xboxController);
    }

    public void initDefaultCommand() {
	
        // Set the default command for a subsystem here.
        setDefaultCommand(new MecanumDrive());
    }
}

