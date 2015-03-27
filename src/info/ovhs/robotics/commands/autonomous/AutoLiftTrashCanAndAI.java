package info.ovhs.robotics.commands.autonomous;

import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLiftTrashCanAndAI extends Command {

	
	private double initialTime;
	private double time;
	private double power;
	private boolean AiIn;
	private double AiSpeed;
				
    public AutoLiftTrashCanAndAI(double time) {
		this(1, time, 1, false);
	}
	
	/**
	 * Drives the robot forward
	 * @param power What power to drive the robot at
	 * @param time How long to drive the robot (in seconds)
	 */
	public AutoLiftTrashCanAndAI(double power, double time) {
		this(power, time, 1,  true);
	}
		
	public AutoLiftTrashCanAndAI(double power, double time, boolean AiIn) {
		this(power, time, 1, AiIn);
	}
	
	public AutoLiftTrashCanAndAI(double power, double time, double AiSpeed, boolean AiIn) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.rearMotorSpool);
    	this.time = time;
    	this.power = power;
    	this.AiIn = AiIn;
    	this.AiSpeed = AiSpeed;
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.initialTime = System.nanoTime();
    	CommandBase.rearMotorSpool.forward(this.power);
    	int AiInInt = this.AiIn ? 1 : 2;
    	switch (AiInInt) {
    	case 1:
    		CommandBase.activeInput.suckInTote(AiSpeed);
    		break;
    	case 2:
    		CommandBase.activeInput.releaseTote(AiSpeed);
    		break;
    	default:
    		break;	
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.nanoTime() >= this.initialTime + this.time * Math.pow(10, 9);
    }

    // Called once after isFinished returns true
    protected void end() {
    	CommandBase.rearMotorSpool.stop();
//    	new Drive(2).start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
