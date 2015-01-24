
package info.ovhs.robotics.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import info.ovhs.robotics.RobotMap;

/**
 *
 */
public class ConveyerBelt extends Subsystem {

	SpeedController motor = RobotMap.conveyerBeltSpeedController;
		
	
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	protected static ConveyerBelt instance;
		 = RobotMap.encoder
	
	public static ConveyerBelt getInstance() {
		if (ConveyerBelt.instance == null) {
			ConveyerBelt.instance = new ConveyerBelt();
			
		}
		
		return ConveyerBelt.instance;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void forward(double speed) {
    	if (speed > 0 && speed <= 1) {
    		motor.set(speed);
    	}
	}
	
	public void backward(double speed) {
		if (speed < 0 && speed >= -1) {
			motor.set(speed);
		}
	}
	
	public void stop() {
		motor.set(0);
	}
	
	public void setSpeed(double speed) {
		if (speed <= 1 && speed >= -1) {
			motor.set(speed);
		}
	}
}

