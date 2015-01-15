package info.ovhs.robotics;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import info.ovhs.robotics.Constants;



/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static Constants constant;
	public static SpeedController speedController1;
    public static SpeedController speedController2;
    public static SpeedController speedController3;
    public static SpeedController speedController4;
    public static RobotDrive drive;

    public static void init() {
    	constant = new Constants();
    	
    	boolean leftMotorReversed = constant.LEFT_MOTOR_REVERSED;
    	boolean rightMotorReversed = constant.RIGHT_MOTOR_REVERSED;
    	  
        speedController1 = new Victor(0);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 1", (Victor) speedController1);
        
        speedController2 = new Victor(1);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 2", (Victor) speedController2);
        
        speedController3 = new Victor(2);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 3", (Victor) speedController3);
        
        speedController4 = new Victor(3);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 4", (Victor) speedController4);
        
        drive = new RobotDrive(speedController1, speedController2, speedController3, speedController4);
        
        drive.setSafetyEnabled(false);
        drive.setMaxOutput(1.0);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, leftMotorReversed);   
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, rightMotorReversed);
    }
	
}
