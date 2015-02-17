package info.ovhs.robotics;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Gyro;
import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static SpeedController frontLeftDriveSpeedController;
	public static SpeedController rearLeftDriveSpeedController;
	public static SpeedController frontRightDriveSpeedController;
	public static SpeedController rearRightDriveSpeedController;
	public static SpeedController conveyerBeltSpeedController;
	public static SpeedController rearMotorSpoolSpeedController;
	public static SpeedController testSpeedControllerLeft;
	public static SpeedController testSpeedControllerRight;
	public static RobotDrive drive;
	public static Encoder conveyerBeltEncoder;
	public static Encoder rearEncoder;
	public static Gyro robotGyro;
	public static AnalogInput robotGyroTempSensor;
	public static DigitalInput robotLimitSwitch;
	public static DigitalInput autonomousSwitch1;
	public static DigitalInput autonomousSwitch2;
	public static PowerDistributionPanel PDP;

	public static void init() {
		
		Robot.print("Begin RobotMap Init");

		RobotMap.setupDriveMotors();
		
		RobotMap.createGyro();
		
		RobotMap.createGyroTempSensor();
		
		RobotMap.createEncoders();
		
		RobotMap.createLimitSwitches();
		
		RobotMap.createAutonomousSwitches();
		
		RobotMap.createPDPObject();
		
		Robot.print("End RobotMap Init");
	}
	
	public static void setupDriveMotors() {
		Robot.print("Begin setupDriveMotors");
		
		frontLeftDriveSpeedController = new Talon(Constants.Ports.PWM.FRONT_LEFT_DRIVE_MOTOR);
		LiveWindow.addActuator("DriveTrain", "Front Left Drive Speed Controller",
				(Talon) frontLeftDriveSpeedController);

		rearLeftDriveSpeedController = new Talon(Constants.Ports.PWM.REAR_LEFT_DRIVE_MOTOR);
		LiveWindow.addActuator("DriveTrain", "Back Left Drive Speed Controller",
				(Talon) rearLeftDriveSpeedController);

		frontRightDriveSpeedController = new Talon(Constants.Ports.PWM.FRONT_RIGHT_DRIVE_MOTOR);
		LiveWindow.addActuator("DriveTrain", "Front Right Drive Speed Controller",
				(Talon) frontRightDriveSpeedController);

		rearRightDriveSpeedController = new Talon(Constants.Ports.PWM.REAR_RIGHT_DRIVE_MOTOR);
		LiveWindow.addActuator("DriveTrain", "Back Right Drive Speed Controller",
				(Talon) rearRightDriveSpeedController);
		
		conveyerBeltSpeedController = new Victor(Constants.Ports.PWM.CONVEYER_BELT_MOTOR);
		LiveWindow.addActuator("Conveyer Belt", "Conveyer Belt Motor", (Victor) conveyerBeltSpeedController);

		rearMotorSpoolSpeedController = new Victor(Constants.Ports.PWM.REAR_MOTOR);
		LiveWindow.addActuator("Rear Motor Spool", "Rear Motor Spool Motor", (Victor) rearMotorSpoolSpeedController);
		
		testSpeedControllerLeft = new Talon(Constants.Ports.PWM.TEST_MOTOR_LEFT);
		LiveWindow.addActuator("Test", "Test Motor Left", (Talon) testSpeedControllerLeft);
		
		testSpeedControllerRight = new Victor(Constants.Ports.PWM.TEST_MOTOR_RIGHT);
		LiveWindow.addActuator("Test", "Test Motor Right", (Victor) testSpeedControllerRight);

		
		drive = new RobotDrive(frontLeftDriveSpeedController, rearLeftDriveSpeedController,
				frontRightDriveSpeedController, rearRightDriveSpeedController);

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
		
		Robot.print("End setupDriveMotors");
	}
	
	public static void createGyro() {
		
		Robot.print("Begin createGyro");
		
		robotGyro = new Gyro(Constants.Ports.Analog.DRIVE_BASE_GYRO);
		robotGyro.setSensitivity(.007);
		LiveWindow.addSensor("Drive Train", "Gyro 1", robotGyro);
		
		Robot.print("End createGyro");
	}
	
	public static void createGyroTempSensor() {
		Robot.print("Begin createGyroTempSensor");
		
		robotGyroTempSensor = new AnalogInput(Constants.Ports.Analog.GYRO_TEMP_SENSOR);
		LiveWindow.addSensor("DriveTrain", "Temperature Sensor", robotGyroTempSensor);
		
		Robot.print("End createGyroTempSensor");
	}
	
	public static void createEncoders() {
		
		Robot.print("Begin createEncoders");
		
		try {
			conveyerBeltEncoder = new Encoder(Constants.Ports.DIO.CONVEYER_BELT_ENCODER_A, Constants.Ports.DIO.CONVEYER_BELT_ENCODER_B, Constants.ConveyerBelt.Encoder.REVERSED, EncodingType.k4X);
			conveyerBeltEncoder.setDistancePerPulse(Constants.ConveyerBelt.Encoder.DISTANCE_PER_PULSE);
			conveyerBeltEncoder.reset();
			LiveWindow.addSensor("Conveyer Belt", "Conveyer Belt Encoder", conveyerBeltEncoder);
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
		try {
			rearEncoder = new Encoder(Constants.Ports.DIO.REAR_MOTOR_ENCODER_A, Constants.Ports.DIO.REAR_MOTOR_ENCODER_B, Constants.RearMotorSpool.Encoder.REVERSED, EncodingType.k4X);
			rearEncoder.setDistancePerPulse(Constants.RearMotorSpool.Encoder.DISTANCE_PER_PULSE);
			rearEncoder.reset();
			LiveWindow.addSensor("Rear Motor Spool", "Rear Encoder", rearEncoder);
		}
		catch (Exception exception){
			System.out.println(exception);
		}
		
		Robot.print("End createEncoders");
	}
	
	public static void createLimitSwitches() {
		
		Robot.print("Begin createLimitSwitches");
		
		robotLimitSwitch = new DigitalInput(Constants.Ports.DIO.LIMIT_SWITCH);
		LiveWindow.addActuator("DriveTrain", "Limit Switch", robotLimitSwitch);
		
		Robot.print("End createLimitSwitches");
	}
	
	public static void createAutonomousSwitches() {
		
		Robot.print("Begin createAutonomousSwitches");
		
		autonomousSwitch1 = new DigitalInput(Constants.Ports.DIO.AUTONOMOUS_SWITCH_A);
		LiveWindow.addSensor("Autonomous", "Autonomous Switch 1", autonomousSwitch1);
		
		autonomousSwitch2 = new DigitalInput(Constants.Ports.DIO.AUTONOMOUS_SWITCH_B);
		LiveWindow.addSensor("Autonomous", "Autonomous Switch 2", autonomousSwitch2);
		
		Robot.print("End createAutonomousSwitches");
	}
	
	public static void setInitialConveyerEncoderDistance() {
		Robot.print("Begin setInitialConveyerEncoderDistance");
		
    	CommandBase.conveyerBelt.initialEncoderValue = CommandBase.conveyerBelt.encoder.getDistance();
    	
    	Robot.print("End setInitialConveyerEncoderDistance");
    }
    
    public static void setInitialRearEncoderDistance() {
		Robot.print("Begin setInitialRearEncoderDistance");

    	CommandBase.rearMotorSpool.initialEncoderValue = CommandBase.rearMotorSpool.encoder.getDistance();
    	
		Robot.print("End setInitialRearEncoderDistance");
	}
    
    public static void createPDPObject() {
    	Robot.print("Begin CreatePDPObject");
    	
    	PDP = new PowerDistributionPanel();
    	
    	Robot.print("End CreatePDPObject");
    }
}
