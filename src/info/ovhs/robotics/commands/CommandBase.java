package info.ovhs.robotics.commands;

import info.ovhs.robotics.OI;
import info.ovhs.robotics.subsystems.ConveyerBelt;
import info.ovhs.robotics.subsystems.DriveTrain;
import info.ovhs.robotics.subsystems.PIDDriveTrain;
import info.ovhs.robotics.subsystems.RearMotorSpool;
import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	
	public static DriveTrain driveTrain;
	public static OI oi;
	public static ConveyerBelt conveyerBelt;
	public static RearMotorSpool rearMotorSpool;
	public static PIDDriveTrain pidDriveTrain;
	
	public static void init() {
		CommandBase.driveTrain = DriveTrain.getInstance();
		CommandBase.conveyerBelt = ConveyerBelt.getInstance();
		CommandBase.rearMotorSpool = RearMotorSpool.getInstance();
		CommandBase.pidDriveTrain = PIDDriveTrain.getInstance();
		
		// Must be initialized after all subsystems
		CommandBase.oi = OI.getInstance();
	}
}
