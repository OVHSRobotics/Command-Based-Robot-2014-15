package info.ovhs.robotics;

public class Encoder extends RobotMap {
	
	public void EncoderReader() {
		encoder.reset();
		encoder.setDistancePerPulse(Constants.EncoderConstants.DISTANCE_PER_PULSE);
		
	}
	
	
	/**
	 * Resets the value of the encoder
	 */
	public void Reset () {
		encoder.reset();
	}
	
	public double Rate() {
		return encoder.getRate();
	}
	
}
