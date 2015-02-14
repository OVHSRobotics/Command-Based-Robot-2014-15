package info.ovhs.robotics;

import info.ovhs.robotics.commands.autonomous.AutonomousCommand;
import info.ovhs.robotics.commands.conveyer.*;
import info.ovhs.robotics.commands.drive.*;
import info.ovhs.robotics.commands.rearmotorspool.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private static OI instance;
	
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
    public JoystickButton leftBumper;
    public JoystickButton rightBumper;
    public JoystickTriggerAsButton leftTrigger;
    public JoystickTriggerAsButton rightTrigger;
    public static Joystick xboxController;
    public static Joystick fireButton;
    
    private OI() {

    	xboxController = new Joystick(Constants.Ports.Joystick.CONTROLLER);
    	fireButton = new Joystick(Constants.Ports.Joystick.FIRE_BUTTON);
    	aButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.A);
	    aButton.whenPressed(new LiftTote());
	    bButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.B);
	    bButton.whenPressed(new DropTote());
	    xButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.X);
	    xButton.whenPressed(new RearLiftTrashCan());
	    yButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.Y);
	    yButton.whenPressed(new RearDropTrashCan());
	    backButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.BACK);
	    backButton.whenPressed(new ResetConveyer());
	    startButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.START);
	    startButton.whileHeld(new ResetRear());
	    leftBumper = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.LEFT_BUMPER);
	    leftBumper.whileHeld(new RearMove(false));
	    rightBumper = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.RIGHT_BUMPER);
	    rightBumper.whileHeld(new RearMove(true));
	    leftTrigger = new JoystickTriggerAsButton(xboxController, Constants.OperatorControls.Controller.Axes.LEFT_TRIGGER, .04);
	    leftTrigger.whileHeld(new ConveyerMove(false));
	    rightTrigger = new JoystickTriggerAsButton(xboxController, Constants.OperatorControls.Controller.Axes.RIGHT_TRIGGER, .04);
	    rightTrigger.whileHeld(new ConveyerMove(true));
	    
    	
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());

        SmartDashboard.putData("MecanumDrive", new MecanumDrive());

        SmartDashboard.putData("TankDrive", new TankDrive());

        SmartDashboard.putData("ArcadeDrive", new ArcadeDrive());
                
        SmartDashboard.putData("Toggle Drive Style", new ToggleDriveStyle());
       

    }
    
    public static OI getInstance() {
    	if (OI.instance == null) {
    		OI.instance = new OI();
    	}
    	
    	return OI.instance;
    }
    
//    public static double getRawAxis( int axis ) {
//        return xboxController.getRawAxis(axis) + axisErrors[axis];
//    }
    
    /**
     * Gets the value for the x-axis on the left stick of the xbox controller
     * 
     * @return Value for the x-axis on the left stick of the xbox controller
     */
    public static double getLeftStickXAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.LEFT_STICK_X);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.Deadzone.LEFT_X_CENTER, Constants.OperatorControls.Controller.Deadzone.LEFT_X, rawAxis);
    }
    
    /**
     * Gets the value for the y-axis on the left stick of the xbox controller
     * 
     * @return Value for the y-axis on the left stick of the xbox controller
     */
    public static double getLeftStickYAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.LEFT_STICK_Y);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.Deadzone.LEFT_Y_CENTER, Constants.OperatorControls.Controller.Deadzone.LEFT_Y, rawAxis);
	}
    
    /**
     * Gets the value for the x-axis on the right stick of the xbox controller
     * 
     * @return Value for the x-axis on the right stick of the xbox controller
     */    
    public static double getRightStickXAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.RIGHT_STICK_X);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.Deadzone.RIGHT_X_CENTER, Constants.OperatorControls.Controller.Deadzone.RIGHT_X, rawAxis);
    }
    
    /**
     * Gets the value for the y-axis on the right stick of the xbox controller
     * 
     * @return Value for the y-axis on the right stick of the xbox controller
     */    
    public static double getRightStickYAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.RIGHT_STICK_Y);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.Deadzone.RIGHT_Y_CENTER, Constants.OperatorControls.Controller.Deadzone.RIGHT_Y, rawAxis);
    }

	public Joystick getController() {
		return xboxController;
	}
	
	/**
	 * Gets the value after the scaling for the deadzones
	 * 
	 * @param center Center value for the deadzones
	 * @param deadZone The amount for the deadzone
	 * @param value Current value
	 * @return Value after deadzone scaling
	 */
	private static double getValueAfterDeadZoneScaling(double center, double deadZone, double value) {
		if (value > center + deadZone) {
    		return OI.positiveScaling(center, deadZone, value);
    	} else if (value < center - deadZone) {
    		return OI.negativeScaling(center, deadZone, value);
    	} else {
    		return 0;
    	}
	}
	
	/**
	 * Gets the value after the positive scaling for the deadzones
	 * 
	 * @param center Center value for the deadzones
	 * @param deadZone The amount for the deadzone
	 * @param value Current value
	 * @return Value after positive deadzone scaling
	 */
	private static double positiveScaling(double center, double deadZone, double value) {
		return (1 - 0) * (value - (center + deadZone)) / (1 - (center + deadZone));
	}
	
	/**
	 * Gets the value after the negative scaling for the deadzones
	 * 
	 * @param center Center value for the deadzones
	 * @param deadZone The amount for the deadzone
	 * @param value Current value
	 * @return Value after negative deadzone scaling
	 */	
	private static double negativeScaling(double center, double deadZone, double value) {
		return (-1 - 0) * (value - (center - deadZone)) / (-1 - (center - deadZone));
	}
    
}

