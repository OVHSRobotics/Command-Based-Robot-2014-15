package info.ovhs.robotics.commands.conveyer;

import info.ovhs.robotics.commands.CommandBase;
import edu.wpi.first.wpilibj.command.Command;

public class Hold extends Command {
	
	private double desiredDistance;
	private double lastError;
	private long lastTime;
	private double lastCommand;
	private double totalError;
	
	private double k_p = 1;
	private double k_i = 0.1;
	private double k_d = 0;

    public Hold() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.conveyerBelt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CommandBase.conveyerBelt.stop();
    	this.desiredDistance = CommandBase.conveyerBelt.encoder.getDistance();
    	this.lastError = 0;
    	this.lastTime = System.nanoTime();
    	this.lastCommand = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentCommand = this.lastCommand;
    	double error = CommandBase.conveyerBelt.encoder.getDistance() - this.desiredDistance;
    	long currentTime = System.nanoTime();
    	double deltaTime = (currentTime - this.lastTime) / Math.pow(10, 9);
    	this.totalError += error * deltaTime;
    	
    	double deltaCommand = this.k_p * (error) + 
    			this.k_i * (this.totalError) +
    			this.k_d * (error - lastError) / deltaTime;
    	
    	currentCommand -= deltaCommand;
    			
    	// Scale to [-1, 1]
    	if (Math.abs(currentCommand) > 1) {
    		currentCommand = currentCommand / Math.abs(currentCommand);
    	}
    	
    	CommandBase.conveyerBelt.forward(currentCommand);
    	
    	this.lastError = error;
    	this.lastTime = currentTime;
    	this.lastCommand = currentCommand;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
}
