package info.ovhs.robotics.commands.conveyer;

import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Keeps the conveyer in the same place (Does not use PID)
 */
public class Hold extends Command {

	/**
	 * Keeps the conveyer in the same place (Does not use PID)
	 */
    public Hold() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (CommandBase.conveyerBelt);
    }

    /**
     *  Called just before this Command runs the first time
     */
    protected void initialize() {
    	CommandBase.conveyerBelt.stop();
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    }

    /**
     *  Make this return true when this Command no longer needs to run execute()
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     *  Called once after isFinished returns true
     */
    protected void end() {
    	CommandBase.conveyerBelt.stop();
    }

    /**
     *  Called when another command which requires one or more of the same subsystems is scheduled to run
     */
    protected void interrupted() {
    	this.end();
    }
}
