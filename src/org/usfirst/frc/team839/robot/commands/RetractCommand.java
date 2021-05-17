package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RetractCommand extends Command
{
	double power = 1;
    public RetractCommand(int duration)
    {
    	this.setTimeout(duration);
    	this.power = .5;
    	requires(Robot.armscale);
    }
    
    public RetractCommand()
    {
    	requires(Robot.armscale);
    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
        Robot.armscale.retract(this.power);
    }

    protected boolean isFinished() 
    {
    	return this.isTimedOut();
    }

    protected void end() 
    {
    	Robot.armscale.stop();

    }

    protected void interrupted() 
    {
    	end();
    }
}