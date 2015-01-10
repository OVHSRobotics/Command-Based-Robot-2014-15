package info.ovhs.robotics.commands;

import info.ovhs.robotics.OI;
import info.ovhs.robotics.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	
	protected static DriveTrain driveTrain;
	protected static OI oi;
	
	public static void init() {
		CommandBase.driveTrain = DriveTrain.getInstance();
		CommandBase.oi = OI.getInstance();
	}
}
