package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;
import org.usfirst.frc.team839.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class RotateArmCommand extends Command
{
	double maxUp = 0;
	double maxDown = 0;
	public RotateArmCommand()
	{
		requires( Robot.accessory );
		RobotMap.armMotor.setEncPosition(0);
	}
	 
	@Override
	protected void initialize()
	{
	}

	@Override
	protected void execute()
	{
	    double y = Robot.oi.accessoryController.getY();
        if(Math.abs(y) >= 0.1)
        {
            Robot.accessory.move(-y);
        }
        else
        {
        	Robot.accessory.move(0);
        }      
//		SmartDashboard.putNumber("Arm Position", RobotMap.armMotor.getEncPosition());
        
    }

	    @Override
	    protected boolean isFinished()
	    {
	        return false;
	    }

	    @Override
	    protected void end()
	    {

	        Robot.accessory.move(0);
	    }

	    @Override
	    protected void interrupted()
	    {
	        end();
	    }

}
