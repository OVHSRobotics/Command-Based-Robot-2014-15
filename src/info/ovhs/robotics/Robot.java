
package info.ovhs.robotics;

import info.ovhs.robotics.commands.CommandBase;
import info.ovhs.robotics.commands.autonomous.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command driveForward;
    Command liftOneTrashCanAndOneTote;
    Command liftOneTrashCanAndThreeTotesThenDropAll;
    Command pickUpOneTote;    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	print("Begin Robot Init");
    	
    	RobotMap.init();
        CommandBase.init();
        
        print("Initializing Gyro");        
        RobotMap.robotGyro.initGyro();
        
        RobotMap.setInitialConveyerEncoderDistance();
        RobotMap.setInitialRearEncoderDistance();
        
        
        
        
        print("End Robot Init");
        print("Robot is Ready");
    	
        // OI must be constructed after subsystems. If the OI creates Commands 
        //(which it very likely will), subsystems are not guaranteed to be 
        // constructed yet. Thus, their requires() statements may grab null 
        // pointers. Bad news. Don't move it.
        //oi = new OI();

        // instantiate the command used for the autonomous period
        //autonomousCommand = new AutonomousCommand();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){    	
    	Scheduler.getInstance().removeAll();
    }

    public void disabledPeriodic() {   	
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
//        if (autonomousCommand != null) {
//        	autonomousCommand.start();
//        }
        
        print("Entering autonomous mode");
        
        driveForward = new Drive(1, 1.5, true);
        if (driveForward != null) {
        	driveForward.start();
        }
        /*
        
//        boolean switch1 = RobotMap.autonomousSwitch1.get();
//        boolean switch2 = RobotMap.autonomousSwitch2.get();
//        
//        if (switch1 && switch2) {
//        	// Drives forward at full power for 3 seconds
//        	driveForward = new Drive(1, 3, true);
//        	if (driveForward != null){
//        		driveForward.start();
//        	}
//        } else if (switch1 && !switch2) {
//        	pickUpOneTote = new PickUpOneTote();
//        	if (pickUpOneTote != null) {
//        		pickUpOneTote.start();
//        	}
//        } else if (!switch1 && switch2) {
//        	liftOneTrashCanAndOneTote = new LiftOneTrashCanAndOneTote();
//        	if (liftOneTrashCanAndOneTote != null) {
//        		liftOneTrashCanAndOneTote.start();
//        	}
//        } else if (!switch1 && !switch2) {
//        	liftOneTrashCanAndThreeTotesThenDropAll = new LiftOneTrashCanAndThreeTotesThenDropAll();
//        	if (liftOneTrashCanAndThreeTotesThenDropAll != null) {
//        		liftOneTrashCanAndThreeTotesThenDropAll.start();
//        	}
//        } else {
//        	//do nothing; impossible case
//        }
 */
    }
   

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
//        if (autonomousCommand != null) {
//        	autonomousCommand.cancel();
//        }
    	if (driveForward != null) {
    		driveForward.cancel();
    	}
