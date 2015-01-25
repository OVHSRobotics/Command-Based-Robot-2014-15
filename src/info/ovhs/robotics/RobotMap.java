package info.ovhs.robotics;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Gyro;
import info.ovhs.robotics.Constants;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static SpeedController speedController1;
	public static SpeedController speedController2;
	public static SpeedController speedController3;
	public static SpeedController speedController4;
	public static SpeedController conveyerBeltSpeedController;
	public static RobotDrive drive;
	public static Encoder encoder;

	public static Gyro gyro1;

	public static void init() {

		speedController1 = new Victor(Constants.PortConstants.FRONT_LEFT_DRIVE_MOTOR_PORT);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 1",
				(Victor) speedController1);

		speedController2 = new Victor(Constants.PortConstants.REAR_LEFT_DRIVE_MOTOR_PORT);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 2",
				(Victor) speedController2);

		speedController3 = new Victor(Constants.PortConstants.FRONT_RIGHT_DRIVE_MOTOR_PORT);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 3",
				(Victor) speedController3);

		speedController4 = new Victor(Constants.PortConstants.REAR_RIGHT_DRIVE_MOTOR_PORT);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 4",
				(Victor) speedController4);
		
		conveyerBeltSpeedController = new Victor(Constants.PortConstants.CONVEYER_BELT_MOTOR_PORT);

		drive = new RobotDrive(speedController1, speedController2,
				speedController3, speedController4);

		drive.setSafetyEnabled(false);
		drive.setMaxOutput(Constants.MotorConstants.MOTOR_MAX_OUTPUT);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft,
				Constants.MotorConstants.LEFT_MOTOR_REVERSED);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearRight,
				Constants.MotorConstants.RIGHT_MOTOR_REVERSED);

		gyro1 = new Gyro(Constants.PortConstants.DRIVE_BASE_GYRO_PORT);
		gyro1.setSensitivity(.007);
		LiveWindow.addSensor("Drive Train", "Gyro 1", gyro1);
		
		encoder = new Encoder(Constants.EncoderConstants.ENCODER_PORT_A, Constants.EncoderConstants.ENCODER_PORT_B, Constants.EncoderConstants.ENCODER_REVERSED, EncodingType.k4X);
	}

}
