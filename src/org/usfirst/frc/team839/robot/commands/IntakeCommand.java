package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class  IntakeCommand extends Command
{
	double power = 1;
    public IntakeCommand(double duration)
    {
    	this.setTimeout(duration);
    	this.power = .5;
    	requires(Robot.armintake);
    }
    
    public IntakeCommand()
    {
    	requires(Robot.armintake);
    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
        Robot.armintake.intake(this.power);
        Robot.fastarmintake.intake(this.power);
    }

    protected boolean isFinished() 
    {
    	return this.isTimedOut();
    }

    protected void end() 
    {
    	Robot.armintake.stop();
    	Robot.fastarmintake.stop();
    }

    protected void interrupted() 
    {
    	end();
    }
}