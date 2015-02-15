package info.ovhs.robotics;

import info.ovhs.robotics.commands.Rumble;
import info.ovhs.robotics.commands.conveyer.ConveyerMove;
import info.ovhs.robotics.commands.conveyer.DropTote;
import info.ovhs.robotics.commands.conveyer.LiftTote;
import info.ovhs.robotics.commands.conveyer.LowerConveyerToBottom;
import info.ovhs.robotics.commands.conveyer.ResetConveyer;
import info.ovhs.robotics.commands.drive.ArcadeDrive;
import info.ovhs.robotics.commands.drive.MecanumDrive;
import info.ovhs.robotics.commands.drive.TankDrive;
import info.ovhs.robotics.commands.rearmotorspool.RearDropTrashCan;
import info.ovhs.robotics.commands.rearmotorspool.RearLiftTrashCan;
import info.ovhs.robotics.commands.rearmotorspool.RearMove;
import info.ovhs.robotics.commands.rearmotorspool.ResetRear;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
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
    
    public JoystickButton aButton, bButton, xButton, yButton, backButton, startButton, leftBumper, rightBumper;
    public JoystickTriggerAsButton leftTrigger, rightTrigger;
    public JoystickPOVAsButton leftPOV, rightPOV, upPOV, downPOV;
    public static Joystick xboxController, fireButton;
    
    private OI() {

    	xboxController = new Joystick(Constants.Ports.Joystick.CONTROLLER);
    	fireButton = new Joystick(Constants.Ports.Joystick.FIRE_BUTTON);
    	aButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.A);
	    aButton.whenPressed(new LiftTote(50));
	    bButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.B);
	    bButton.whenPressed(new DropTote(50));
	    xButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.X);
	    xButton.whenPressed(new RearLiftTrashCan(50));
	    yButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.Y);
	    yButton.whenPressed(new RearDropTrashCan(50));
	    backButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.BACK);
	    backButton.whenPressed(new ResetConveyer());
	    startButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.START);
	    startButton.whileHeld(new ResetRear());
	    leftBumper = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.LEFT_BUMPER);
	    leftBumper.whileHeld(new RearMove(false));
	    leftBumper.whileHeld(new Rumble(25));
	    rightBumper = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Buttons.RIGHT_BUMPER);
	    rightBumper.whileHeld(new RearMove(true));
	    rightBumper.whileHeld(new Rumble(25));
	    leftTrigger = new JoystickTriggerAsButton(xboxController, Constants.OperatorControls.Controller.Axes.LEFT_TRIGGER, .04);
	    leftTrigger.whileHeld(new ConveyerMove(false));
	    leftTrigger.whileHeld(new Rumble(25));
	    rightTrigger = new JoystickTriggerAsButton(xboxController, Constants.OperatorControls.Controller.Axes.RIGHT_TRIGGER, .04);
	    rightTrigger.whileHeld(new ConveyerMove(true));
	    rightTrigger.whileHeld(new Rumble(25));
	    leftPOV = new JoystickPOVAsButton(xboxController, Constants.OperatorControls.Controller.POV.LEFT, Constants.OperatorControls.Controller.POV.LEFT_THRESHOLD);
	    leftPOV.whileHeld(new DropTote(50));
	    rightPOV = new JoystickPOVAsButton(xboxController, Constants.OperatorControls.Controller.POV.RIGHT, Constants.OperatorControls.Controller.POV.RIGHT_THRESHOLD);
	    upPOV = new JoystickPOVAsButton(xboxController, Constants.OperatorControls.Controller.POV.UP, Constants.OperatorControls.Controller.POV.UP_THRESHOLD);
	    downPOV = new JoystickPOVAsButton(xboxController, Constants.OperatorControls.Controller.POV.DOWN, Constants.OperatorControls.Controller.POV.DOWN_THRESHOLD);
	    
    	
        // SmartDashboard Buttons
        SmartDashboard.putData("MecanumDrive", new MecanumDrive());

        SmartDashboard.putData("TankDrive", new TankDrive());

        SmartDashboard.putData("ArcadeDrive", new ArcadeDrive());
        
        SmartDashboard.putData("Lower Conveyer To Bottom", new LowerConveyerToBottom());
        
        SmartDashboard.putData("Reset Conveyer", new ResetConveyer());
        
        SmartDashboard.putData("Reset Rear", new ResetRear());
    }
    
    public static OI getInstance() {
    	if (OI.instance == null) {
    		OI.instance = new OI();
    	}
    	
    	return OI.instance;
    }
        
    /**
     * Gets the value for the x-axis on the left stick of the xbox controller
     * 
     * @return Value for the x-axis on the left stick of the xbox controller
     */
    public static double getLeftStickXAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.LEFT_STICK_X);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.Deadzone.LEFT_X_CENTER, Constants.OperatorControls.Controller.Deadzone.LEFT_X, rawAxis, Constants.OperatorControls.Controller.ScalingValue.LEFT_X);
    }
    
    /**
     * Gets the value for the y-axis on the left stick of the xbox controller
     * 
     * @return Value for the y-axis on the left stick of the xbox controller
     */
    public static double getLeftStickYAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.LEFT_STICK_Y);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.Deadzone.LEFT_Y_CENTER, Constants.OperatorControls.Controller.Deadzone.LEFT_Y, rawAxis, Constants.OperatorControls.Controller.ScalingValue.LEFT_Y);
	}
    
    /**
     * Gets the value for the x-axis on the right stick of the xbox controller
     * 
     * @return Value for the x-axis on the right stick of the xbox controller
     */    
    public static double getRightStickXAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.RIGHT_STICK_X);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.Deadzone.RIGHT_X_CENTER, Constants.OperatorControls.Controller.Deadzone.RIGHT_X, rawAxis, Constants.OperatorControls.Controller.ScalingValue.RIGHT_X);
    }
    
    /**
     * Gets the value for the y-axis on the right stick of the xbox controller
     * 
     * @return Value for the y-axis on the right stick of the xbox controller
     */    
    public static double getRightStickYAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.RIGHT_STICK_Y);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.Deadzone.RIGHT_Y_CENTER, Constants.OperatorControls.Controller.Deadzone.RIGHT_Y, rawAxis, Constants.OperatorControls.Controller.ScalingValue.RIGHT_Y);
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
	private static double getValueAfterDeadZoneScaling(double center, double deadZone, double value, double scalingPower) {
		double returnValue = 0;
		if (value > center + deadZone) {
    		returnValue = OI.positiveScaling(center, deadZone, value);
    	} else if (value < center - deadZone) {
    		returnValue = OI.negativeScaling(center, deadZone, value);
    	} else {
    		returnValue = 0;
    	}
		
		
			return OI.getExponentialValue(returnValue, scalingPower);
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
	
	/**
	 * Returns the exponentially scaled value
	 * @param value Value that you're scaling
	 * @param scalingPower Exponent to scale it to
	 * @return Exponentially Scaled Value
	 */
    public static double getExponentialValue(double value, double scalingPower) {
    	return Math.pow(value, scalingPower);
    }
    
    public static void controllerRumble(Joystick controller, double time) {
    	double initialTime = System.nanoTime();
    	if (System.nanoTime() < initialTime + time * Math.pow(10, 9)) {
    		controller.setRumble(RumbleType.kLeftRumble, 1);
    		controller.setRumble(RumbleType.kRightRumble, 1);
    	} else {
    		controller.setRumble(RumbleType.kLeftRumble, 0);
    		controller.setRumble(RumbleType.kRightRumble, 0);
    	}
    }
    
    public static void startControllerRumble(Joystick controller) {
    	controller.setRumble(RumbleType.kLeftRumble, 1);
    	controller.setRumble(RumbleType.kRightRumble, 1);
    }
    
    public static void stopControllerRumble(Joystick controller) {
    	controller.setRumble(RumbleType.kLeftRumble, 0);
    	controller.setRumble(RumbleType.kRightRumble, 0);
    }
    
}