/*
        
        if (RobotMap.autonomousSwitch1.get() && RobotMap.autonomousSwitch2.get()) {
        	if (driveForward != null){
        		driveForward.cancel();
        	}
        } else if (RobotMap.autonomousSwitch1.get() && !RobotMap.autonomousSwitch2.get()) {
        	if (pickUpOneTote != null) {
        		pickUpOneTote.cancel();
        	}
        } else if (!RobotMap.autonomousSwitch1.get() && RobotMap.autonomousSwitch2.get()) {
        	if (liftOneTrashCanAndOneTote != null) {
        		liftOneTrashCanAndOneTote.cancel();
        	}
        } else if (!RobotMap.autonomousSwitch1.get() && !RobotMap.autonomousSwitch2.get()) {
        	if (liftOneTrashCanAndThreeTotesThenDropAll != null) {
        		liftOneTrashCanAndThreeTotesThenDropAll.cancel();
        	}
        }
*/
        
        print("Entering teleop mode");
        if (CommandBase.driveTrain.getCurrentCommand() == null) {
        	CommandBase.driveTrain.initDefaultCommand();
        }
                
        updateStatus();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
    	Scheduler.getInstance().run();
        
        // Updates SmartDashboard
        updateStatus();
        
        // Moves Robot for Testing
        //CommandBase.driveTrain.mecanumDriveController(0);
        
    }
    
    /**
     * This function is called once when test mode begins.
     */
    public void testInit() {
        print("Entering test mode");
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	print("In test mode");
    	
        LiveWindow.run();
    }
    
    public static void print( Object message) {
    	System.out.println(message);
    }
    
    
    public static void updateStatus() {
        // Add data to the "SmartDashboard"
    	Robot.updateSubsystemStatus();
        SmartDashboard.putNumber("Conveyer Encoder Distance", RobotMap.conveyerBeltEncoder.getDistance());
        SmartDashboard.putNumber("Conveyer Encoder Raw", RobotMap.conveyerBeltEncoder.getRaw());
        SmartDashboard.putNumber("Rear Encoder Distance", RobotMap.rearEncoder.getDistance());
        SmartDashboard.putNumber("Rear Encoder Raw", RobotMap.rearEncoder.getRaw());
        SmartDashboard.putNumber("Front Left Motor", RobotMap.frontLeftDriveSpeedController.get());
        SmartDashboard.putNumber("Front Right Motor", RobotMap.frontRightDriveSpeedController.get());
        SmartDashboard.putNumber("Rear Left Motor", RobotMap.rearLeftDriveSpeedController.get());
        SmartDashboard.putNumber("Rear Right Motor", RobotMap.rearRightDriveSpeedController.get());
        SmartDashboard.putNumber("Left Y Axis", OI.getXboxLeftStickYAxis());
        SmartDashboard.putNumber("Left X Axis", OI.getXboxLeftStickXAxis());
        SmartDashboard.putNumber("Right Y Axis", OI.getXboxRightStickYAxis());
        SmartDashboard.putNumber("Right X Axis", OI.getXboxRightStickXAxis());
        SmartDashboard.putNumber("Left Y Axis Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.LEFT_STICK_Y));
        SmartDashboard.putNumber("Left X Axis Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.LEFT_STICK_X));
        SmartDashboard.putNumber("Right Y Axis Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.RIGHT_STICK_Y));
        SmartDashboard.putNumber("Right X Axis Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Xbox.Axes.RIGHT_STICK_X));
        Robot.updatePDPStatus();
    }
    
    public static void updateSubsystemStatus() {
    	SmartDashboard.putData(CommandBase.driveTrain);
        SmartDashboard.putData(CommandBase.conveyerBelt);
        SmartDashboard.putData(CommandBase.rearMotorSpool);
    }
    
    public static void updatePDPStatus() {
    	SmartDashboard.putNumber("PDP Total Current", RobotMap.PDP.getTotalCurrent());
        SmartDashboard.putNumber("PDP Current Port 0", RobotMap.PDP.getCurrent(0));
        SmartDashboard.putNumber("PDP Current Port 1", RobotMap.PDP.getCurrent(1));
        SmartDashboard.putNumber("PDP Current Port 2", RobotMap.PDP.getCurrent(2));
        SmartDashboard.putNumber("PDP Current Port 3", RobotMap.PDP.getCurrent(3));
        SmartDashboard.putNumber("PDP Current Port 4", RobotMap.PDP.getCurrent(4));
        SmartDashboard.putNumber("PDP Current Port 5", RobotMap.PDP.getCurrent(5));
        SmartDashboard.putNumber("PDP Current Port 6", RobotMap.PDP.getCurrent(6));
        SmartDashboard.putNumber("PDP Current Port 7", RobotMap.PDP.getCurrent(7));
        SmartDashboard.putNumber("PDP Current Port 8", RobotMap.PDP.getCurrent(8));
        SmartDashboard.putNumber("PDP Current Port 9", RobotMap.PDP.getCurrent(9));
        SmartDashboard.putNumber("PDP Current Port 10", RobotMap.PDP.getCurrent(10));
        SmartDashboard.putNumber("PDP Current Port 11", RobotMap.PDP.getCurrent(11));
        SmartDashboard.putNumber("PDP Current Port 12", RobotMap.PDP.getCurrent(12));
        SmartDashboard.putNumber("PDP Current Port 13", RobotMap.PDP.getCurrent(13));
        SmartDashboard.putNumber("PDP Current Port 14", RobotMap.PDP.getCurrent(14));
        SmartDashboard.putNumber("PDP Current Port 15", RobotMap.PDP.getCurrent(15));
        SmartDashboard.putNumber("PDP Temperature", RobotMap.PDP.getTemperature());
        SmartDashboard.putNumber("PDP Voltage", RobotMap.PDP.getVoltage());
        SmartDashboard.putNumber("PDP Total Energy", RobotMap.PDP.getTotalEnergy());
        SmartDashboard.putNumber("PDP Total Power", RobotMap.PDP.getTotalPower());
    }
}
