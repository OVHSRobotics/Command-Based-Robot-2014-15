package info.ovhs.robotics;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class JoystickPOVAsButton extends Button {
	
	GenericHID joystick;
	double expected;
	double threshold;

	public JoystickPOVAsButton(GenericHID joystick, double expected, double threshold) {
		this.joystick = joystick;
		this.expected = expected;
		this.threshold = threshold;
	}

	@Override
	public boolean get() {
		if (this.joystick.getPOV() >= (this.expected - this.threshold)) {
			return true;
		} else {
			return false;
		}
	}

}
