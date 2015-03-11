package info.ovhs.robotics;

import edu.wpi.first.wpilibj.DigitalInput;

public class AutonomousSwitch extends DigitalInput {

	public AutonomousSwitch(int channel) {
		super(channel);
	}
	
	public String toString() {
		if (this.get()) {
			return "1";
		} else {
			return "0";
		}
	}

}
