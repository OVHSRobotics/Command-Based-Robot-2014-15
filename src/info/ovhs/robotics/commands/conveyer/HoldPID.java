package info.ovhs.robotics.commands.conveyer;

import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *Uses PID Controller in order to hold tote in one position using the conveyer belt
 */
public class HoldPID extends PIDCommand {
	
	private static final double k_p = 1;
	private static final double k_i = 0.1;
	private static final double k_d = 0;
	
    public HoldPID() {
    	super(k_p, k_i, k_d);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.conveyerBelt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CommandBase.conveyerBelt.stop();
    	this.setSetpoint(CommandBase.conveyerBelt.encoder.getDistance());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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

	@Override
	protected double returnPIDInput() {
		return CommandBase.conveyerBelt.encoder.getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		CommandBase.conveyerBelt.setSpeed(output);
	}
}
