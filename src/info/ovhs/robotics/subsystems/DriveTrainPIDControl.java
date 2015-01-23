package info.ovhs.robotics.subsystems;

import info.ovhs.robotics.RobotMap;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class DriveTrainPIDControl extends PIDSubsystem {

	Gyro gyro1 = RobotMap.gyro1;
	SpeedController speedController1 = RobotMap.speedController1;
	SpeedController speedController2 = RobotMap.speedController2;
	SpeedController speedController3 = RobotMap.speedController3;
	SpeedController speedController4 = RobotMap.speedController4;
	
    // Initialize your subsystem here
    public DriveTrainPIDControl() {
    	super("DriveTrainPIDControl", 10.0, .1, 1.0E-4);
    	setAbsoluteTolerance(0.2);
    	getPIDController().setContinuous(false);
    	LiveWindow.addActuator("Drive Train PID Control", "PID Controller", getPIDController());
    	
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
    	return gyro1.pidGet();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	
    	speedController1.pidWrite(output);
    }
}
