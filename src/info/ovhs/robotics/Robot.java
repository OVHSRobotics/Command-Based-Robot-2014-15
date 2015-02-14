
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
    	print("Entering disabled mode");
    	
    	Scheduler.getInstance().removeAll();
    }

    public void disabledPeriodic() {
    	print("In disabled mode");
    	
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
//        if (autonomousCommand != null) {
//        	autonomousCommand.start();
//        }
        
        print("Entering autonomous mode");
        
        boolean switch1 = RobotMap.autonomousSwitch1.get();
        boolean switch2 = RobotMap.autonomousSwitch2.get();
        
        if (switch1 && switch2) {
        	// Drives forward at full power for 3 seconds
        	driveForward = new Drive(1, 3, true);
        	if (driveForward != null){
        		driveForward.start();
        	}
        } else if (switch1 && !switch2) {
        	pickUpOneTote = new PickUpOneTote();
        	if (pickUpOneTote != null) {
        		pickUpOneTote.start();
        	}
        } else if (!switch1 && switch2) {
        	liftOneTrashCanAndOneTote = new LiftOneTrashCanAndOneTote();
        	if (liftOneTrashCanAndOneTote != null) {
        		liftOneTrashCanAndOneTote.start();
        	}
        } else if (!switch1 && !switch2) {
        	liftOneTrashCanAndThreeTotesThenDropAll = new LiftOneTrashCanAndThreeTotesThenDropAll();
        	if (liftOneTrashCanAndThreeTotesThenDropAll != null) {
        		liftOneTrashCanAndThreeTotesThenDropAll.start();
        	}
        } else {
        	//do nothing; impossible case
        }
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
        
    	// Won't this just constantly reset the Scheduler making adding commands pointless?
    	Scheduler.getInstance().run();
        
        // Updates smart dashboard
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
    
    public static void print( String message ) {
        System.out.println(message);
    }
    
    public static void print( double message) {
    	System.out.println(message);
    }
    
    public static void print( int message) {
    	System.out.println(message);
    }
    
    public static void print( boolean message) {
    	System.out.println(message);
    }
    
    public static void print( char message) {
    	System.out.println(message);
    }
    
    public static void print( long message) {
    	System.out.println(message);
    }
    
    public static void print( float message) {
    	System.out.println(message);
    }
    
    public static void updateStatus() {
        // Add data to the "SmartDashboard".
        SmartDashboard.putData(CommandBase.driveTrain);
        SmartDashboard.putData(CommandBase.conveyerBelt);
        SmartDashboard.putData(CommandBase.rearMotorSpool);
        SmartDashboard.putNumber("Conveyer Encoder Distance", RobotMap.conveyerBeltEncoder.getDistance());
        SmartDashboard.putNumber("Conveyer Encoder Raw Value", RobotMap.conveyerBeltEncoder.getRaw());
        SmartDashboard.putNumber("Rear Encoder Distance", RobotMap.rearEncoder.getDistance());
        SmartDashboard.putNumber("Rear Encoder Raw Value", RobotMap.rearEncoder.getRaw());
        SmartDashboard.putNumber("Front Left", RobotMap.frontLeftDriveSpeedController.get());
        SmartDashboard.putNumber("Front Right", RobotMap.frontRightDriveSpeedController.get());
        SmartDashboard.putNumber("Rear Left", RobotMap.rearLeftDriveSpeedController.get());
        SmartDashboard.putNumber("Rear Right", RobotMap.rearRightDriveSpeedController.get());
        SmartDashboard.putNumber("Left Y", OI.getLeftStickYAxis());
        SmartDashboard.putNumber("Left X", OI.getLeftStickXAxis());
        SmartDashboard.putNumber("Right Y", OI.getRightStickYAxis());
        SmartDashboard.putNumber("Right X", OI.getRightStickXAxis());
        SmartDashboard.putNumber("Left Y Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.LEFT_STICK_Y));
        SmartDashboard.putNumber("Left X Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.LEFT_STICK_X));
        SmartDashboard.putNumber("Right Y Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.RIGHT_STICK_Y));
        SmartDashboard.putNumber("Right X Unscaled", OI.xboxController.getRawAxis(Constants.OperatorControls.Controller.Axes.RIGHT_STICK_X));    
    }
}
