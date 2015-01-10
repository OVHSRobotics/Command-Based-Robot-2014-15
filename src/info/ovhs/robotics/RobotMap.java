package info.ovhs.robotics;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.*;



/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
@SuppressWarnings("unused")
public class RobotMap {
	
	public static final class DriveMotors {
		public static final boolean LEFT_REVERSED = true;
		public static final boolean RIGHT_REVERSED = false;
	}
	
	public static SpeedController speedController1;
    public static SpeedController speedController2;
    //public static SpeedController speedController3;
    //public static SpeedController speedController4;
    public static RobotDrive drive;

    public static void init() {
        speedController1 = new Victor(0);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 1", (Victor) speedController1);
        
        speedController2 = new Victor(1);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 2", (Victor) speedController2);
        
        drive = new RobotDrive(speedController1, speedController2);
        
        drive.setSafetyEnabled(true);
        drive.setMaxOutput(1.0);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, RobotMap.DriveMotors.LEFT_REVERSED);   
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, RobotMap.DriveMotors.RIGHT_REVERSED);
    }
	
}
