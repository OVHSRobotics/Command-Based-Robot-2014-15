package info.ovhs.robotics.commands;

import info.ovhs.robotics.OI;
import info.ovhs.robotics.Robot;
import info.ovhs.robotics.subsystems.ConveyerBelt;
import info.ovhs.robotics.subsystems.DriveTrain;
import info.ovhs.robotics.subsystems.RearMotorSpool;
import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	
	public static DriveTrain driveTrain;
	public static OI oi;
	public static ConveyerBelt conveyerBelt;
	public static RearMotorSpool rearMotorSpool;
	
	public static void init() {
		Robot.print("Begin CommandBase Init");
		
		Robot.print("Begin DriveTrain Instance Creation");
		CommandBase.driveTrain = DriveTrain.getInstance();
		
		Robot.print("Begin ConveyerBelt Instance Creation");
		CommandBase.conveyerBelt = ConveyerBelt.getInstance();
		
		Robot.print("Begin RearMotorSpool Instance Creation");
		CommandBase.rearMotorSpool = RearMotorSpool.getInstance();
		
		Robot.print("Begin OI Instance Creation");
		// Must be initialized after all subsystems
		CommandBase.oi = OI.getInstance();
		
		Robot.print("End CommandBase Init");
	}
}
