package info.ovhs.robotics;

import info.ovhs.robotics.commands.*;
import info.ovhs.robotics.commands.autonomous.AutonomousCommand;
import info.ovhs.robotics.commands.conveyer.DropTote;
import info.ovhs.robotics.commands.conveyer.DropTrashCan;
import info.ovhs.robotics.commands.conveyer.Hold;
import info.ovhs.robotics.commands.conveyer.HoldPID;
import info.ovhs.robotics.commands.conveyer.LiftTote;
import info.ovhs.robotics.commands.conveyer.LiftTrashCan;
import info.ovhs.robotics.commands.drive.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private static OI instance;
	
	private static final double [] axisErrors = new double[6];
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    public JoystickButton aButton;
    public JoystickButton bButton;
    public JoystickButton xButton;
    public JoystickButton yButton;
    public JoystickButton backButton;
    public JoystickButton startButton;
    public static Joystick xboxController;
    public static Joystick fireButton;
    
    private OI() {

    	xboxController = new Joystick(Constants.OperatorControlsConstants.CONTROLLER_PORT);
    	fireButton = new Joystick(Constants.OperatorControlsConstants.FIRE_BUTTON_PORT);
    	aButton = new JoystickButton(xboxController, Constants.OperatorControlsConstants.A_BUTTON);
	    aButton.whenPressed(new MecanumDrive());
	    bButton = new JoystickButton(xboxController, Constants.OperatorControlsConstants.B_BUTTON);
	    bButton.whenPressed(new DropTote());
	    xButton = new JoystickButton(xboxController, Constants.OperatorControlsConstants.X_BUTTON);
	    xButton.whenPressed(new DropTrashCan());
	    yButton = new JoystickButton(xboxController, Constants.OperatorControlsConstants.Y_BUTTON);
	    yButton.whenPressed(new HoldPID());
	    backButton = new JoystickButton(xboxController, Constants.OperatorControlsConstants.BACK_BUTTON);
	    backButton.whenPressed(new LiftTote());
	    startButton = new JoystickButton(xboxController, Constants.OperatorControlsConstants.START_BUTTON);
	    startButton.whenPressed(new LiftTrashCan());
    	
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());

        SmartDashboard.putData("MecanumDrive", new MecanumDrive());

        SmartDashboard.putData("TankDrive", new TankDrive());

        SmartDashboard.putData("ArcadeDrive", new ArcadeDrive());
        
        SmartDashboard.putData("Zero Controller", new ZeroControllerInputs());
        
        SmartDashboard.putData("Toggle Drive Style", new ToggleDriveStyle());
       

    }
    
    public static OI getInstance() {
    	if (OI.instance == null) {
    		OI.instance = new OI();
    	}
    	
    	return OI.instance;
    }
    
    /**
     * Returns corrected value for joystick axes
     * @param axis Axis to read
     * @return Corrected value for joystick axis
     */
    public static double getRawAxis( int axis ) {
        return xboxController.getRawAxis(axis) + axisErrors[axis];
    }
    
    public static double getUncorrectedRawAxis (int axis) {
    	return xboxController.getRawAxis(axis);
    }
    
    public static double getLeftStickXAxis() {
    	return getRawAxis(0);
    }
    
    public static double getLeftStickYAxis() {
    	return getRawAxis(1);
    }
    
    public static double getRightStickXAxis() {
    	return getRawAxis(4);
    }
    
    public static double getRightStickYAxis() {
    	return getRawAxis(5);
    }
    
    public static double getLeftStickXAxisSafe() {
    	if (OI.getLeftStickXAxis() <= Constants.MotorConstants.MOTOR_MAX_OUTPUT && OI.getLeftStickXAxis() >= Constants.MotorConstants.MOTOR_MAX_OUTPUT) {
    		return OI.getLeftStickXAxis();
    	} else {
    		return 0;
    	}
    }
    
    public static double getLeftStickYAxisSafe() {
    	if (OI.getLeftStickYAxis() <= Constants.MotorConstants.MOTOR_MAX_OUTPUT && OI.getLeftStickYAxis() >= Constants.MotorConstants.MOTOR_MAX_OUTPUT) {
    		return OI.getLeftStickYAxis();
    	} else {
    		return 0;
    	}
    }
    
    public static double getRightStickXAxisSafe() {
    	if (OI.getRightStickXAxis() <= Constants.MotorConstants.MOTOR_MAX_OUTPUT && OI.getRightStickXAxis() >= Constants.MotorConstants.MOTOR_MAX_OUTPUT) {
    		return OI.getRightStickXAxis();
    	} else {
    		return 0;
    	}
    }
    
    public static double getRightStickYAxisSafe() {
    	if (OI.getRightStickYAxis() <= Constants.MotorConstants.MOTOR_MAX_OUTPUT && OI.getRightStickYAxis() >= Constants.MotorConstants.MOTOR_MAX_OUTPUT) {
    		return OI.getRightStickYAxis();
    	} else {
    		return 0;
    	}
    }
    
//    public static boolean leftTriggerAllWayDown() {
//    	return (Math.abs(xboxController.getRawAxis(3))>= .98);
//    }
//    
//    public static boolean rightTriggerAllWayDown() {
//    	return ((-1 * Math.abs(xboxController.getRawAxis(3)) <= -.98));
//    }

	public Joystick getController() {
		return xboxController;
	}
	

	
    /**
     * Read all of the axis values to determine the error values, and then record the corrections for each of these axes. This
     * method should be called only when the controller axes are physically in their neutral position.
     */
    public void zeroAxisReadings() {
        for (int i = 0; i < axisErrors.length; i++) {
            axisErrors[i] = -xboxController.getRawAxis(i);
        }
    }
    
}

