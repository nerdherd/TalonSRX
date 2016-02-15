
package org.usfirst.frc.team687.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	CANTalon talon;
	Joystick joy;
	
	Compressor c;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	talon = new CANTalon(1);
    	
    	talon.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
    	
    	talon.configEncoderCodesPerRev(1024);
    	
    	talon.changeControlMode(CANTalon.TalonControlMode.Speed);
    	
    	talon.configNominalOutputVoltage(0, 0);
    	talon.configPeakOutputVoltage(-12, 12);
    	
    	talon.setPID(0.125, 0, 0);
    	
    	joy = new Joystick(0);
    	
    	c = new Compressor();
    	c.start();
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	double scalar = (joy.getZ() + 1) * 5;
        if(joy.getRawButton(1))	{
        	talon.set(60*scalar);
        }	else	{
        	talon.set(0);
        }
        
        SmartDashboard.putNumber("Desired", scalar * 60);
        SmartDashboard.putNumber("Vel", talon.getEncVelocity());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
