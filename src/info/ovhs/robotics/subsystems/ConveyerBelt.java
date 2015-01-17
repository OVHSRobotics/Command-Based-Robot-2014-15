
package info.ovhs.robotics.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import info.ovhs.robotics.RobotMap;

/**
 *
 */
public class ConveyerBelt extends Subsystem {
	
	SpeedController motor = RobotMap.conveyerBeltSpeedController;
	
	public void initDefaultCommand() {
	}
		
	public void forward() {
		motor.set(1);
	}
	
	public void backward() {
		motor.set(-1);
	}
	
	public void stop() {
		motor.set(0);
	}
}

