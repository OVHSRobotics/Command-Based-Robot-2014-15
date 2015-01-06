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
	public static SpeedController speedController1;
    public static SpeedController speedController2;
    public static RobotDrive unnamedDrive;

    public static void init() {
        speedController1 = new Victor(0);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 1", (Victor) speedController1);
        
        speedController2 = new Victor(1);
        LiveWindow.addActuator("DriveTrain", "Speed Controller 2", (Victor) speedController2);
        
        unnamedDrive = new RobotDrive(speedController1, speedController2);
        
        unnamedDrive.setSafetyEnabled(true);
        unnamedDrive.setExpiration(0.1);
        unnamedDrive.setSensitivity(0.5);
        unnamedDrive.setMaxOutput(1.0);
        unnamedDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);        
    }
	
}
