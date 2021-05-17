package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class  OuttakeCommand extends Command
{
    private double power = 1;

	public OuttakeCommand()
    {
    	requires(Robot.armintake);

    }
	
    public OuttakeCommand(double duration)
    {
    	this.setTimeout(duration);
    	requires(Robot.armintake);

    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
        Robot.armintake.outtake(this.power );
    }

    protected boolean isFinished() 
    {
        return this.isTimedOut();
    }

    protected void end() 
    {
    	Robot.armintake.stop();

    }

    protected void interrupted() 
    {
    	end();
    }
}