package info.ovhs.robotics.commands.autonomous;

import info.ovhs.robotics.commands.conveyer.*;
import info.ovhs.robotics.commands.rearmotorspool.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LiftOneTrashCanAndOneTote extends CommandGroup {
    
    public  LiftOneTrashCanAndOneTote() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new RearLiftTrashCan());
    		
    	//Drive forward set power value and number of seconds;
    		addSequential(new Drive(1, 2, true));
    	
    		addSequential(new LiftTote());
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
    }
}
