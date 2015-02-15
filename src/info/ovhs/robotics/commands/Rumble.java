package info.ovhs.robotics.commands;

import info.ovhs.robotics.OI;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Rumble extends Command {

	double time;
	double initialTime;
	
    public Rumble(double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	OI.startControllerRumble(OI.xboxController);
    	initialTime = System.nanoTime();
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
    	OI.stopControllerRumble(OI.xboxController);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	OI.stopControllerRumble(OI.xboxController);
    }
}
