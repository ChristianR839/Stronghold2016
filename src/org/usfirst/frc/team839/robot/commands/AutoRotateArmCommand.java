package org.usfirst.frc.team839.robot.commands;

import java.util.Calendar;

import org.usfirst.frc.team839.robot.Robot;
import org.usfirst.frc.team839.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoRotateArmCommand extends Command
{
	double maxUp = 0;
	double maxDown = 0;
	double startPosition = 0;
	double maxTotal = 4600;
	int distance = 0;
	double timeInMillis = 0;
	public AutoRotateArmCommand(int distance)
	{
		this.setTimeout(5);
		requires( Robot.accessory );
		this.distance = distance;
		RobotMap.armMotor.setEncPosition(0);
		SmartDashboard.putNumber("Arm Position", RobotMap.armMotor.getEncPosition());
		timeInMillis = Calendar.getInstance().getTimeInMillis();
		startPosition = RobotMap.armMotor.getEncPosition();
	}
	 
	@Override
	protected void initialize()
	{
		startPosition = RobotMap.armMotor.getEncPosition();
	}

	@Override
	protected void execute()
	{
		 if (distance> 0)
			 Robot.accessory.move(.9);
		 else
			 Robot.accessory.move(-.9);
    }

	    @Override
	    protected boolean isFinished()
	    {
	    	SmartDashboard.putNumber("Arm Position", RobotMap.armMotor.getEncPosition());
//	        System.out.println(startPosition + distance);
	        if (distance> 0)
	        	return (this.isTimedOut())||(RobotMap.armMotor.getEncPosition()>= startPosition + distance*.9);
	        else
	        	return (this.isTimedOut())||(RobotMap.armMotor.getEncPosition()<= startPosition + distance*.9);
	    }

	    @Override
	    protected void end()
	    {
//	        Robot.verticalLift.positionMode(false);
	        Robot.accessory.move(0);
	    }

	    @Override
	    protected void interrupted()
	    {
	        end();
	    }

}
