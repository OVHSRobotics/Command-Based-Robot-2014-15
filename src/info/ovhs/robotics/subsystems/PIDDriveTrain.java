package info.ovhs.robotics.subsystems;

import info.ovhs.robotics.OI;
import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PIDDriveTrain extends PIDSubsystem {

	private static final double k_p = 1;
	private static final double k_i = 0.1;
	private static final double k_d = 0;
	private static PIDDriveTrain instance;
	
    // Initialize your subsystem here
    public PIDDriveTrain() {
    	super(k_p, k_i, k_d);
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return RobotMap.gyro1.getAngle();
    }
    
    protected void usePIDOutput(double output) {
    	if (OI.getRightStickXAxis() == 0) {
    		RobotMap.drive.mecanumDrive_Cartesian(OI.getLeftStickXAxis(), OI.getLeftStickYAxis(), 0, RobotMap.gyro1.getAngle());
    	} else {
    		RobotMap.drive.mecanumDrive_Cartesian(OI.getLeftStickXAxis(), OI.getLeftStickYAxis(), output, RobotMap.gyro1.getAngle());
    	}
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
    
    /**
	 * Gets instance of PIDDriveTrain Subsystem or creates one if one is not present
	 * 
	 * @return Instance of PIDDriveTrain Subsystem
	 */
	public static PIDDriveTrain getInstance() {
		if (PIDDriveTrain.instance == null) {
			PIDDriveTrain.instance = new PIDDriveTrain();
		}
		
		return PIDDriveTrain.instance;
	}
}
