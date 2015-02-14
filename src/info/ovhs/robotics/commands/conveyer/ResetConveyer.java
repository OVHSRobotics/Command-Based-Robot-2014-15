package info.ovhs.robotics.commands.conveyer;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.Robot;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class ResetConveyer extends PIDCommand {
	
	private static final double k_p = 1.0;
	private static final double k_i = 0.1;
	private static final double k_d = 0;

    public ResetConveyer() {
    	super(k_p, k_i, k_d);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.conveyerBelt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.print(this.toString());
    	CommandBase.conveyerBelt.stop();
    	this.setSetpoint(CommandBase.conveyerBelt.initialEncoderValue);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(CommandBase.conveyerBelt.encoder.getDistance() - CommandBase.conveyerBelt.initialEncoderValue) < Constants.ConveyerBelt.Encoder.RESET_THRESHOLD;
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
    
    @Override    
    public String toString() {
    	return "Resetting Conveyer Belt";
    }
}
