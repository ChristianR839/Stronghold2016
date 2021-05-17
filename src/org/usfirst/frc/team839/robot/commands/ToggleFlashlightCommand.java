package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;
import org.usfirst.frc.team839.robot.subsystems.TargetingLight;

import edu.wpi.first.wpilibj.command.Command;

public class  ToggleFlashlightCommand extends Command
{

	public ToggleFlashlightCommand()
    {
	   	requires(Robot.targetingLight);
	    
    }

    protected void initialize() 
    {
    	System.out.println("light command");
    	TargetingLight.toggle();

    }

    protected void execute() 
    {

    }

    protected boolean isFinished() 
    {
        return true;
    }

    protected void end() 
    {

    }

    protected void interrupted() 
    {
    	end();
    }
}