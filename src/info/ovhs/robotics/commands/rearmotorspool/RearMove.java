package info.ovhs.robotics.commands.rearmotorspool;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.RobotMap;
import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RearMove extends Command {
	
	boolean forward = true;

    public RearMove(boolean forward) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.rearMotorSpool);
    	if (forward) {
    		this.forward = true;
    	} else {
    		this.forward = false;
    	}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (this.forward) {
    		CommandBase.rearMotorSpool.forward(Constants.RearMotorSpool.MANUAL_MOVE_SPEED);
    	} else if (!this.forward && RobotMap.robotLimitSwitch.get()) {
    		CommandBase.rearMotorSpool.backward(0);
    	} else if (!this.forward) {
    		CommandBase.rearMotorSpool.backward(Constants.RearMotorSpool.MANUAL_MOVE_SPEED);
    	} else {
    		//Impossible case
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
