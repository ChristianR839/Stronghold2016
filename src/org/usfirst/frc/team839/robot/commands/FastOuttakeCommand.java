package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;
import org.usfirst.frc.team839.robot.subsystems.TargetingLight;

import edu.wpi.first.wpilibj.command.Command;

public class  FastOuttakeCommand extends Command
{
    private double power = 1;

	public FastOuttakeCommand()
    {
		this.power = 1;
    	requires(Robot.fastarmintake);

    }
    public FastOuttakeCommand(double duration)
    {
    	this.power = 1;
    	this.setTimeout(duration);
    	requires(Robot.fastarmintake);

    }
    public FastOuttakeCommand(double duration, double power)
    {
    	this.setTimeout(duration);
    	this.power = power;
    	requires(Robot.fastarmintake);

    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
        Robot.fastarmintake.outtake(this.power );
    }

    protected boolean isFinished() 
    {
        return this.isTimedOut();
    }

    protected void end() 
    {
    	Robot.fastarmintake.stop();
    	TargetingLight.off();

    }

    protected void interrupted() 
    {
    	end();
    }
}