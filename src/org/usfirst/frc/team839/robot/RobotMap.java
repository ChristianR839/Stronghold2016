package org.usfirst.frc.team839.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap
{
	public static CANTalon frontRightMotor;
	public static CANTalon frontLeftMotor;
	public static CANTalon backRightMotor;
	public static CANTalon backLeftMotor;
	
	public static CANTalon armMotor;
	public static CANTalon intakeMotor;
	public static CANTalon intakeMotor2;
	public static Talon scaleMotor0;
	public static Talon scaleMotor1;
	public static PWM pwm;
	
	private static final int frontRightMotorID = 5;
	private static final int frontLeftMotorID = 3;
	private static final int backRightMotorID = 4;
	private static final int backLeftMotorID = 2;


	private static final int armMotorID = 7;
	private static final int intakeMotorID = 6;
	private static final int intakeMotor2ID = 8; //check in with manuf., orig. is 10
	private static final int scaleMotorID0 = 10; //Check ID in with Manufacturing, orig. is 8
	private static final int scaleMotorID1 = 9; //Check ID in with Manufacturing
	
	public static RobotDrive robotDrive;
	
	public static void init()
	{
		frontRightMotor = new CANTalon(frontRightMotorID);
		frontLeftMotor = new CANTalon(frontLeftMotorID);
		backRightMotor = new CANTalon(backRightMotorID);
		backLeftMotor = new CANTalon(backLeftMotorID);

/*		frontRightMotor.setVoltageRampRate(18);
		frontLeftMotor.setVoltageRampRate(18);
		backRightMotor.setVoltageRampRate(18);
		backLeftMotor.setVoltageRampRate(18);*/
		
		armMotor = new CANTalon(armMotorID);
		armMotor.setEncPosition(0);
		intakeMotor = new CANTalon(intakeMotorID);
		intakeMotor2 = new CANTalon (intakeMotor2ID);
		scaleMotor0 = new Talon(scaleMotorID0);
		scaleMotor1 = new Talon(scaleMotorID1);

		robotDrive = new RobotDrive(frontLeftMotor,backLeftMotor, frontRightMotor, backRightMotor);
		robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
		robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
		robotDrive.setInvertedMotor(MotorType.kRearRight, true);
	}
}