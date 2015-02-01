package info.ovhs.robotics;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public class PIDDrive extends PIDController {

	private static final double k_p = 1;
	private static final double k_i = 0.1;
	private static final double k_d = 0;
	private static final PIDOutput output = null;
	
	public PIDDrive() {
		super(k_p, k_i, k_d, RobotMap.gyro1, output);
	}

}
