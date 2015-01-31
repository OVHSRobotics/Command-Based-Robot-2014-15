package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Uses PID Controller in order to hold tote in one position using the rear motor spool
 */
public class RearHoldPID extends PIDCommand {
	
	private static final double k_p = 1;
	private static final double k_i = 0.1;
	private static final double k_d = 0;

    public RearHoldPID() {
    	super(k_p, k_i, k_d);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.rearMotorSpool);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CommandBase.rearMotorSpool.stop();
    	this.setSetpoint(CommandBase.rearMotorSpool.encoder.getDistance());
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
    	CommandBase.rearMotorSpool.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	CommandBase.rearMotorSpool.stop();
    }
    
    @Override
    protected double returnPIDInput() {
    	return CommandBase.rearMotorSpool.encoder.getDistance();
    }
    
    @Override
    protected void usePIDOutput(double output) {
    	CommandBase.rearMotorSpool.setSpeed(output);
    }
}
