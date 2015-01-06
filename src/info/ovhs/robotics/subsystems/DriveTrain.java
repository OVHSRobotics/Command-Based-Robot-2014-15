
package info.ovhs.robotics.subsystems;

import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {
    SpeedController speedController1 = RobotMap.speedController1;
    SpeedController speedController2 = RobotMap.speedController2;
    RobotDrive unnamedDrive = RobotMap.unnamedDrive;

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

