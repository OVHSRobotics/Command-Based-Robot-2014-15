package info.ovhs.robotics.commands.autonomous;

import info.ovhs.robotics.commands.conveyer.DropTote;
import info.ovhs.robotics.commands.conveyer.LiftTote;
import info.ovhs.robotics.commands.rearmotorspool.RearLiftTrashCan;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Lifts one trash can, drives, then lifts 3 totes sequentially, driving in between each tote pickup, then drops them all
 */
public class LiftOneTrashCanAndThreeTotesThenDropAll extends CommandGroup {
    
	/**
	 * Lifts one trash can, drives, then lifts 3 totes sequentially, driving in between each tote pickup, then drops them all
	 */
    public  LiftOneTrashCanAndThreeTotesThenDropAll() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    		addSequential(new RearLiftTrashCan());
    		
    		//Drive forward set power value and number of seconds
    		addSequential(new Drive(1, 2, true));
    		
    		addSequential(new LiftTote());
    		//Completely lift first tote
    		
    		//Drive forward set power value and number of seconds
    		addSequential(new Drive(1,2, true));
    		
    		addSequential(new LiftTote());
    		//Completely lift second tote
    		
    		//Drive forward set power value and number of seconds
    		addSequential(new Drive(1,2, true));
    		
    		addSequential(new LiftTote());
    		//Completely lift third tote
    		
    		addSequential(new DropTote());
    		addSequential(new DropTote());
    		addSequential(new DropTote());
    		//Drop all three totes
    		
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
