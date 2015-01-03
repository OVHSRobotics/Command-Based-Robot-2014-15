package info.ovhs.robotics;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Creating the speed controller PWM Mappings
	public static int frontLeftSpeedController; //index 0; PWM Channel 1
    public static int frontRightSpeedController; //index 1; PWM Channel 2
    public static int backLeftSpeedController; //index 2; PWM Channel 3
    public static int backRightSpeedController; //index 3; PWM Channel 4
    public final int[] speedControllerMap = {1,2,3,4};
        
	private static RobotMap instance;
    
	public static RobotMap getInstance()
    {
        if (instance != null)
         return instance;
        instance = new RobotMap();
     return instance;
    }

public RobotMap() {
	frontLeftSpeedController = speedControllerMap[0];
    frontRightSpeedController = speedControllerMap[1];
    backLeftSpeedController = speedControllerMap[2];
    backRightSpeedController = speedControllerMap[3];
}
	
}
