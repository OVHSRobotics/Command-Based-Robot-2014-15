package info.ovhs.robotics;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
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
		driveSpeedController1 = new Talon(Constants.Ports.PWM.FRONT_LEFT_DRIVE_MOTOR);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 1",
				(Talon) driveSpeedController1);

		driveSpeedController2 = new Talon(Constants.Ports.PWM.REAR_LEFT_DRIVE_MOTOR);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 2",
				(Talon) driveSpeedController2);

		driveSpeedController3 = new Talon(Constants.Ports.PWM.FRONT_RIGHT_DRIVE_MOTOR);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 3",
				(Talon) driveSpeedController3);

		driveSpeedController4 = new Talon(Constants.Ports.PWM.REAR_RIGHT_DRIVE_MOTOR);
		LiveWindow.addActuator("DriveTrain", "Speed Controller 4",
				(Talon) driveSpeedController4);
		
		conveyerBeltSpeedController1 = new Victor(Constants.Ports.PWM.CONVEYER_BELT_MOTOR);

		rearMotorSpoolSpeedController1 = new Victor(Constants.Ports.PWM.REAR_MOTOR);
		
		drive = new RobotDrive(driveSpeedController1, driveSpeedController2,
				driveSpeedController3, driveSpeedController4);

		drive.setSafetyEnabled(false);
		drive.setMaxOutput(Constants.Motors.MOTOR_MAX_OUTPUT);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft,
				Constants.Motors.LEFT_REAR_REVERSED);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearRight,
				Constants.Motors.RIGHT_REAR_REVERSED);
		drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, 
				Constants.Motors.LEFT_FRONT_REVERSED);
		drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, 
				Constants.Motors.RIGHT_FRONT_REVERSED);
	}
	
	public static void createGyro() {
		gyro1 = new Gyro(Constants.Ports.Analog.DRIVE_BASE_GYRO);
		gyro1.setSensitivity(.007);
		LiveWindow.addSensor("Drive Train", "Gyro 1", gyro1);
	}
	
	public static void createEncoders() {
		try {
			conveyerBeltEncoder = new Encoder(Constants.Ports.DIO.CONVEYER_BELT_ENCODER_A, Constants.Ports.DIO.CONVEYER_BELT_ENCODER_B, Constants.ConveyerBelt.Encoder.REVERSED, EncodingType.k4X);
			conveyerBeltEncoder.setDistancePerPulse(Constants.ConveyerBelt.Encoder.DISTANCE_PER_PULSE);
			conveyerBeltEncoder.reset();
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
		try {
			rearEncoder = new Encoder(Constants.Ports.DIO.REAR_MOTOR_ENCODER_A, Constants.Ports.DIO.REAR_MOTOR_ENCODER_B, Constants.RearMotorSpool.Encoder.REVERSED, EncodingType.k4X);
			rearEncoder.setDistancePerPulse(Constants.RearMotorSpool.Encoder.DISTANCE_PER_PULSE);
			rearEncoder.reset();
		}
		catch (Exception exception){
			System.out.println(exception);
		}
	}
	public static void createLimitSwitches() {
		limitSwitch1 = new DigitalInput(Constants.Ports.Analog.LIMIT_SWITCH);
	}
}
