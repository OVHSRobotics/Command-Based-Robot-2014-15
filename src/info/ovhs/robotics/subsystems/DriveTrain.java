package info.ovhs.robotics.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;
import info.ovhs.robotics.OI;
import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.*;


/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
RobotDrive unnamedDrive = new RobotDrive(RobotMap.frontLeftSpeedController,RobotMap.backLeftSpeedController,RobotMap.frontRightSpeedController,RobotMap.backRightSpeedController);
            
    
    public static DriveTrain instance;
    
    public static DriveTrain getInstance() {
      if (instance == null) {
          instance = new DriveTrain();
      }
      return instance;
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        this.setDefaultCommand(new ExampleCommand());
    }
    public void invertGearbox(){
        // Invert the two front motors because of the mirroring of the gearboxes
        unnamedDrive.setInvertedMotor(edu.wpi.first.wpilibj.RobotDrive.MotorType.kRearRight, true);
        unnamedDrive.setInvertedMotor(edu.wpi.first.wpilibj.RobotDrive.MotorType.kFrontRight, true);        
    }
    public void arcadeDrive() {
    	unnamedDrive.arcadeDrive(OI.xboxController);
    }
    public void tankDrive() {
    	unnamedDrive.tankDrive(OI.getLeftStickY(), OI.getRightStickY());
    }
}

