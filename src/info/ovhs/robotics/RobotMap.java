package info.ovhs.robotics;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
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

	public static SpeedController driveSpeedController1;
	public static SpeedController driveSpeedController2;
	public static SpeedController driveSpeedController3;
	public static SpeedController driveSpeedController4;
	public static SpeedController conveyerBeltSpeedController1;
	public static SpeedController rearMotorSpoolSpeedController1;
	public static RobotDrive drive;
	public static Encoder conveyerBeltEncoder;
	public static Encoder rearEncoder;
	public static Gyro gyro1;
	public static DigitalInput limitSwitch1;

	public static void init() {

		RobotMap.setupDriveMotors();
		
		RobotMap.createGyro();
		
		RobotMap.createEncoders();
		
		RobotMap.createLimitSwitches();
	}
	
	public static void setupDriveMotors() {
		driveSpeedController1 = new Victor(Constants.PortConstants.FRONT_LEFT_DRIVE_MOTOR_PORT);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 1",
				(Victor) driveSpeedController1);

		driveSpeedController2 = new Victor(Constants.PortConstants.REAR_LEFT_DRIVE_MOTOR_PORT);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 2",
				(Victor) driveSpeedController2);

		driveSpeedController3 = new Victor(Constants.PortConstants.FRONT_RIGHT_DRIVE_MOTOR_PORT);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 3",
				(Victor) driveSpeedController3);

		driveSpeedController4 = new Victor(Constants.PortConstants.REAR_RIGHT_DRIVE_MOTOR_PORT);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 4",
				(Victor) driveSpeedController4);
		
		conveyerBeltSpeedController1 = new Victor(Constants.PortConstants.CONVEYER_BELT_MOTOR_PORT);

		rearMotorSpoolSpeedController1 = new Victor(Constants.PortConstants.REAR_MOTOR_PORT);
		
		drive = new RobotDrive(driveSpeedController1, driveSpeedController2,
				driveSpeedController3, driveSpeedController4);

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
	
	public static void createEncoders() {
		try {
			conveyerBeltEncoder = new Encoder(Constants.ConveyerBeltEncoderConstants.ENCODER_PORT_A, Constants.ConveyerBeltEncoderConstants.ENCODER_PORT_B, Constants.ConveyerBeltEncoderConstants.ENCODER_REVERSED, EncodingType.k4X);
			conveyerBeltEncoder.setDistancePerPulse(Constants.ConveyerBeltEncoderConstants.DISTANCE_PER_PULSE);
			conveyerBeltEncoder.reset();
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
		try {
			rearEncoder = new Encoder(Constants.RearEncoderConstants.ENCODER_PORT_A, Constants.RearEncoderConstants.ENCODER_PORT_B, Constants.RearEncoderConstants.ENCODER_REVERSED, EncodingType.k4X);
			rearEncoder.setDistancePerPulse(Constants.RearEncoderConstants.DISTANCE_PER_PULSE);
			rearEncoder.reset();
		}
		catch (Exception exception){
			System.out.println(exception);
		}
	}
	public static void createLimitSwitches() {
		limitSwitch1 = new DigitalInput(Constants.PortConstants.LIMIT_SWITCH_PORT);
	}
}
