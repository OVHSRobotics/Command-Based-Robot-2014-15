package info.ovhs.robotics;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class JoystickPOVAsButton extends Button {
	
	GenericHID joystick;
	double expected;
	double threshold;

	/**
	 * Uses a Joystick POV axis as a button
	 * 
	 * @param joystick Which joystick to use
	 * @param expected Expected POV Value
	 * @param threshold Threshold to read true for button
	 */
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
