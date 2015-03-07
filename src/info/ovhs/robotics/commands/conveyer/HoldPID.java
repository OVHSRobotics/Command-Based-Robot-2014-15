package info.ovhs.robotics.commands.conveyer;

import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *Uses PID Controller in order to hold tote in one position using the conveyer belt
 */
public class HoldPID extends PIDCommand {
	
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
	 * Uses PID Controller in order to hold tote in one position using the conveyer belt
	 */
    public HoldPID() {
    	super(k_p, k_i, k_d);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.conveyerBelt);
    }

    /**
     *  Called just before this Command runs the first time
     */
    protected void initialize() {
    	CommandBase.conveyerBelt.stop();
    	this.getPIDController().enable();
    	this.setSetpoint(CommandBase.conveyerBelt.encoder.getDistance());
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
     * Called when another command which requires one or more of the same subsystems is scheduled to run
     */
    protected void interrupted() {
    	CommandBase.conveyerBelt.stop();
    }

    /**
     * Returns the Input to the PID Controller
     */
	@Override
	protected double returnPIDInput() {
		SmartDashboard.putNumber("Conveyer PID Input", CommandBase.conveyerBelt.encoder.getDistance());
		return CommandBase.conveyerBelt.encoder.getDistance();
	}

	/**
	 * Uses the PID Controller output
	 * 
	 * <p>
	 * Sets the conveyer belt motor to the PID Controller's output value
	 * </p>
	 */
	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("Conveyer PID Output", output);
		CommandBase.conveyerBelt.setSpeed(output);
	}
}
