package org.usfirst.frc.team839.robot.subsystems;

import org.usfirst.frc.team839.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *The arm intake
 */
public class ArmIntake extends Subsystem
{
    static CANTalon intakeMotor = RobotMap.intakeMotor;

    public void initDefaultCommand() 
    {
    	
    }
    
    public void intake(double power)
    {
    	intakeMotor.set(power);
    }
    
    public void outtake(double power)
    {
    	intakeMotor.set(-1*power);
    }
    
   
    public void stop()
    {
    	intakeMotor.set(0);
    }
}
