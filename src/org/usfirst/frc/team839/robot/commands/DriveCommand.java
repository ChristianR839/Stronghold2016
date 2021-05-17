package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;
import org.usfirst.frc.team839.robot.RobotMap;
import org.usfirst.frc.team839.robot.subsystems.Accessory;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command
{
	private double clicksToTravel = 0;
	private double targetDistance = 0;
	final double clicksPerInch = 812/(31.42/2.54);//365.76
	//final double practiceClicksPerInch = 1440/(31.42/2.54);//365.76
	private AHRS ahrs;
	double angle = 0;
	double rightfactor =0; 
	double leftfactor = 0;
	double output = 0;
	private double startAngle;
	double initialEncoderPosition = 0;
	double liftDistance = 0;
	double dropDistance = 0;
	double armPosition = 0;
	boolean taperSpeed = true;
	public DriveCommand(double distanceInInches, double turnAngle, double liftDistance, double dropDistance, boolean taperSpeed)
    {
		requires(Robot.drivetrain);
//		System.out.println("DriveCommand()) ");
    	
	   	RobotMap.backRightMotor.setEncPosition(0);
		clicksToTravel = clicksPerInch * distanceInInches;
//    	System.out.println("clicksToTravel " + clicksToTravel);
 	    this.startAngle = turnAngle;	
 	    this.liftDistance = liftDistance * clicksPerInch;
 	    this.dropDistance = dropDistance * clicksPerInch;
 	    this.taperSpeed = taperSpeed;
		ahrs = Robot.ahrs;
    }
	public DriveCommand(double distanceInInches, double turnAngle, double maxSpeed)
    {
		requires(Robot.drivetrain);
//		System.out.println("DriveCommand()) ");
    	
	   	RobotMap.backRightMotor.setEncPosition(0);
		clicksToTravel = clicksPerInch * distanceInInches;
//    	System.out.println("clicksToTravel " + clicksToTravel);
 	    this.startAngle = turnAngle;	
		
		ahrs = Robot.ahrs;
    }
	
    protected void initialize()
    {
    	ahrs.reset();
    	ahrs.resetDisplacement();
    	
    	Accessory.enableSoftLimit(initialEncoderPosition - this.liftDistance, 0);
    	
    	//startAngle = ahrs.getAngle();
//    	RobotMap.backRightMotor.setEncPosition(0);
//    	System.out.println("init.initialEncoderPosition " + RobotMap.backRightMotor.getEncPosition());
    	initialEncoderPosition = RobotMap.backRightMotor.getEncPosition() ;
    	targetDistance = initialEncoderPosition - clicksToTravel;
    	this.armPosition = RobotMap.armMotor.getEncPosition();
//    	System.out.println("init.targetDistance " + targetDistance);
    }
    
    @Override
    protected void execute()
    {
		angle = startAngle - ahrs.getYaw();
//		System.out.println("current.angle : " + ahrs.getYaw());
//		System.out.println("delta.angle : " + angle);
		double position = RobotMap.backRightMotor.getEncPosition();
		rightfactor = Math.abs(angle<0?(angle/50):0); 
		leftfactor = Math.abs(angle>0?(angle/50):0); 
		output = 1;
		if(this.taperSpeed)
		{
			output = (clicksToTravel-Math.abs(position))/clicksToTravel;
			output = (output>.5)?output:.5;
		}
//		System.out.println("execute.output : " + output);
//		System.out.println("execute.leftFactor: " + leftfactor);
//		System.out.println("execute.rightFactor : " + rightfactor);
		Robot.drivetrain.setDriveSpeeds(output + leftfactor,output + rightfactor);
//		System.out.println("liftdistance " + this.liftDistance);
//		System.out.println("position " + position);
 	    if(position <= (initialEncoderPosition - this.liftDistance) && position > (initialEncoderPosition - this.dropDistance))
 	    {
// 	    	System.out.println(position);
 	    	if(RobotMap.armMotor.getEncPosition()> armPosition - 400)
 	    	{
// 	    		System.out.println("ARM Position" + RobotMap.armMotor.getEncPosition());
 	    		Accessory.down();
 	    	}
 	    	else
 	    	{
 	    		Accessory.stop();
 	    	}
 	    }


    }

    @Override
    protected boolean isFinished()
    {
//       	System.out.println(RobotMap.backRightMotor.getEncPosition() + " out of " + (targetDistance));
//      	System.out.println("currentEncoderPosition " + RobotMap.backRightMotor.getEncPosition());
//      	System.out.println("initialEncoderPosition " + initialEncoderPosition);
    	return RobotMap.backRightMotor.getEncPosition() <= targetDistance;
    }

    @Override
    protected void end()
    {
    	Accessory.disableSoftLimit();
    	Accessory.stop();
    	Robot.drivetrain.stop();
    }

    @Override
    protected void interrupted()
    {
    	end();
    }
}
