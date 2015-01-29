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

	public static SpeedController motorSpeedController1;
	public static SpeedController motorSpeedController2;
	public static SpeedController motorSpeedController3;
	public static SpeedController motorSpeedController4;
	public static SpeedController conveyerBeltSpeedController1;
	public static RobotDrive drive;
	public static Encoder encoder;
	public static Gyro gyro1;

	public static void init() {

		RobotMap.setupDriveMotors();
		
		RobotMap.createGyro();
		
		RobotMap.createEncoder();
	}
	
	public static void setupDriveMotors() {
		motorSpeedController1 = new Victor(Constants.PortConstants.FRONT_LEFT_DRIVE_MOTOR_PORT);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 1",
				(Victor) motorSpeedController1);

		motorSpeedController2 = new Victor(Constants.PortConstants.REAR_LEFT_DRIVE_MOTOR_PORT);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 2",
				(Victor) motorSpeedController2);

		motorSpeedController3 = new Victor(Constants.PortConstants.FRONT_RIGHT_DRIVE_MOTOR_PORT);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 3",
				(Victor) motorSpeedController3);

		motorSpeedController4 = new Victor(Constants.PortConstants.REAR_RIGHT_DRIVE_MOTOR_PORT);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 4",
				(Victor) motorSpeedController4);
		
		conveyerBeltSpeedController1 = new Victor(Constants.PortConstants.CONVEYER_BELT_MOTOR_PORT);

		drive = new RobotDrive(motorSpeedController1, motorSpeedController2,
				motorSpeedController3, motorSpeedController4);

		drive.setSafetyEnabled(false);
		drive.setMaxOutput(Constants.MotorConstants.MOTOR_MAX_OUTPUT);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft,
				Constants.MotorConstants.LEFT_REAR_MOTOR_REVERSED);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearRight,
				Constants.MotorConstants.RIGHT_REAR_MOTOR_REVERSED);
		drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, 
				Constants.MotorConstants.LEFT_FRONT_MOTOR_REVERSED);
		drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, 
				Constants.MotorConstants.RIGHT_FRONT_MOTOR_REVERSED);
	}
	
	public static void createGyro() {
		gyro1 = new Gyro(Constants.PortConstants.DRIVE_BASE_GYRO_PORT);
		gyro1.setSensitivity(.007);
		LiveWindow.addSensor("Drive Train", "Gyro 1", gyro1);
	}
	
	public static void createEncoder() {
		try {
			encoder = new Encoder(Constants.EncoderConstants.ENCODER_PORT_A, Constants.EncoderConstants.ENCODER_PORT_B, Constants.EncoderConstants.ENCODER_REVERSED, EncodingType.k4X);
			encoder.setDistancePerPulse(Constants.EncoderConstants.DISTANCE_PER_PULSE);
			encoder.reset();
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
	}
}
