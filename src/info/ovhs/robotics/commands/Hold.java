package info.ovhs.robotics.commands;

import edu.wpi.first.wpilibj.command.Command;

// TODO: Implement PID loop to actually hold conveyer belt in place

public class Hold extends Command {

    public Hold() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.conveyerBelt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CommandBase.conveyerBelt.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	CommandBase.conveyerBelt.stop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	CommandBase.conveyerBelt.stop();
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	CommandBase.conveyerBelt.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	CommandBase.conveyerBelt.stop();    
    }
}
