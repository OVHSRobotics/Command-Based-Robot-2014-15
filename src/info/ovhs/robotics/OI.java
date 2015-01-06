

package info.ovhs.robotics;

import info.ovhs.robotics.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
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

    public OI() {

        xboxController = new Joystick(1);
        
        startButton = new JoystickButton(xboxController, 8);
        //startButton.whenPressed(new ArcadeDrive());
        backButton = new JoystickButton(xboxController, 7);
        //backButton.whileHeld(new TankDrive());
        yButton = new JoystickButton(xboxController, 4);
        //yButton.whenPressed(new MecanumDrive());
        xButton = new JoystickButton(xboxController, 3);
        xButton.whileHeld(new ArcadeDrive());
        bButton = new JoystickButton(xboxController, 2);
        bButton.whenPressed(new TankDrive());
        aButton = new JoystickButton(xboxController, 1);
        aButton.whenPressed(new MecanumDrive());

	    
        /*// SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());

        SmartDashboard.putData("MecanumDrive", new MecanumDrive());

        SmartDashboard.putData("TankDrive", new TankDrive());

        SmartDashboard.putData("ArcadeDrive", new ArcadeDrive());*/

    }
    
    public Joystick getxboxController() {
        return xboxController;
    }
    public static double getLeftStickXAxis() {
    	return xboxController.getRawAxis(1);
    }
    public static double getLeftStickYAxis() {
    	return xboxController.getRawAxis(2);
    }
    public static double getRightStickXAxis() {
    	return xboxController.getRawAxis(4);
    }
    public static double getRightStickYAxis() {
    	return xboxController.getRawAxis(5);
    }
    public static boolean leftTriggerAllWayDown() {
    	return (Math.abs(xboxController.getRawAxis(3))>= .98);
    }
    public static boolean rightTriggerAllWayDown() {
    	return ((-1 * Math.abs(xboxController.getRawAxis(3)) <= -.98));
    }

}

