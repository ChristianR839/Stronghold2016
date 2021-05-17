package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;
import org.usfirst.frc.team839.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class DrivePIDCommand extends PIDCommand
{
	private double clicksToTravel = 0;
	double clicksPerInch = 1440/(31.42/2.54);
	
	private AHRS ahrs;
	
	public DrivePIDCommand(double distanceInInches)
    {
		super(.8, .002, 0);
		requires(Robot.drivetrain);
		System.out.println("DriveCommand()) ");
    	RobotMap.backRightMotor.setEncPosition(0);
    	RobotMap.backLeftMotor.setEncPosition(0);
    	setInputRange(0,100000);
		ahrs = Robot.ahrs;
    	ahrs.reset();

		clicksToTravel = clicksPerInch * distanceInInches;
	   	setSetpoint(clicksToTravel);

    	System.out.println("clicksToTravel " + clicksToTravel);

    }
	
    protected void initialize()
    {
    	RobotMap.backRightMotor.setEncPosition(0);
    	RobotMap.backLeftMotor.setEncPosition(0);
    }
    @Override
    protected void execute()
    {
    }

    @Override
    protected boolean isFinished()
    {
    	System.out.println("isFinished: " + (clicksToTravel - Math.abs((RobotMap.backRightMotor.getEncPosition()))));
    	return Math.abs(clicksToTravel - Math.abs((RobotMap.backRightMotor.getEncPosition()))) < 30;
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

	@Override
	protected double returnPIDInput()
	{
//		System.out.println("returnPIDInput: " + -1*RobotMap.backRightMotor.getEncPosition());
		return -1*RobotMap.backRightMotor.getEncPosition();
	}

	@Override
	protected void usePIDOutput(double output)
	{
		double angle = ahrs.getAngle();
		double rightfactor = angle<180?angle/100:0; 
		double leftfactor = angle>180?(365-angle)/100:0;
//		System.out.println("DrivePIDCommand.usePIDOutput.angle = " + (angle>0&&angle<180?angle:(365.0-angle)));
//		System.out.println("usePIDOutput.output : " + output);
//		System.out.println("usePIDOutput.leftFactor: " + leftfactor);
//		System.out.println("usePIDOutput.rightFactor : " + rightfactor);
		Robot.drivetrain.setDriveSpeeds(output*.7 + leftfactor,output*.7 + rightfactor);
	}
}
