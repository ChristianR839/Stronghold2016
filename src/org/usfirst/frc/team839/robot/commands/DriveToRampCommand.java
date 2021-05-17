package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;
import org.usfirst.frc.team839.robot.RobotMap;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class DriveToRampCommand extends Command
{
	private double clicksToTravel = 0;
	private double targetDistance = 0;
	//final double clicksPerInch = 812/(31.42/2.54);//365.76
	final double practiceClicksPerInch = 1440/(31.42/2.54);//365.76
	private AHRS ahrs;
	double angle = 0;
	double rightfactor =0; 
	double leftfactor = 0;
	double output = 0;
	private double startAngle;
	double initialEncoderPosition = 0;
	
	public DriveToRampCommand(double distanceInInches, double turnAngle)
    {
		requires(Robot.drivetrain);
//		System.out.println("DriveCommand()) ");
    	
	   	RobotMap.backRightMotor.setEncPosition(0);
		clicksToTravel = practiceClicksPerInch * distanceInInches;
//    	System.out.println("clicksToTravel " + clicksToTravel);
 	    this.startAngle = turnAngle;	
		
		ahrs = Robot.ahrs;
    }
	public DriveToRampCommand(double distanceInInches, double turnAngle, double maxSpeed)
    {
		requires(Robot.drivetrain);
//		System.out.println("DriveCommand()) ");
    	
	   	RobotMap.backRightMotor.setEncPosition(0);
		clicksToTravel = practiceClicksPerInch * distanceInInches;
//    	System.out.println("clicksToTravel " + clicksToTravel);
 	    this.startAngle = turnAngle;	
		
		ahrs = Robot.ahrs;
    }
	
    protected void initialize()
    {
    	ahrs.reset();
    	ahrs.resetDisplacement();

    	//startAngle = ahrs.getAngle();
//    	RobotMap.backRightMotor.setEncPosition(0);
//    	System.out.println("init.initialEncoderPosition " + RobotMap.backRightMotor.getEncPosition());
    	initialEncoderPosition = RobotMap.backRightMotor.getEncPosition() ;
    	targetDistance = initialEncoderPosition - clicksToTravel;
//    	System.out.println("init.targetDistance " + targetDistance);
    }
    
    @Override
    protected void execute()
    {
		angle = startAngle - ahrs.getYaw();
//		System.out.println("current.angle : " + ahrs.getYaw());
//		System.out.println("delta.angle : " + angle);
			
		rightfactor = Math.abs(angle<0?(angle/50):0); 
		leftfactor = Math.abs(angle>0?(angle/50):0); 

		output = (clicksToTravel-Math.abs(RobotMap.backRightMotor.getEncPosition()))/clicksToTravel;
		output = (output>.5)?output:.5;
//		System.out.println("execute.output : " + output);
//		System.out.println("execute.leftFactor: " + leftfactor);
//		System.out.println("execute.rightFactor : " + rightfactor);
		Robot.drivetrain.setDriveSpeeds(output + leftfactor,output + rightfactor);
    }

    @Override
    protected boolean isFinished()
    {
 //       	System.out.println(RobotMap.backRightMotor.getEncPosition() + " out of " + (targetDistance));
//      	System.out.println("currentEncoderPosition " + RobotMap.backRightMotor.getEncPosition());
//      	System.out.println("initialEncoderPosition " + initialEncoderPosition);
        	//RobotMap.backRightMotor.getEncPosition() <= targetDistance 
           	return ahrs.getRoll()> 10;
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
