package info.ovhs.robotics.commands.autonomous;

import info.ovhs.robotics.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DefaultAuto extends CommandGroup {
    
    public  DefaultAuto() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new AutoLiftTrashCan(Constants.Autonomous.DefaultAuto.Step1LiftCan.POWER, Constants.Autonomous.DefaultAuto.Step1LiftCan.TIME_SECONDS));
    	addSequential(new DriveAndAI(Constants.Autonomous.DefaultAuto.Step2DriveForwardWithAI.Drive.POWER, Constants.Autonomous.DefaultAuto.Step2DriveForwardWithAI.Drive.TIME_SECONDS,
    			Constants.Autonomous.DefaultAuto.Step2DriveForwardWithAI.Drive.FORWARD, Constants.Autonomous.DefaultAuto.Step2DriveForwardWithAI.AI.POWER,
    			Constants.Autonomous.DefaultAuto.Step2DriveForwardWithAI.AI.IN));
    	addSequential(new Strafe(Constants.Autonomous.DefaultAuto.Step3Strafe.FRONT_LEFT_POWER, Constants.Autonomous.DefaultAuto.Step3Strafe.FRONT_RIGHT_POWER, Constants.Autonomous.DefaultAuto.Step3Strafe.REAR_LEFT_POWER, Constants.Autonomous.DefaultAuto.Step3Strafe.REAR_RIGHT_POWER, 
    			Constants.Autonomous.DefaultAuto.Step3Strafe.TIME_SECONDS, Constants.Autonomous.DefaultAuto.Step3Strafe.RIGHT));
    	addSequential(new DriveAndAI(Constants.Autonomous.DefaultAuto.Step4DriveBackwardWithAI.Drive.POWER, Constants.Autonomous.DefaultAuto.Step4DriveBackwardWithAI.Drive.TIME_SECONDS, Constants.Autonomous.DefaultAuto.Step4DriveBackwardWithAI.Drive.FORWARD, Constants.Autonomous.DefaultAuto.Step4DriveBackwardWithAI.AI.POWER, 
    			Constants.Autonomous.DefaultAuto.Step4DriveBackwardWithAI.AI.IN));
    }
}
