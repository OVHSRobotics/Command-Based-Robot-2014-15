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
		SmartDashboard.putNumber("Left Y Axis", OI.getXboxLeftStickYAxis());
        SmartDashboard.putNumber("Left X Axis", OI.getXboxLeftStickXAxis());
        SmartDashboard.putNumber("Right Y Axis", OI.getXboxRightStickYAxis());
        SmartDashboard.putNumber("Right X Axis", OI.getXboxRightStickXAxis());
        SmartDashboard.putNumber("Left Y Axis Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.LEFT_STICK_Y));
        SmartDashboard.putNumber("Left X Axis Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.LEFT_STICK_X));
        SmartDashboard.putNumber("Right Y Axis Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.RIGHT_STICK_Y));
        SmartDashboard.putNumber("Right X Axis Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.RIGHT_STICK_X));
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
	
	public static void ActiveInput() {
		SmartDashboard.putData(CommandBase.activeInput);
		SmartDashboard.putNumber("Left Active Input Speed Controller", RobotMap.activeInputSpeedControllerLeft.get());
		SmartDashboard.putNumber("Right Active Input Speed Controller", RobotMap.activeInputSpeedControllerRight.get());
	}
}