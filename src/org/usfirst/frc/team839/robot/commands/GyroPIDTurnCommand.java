package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class GyroPIDTurnCommand extends PIDCommand
{
	private static final int RIGHT = 1;
	private static final int LEFT = -1;
	private AHRS ahrs;
	private double degrees;
	private double startAngle;
	private int direction = 1;
	
	public GyroPIDTurnCommand(double degrees)
    {
		
		super(.10, .001, 0,.001);
		this.degrees = degrees;
		ahrs = Robot.ahrs;
    	ahrs.reset();
    	setInputRange(0,360);
    	setSetpoint(degrees);
    	
    	System.out.println("ahrs.reset(): "+ ahrs.getAngle());
		ahrs.startLiveWindowMode();
		LiveWindow.addActuator("gyro", "gyro", getPIDController());
    }

    protected void initialize()
    {
     }

    protected void execute()
    {


    }

    protected boolean isFinished()
    {
    	return Math.abs(ahrs.getAngle() - degrees) < .5;
    }

    protected void end()
    {
    	System.out.println("end");
    	Robot.drivetrain.setDriveSpeeds(0, 0);
    }

    protected void interrupted()
    {
    	end();
    }
    double getAdjustedAngle(double angle)
    {
    	if (Math.ceil(ahrs.getAngle())==360)
    	{
    		angle = 0;
    	}
    	System.out.println("getAdjustedAngle: " + angle);
    	return angle;
    }


	@Override
	protected double returnPIDInput()
	{
		return getAdjustedAngle(ahrs.getAngle());
	}

	@Override
	protected void usePIDOutput(double output)
	{
		System.out.println("usePIDOutput " + output);
		Robot.drivetrain.setDriveSpeeds(output*0.7, -output*0.7);

	}
}
