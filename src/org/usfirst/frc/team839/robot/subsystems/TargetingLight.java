package org.usfirst.frc.team839.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The scale arm
 */
public class TargetingLight extends Subsystem
{
	static Relay spike = new Relay(1);
	
	public void initDefaultCommand() 
    {
    	
    } 
	
	public static void off()
	{
		spike.set(Value.kOff);
	}
	
	public static void toggle()
	{
		
		//I2C i2c 
		System.out.println("spike.get() " + spike.get());
		if(spike.get()== Relay.Value.kOff)
		{
			System.out.println("turn on");
			spike.set(Relay.Value.kForward);
		}
		else
		{
			System.out.println("turn off");
			spike.set(Relay.Value.kOff);
		}
	}
    public void stop()
    {
    	spike.set(Value.kOff);
    }
}
