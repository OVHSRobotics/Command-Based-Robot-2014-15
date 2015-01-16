
package info.ovhs.robotics.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ConveyerBelt extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	protected static ConveyerBelt instance;
	
	public static ConveyerBelt getInstance() {
		if (ConveyerBelt.instance == null) {
			ConveyerBelt.instance = new ConveyerBelt();
		}
		
		return ConveyerBelt.instance;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

