package info.ovhs.robotics;

import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class SmartDashboardUpdate{
	
	/**
     * Updates the SmartDashboard data output for the Drive Train
     * 
     * <p>
     * For operator use
     * </p>
     */
	public static void DriveTrain() {
		SmartDashboard.putNumber("Front Left Drive Motor", RobotMap.frontLeftDriveSpeedController.get());
        SmartDashboard.putNumber("Front Right Drive Motor", RobotMap.frontRightDriveSpeedController.get());
        SmartDashboard.putNumber("Rear Left Drive Motor", RobotMap.rearLeftDriveSpeedController.get());
        SmartDashboard.putNumber("Rear Right Drive Motor", RobotMap.rearRightDriveSpeedController.get());
        SmartDashboard.putData(CommandBase.driveTrain);
	}
	
	/**
     * Updates the SmartDashboard data output for the Conveyer Belt
     * 
     * <p>
     * For operator use
     * </p>
     */
	public static void ConveyerBelt() {
		SmartDashboard.putNumber("Conveyer Encoder Distance", RobotMap.conveyerBeltEncoder.getDistance());
        SmartDashboard.putNumber("Conveyer Encoder Raw", RobotMap.conveyerBeltEncoder.getRaw());
        SmartDashboard.putNumber("Conveyer Motor", RobotMap.conveyerBeltSpeedController.get());
        SmartDashboard.putData(CommandBase.conveyerBelt);
	}
	
	/**
     * Updates the SmartDashboard data output for the Rear Motor Spool
     * 
     * <p>
     * For operator use
     * </p>
     */
	public static void RearMotorSpool() {
        SmartDashboard.putNumber("Rear Encoder Distance", RobotMap.rearEncoder.getDistance());
        SmartDashboard.putNumber("Rear Encoder Raw", RobotMap.rearEncoder.getRaw());
		SmartDashboard.putNumber("Rear Motor Spool Motor", RobotMap.rearMotorSpoolSpeedController.get());
        SmartDashboard.putData(CommandBase.rearMotorSpool);
	}
	
	/**
     * Updates the SmartDashboard data output for the Joystick Outputs
     * 
     * <p>
     * For operator use
     * </p>
     */
	public static void JoystickOutput() {
		SmartDashboard.putNumber("Xbox Left Y Axis", OI.getXboxLeftStickYAxis());
        SmartDashboard.putNumber("Xbox Left X Axis", OI.getXboxLeftStickXAxis());
        SmartDashboard.putNumber("Xbox Right Y Axis", OI.getXboxRightStickYAxis());
        SmartDashboard.putNumber("Xbox Right X Axis", OI.getXboxRightStickXAxis());
//        SmartDashboard.putNumber("Left Y Axis Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.LEFT_STICK_Y));
//        SmartDashboard.putNumber("Left X Axis Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.LEFT_STICK_X));
//        SmartDashboard.putNumber("Right Y Axis Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.RIGHT_STICK_Y));
//        SmartDashboard.putNumber("Right X Axis Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.RIGHT_STICK_X));
        SmartDashboard.putNumber("Operator X Axis", OI.getOperatorControllerXAxis());
        SmartDashboard.putNumber("Operator Y Axis", OI.getOperatorControllerYAxis());
        SmartDashboard.putNumber("Operator Z Axis", OI.getOperatorControllerZAxis());
	}
	
	/**
     * Updates the SmartDashboard data output for all switches on the robot
     * 
     * <p>
     * For operator use
     * </p>
     */
	public static void Switches() {
		SmartDashboard.putNumber("Auto Switch 1 Setting", RobotMap.autoSwitchReadout(1));
        SmartDashboard.putNumber("Auto Switch 2 Setting", RobotMap.autoSwitchReadout(2));
        SmartDashboard.putString("Limit Switch", RobotMap.limitSwitchReadout());
	}
	
//	/**
//     * Updates the SmartDashboard data output for the PDP CAN Outputs
//     * 
//     * <p>
//     * For operator use
//     * </p>
//     */
//	public static void PDP() {
//		SmartDashboard.putNumber("PDP Total Current", RobotMap.PDP.getTotalCurrent());
//        SmartDashboard.putNumber("PDP Current Port 0", RobotMap.PDP.getCurrent(0));
//        SmartDashboard.putNumber("PDP Current Port 1", RobotMap.PDP.getCurrent(1));
//        SmartDashboard.putNumber("PDP Current Port 2", RobotMap.PDP.getCurrent(2));
//        SmartDashboard.putNumber("PDP Current Port 3", RobotMap.PDP.getCurrent(3));
//        SmartDashboard.putNumber("PDP Current Port 13", RobotMap.PDP.getCurrent(13));
//        SmartDashboard.putNumber("PDP Current Port 14", RobotMap.PDP.getCurrent(14));
//        SmartDashboard.putNumber("PDP Current Port 15", RobotMap.PDP.getCurrent(15));
//        SmartDashboard.putNumber("PDP Temperature", RobotMap.PDP.getTemperature());
//        SmartDashboard.putNumber("PDP Voltage", RobotMap.PDP.getVoltage());
//        SmartDashboard.putNumber("PDP Total Energy", RobotMap.PDP.getTotalEnergy());
//        SmartDashboard.putNumber("PDP Total Power", RobotMap.PDP.getTotalPower());
//	}
	
	public static void OperatorCommandSwitch() {
		String o1 = CommandBase.oi.operatorController1.getSetting();
		String o2 = CommandBase.oi.operatorController2.getSetting();
		String o3 = CommandBase.oi.operatorController3.getSetting();
		String o4 = CommandBase.oi.operatorController4.getSetting();
		String o5 = CommandBase.oi.operatorController5.getSetting();
		String returned;
		if (o1.equals(o2) && o1.equals(o3) && o1.equals(o4) && o1.equals(o5) && o2.equals(o3) && o2.equals(o4) && o2.equals(o5) && o3.equals(o4) && o3.equals(o5) && o4.equals(o5)) {
			returned = o1;
		} else {
			returned = "There is a problem with the swap method";
		}
		SmartDashboard.putString("Operator Command Mode: ", returned );
	}
}