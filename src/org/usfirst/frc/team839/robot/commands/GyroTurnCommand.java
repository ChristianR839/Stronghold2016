package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class GyroTurnCommand extends Command//extends PIDController
{
	private static final int RIGHT = 1;
	private static final int LEFT = -1;
	private AHRS ahrs;
	private double degrees;
	private double startAngle;
	private int direction = 1;
	
	public GyroTurnCommand(double degrees)
    {
		super();
		this.setTimeout(5);
		this.degrees = degrees;
		ahrs = Robot.ahrs;
    	ahrs.reset();
		//System.out.println("ahrs.reset(): "+ ahrs.getAngle());
		ahrs.startLiveWindowMode();

    }
	
    protected void initialize()
    {

        try {
        	if (ahrs != null)
        		ahrs.reset();           
    		ahrs.startLiveWindowMode();
    		startAngle = ahrs.getAngle();
        } catch (RuntimeException ex ) {
        	System.out.println("initialize messed up: ");
        	DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        	ex.printStackTrace();
        }
    }

    protected void execute()
    {
    	Robot.drivetrain.setDriveSpeeds(.55, -.55);

    }

    protected boolean isFinished()
    {
    	//System.out.println(ahrs.getAngle() + ">=" + (startAngle + degrees) );
    	return this.isTimedOut() || ahrs.getAngle() >= startAngle + degrees;//ahrs.getAngle()
    }

    protected void end()
    {
    	System.out.println("end");
    	Robot.drivetrain.stop();
    }

    protected void interrupted()
    {
    	end();
    }
    double getAdjustedAngle(double angle)
    {
    	
//		System.out.println("getAdjustedAngle: " + (Math.ceil(ahrs.getAngle())-360));
    	if (Math.ceil(ahrs.getAngle())>360)
    	{
    		return Math.ceil(ahrs.getAngle())-360;
    	}
    	return angle;
    }
}
