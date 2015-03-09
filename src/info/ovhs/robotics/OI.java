package info.ovhs.robotics;

import info.ovhs.robotics.commands.activeinput.ReleaseTote;
import info.ovhs.robotics.commands.activeinput.SuckInTote;
import info.ovhs.robotics.commands.conveyer.ConveyerJoystickControl;
import info.ovhs.robotics.commands.conveyer.ConveyerMove;
import info.ovhs.robotics.commands.conveyer.DropTote;
import info.ovhs.robotics.commands.conveyer.LiftTote;
import info.ovhs.robotics.commands.conveyer.ResetConveyer;
import info.ovhs.robotics.commands.rearmotorspool.RearMove;
import info.ovhs.robotics.commands.rearmotorspool.ResetRear;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	/**	
	 * Instance of the OI Class
	 */
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
    /**
     * Button on the xbox Controller
     */
    public JoystickButton aButton, bButton, xButton, yButton, backButton, startButton, leftBumper, rightBumper;
    
    /**
     * Button on the joystick for operator control
     */
    public JoystickButton operatorController1, operatorController2, operatorController3, operatorController4, operatorController5, operatorController6, operatorController7, operatorController8, operatorController9, operatorController10, operatorController11;
   
    /**
     * Trigger on xbox Controller
     */
    public JoystickTriggerAsButton leftTrigger, rightTrigger;
    
    /**
     * POV on Xbox Controller
     */
    public JoystickPOVAsButton leftPOV, rightPOV, upPOV, downPOV;
    
    /**
     * Joystick
     */
    public static Joystick xboxController, operatorController;
    
    /**
     * Creates all Operator Interface Objects
     */
    private OI() {
    	xboxController = new Joystick(Constants.Ports.Joystick.XBOX_CONTROLLER);
    	operatorController = new Joystick(Constants.Ports.Joystick.OPERATOR_CONTROLLER);
    	aButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.A);
	    aButton.whenPressed(new LiftTote(50));
	    bButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.B);
	    bButton.whileHeld(new DropTote(50));
	    xButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.X);
	    xButton.whenPressed(new SuckInTote());
	    yButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.Y);
	    yButton.whenPressed(new ReleaseTote());
	    backButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.BACK);
	    backButton.whenPressed(new ResetConveyer());
	    startButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.START);
	    startButton.whileHeld(new ResetRear());
	    leftBumper = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.LEFT_BUMPER);
	    leftBumper.whileHeld(new RearMove(false));
	    rightBumper = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.RIGHT_BUMPER);
	    rightBumper.whileHeld(new RearMove(true));
	    leftTrigger = new JoystickTriggerAsButton(xboxController, Constants.OperatorControls.Controller.Xbox.Axes.LEFT_TRIGGER, .04);
	    leftTrigger.whileHeld(new ConveyerMove(false));
	    rightTrigger = new JoystickTriggerAsButton(xboxController, Constants.OperatorControls.Controller.Xbox.Axes.RIGHT_TRIGGER, .04);
	    rightTrigger.whileHeld(new ConveyerMove(true));
	    operatorController1 = new JoystickButton(operatorController, Constants.OperatorControls.Controller.OperatorController.Buttons.OPERATOR_CONTROL_1);
	    operatorController1.whileHeld(new ConveyerJoystickControl());
	    operatorController2 = new JoystickButton(operatorController, Constants.OperatorControls.Controller.OperatorController.Buttons.OPERATOR_CONTROL_2);
	    operatorController2.whileHeld(new ConveyerMove(true));
	    operatorController3 = new JoystickButton(operatorController, Constants.OperatorControls.Controller.OperatorController.Buttons.OPERATOR_CONTROL_3);
	    operatorController3.whileHeld(new ConveyerMove(false));
	    
	    operatorController4 = new JoystickButton(operatorController, Constants.OperatorControls.Controller.OperatorController.Buttons.OPERATOR_CONTROL_4);
	    operatorController4.whenPressed(new LiftTote());
	    operatorController5 = new JoystickButton(operatorController, Constants.OperatorControls.Controller.OperatorController.Buttons.OPERATOR_CONTROL_5);
	    operatorController5.whenPressed(new DropTote());
    }
    
    public void xboxControllerMappings() {
//    	xboxController = new Joystick(Constants.Ports.Joystick.XBOX_CONTROLLER);
//    	aButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.A); //does nothing
//	    bButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.B); //does nothing
//	    xButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.X); //does nothing
//	    yButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.Y); //does nothing
//	    backButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.BACK); //does nothing
//	    startButton = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.START); //does nothing
//	    leftBumper = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.LEFT_BUMPER); 
//	    leftBumper.whileHeld(new SuckInTote());
//	    rightBumper = new JoystickButton(xboxController, Constants.OperatorControls.Controller.Xbox.Buttons.RIGHT_BUMPER);
//	    rightBumper.whileHeld(new ReleaseTote());
//	    leftTrigger = new JoystickTriggerAsButton(xboxController, Constants.OperatorControls.Controller.Xbox.Axes.LEFT_TRIGGER, .04); //does nothing
//	    rightTrigger = new JoystickTriggerAsButton(xboxController, Constants.OperatorControls.Controller.Xbox.Axes.RIGHT_TRIGGER, .04); //does nothing
    }
    
    public void operatorControllerMappings() {
//    	operatorController = new Joystick(Constants.Ports.Joystick.OPERATOR_CONTROLLER);
//   	operatorController1 = new JoystickButton(operatorController, Constants.OperatorControls.Controller.OperatorController.Buttons.OPERATOR_CONTROL_1);
//		operatorController1.whileHeld(new ConveyerJoystickControl(Constants.Ports.Joystick.OPERATOR_CONTROLLER, Constants.OperatorControls.Controller.OperatorController.Axes.Y));
//	    operatorController2 = new JoystickButton(operatorController, Constants.OperatorControls.Controller.OperatorController.Buttons.OPERATOR_CONTROL_2);
//	    operatorController3 = new JoystickButton(operatorController, Constants.OperatorControls.Controller.OperatorController.Buttons.OPERATOR_CONTROL_3);
//	    operatorController3.whileHeld(new ConveyerJoystickControl(Constants.Ports.Joystick.OPERATOR_CONTROLLER, Constants.OperatorControls.Controller.OperatorController.Axes.Y));
//	    operatorController4 = new JoystickButton(operatorController, Constants.OperatorControls.Controller.OperatorController.Buttons.OPERATOR_CONTROL_4);
//	    operatorController4.whenPressed(new DropTote());
//	    operatorController5 = new JoystickButton(operatorController, Constants.OperatorControls.Controller.OperatorController.Buttons.OPERATOR_CONTROL_5);
//	    operatorController5.whenPressed(new LiftTote());
    }
    
    
    /**
     * Gets the instance of the OI Class and creates one if one doesn't exist
     * @return instance of OI Class
     */
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
    public static double getXboxLeftStickXAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.LEFT_STICK_X);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.Xbox.Deadzone.LEFT_X_CENTER, Constants.OperatorControls.Controller.Xbox.Deadzone.LEFT_X, rawAxis, Constants.OperatorControls.Controller.Xbox.ScalingValue.LEFT_X);
    }
    
    /**
     * Gets the value for the y-axis on the left stick of the xbox controller
     * 
     * @return Value for the y-axis on the left stick of the xbox controller
     */
    public static double getXboxLeftStickYAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.LEFT_STICK_Y);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.Xbox.Deadzone.LEFT_Y_CENTER, Constants.OperatorControls.Controller.Xbox.Deadzone.LEFT_Y, rawAxis, Constants.OperatorControls.Controller.Xbox.ScalingValue.LEFT_Y);
	}
    
    /**
     * Gets the value for the x-axis on the right stick of the xbox controller
     * 
     * @return Value for the x-axis on the right stick of the xbox controller
     */    
    public static double getXboxRightStickXAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.RIGHT_STICK_X);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.Xbox.Deadzone.RIGHT_X_CENTER, Constants.OperatorControls.Controller.Xbox.Deadzone.RIGHT_X, rawAxis, Constants.OperatorControls.Controller.Xbox.ScalingValue.RIGHT_X);
    }
    
    /**
     * Gets the value for the y-axis on the right stick of the xbox controller
     * 
     * @return Value for the y-axis on the right stick of the xbox controller
     */    
    public static double getXboxRightStickYAxis() {
    	double rawAxis = OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.RIGHT_STICK_Y);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.Xbox.Deadzone.RIGHT_Y_CENTER, Constants.OperatorControls.Controller.Xbox.Deadzone.RIGHT_Y, rawAxis, Constants.OperatorControls.Controller.Xbox.ScalingValue.RIGHT_Y);
    }
    
    /**
     * Gets the value for the x-axis on the trash can controller    
     * @return value for the x-axis on the trash can controller
     */
    public static double getOperatorControllerXAxis() {
    	double rawAxis = OI.operatorController.getRawAxis(Constants.OperatorControls.Controller.OperatorController.Axes.X);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.OperatorController.Deadzone.X_CENTER, Constants.OperatorControls.Controller.OperatorController.Deadzone.X, rawAxis, Constants.OperatorControls.Controller.OperatorController.ScalingValue.X);
    }
    
    /**
     * Gets the value for the y-axis on the trash can controller    
     * @return value for the y-axis on the trash can controller
     */
    public static double getOperatorControllerYAxis() {
    	double rawAxis = OI.operatorController.getRawAxis(Constants.OperatorControls.Controller.OperatorController.Axes.Y);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.OperatorController.Deadzone.Y_CENTER, Constants.OperatorControls.Controller.OperatorController.Deadzone.Y, rawAxis, Constants.OperatorControls.Controller.OperatorController.ScalingValue.Y);
    }
    
    /**
     * Gets the value for the z-axis on the trash can controller
     * <p>
     * z-axis is paddle lever right below the joystick
     * </p>
     * 
     * @return value for the z-axis on the trash can controller
     */
    public static double getOperatorControllerZAxis() {
    	double rawAxis = OI.operatorController.getRawAxis(Constants.OperatorControls.Controller.OperatorController.Axes.Z);
    	return OI.getValueAfterDeadZoneScaling(Constants.OperatorControls.Controller.OperatorController.Deadzone.Z_CENTER, Constants.OperatorControls.Controller.OperatorController.Deadzone.Z, rawAxis, Constants.OperatorControls.Controller.OperatorController.ScalingValue.Z);
    }

    /**
     * Gets the instance of the xbox controller
     * @return instance of xbox controller
     */
	public Joystick getXboxController() {
		return xboxController;
	}
	
	/**
	 * Gets the instance of the operator controller
	 * @return instance of the operator controller
	 */
	public Joystick getOperatorController() {
		return operatorController;
	}
	
	/**
	 * Gets the value after the scaling for the deadzones
	 * 
	 * @param center Center value for the deadzones
	 * @param deadZone The amount for the deadzone
	 * @param value Current value
	 * @param scalingPower Scaling for the driving power
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
    
    /**    
     * Rumbles the controller
     * 
     * @param controller Controller to rumble
     * @param time How long to rumble
     */
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
    
    /**
     * Starts the controller rumbling on both sides 
     * 
     * @param controller Which controller to rumble
     */
    public static void startControllerRumble(Joystick controller) {
    	controller.setRumble(RumbleType.kLeftRumble, 1);
    	controller.setRumble(RumbleType.kRightRumble, 1);
    }
    
    /**
     * Starts the controller rumbling on a specified side
     * 
     * @param controller Which controller to rumble
     * @param side Which side to rumble; choose from "left" or "right"
     */
    public static void startControllerRumble(Joystick controller, String side) {
    	if (side.equalsIgnoreCase("left")) {
    		controller.setRumble(RumbleType.kLeftRumble, 0);
    	} else if (side.equalsIgnoreCase("right")){
    		controller.setRumble(RumbleType.kRightRumble, 0);
    	} else {
    		controller.setRumble(RumbleType.kLeftRumble, 0);
    		controller.setRumble(RumbleType.kRightRumble, 0);
    	}
    }
    
    /**    
     * Stops the controller from rumbling on both sides
     * 
     * @param controller Which controller to stop rumbling
     */
    public static void stopControllerRumble(Joystick controller) {
    	controller.setRumble(RumbleType.kLeftRumble, 0);
    	controller.setRumble(RumbleType.kRightRumble, 0);
    }
    
    /**
     * Stops the controller from rumbling
     * 
     * @param controller Which controller to stop rumbling
     * @param side Which side to stop rumbling
     */
    public static void stopControllerRumble(Joystick controller, String side) {
    	switch (side) {
    		case "left": controller.setRumble(RumbleType.kLeftRumble, 0);
    		break;
    		case "right": controller.setRumble(RumbleType.kRightRumble, 0);
    		break;
    		default: controller.setRumble(RumbleType.kLeftRumble, 0);
    		controller.setRumble(RumbleType.kRightRumble, 0);
    		break;
    	}
    }
    
}
