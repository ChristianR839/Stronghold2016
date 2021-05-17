package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnCommand extends Command
{
	public TurnCommand()
    {
		requires(Robot.drivetrain);
    }
	
	public TurnCommand(double timeout)
    {
		super(timeout);
		requires(Robot.drivetrain);
    }
	
    protected void initialize()
    {

    }

    @Override
    protected void execute()
    {
    	Robot.drivetrain.setDriveSpeeds(-.5, .5);
    }

    @Override
    protected boolean isFinished()
    {
    	return this.isTimedOut();
    }

    @Override
    protected void end()
    {
    	Robot.drivetrain.stop();
    }

    @Override
    protected void interrupted()
    {
    	end();
    }
}
