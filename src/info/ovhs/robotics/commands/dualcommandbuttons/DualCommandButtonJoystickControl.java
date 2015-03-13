package info.ovhs.robotics.commands.dualcommandbuttons;

import info.ovhs.robotics.Constants;
import info.ovhs.robotics.OI;
import info.ovhs.robotics.commands.CommandBase;
import info.ovhs.robotics.commands.conveyer.ConveyerJoystickControl;
import info.ovhs.robotics.commands.rearmotorspool.RearJoystickControl;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DualCommandButtonJoystickControl extends Command {
	
	Command command1 = new ConveyerJoystickControl(OI.operatorController, Constants.OperatorControls.Controller.OperatorController.Axes.Y);
	Command command2 = new RearJoystickControl(OI.operatorController, Constants.OperatorControls.Controller.OperatorController.Axes.Y);

    public DualCommandButtonJoystickControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(CommandBase.dualCommand);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	if (CommandBase.dualCommand.state) {
//    		command1.initialize();
//    	} else {
//    		command2.initialize();
//    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (CommandBase.dualCommand.state && !command1.isRunning()) {
    		command1.start();
    	}
    	
    	if (CommandBase.dualCommand.state && command2.isRunning()){
    		command2.cancel();
    	}
    	if (!CommandBase.dualCommand.state && !command2.isRunning()){
    		command2.start();
    	}
    	
    	if (!CommandBase.dualCommand.state && command1.isRunning()) {
    		command1.cancel();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (command1.isRunning()) {
    		command1.cancel();
    	} 
    	if (command2.isRunning()) {
    		command2.cancel();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
