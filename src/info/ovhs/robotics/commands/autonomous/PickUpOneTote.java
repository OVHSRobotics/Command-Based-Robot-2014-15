package info.ovhs.robotics.commands.autonomous;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PickUpOneTote extends Command {
	private double initialValueEncoder;

    public PickUpOneTote() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.conveyerBelt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initialValueEncoder = CommandBase.conveyerBelt.encoder.getDistance();
    	CommandBase.conveyerBelt.forward(.04);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return CommandBase.conveyerBelt.encoder.getDistance() - initialValueEncoder >= Constants.FieldElements.TOTE_HEIGHT_INCHES / 12;
    }

    // Called once after isFinished returns true
    protected void end() {
    	CommandBase.conveyerBelt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
