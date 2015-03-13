package info.ovhs.robotics.subsystems;

import info.ovhs.robotics.OI;
import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.activeinput.StopActiveInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ActiveInput extends Subsystem {
    
	protected static ActiveInput instance;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	protected SpeedController activeInputSpeedControllerLeft = RobotMap.activeInputSpeedControllerLeft;
	protected SpeedController activeInputSpeedControllerRight = RobotMap.activeInputSpeedControllerRight;

	/**
	 * Gets instance of Active Input Subsystem or creates one if one is not present
	 * 
	 * @return Instance of Active Input Subsystem
	 */
	public static ActiveInput getInstance() {
		if (ActiveInput.instance == null) {
			ActiveInput.instance = new ActiveInput();
		}
		
		return ActiveInput.instance;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StopActiveInput());
    }
    
    public void moveWithJoystickInput() {
    	activeInputSpeedControllerLeft.set(OI.getXboxRightStickYAxis());
    	activeInputSpeedControllerRight.set(OI.getXboxRightStickYAxis());
    }
    
    public void suckInTote(double moveSpeed) {
    	activeInputSpeedControllerLeft.set(-moveSpeed);
    	activeInputSpeedControllerRight.set(-moveSpeed);
    }
    
    public void releaseTote(double moveSpeed) {
    	activeInputSpeedControllerLeft.set(moveSpeed);
    	activeInputSpeedControllerRight.set(moveSpeed);
    }
    
    public void stop() {
    	activeInputSpeedControllerLeft.set(0);
    	activeInputSpeedControllerRight.set(0);
    }
}

