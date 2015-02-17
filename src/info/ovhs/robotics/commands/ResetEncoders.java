package info.ovhs.robotics.commands;

import info.ovhs.robotics.Robot;
import info.ovhs.robotics.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetEncoders extends Command {
	
	private double initialTime;

    public ResetEncoders() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (CommandBase.conveyerBelt);
    	requires (CommandBase.rearMotorSpool);
    	this.initialTime = System.nanoTime();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.print("DO NOT MOVE CONVEYER OR REAR MOTOR SPOOL; ENCODERS RESETTING"); 
    	for (int i = 2; i < 0; i--) {
    		Robot.print("Resetting in " + i + "seconds.");
    	}
    	if (System.nanoTime() > initialTime + 2 * Math.pow(10, 9)) {
    		RobotMap.resetEncoders();
    	}
    	Robot.print("Finished Resetting Encoders");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
