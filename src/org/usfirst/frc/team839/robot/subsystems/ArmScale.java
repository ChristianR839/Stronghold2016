package org.usfirst.frc.team839.robot.subsystems;

import org.usfirst.frc.team839.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The scale arm
 */
public class ArmScale extends Subsystem
{
	static Talon scaleMotor0 = RobotMap.scaleMotor0;
	static Talon scaleMotor1 = RobotMap.scaleMotor1;
	
	public void initDefaultCommand() 
    {
    	
    }
	
    public void extend(double power)
    {
    	scaleMotor0.set(power);
    	scaleMotor1.set(power);
    }
    
    public void retract(double power)
    {
    	scaleMotor0.set(-1*power);
    	scaleMotor1.set(-1*power);
    }
    
    public void stop()
    {
    	scaleMotor0.set(0);
    	scaleMotor1.set(0);
    }
}
