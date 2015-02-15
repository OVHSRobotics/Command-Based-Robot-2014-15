package info.ovhs.robotics;

public final class Constants {
	
	public static final int DRIVE_TRAIN_GEAR_RATIO = 1;	
	
	public static final int ROBOT_WEIGHT = 120;
	
	public static final class OperatorControls {
		
		public static final int MAX_JOYSTICK_AXIS = 1;
		
		public static final int MIN_JOYSTICK_AXIS = -1;
		
		public static final int MIN_TRIGGER_AXIS = 0;
		
		public static final int MAX_TRIGGER_AXIS = 1;
		
		public static final class Controller {
			
			public static final class Axes {
			
				public static final int LEFT_STICK_X = 0;
				
				public static final int LEFT_STICK_Y = 1;
				
				public static final int LEFT_TRIGGER = 2;
				
				public static final int RIGHT_TRIGGER = 3;
				
				public static final int RIGHT_STICK_X = 4;
				
				public static final int RIGHT_STICK_Y = 5;
			}
			
			public static final class Buttons {
			
				public static final int A = 1;
				
				public static final int B = 2;
				
				public static final int X = 3;
				
				public static final int Y = 4;
				
				public static final int LEFT_BUMPER = 5;
				
				public static final int RIGHT_BUMPER = 6;
				
				public static final int BACK = 7;
				
				public static final int START = 8;
				
				public static final int LEFT_STICK = 9;
				
				public static final int RIGHT_STICK = 10;
			}
			
			public static final class POV {
				
				public static final int CENTER = -1;
			
				public static final int UP = 0;
				
				public static final double UP_THRESHOLD = 0;
				
				public static final int RIGHT = 90;
				
				public static final int RIGHT_THRESHOLD = 0;
				
				public static final int DOWN = 180;
				
				public static final int DOWN_THRESHOLD = 0;
				
				public static final int LEFT = 270;

				public static final int LEFT_THRESHOLD = 0;
			}
			
			public static final class Deadzone {
				
				public static final double LEFT_X_CENTER = -0.043;
				
				public static final double LEFT_Y_CENTER = -0.039;
				
				public static final double RIGHT_X_CENTER = -0.125;
				
				public static final double RIGHT_Y_CENTER = 0;
				
				public static final double LEFT_X = 0.10;
				
				public static final double LEFT_Y = 0.07;
				
				public static final double RIGHT_X = 0.260;
				
				public static final double RIGHT_Y = 0.225;
			}	
		}
	}
	
	public static final class FieldElements {
		
		public static final double TRASHCAN_HEIGHT_INCHES = 28.8;
	
		public static final double TRASHCAN_WEIGHT_POUNDS = 8.65;
	
		public static final double TOTE_HEIGHT_INCHES = 12.1;
	
		public static final double TOTE_WEIGHT_POUNDS = 7.8;
	}

	public static final class ConveyerBelt {
		
		public static final double SPROCKET_DIAMETER_INCHES = 2.5;

		public static final double SPROCKET_RADIUS_INCHES = Constants.ConveyerBelt.SPROCKET_DIAMETER_INCHES/2;
	
		public static final double SPROCKET_CIRCUMFRENCE_INCHES = Constants.ConveyerBelt.SPROCKET_DIAMETER_INCHES * Math.PI;	
		
		public static final double SPROCKET_TO_GEARBOX_GEAR_RATIO = 19/16;
		
		public static final double MANUAL_MOVE_SPEED = .5;
		
		public static final class Encoder {
		
			public static final double GEARBOX_PULSES_PER_REVOLUTION = 497;
			
			public static final double GEARBOX_REVOLUTIONS_PER_PULSE = 1/Constants.ConveyerBelt.Encoder.GEARBOX_PULSES_PER_REVOLUTION;
		
			public static final double DISTANCE_PER_PULSE = Constants.ConveyerBelt.SPROCKET_CIRCUMFRENCE_INCHES * Constants.ConveyerBelt.SPROCKET_TO_GEARBOX_GEAR_RATIO * Constants.ConveyerBelt.Encoder.GEARBOX_REVOLUTIONS_PER_PULSE;
			
			public static final boolean REVERSED = false;
			
			public static final double RESET_THRESHOLD = 3;
		}
	}
	
	public static final class Motors {
		
			public static final double MOTOR_MAX_OUTPUT = 1.0;
			
			public static final boolean LEFT_REAR_REVERSED = false;
			
			public static final boolean RIGHT_REAR_REVERSED = true;
			
			public static final boolean LEFT_FRONT_REVERSED = true;
			
			public static final boolean RIGHT_FRONT_REVERSED = false;
	}
	
	public static final class Ports {
		
		public static final class Joystick {
			
			public static final int CONTROLLER = 0;

			public static final int FIRE_BUTTON = 1;
		}
		
		public static final class PWM {
			
			public static final int CONVEYER_BELT_MOTOR = 4;
			
			public static final int FRONT_LEFT_DRIVE_MOTOR = 0;
		
			public static final int REAR_LEFT_DRIVE_MOTOR = 1;
			
			public static final int FRONT_RIGHT_DRIVE_MOTOR = 2;
			
			public static final int REAR_RIGHT_DRIVE_MOTOR = 3;
			
			public static final int REAR_MOTOR = 5;
		}
		
		public static final class DIO {
			
			public static final int CONVEYER_BELT_ENCODER_A = 8;
		
			public static final int CONVEYER_BELT_ENCODER_B = 7;
			
			public static final int REAR_MOTOR_ENCODER_A = 6;
		
			public static final int REAR_MOTOR_ENCODER_B = 5;
			
			public static final int AUTONOMOUS_SWITCH_A = 0;
			
			public static final int AUTONOMOUS_SWITCH_B = 1;
			
			public static final int LIMIT_SWITCH = 2;
		}
		
		public static final class Analog {
			
			public static final int DRIVE_BASE_GYRO = 0;
			
			public static final int GYRO_TEMP_SENSOR = 1;
			
		}
	}

	public static final class RearMotorSpool {
		
		public static final double SPROCKET_DIAMETER_FEET = 1;
	
		public static final double SPROCKET_RADIUS_FEET = Constants.RearMotorSpool.SPROCKET_DIAMETER_FEET/2;
	
		public static final double SPROCKET_CIRCUMFRENCE_FEET = Constants.RearMotorSpool.SPROCKET_DIAMETER_FEET * Math.PI;
		
		public static final double SPROCKET_TO_GEARBOX_GEAR_RATIO = 36/16;
		
		public static final double MANUAL_MOVE_SPEED = .5;
		
		public static final class Encoder {
			
			public static final double DISTANCE_PER_PULSE = Constants.RearMotorSpool.SPROCKET_CIRCUMFRENCE_FEET * Constants.RearMotorSpool.SPROCKET_TO_GEARBOX_GEAR_RATIO * Constants.RearMotorSpool.Encoder.GEARBOX_REVOLUTIONS_PER_PULSE;
						
			public static final double GEARBOX_PULSES_PER_REVOLUTION = 497;
			
			public static final double GEARBOX_REVOLUTIONS_PER_PULSE = 1/Constants.RearMotorSpool.Encoder.GEARBOX_PULSES_PER_REVOLUTION;
					
			public static final boolean REVERSED = true;
			
			public static final double RESET_THRESHOLD = 3;
		}	
	}
}
