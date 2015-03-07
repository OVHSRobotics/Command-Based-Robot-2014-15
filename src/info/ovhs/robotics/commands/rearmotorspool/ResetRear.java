package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.Robot;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Uses a PID Controller to reset the rear motor spool to the original location
 */
public class ResetRear extends PIDCommand {
	
	/**
	 * Proportional PID Constant
	 */
	private static final double k_p = 1.0;
	/**
	 * Integral PID Constant
	 */
	private static final double k_i = 0.1;
	/**
	 * Derivative PID Constant
	 */
	private static final double k_d = 0;

	/**
	 * Uses a PID Controller to reset the rear motor spool to the original location
	 */
    public ResetRear() {
    	super(k_p, k_i, k_d);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(CommandBase.rearMotorSpool);
    }

    /**
     *  Called just before this Command runs the first time
     */
    protected void initialize() {
    	Robot.print(this.toString());
    	CommandBase.rearMotorSpool.stop();
    	this.setSetpoint(CommandBase.rearMotorSpool.initialEncoderValue);
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
    	return Math.abs(CommandBase.rearMotorSpool.encoder.getDistance() - CommandBase.rearMotorSpool.initialEncoderValue) < Constants.RearMotorSpool.Encoder.RESET_THRESHOLD;
    }

    /**
     *  Called once after isFinished returns true
     */
    protected void end() {
    	CommandBase.rearMotorSpool.stop();
    }

    /**
     *  Called when another command which requires one or more of the same subsystems is scheduled to run
     */
    protected void interrupted() {
    	CommandBase.rearMotorSpool.stop();
    }
    
    /**
     * Returns the PID controller input
     */
    @Override
    protected double returnPIDInput() {
    	return CommandBase.rearMotorSpool.encoder.getDistance();
    }
    
    /**
     * Uses the PID Controller Output and sets the rear motor spool motor
     */
    @Override
    protected void usePIDOutput(double output) {
    	CommandBase.rearMotorSpool.setSpeed(output);
    }
    
    /**
     * String representation of the command
     */
    @Override
    public String toString() {
    	return "Resetting RearMotorSpool";
    }
}
