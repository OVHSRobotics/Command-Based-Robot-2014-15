package info.ovhs.robotics.commands.autonomous;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoReleaseTote extends Command {

	private double moveSpeed;
	private double time;
	private double initialTime;
	
    public AutoReleaseTote() {
    	this(Constants.ActiveIntake.AUTO_MOVE_SPEED, 3);
    }
    
    public AutoReleaseTote(double moveSpeed, double time) { 
    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.activeInput);
    	this.moveSpeed = moveSpeed;
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.initialTime = System.nanoTime();
    	CommandBase.activeInput.releaseTote(this.moveSpeed);
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
    	CommandBase.activeInput.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	CommandBase.activeInput.stop();
    }
}
