package info.ovhs.robotics.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwapDualCommandState extends Command {

    public SwapDualCommandState() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.dualCommand);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CommandBase.dualCommand.changeState();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
