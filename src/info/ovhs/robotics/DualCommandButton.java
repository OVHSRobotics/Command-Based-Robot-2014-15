package info.ovhs.robotics;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;

public class DualCommandButton extends Button {

	GenericHID m_joystick;
	int m_buttonNumber;
	/**
	 * Which command to use; true is command 2, false is command 1
	 */
	boolean commandChoice;
	
	Command command1;
	Command command2;
	
	
	
	public DualCommandButton(GenericHID joystick, int buttonNumber, boolean commandChoice, Command command1, Command command2) {
		this.m_joystick = joystick;
		this.m_buttonNumber = buttonNumber;
		this.commandChoice = commandChoice;
		this.command1 = command1;
		this.command2 = command2;
	}
	
	public String getSetting() {
		if (this.commandChoice) {
			return "Command 2";
		} else {
			return "Command 1";
		}
	}

	@Override
	public boolean get() {
		return m_joystick.getRawButton(m_buttonNumber);
	}
	
	public void switchCommand() {
		this.commandChoice = !this.commandChoice;
	}
	
	public void whileHeld() {
		if (this.commandChoice) {
			//whileActive(this.command2);
			if(this.get()) {
				this.command2.start();
			} else {
				this.command2.cancel();
			}
//			super.whileHeld(this.command2);
		} else {
//			whileActive(this.command1);
			if(this.get()) {
				this.command1.start();
			} else {
				this.command1.cancel();
			}
//			super.whileHeld(this.command1);
		}
	}
	
	public void whenPressed() {
		if (this.commandChoice) {
			if(this.get() && !this.command2.isRunning()) {
				this.command2.start();
			} else if (!this.get() && this.command2.isRunning()) {
				//do nothing
			} else {
				//do nothing
			}
		}
			
//			super.whenPressed(this.command2);
	else {
			//whenActive(this.command1);
		if(this.get() && !this.command1.isRunning()) {
			this.command1.start();
		} else if (!this.get() && this.command1.isRunning()) {
			//do nothing
		} else {
			//do nothing
		}
//			super.whenPressed(this.command1);
		}
	}
	
	

}
