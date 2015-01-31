package info.ovhs.robotics;

public final class Constants {
	
	public static final int DRIVE_TRAIN_GEAR_RATIO = 1;	
	
	public static final int ROBOT_WEIGHT = 120;
	
	public static final class FieldElementsConstants {
		
		public static final double TRASHCAN_HEIGHT_INCHES = 28.8;
	
		public static final double TRASHCAN_WEIGHT_POUNDS = 8.65;
	
		public static final double TOTE_HEIGHT_INCHES = 12.1;
	
		public static final double TOTE_WEIGHT_POUNDS = 7.8;
	}

	public static final class ConveyerBeltEncoderConstants {
		
		public static final int SPROCKET_DIAMETER_FEET = 2; //Arbitrary number, change if necessary

		public static final int SPROCKET_RADIUS_FEET = 1; //Arbitrary number, change if necessary	
	
		public static final double SPROCKET_CIRCUMFRENCE_FEET = Constants.RearEncoderConstants.SPROCKET_DIAMETER_FEET * Math.PI;	
		
		public static final double GEARBOX_PULSES_PER_REVOLUTION = 497;
		
		public static final double GEARBOX_REVOLUTIONS_PER_PULSE = 1/Constants.RearEncoderConstants.GEARBOX_PULSES_PER_REVOLUTION;
		
		public static final double SPROCKET_TO_GEARBOX_GEAR_RATIO = 10;
	
		public static final double DISTANCE_PER_PULSE = Constants.ConveyerBeltEncoderConstants.SPROCKET_CIRCUMFRENCE_FEET * Constants.ConveyerBeltEncoderConstants.SPROCKET_TO_GEARBOX_GEAR_RATIO * Constants.ConveyerBeltEncoderConstants.GEARBOX_REVOLUTIONS_PER_PULSE;
	
		public static final double ENCODER_TICKS_TO_FEET_RATIO = 1000 / 1;
		
		public static final int ENCODER_PORT_A = 8;
		
		public static final int ENCODER_PORT_B = 7;
		
		public static final boolean ENCODER_REVERSED = false;
	}
	
	public static final class MotorConstants {
		
			public static final double MOTOR_MAX_OUTPUT = 1.0;
			
			public static final boolean LEFT_REAR_MOTOR_REVERSED = true;
			
			public static final boolean RIGHT_REAR_MOTOR_REVERSED = false;
			
			public static final boolean LEFT_FRONT_MOTOR_REVERSED = true;
			
			public static final boolean RIGHT_FRONT_MOTOR_REVERSED = false;
	}
	
	public static final class PortConstants {
		
		public static final int CONVEYER_BELT_MOTOR_PORT = 4;

		public static final int FRONT_LEFT_DRIVE_MOTOR_PORT = 0;
		
		public static final int REAR_LEFT_DRIVE_MOTOR_PORT = 1;
		
		public static final int FRONT_RIGHT_DRIVE_MOTOR_PORT = 2;
		
		public static final int REAR_RIGHT_DRIVE_MOTOR_PORT = 3;
		
		public static final int DRIVE_BASE_GYRO_PORT = 0;
		
		public static final int REAR_MOTOR_PORT = 5;
	}

	public static final class OperatorControlsConstants {
				
		public static final int CONTROLLER_PORT = 0;

		public static final int FIRE_BUTTON_PORT = 1;
		
		public static final int LEFT_STICK_X_AXIS = 0;
		
		public static final int LEFT_STICK_Y_AXIS = 1;
		
		public static final int LEFT_TRIGGER_AXIS = 2;
		
		public static final int RIGHT_TRIGGER_AXIS = 3;
		
		public static final int RIGHT_STICK_X_AXIS = 4;
		
		public static final int RIGHT_STICK_Y_AXIS = 5;
		
		public static final int A_BUTTON = 1;
		
		public static final int B_BUTTON = 2;
		
		public static final int X_BUTTON = 3;
		
		public static final int Y_BUTTON = 4;
		
		public static final int LEFT_BUMPER = 5;
		
		public static final int RIGHT_BUMPER = 6;
		
		public static final int BACK_BUTTON = 7;
		
		public static final int START_BUTTON = 8;
		
		public static final int PUSH_LEFT_STICK_BUTTON = 9;
		
		public static final int PUSH_RIGHT_STICK_BUTTON = 10;
		
		public static final int POV_CENTER = -1;
		
		public static final int POV_UP = 0;
		
		public static final int POV_RIGHT = 90;
		
		public static final int POV_DOWN = 180;
		
		public static final int POV_LEFT = 270;		
		
		public static final int MAX_JOYSTICK_AXIS = 1;
		
		public static final int MIN_JOYSTICK_AXIS = -1;
		
		public static final int MIN_TRIGGER_AXIS = 0;
		
		public static final int MAX_TRIGGER_AXIS = 1;
	}

	public static final class RearEncoderConstants {
		
		public static final int SPROCKET_DIAMETER_FEET = 2; //Arbitrary number, change if necessary
	
		public static final int SPROCKET_RADIUS_FEET = 1; //Arbitrary number, change if necessary	
	
		public static final double SPROCKET_CIRCUMFRENCE_FEET = Constants.RearEncoderConstants.SPROCKET_DIAMETER_FEET * Math.PI;	
		
		public static final double GEARBOX_PULSES_PER_REVOLUTION = 497;
		
		public static final double GEARBOX_REVOLUTIONS_PER_PULSE = 1/Constants.RearEncoderConstants.GEARBOX_PULSES_PER_REVOLUTION;
		
		public static final double SPROCKET_TO_GEARBOX_GEAR_RATIO = 10;
	
		public static final double DISTANCE_PER_PULSE = Constants.RearEncoderConstants.SPROCKET_CIRCUMFRENCE_FEET * Constants.RearEncoderConstants.SPROCKET_TO_GEARBOX_GEAR_RATIO * Constants.RearEncoderConstants.GEARBOX_REVOLUTIONS_PER_PULSE;
	
		public static final double ENCODER_TICKS_TO_FEET_RATIO = 1000 / 1;
		
		public static final int ENCODER_PORT_A = 8;
		
		public static final int ENCODER_PORT_B = 7;
		
		public static final boolean ENCODER_REVERSED = true;
	}
}
