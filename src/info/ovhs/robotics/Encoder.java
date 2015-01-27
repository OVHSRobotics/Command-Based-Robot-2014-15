package info.ovhs.robotics;

import edu.wpi.first.wpilibj.*;
import info.ovhs.robotics.RobotMap;

public class Encoder extends RobotMap {
	
	public void EncoderInit() {
		encoder.reset();
		encoder.setDistancePerPulse(Constants.EncoderConstants.DISTANCE_PER_PULSE);		
	}
	
	
	/**
	 * Resets the value of the encoder
	 */
	public void Reset () {
		encoder.reset();
	}
	
	/**
	 * Returns the rate of the encoder
	 * 
	 * @return Rate of the encoder
	 */
	public double Rate() {
		return encoder.getRate();
	}
	
	/**
	 * Returns the distance from the encoder
	 * 
	 * @return Distance from Encoder
	 */
	public double Distance() {
		return encoder.getDistance();
	}
	
	/**
	 * Returns the raw value from the encoder
	 * 
	 * @return Encoder Raw Value
	 */
	public double Raw() {
		return encoder.getRaw();
	}
	
	
}
