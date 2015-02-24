package info.ovhs.robotics;

import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	/**
	 * Speed Controller for the Front Left Drive Motor
	 */
	public static SpeedController frontLeftDriveSpeedController;
	/**
	 * Speed Controller for the Back Left Drive Motor
	 */
	public static SpeedController rearLeftDriveSpeedController;
	/**
	 * Speed Controller for the Front Right Drive Motor
	 */
	public static SpeedController frontRightDriveSpeedController;
	/**
	 * Speed Controller for the Back Right Drive Motor
	 */
	public static SpeedController rearRightDriveSpeedController;
	/**
	 * Speed Controller for the Conveyer Belt Motor
	 */
	public static SpeedController conveyerBeltSpeedController;
	/**
	 * Speed Controller for the Rear Motor Spool
	 */
	public static SpeedController rearMotorSpoolSpeedController;
	/**
	 * Speed Controller for the Active Input Motor on the left
	 */
	public static SpeedController activeInputSpeedControllerLeft;
	/**
	 * Speed Controller for the Active Input Motor on the right
	 */
	public static SpeedController activeInputSpeedControllerRight;
	/**
	 * RobotDrive Drive Motor Configuration
	 */
	public static RobotDrive drive;
	/**
	 * Encoder on the Conveyer Belt
	 */
	public static Encoder conveyerBeltEncoder;
	/**
	 * Encoder on the Rear Motor Spool
	 */
	public static Encoder rearEncoder;
	/**
	 * Gyroscope on the robot
	 * 
	 * <p>
	 * Not currently being used, power output lowered when gyro is plugged in
	 * </p>
	 */
	public static Gyro robotGyro;
	/**
	 * Temperature sensor integrated into the robot gyroscope
	 * 
	 * <p>
	 * Not currently being used, power output lowered when gyro is plugged in
	 * </p>
	 */
	public static AnalogInput robotGyroTempSensor;
	/**
	 * Limit switch on the robot in order to tell when the arms are all the way down
	 */
	public static DigitalInput robotLimitSwitch;
	/**
	 * Switch to switch between different autonomous modes
	 */
	public static DigitalInput autonomousSwitch1;
	/**
	 * Switch to switch between different autonomous modes
	 */
	public static DigitalInput autonomousSwitch2;
	/**
	 * Power Distribution Panel for determining current draw from the PDP
	 */
	public static PowerDistributionPanel PDP;

	/**
	 * Initializes the RobotMap as well as creates all of the objects for the sensors and actuators on the robot
	 */
	public static void init() {
		
		Robot.print("Begin RobotMap Init");

		RobotMap.setupDrive();
		
		RobotMap.setupSubsystemSpeedControllers();
		
		RobotMap.createGyro();
		
		RobotMap.createGyroTempSensor();
		
		RobotMap.createEncoders();
		
		RobotMap.createLimitSwitches();
		
		RobotMap.createAutonomousSwitches();
		
		RobotMap.createPDPObject();
		
		Robot.print("End RobotMap Init");
	}
	
	/**
	 * Sets up the configuration for the driving systems on the robot
	 */
	public static void setupDrive() {
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
	
	/**
	 * Sets up the speed controllers that are for anything other than driving
	 */
	public static void setupSubsystemSpeedControllers() {
		Robot.print("Begin setupSubsystemSpeedControllers");
		
		conveyerBeltSpeedController = new Victor(Constants.Ports.PWM.CONVEYER_BELT_MOTOR);
		LiveWindow.addActuator("Conveyer Belt", "Conveyer Belt Motor", (Victor) conveyerBeltSpeedController);

		rearMotorSpoolSpeedController = new Talon(Constants.Ports.PWM.REAR_MOTOR);
		LiveWindow.addActuator("Rear Motor Spool", "Rear Motor Spool Motor", (Talon) rearMotorSpoolSpeedController);
		
		activeInputSpeedControllerLeft = new Talon(Constants.Ports.PWM.TEST_MOTOR_LEFT);
		LiveWindow.addActuator("Test", "Test Motor Left", (Talon) activeInputSpeedControllerLeft);
		
		activeInputSpeedControllerRight = new Victor(Constants.Ports.PWM.TEST_MOTOR_RIGHT);
		LiveWindow.addActuator("Test", "Test Motor Right", (Victor) activeInputSpeedControllerRight);		
		
		Robot.print("End setupSubsystemSpeedControllers");
	}
	
	/**
	 * Creates the Gyro object on the robot
	 */
	public static void createGyro() {
		
		Robot.print("Begin createGyro");
		
		robotGyro = new Gyro(Constants.Ports.Analog.DRIVE_BASE_GYRO);
		robotGyro.setSensitivity(.007);
		LiveWindow.addSensor("Drive Train", "Gyro 1", robotGyro);
		
		Robot.print("End createGyro");
	}
	
	/**
	 * Creates the Gyro Temp Sensor object on the robot
	 */
	public static void createGyroTempSensor() {
		Robot.print("Begin createGyroTempSensor");
		
		robotGyroTempSensor = new AnalogInput(Constants.Ports.Analog.GYRO_TEMP_SENSOR);
		LiveWindow.addSensor("DriveTrain", "Temperature Sensor", robotGyroTempSensor);
		
		Robot.print("End createGyroTempSensor");
	}
	
	/**
	 * Creates the objects for all encoders on the robot
	 */
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
	
	/**
	 * Creates the objects for all limit switches on the robot
	 */
	public static void createLimitSwitches() {
		
		Robot.print("Begin createLimitSwitches");
		
		robotLimitSwitch = new DigitalInput(Constants.Ports.DIO.LIMIT_SWITCH);
		LiveWindow.addActuator("DriveTrain", "Limit Switch", robotLimitSwitch);
		
		Robot.print("End createLimitSwitches");
	}
	
	/**
	 * Creates the objects for the autonomous switches on the robot
	 */
	public static void createAutonomousSwitches() {
		
		Robot.print("Begin createAutonomousSwitches");
		
		autonomousSwitch1 = new DigitalInput(Constants.Ports.DIO.AUTONOMOUS_SWITCH_A);
		LiveWindow.addSensor("Autonomous", "Autonomous Switch 1", autonomousSwitch1);
		
		autonomousSwitch2 = new DigitalInput(Constants.Ports.DIO.AUTONOMOUS_SWITCH_B);
		LiveWindow.addSensor("Autonomous", "Autonomous Switch 2", autonomousSwitch2);
		
		Robot.print("End createAutonomousSwitches");
	}
	
	/**
	 * Sets the initial distance for the conveyer encoder in order to use the reset command
	 */
	public static void setInitialConveyerEncoderDistance() {
		Robot.print("Begin setInitialConveyerEncoderDistance");
		
    	CommandBase.conveyerBelt.initialEncoderValue = CommandBase.conveyerBelt.encoder.getDistance();
    	
    	Robot.print("End setInitialConveyerEncoderDistance");
    }
    
	/**
	 * Sets the initial distance for the rear encoder in order to use the reset command
	 */
    public static void setInitialRearEncoderDistance() {
		Robot.print("Begin setInitialRearEncoderDistance");

    	CommandBase.rearMotorSpool.initialEncoderValue = CommandBase.rearMotorSpool.encoder.getDistance();
    	
		Robot.print("End setInitialRearEncoderDistance");
	}
    
    /**
     * Creates the object for the PDP in order to get current draws from the PDP on the robot
     */
    public static void createPDPObject() {
    	Robot.print("Begin CreatePDPObject");
    	
    	PDP = new PowerDistributionPanel();
    	
    	Robot.print("End CreatePDPObject");
    }
    
    /**
     * Resets both of the encoders and then sets a new initial distance
     */
    public static void resetEncoders() {
    	Robot.print("Resetting Encoders");
    	
    	CommandBase.conveyerBelt.encoder.reset();
    	CommandBase.rearMotorSpool.encoder.reset();
    	
    	RobotMap.setInitialConveyerEncoderDistance();
    	RobotMap.setInitialRearEncoderDistance();
    	
    	Robot.print("Finished Resetting Encoders");
    }
    
}
