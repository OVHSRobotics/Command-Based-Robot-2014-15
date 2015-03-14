package info.ovhs.robotics.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTest1 extends CommandGroup {
    
    public  AutoTest1() {
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
    	
    	addSequential(new AutoLiftTrashCan(1, 4.5));
    	addSequential(new DriveAndAI(.4, 1.1, true, 1, true));
    	addSequential(new Strafe(1, 1, .8, .8, 1.75, false));
    	addSequential(new DriveAndAI(.4, 1.1, false, 1, false));

    }
}
