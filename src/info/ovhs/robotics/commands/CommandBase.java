package info.ovhs.robotics.commands;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.OI;
import info.ovhs.robotics.Robot;
import info.ovhs.robotics.subsystems.ActiveInput;
import info.ovhs.robotics.subsystems.ConveyerBelt;
import info.ovhs.robotics.subsystems.DriveTrain;
import info.ovhs.robotics.subsystems.RearMotorSpool;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is to hold all of the subsystems on the robot in order to have one instance of the subsystems to work
 * off of.  Call all subsystems as instances in the CommandBase.
 * 
 * <p>
 * INITIALIZE OI LAST!!!
 * </p>
 */
public abstract class CommandBase extends Command {
	
	/**
	 * Instance of the Drive Train Subsystem
	 */
	public static DriveTrain driveTrain;
	/**
	 * Instance of the OI Class
	 */
	public static OI oi;
	/**
	 * Instance of the Conveyer Belt Subsystem
	 */
	public static ConveyerBelt conveyerBelt;
	/**
	 * Instance of the Rear Motor Spool Subsystem
	 */
	public static RearMotorSpool rearMotorSpool;
	/**
	 * Instance of the Active Input Subsystem
	 */
	public static ActiveInput activeInput;
	
	/**
	 * Initializes the subsystems on the robot
	 */
	public static void init() {
		if (Constants.VERBOSE_OUTPUT) {
			Robot.print("Begin CommandBase Init");
		}
		
		if (Constants.VERBOSE_OUTPUT) {
			Robot.print("Begin DriveTrain Instance Creation");
		}
		CommandBase.driveTrain = DriveTrain.getInstance();
		
		if (Constants.VERBOSE_OUTPUT) {
			Robot.print("Begin ConveyerBelt Instance Creation");
		}
		CommandBase.conveyerBelt = ConveyerBelt.getInstance();
		
		if (Constants.VERBOSE_OUTPUT) {
			Robot.print("Begin RearMotorSpool Instance Creation");
		}
		CommandBase.rearMotorSpool = RearMotorSpool.getInstance();
		
		if (Constants.VERBOSE_OUTPUT) {
			Robot.print("Begin ActiveInput Instance Creation");
		}
		CommandBase.activeInput = ActiveInput.getInstance();
		
		if (Constants.VERBOSE_OUTPUT) {
			Robot.print("Begin OI Instance Creation");
		}
		// Must be initialized after all subsystems
		CommandBase.oi = OI.getInstance();
		
		if (Constants.VERBOSE_OUTPUT) {
			Robot.print("End CommandBase Init");
		}
	}
}
