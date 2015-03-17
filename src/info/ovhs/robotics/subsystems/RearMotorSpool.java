package info.ovhs.robotics.subsystems;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.rearmotorspool.RearHold;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Rear Motor Spool Subsystem
 */
public class RearMotorSpool extends Subsystem {

	/**
	 * Instance of Rear Motor Spool Subsystem
	 */
	protected static RearMotorSpool instance;
	/**
	 * Speed Controller for the Rear Motor Spool
	 */
	protected SpeedController rearSpoolMotor = RobotMap.rearMotorSpoolSpeedController;		
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public boolean switchHasBeenHit = false;
	
	/**
	 * Rear Encoder
	 */
	public Encoder encoder = RobotMap.rearEncoder;
	
	/**
	 * Initial value of rear encoder
	 */
	public double initialEncoderValue;
	
	/**
	 * Gets instance of Rear Motor Spool Subsystem or creates one if one is not present
	 * 
	 * @return Instance of Conveyer Belt Subsystem
	 */
	public static RearMotorSpool getInstance() {
		if (RearMotorSpool.instance == null) {
			RearMotorSpool.instance = new RearMotorSpool();
		}
		
		return RearMotorSpool.instance;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
//    	setDefaultCommand(new RearHoldPID());
    	setDefaultCommand(new RearHold());

    }
    
    /**
     * Moves the Rear Motor Spool forward at max speed
     * <p>
     * This can be called within commands.
     * </p>
     */
    public void forward() {
    	rearSpoolMotor.set(Constants.Motors.MOTOR_MAX_OUTPUT);
    }

    /**
     * Moves the Rear Motor Spool forward at a set speed
     * <p>
     * This can be called within commands.
     * </p>
     * @param speed Speed for Conveyer Belt to move forward at
     */
    public void forward(double speed) {
    	if (speed > 0 && speed <= 1) {
    		rearSpoolMotor.set(speed);
    	}
	}
    
    /**
     * Moves the Rear Motor Spool backwards at max speed
     * <p>
     * This can be called within commands
     * </p>
     */    
    public void backward() {
    	rearSpoolMotor.set(Constants.Motors.MOTOR_MAX_OUTPUT);
    }
    
    /**
     * Moves the Rear Motor Spool backwards at a set speed
     * <p>
     * This can be called within commands.
     * </p>
     * @param speed Speed for Conveyer Belt to move at; can be either positive or negative, will move backwards either way
     */
	public void backward(double speed) {
		if (speed < 0 && speed >= -1) {
			rearSpoolMotor.set(speed);
		}
		if (speed > 0 && speed <= 1) {
			rearSpoolMotor.set(-speed);
		}
	}
	
	/**
	 * Stops the Rear Motor Spool
	 * <p>
	 * This can be called within commands
	 * </p>
	 */
	public void stop() {
		rearSpoolMotor.set(0);
	}
	
	/**
	 * Sets the speed for the Rear Motor Spool
	 * <p>
	 * This can be called within commands.
	 * </p> 
	 * @param speed Speed to set the rear motor spool at
	 */
	public void setSpeed(double speed) {
		if (speed <= 1 && speed >= -1) {
			rearSpoolMotor.set(speed);
		}	
	}
}

