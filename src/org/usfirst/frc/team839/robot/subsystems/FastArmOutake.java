package org.usfirst.frc.team839.robot.subsystems;

import org.usfirst.frc.team839.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *The arm intake
 */
public class FastArmOutake extends Subsystem
{

    static CANTalon intakeMotor2 = RobotMap.intakeMotor2;

    public void initDefaultCommand() 
    {
    	
    }
    
    public void intake(double power)
    {
    	intakeMotor2.set(.5*power);
    }
    
    public void outtake(double power)
    {
    	intakeMotor2.set(-power);
    }
    
    public void stop()
    {
    	intakeMotor2.set(0);
    }
}
