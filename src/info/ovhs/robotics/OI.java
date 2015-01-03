package info.ovhs.robotics;

import edu.wpi.first.wpilibj.buttons.Button;
import info.ovhs.robotics.commands.ExampleCommand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private static int LEFT_STICK_X = 1, LEFT_STICK_Y = 2, TRIGGERS = 3, RIGHT_STICK_X = 4, RIGHT_STICK_Y = 5;
    private static int A_BUTTON = 1, B_BUTTON = 2, X_BUTTON = 3, Y_BUTTON = 4, LB = 5, RB = 6, 
    		BACK = 7, START = 8, LEFT_JOY_CLICK = 9, RIGHT_JOY_CLICK = 10;
	
	public static Joystick xboxController = new Joystick(1);

    
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
	
	Button aButton = new JoystickButton(xboxController, A_BUTTON);
	Button bButton = new JoystickButton(xboxController, B_BUTTON);
	Button xButton = new JoystickButton(xboxController, X_BUTTON);
	Button yButton = new JoystickButton(xboxController, Y_BUTTON);
	Button backButton = new JoystickButton(xboxController, BACK);
	Button startButton = new JoystickButton(xboxController, START);
    
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
	
	public static double getLeftStickX() {
		return xboxController.getRawAxis(LEFT_STICK_X);
	}
	public static double getLeftStickY() {
		return xboxController.getRawAxis(LEFT_STICK_Y);
	}
	public static double getRightStickX() {
		return xboxController.getRawAxis(RIGHT_STICK_X);
	}
	public static double getRightStickY() {
		return xboxController.getRawAxis(RIGHT_STICK_Y);
	}
	public static double getTriggerL() {
		return Math.abs(xboxController.getRawAxis(TRIGGERS));
	}
	public static double getTriggerR() {
		return Math.abs(xboxController.getRawAxis(TRIGGERS));
	}
	public static boolean isStartButtonPressed() {
		return xboxController.getRawButton(START);
	}
	
}

