package info.ovhs.robotics;

import info.ovhs.robotics.commands.autonomous.AutonomousCommand;
import info.ovhs.robotics.commands.conveyer.DropTote;
import info.ovhs.robotics.commands.conveyer.DropTrashCan;
import info.ovhs.robotics.commands.conveyer.HoldPID;
import info.ovhs.robotics.commands.conveyer.JoystickControlConveyer;
import info.ovhs.robotics.commands.conveyer.LiftTote;
import info.ovhs.robotics.commands.conveyer.LiftTrashCan;
import info.ovhs.robotics.commands.drive.ArcadeDrive;
import info.ovhs.robotics.commands.drive.MecanumDrive;
import info.ovhs.robotics.commands.drive.TankDrive;
import info.ovhs.robotics.commands.drive.ToggleDriveStyle;
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
    public static Joystick xboxController;
    public static Joystick fireButton;
    
    private OI() {

    	xboxController = new Joystick(Constants.Ports.Joystick.CONTROLLER);
    	fireButton = new Joystick(Constants.Ports.Joystick.FIRE_BUTTON);
    	aButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.A);
	    aButton.whenPressed(new MecanumDrive());
	    bButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.B);
	    bButton.whenPressed(new DropTote());
	    xButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.X);
	    xButton.whenPressed(new DropTrashCan());
	    yButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.Y);
	    yButton.whenPressed(new HoldPID());
	    backButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.BACK);
	    backButton.whenPressed(new LiftTote());
	    startButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.START);
	    startButton.whileHeld(new JoystickControlConveyer());
    	
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
    
    public static double getLeftStickXAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.LEFT_STICK_X);
    	if (rawAxis > (Constants.OperatorControls.Controller.Deadzone.LEFT_X_CENTER + Constants.OperatorControls.Controller.Deadzone.LEFT_X)) {
    		return OI.positiveScalingX(rawAxis);
    	} else if (rawAxis < (Constants.OperatorControls.Controller.Deadzone.LEFT_X_CENTER + Constants.OperatorControls.Controller.Deadzone.LEFT_X)) {
    		return OI.negativeScalingX(rawAxis);
    	} else {
    		return 0;
    	}
    }
    
    public static double getLeftStickYAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.LEFT_STICK_Y);
    	if (rawAxis > (Constants.OperatorControls.Controller.Deadzone.LEFT_Y_CENTER + Constants.OperatorControls.Controller.Deadzone.LEFT_Y)) {
    		return OI.positiveScalingY(rawAxis);
    	} else if (rawAxis < (Constants.OperatorControls.Controller.Deadzone.LEFT_Y_CENTER + Constants.OperatorControls.Controller.Deadzone.LEFT_Y)) {
    		return OI.negativeScalingY(rawAxis);
    	} else {
    		return 0;
    	}
	}
    
    public static double getRightStickXAxis() {
    	return OI.xboxController.getRawAxis(4);
    }
    
    public static double getRightStickYAxis() {
    	return OI.xboxController.getRawAxis(5);
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
	

	private static double positiveScalingX(double x) {
		return (1-0) * ((x-(Constants.OperatorControls.Controller.Deadzone.LEFT_X_CENTER + Constants.OperatorControls.Controller.Deadzone.LEFT_X)) / (1 - (Constants.OperatorControls.Controller.Deadzone.LEFT_X_CENTER + Constants.OperatorControls.Controller.Deadzone.LEFT_X)));
	}

	private static double positiveScalingY(double y) {
		return (1-0) * ((y-(Constants.OperatorControls.Controller.Deadzone.LEFT_Y_CENTER + Constants.OperatorControls.Controller.Deadzone.LEFT_Y)) / (1 - (Constants.OperatorControls.Controller.Deadzone.LEFT_Y_CENTER + Constants.OperatorControls.Controller.Deadzone.LEFT_Y)));
	}
	
	private static double negativeScalingX(double x) {
		return (-1-0) * ((x-(Constants.OperatorControls.Controller.Deadzone.LEFT_X_CENTER + Constants.OperatorControls.Controller.Deadzone.LEFT_X)) / (-1 - (Constants.OperatorControls.Controller.Deadzone.LEFT_X_CENTER + Constants.OperatorControls.Controller.Deadzone.LEFT_X)));
	}

	private static double negativeScalingY(double y) {
		return (-1-0) * ((y-(Constants.OperatorControls.Controller.Deadzone.LEFT_Y_CENTER + Constants.OperatorControls.Controller.Deadzone.LEFT_Y)) / (-1 - (Constants.OperatorControls.Controller.Deadzone.LEFT_Y_CENTER + Constants.OperatorControls.Controller.Deadzone.LEFT_Y)));
	}
    
}

