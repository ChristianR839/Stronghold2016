package org.usfirst.frc.team839.robot.subsystems;

import org.usfirst.frc.team839.robot.RobotMap;
import org.usfirst.frc.team839.robot.commands.RotateArmCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Accessory extends Subsystem
{
	static CANTalon armMotor = RobotMap.armMotor;

    public void initDefaultCommand() 
    {
    	setDefaultCommand(new RotateArmCommand());
    	
    }   
    
    public void move(double power)
    {
        armMotor.set(power);
    }

    public static void up()
    {
    	armMotor.set(1);
    }
    
    public static void down()
    {
    	armMotor.set(-1);
    }
    
    public static void stop()
    {
    	armMotor.set(0);
    }
    
    public static void enableSoftLimit(double up, double down)
    {
    	armMotor.setForwardSoftLimit(up);
    	armMotor.enableForwardSoftLimit(true);
    	armMotor.setReverseSoftLimit(down);
    	armMotor.enableReverseSoftLimit(true);
    }
    
    public static void disableSoftLimit()
    {
    	armMotor.enableForwardSoftLimit(false);
    	armMotor.enableReverseSoftLimit(false);
    }
}
