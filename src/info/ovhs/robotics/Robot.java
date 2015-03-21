
package info.ovhs.robotics;

import info.ovhs.robotics.commands.CommandBase;
import info.ovhs.robotics.commands.autonomous.AutoTest1;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	/**
	 * Command for autonomous mode
	 */
    Command driveForward, testAutoFull, strafeLeft, pickUpOneTote;

    /**
     * Switch to switch between different autonomous modes
     */
    boolean autoSwitch1, autoSwitch2;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	if (Constants.VERBOSE_OUTPUT) {
    		print("Begin Robot Init");
    	}
    	
    	RobotMap.init();
        CommandBase.init();
        
//        if (Constants.VERBOSE_OUTPUT) {
//        	print("Initializing Gyro");        
//        }
//        RobotMap.robotGyro.initGyro();
//        if (Constants.VERBOSE_OUTPUT){
//        	print("End Initializing Gyro");
//        }
        
        if (Constants.VERBOSE_OUTPUT) {
        	print("Setting initial Encoder Distances");
        }
        RobotMap.setInitialConveyerEncoderDistance();
        RobotMap.setInitialRearEncoderDistance();
        
        if (Constants.VERBOSE_OUTPUT) {
        	print("Done Setting initial Encoder Distances");
        }
        
        
        
        if (Constants.VERBOSE_OUTPUT) {
        	print("End Robot Init");
        }
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

    /**
     * This function is called every 20 ms during disabled mode.
     */
    public void disabledPeriodic() {   	
        Scheduler.getInstance().run();
    }

    /**
     * This function is called once at the beginning of autonomous mode.
     * Place all autonomous command initializations here.
     */
    public void autonomousInit() {
        // schedule the autonomous command (example)
//        if (autonomousCommand != null) {
//        	autonomousCommand.start();
//        }
        
        print("Entering autonomous mode");
        
        this.autoSwitch1 = RobotMap.autonomousSwitch1.get();
        this.autoSwitch2 = RobotMap.autonomousSwitch2.get();
        
        if (Constants.AUTO_ON) {
        	if (autoSwitch1 && autoSwitch2) {
//        		// Drives forward at 3/4 power for 1 second
//        		driveForward = new Drive(.8, 1.2, true);
//        		if (driveForward != null){
//        			driveForward.start();
//        		}
        	} else if (autoSwitch1 && !autoSwitch2) {
//        		pickUpOneTote = new PickUpOneTote();
//        		if (pickUpOneTote != null) {
//        			pickUpOneTote.start();
//        		}
        	} else if (!autoSwitch1 && autoSwitch2) {
        		testAutoFull = new AutoTest1();
        		if (testAutoFull != null) {
        			testAutoFull.start();
        		}	
        	} 
        		else if (!autoSwitch1 && !autoSwitch2) {
//        		strafeLeft = new Strafe(.3, 3, true);
//        		if (strafeLeft != null) {
//        			strafeLeft.start();
//        		}
        	} 
        	 SmartDashboardUpdate.DriveTrain();
             SmartDashboardUpdate.ConveyerBelt();
             SmartDashboardUpdate.RearMotorSpool();
             SmartDashboardUpdate.Switches();
             SmartDashboardUpdate.JoystickOutput();
             SmartDashboardUpdate.ActiveInput();
        }
    }
   

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        SmartDashboardUpdate.DriveTrain();
        SmartDashboardUpdate.ConveyerBelt();
        SmartDashboardUpdate.RearMotorSpool();
        SmartDashboardUpdate.Switches();
        SmartDashboardUpdate.JoystickOutput();
        SmartDashboardUpdate.ActiveInput();
    }

    /**
     * This function is called once at the beginning of operator control mode.
     */
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running.

    	if (driveForward != null) {
    		driveForward.cancel();
    	}
        
        if (this.autoSwitch1 && this.autoSwitch2) {
        	if (driveForward != null){
        		driveForward.cancel();
        	}
        } else if (this.autoSwitch1 && !this.autoSwitch2) {
        	if (pickUpOneTote != null) {
        		pickUpOneTote.cancel();
        	}
        } else if (!this.autoSwitch1 && this.autoSwitch2) {
        	if (testAutoFull != null) {
        		testAutoFull.cancel();
        	}
        } else if (!this.autoSwitch1 && !this.autoSwitch2) {
        	if (strafeLeft != null) {
        		strafeLeft.cancel();
        	}
        }
        
        print("Entering teleop mode");
        if (CommandBase.driveTrain.getCurrentCommand() == null) {
        	CommandBase.driveTrain.initDefaultCommand();
        }
                
        SmartDashboardUpdate.DriveTrain();
        SmartDashboardUpdate.ConveyerBelt();
        SmartDashboardUpdate.RearMotorSpool();
        SmartDashboardUpdate.Switches();
        SmartDashboardUpdate.JoystickOutput();
        SmartDashboardUpdate.ActiveInput();
//        SmartDashboardUpdate.PDP();
        SmartDashboardUpdate.ActiveInput();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
    	Scheduler.getInstance().run();
        
        SmartDashboardUpdate.DriveTrain();
        SmartDashboardUpdate.ConveyerBelt();
        SmartDashboardUpdate.RearMotorSpool();
        SmartDashboardUpdate.Switches();
        SmartDashboardUpdate.JoystickOutput();
//        SmartDashboardUpdate.PDP();    
        SmartDashboardUpdate.ActiveInput();
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
    	if (Constants.VERBOSE_OUTPUT) {
    		print("In test mode");
    	}
    	
        LiveWindow.run();
    }
    
    /**
     * Prints a message of any type to the RoboRio Console, which can be accessed with NetConsole for cRIO
     * 
     * <p>
     * Substitute for System.out.println()
     * </p>
     * 
     * @param message Message to print
     */
    public static void print( Object message) {
    	System.out.println(message);
    }
    
    
}
