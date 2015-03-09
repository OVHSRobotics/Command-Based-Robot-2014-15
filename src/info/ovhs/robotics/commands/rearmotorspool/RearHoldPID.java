package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 * Uses PID Controller in order to hold trash can in one position using the rear motor spool
 */
public class RearHoldPID extends PIDCommand {
	
	/**
	 * Proportional PID Constant
	 */
	private static final double k_p = 1;
	/**
	 * Integral PID Constant
	 */
	private static final double k_i = 0.1;
	/**
	 * Derivative PID Constant
	 */
	private static final double k_d = 0;

	/**
	 * Uses PID Controller in order to hold trash can in one position using the rear motor spool
	 */
    public RearHoldPID() {
    	super(k_p, k_i, k_d);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.rearMotorSpool);
    }

    /**
     *  Called just before this Command runs the first time
     */
    protected void initialize() {
    	CommandBase.rearMotorSpool.stop();
    	this.setSetpoint(CommandBase.rearMotorSpool.encoder.getDistance());
    }

    /**
     *  Called repeatedly when this Command is scheduled to run
     */
    protected void execute() {
    	//SmartDashboard.putNumber("PID Setpoint REar", this.getSetpoint());
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
    	CommandBase.rearMotorSpool.stop();
    }

    /**
     *  Called when another command which requires one or more of the same subsystems is scheduled to run
     */
    protected void interrupted() {
    	CommandBase.rearMotorSpool.stop();
    }
    
    /**
     * Retuns the Input to the PID Controller
     */
    @Override
    protected double returnPIDInput() {
    	//Robot.print("Rear spool encoder: " + new Double(CommandBase.rearMotorSpool.encoder.getDistance()).toString());
    	//SmartDashboard.putNumber("Rear Spool Encoder PID Input", CommandBase.rearMotorSpool.encoder.getDistance());
    	return CommandBase.rearMotorSpool.encoder.getDistance();
    }
    
    /**
     * Uses the PID Controller output and moves the rear motor spool motor
     */
    @Override
    protected void usePIDOutput(double output) {
    	CommandBase.rearMotorSpool.setSpeed(output);
    	//Robot.print("Rear speed PID Output: " + output);
    	//SmartDashboard.putNumber("Rear Spool Encoder PID Output", -output);
    }
}
