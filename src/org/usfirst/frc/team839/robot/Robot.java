package org.usfirst.frc.team839.robot;

import org.usfirst.frc.team839.robot.commands.autonomous.CrossChevalle;
import org.usfirst.frc.team839.robot.commands.autonomous.DoNothing;
import org.usfirst.frc.team839.robot.commands.autonomous.DriveAndTurn;
import org.usfirst.frc.team839.robot.commands.autonomous.DriveAndTurnArmUp;
import org.usfirst.frc.team839.robot.commands.autonomous.DriveAndTurnChevalle;
import org.usfirst.frc.team839.robot.commands.autonomous.DriveAndTurnDontShoot;
import org.usfirst.frc.team839.robot.commands.autonomous.DriveAndTurnPos2;
import org.usfirst.frc.team839.robot.subsystems.Accessory;
import org.usfirst.frc.team839.robot.subsystems.ArmIntake;
import org.usfirst.frc.team839.robot.subsystems.ArmScale;
import org.usfirst.frc.team839.robot.subsystems.Drivetrain;
import org.usfirst.frc.team839.robot.subsystems.FastArmIntake;
import org.usfirst.frc.team839.robot.subsystems.FastArmOutake;
import org.usfirst.frc.team839.robot.subsystems.TargetingLight;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	Command autonomousCommand;
	
	public static OI oi;
	public static Drivetrain drivetrain;
	public static ArmIntake armintake;
	public static FastArmIntake fastarmintake;
	public static Accessory accessory;
	public static ArmScale armscale;

	SendableChooser chooser;
	private static final int refreshrate = 60;
	public static AHRS ahrs = new AHRS(SPI.Port.kMXP, (byte)refreshrate);

	public static FastArmOutake fastarmoutake;

	public static TargetingLight targetingLight;
	
//    private static SerialPort serialPort = null;

	//RobotDrive robotDrive;
	//Joystick left, right;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		RobotMap.init();

		drivetrain  = new Drivetrain();
		armintake = new ArmIntake();
		fastarmintake = new FastArmIntake();
		fastarmoutake = new FastArmOutake();
		accessory = new Accessory();
		armscale = new ArmScale();

		targetingLight = new TargetingLight();
		oi = new OI();

		chooser = new SendableChooser();
		chooser.addDefault("Auto: Do Nothing", new DoNothing() );
		chooser.addObject ("Auto: Drive And Turn", new DriveAndTurn() );
		chooser.addObject ("Auto: Low Bar (don't Shoot)",new DriveAndTurnDontShoot());
		chooser.addObject ("Auto: Drive And Turn Position 2", new DriveAndTurnPos2() );
		chooser.addObject ("Auto: Drive And Turn with Chevalle", new DriveAndTurnChevalle() );
		chooser.addObject ("Auto: Cross Chevalle", new CrossChevalle() );		
		chooser.addObject ("Auto: Drive Accross Defense (ARM UP)", new DriveAndTurnArmUp() );
//		chooser.addObject ("Auto: Drive And Turn (PID)", new PIDDriveAndTurn() );
		

//		serialPort = new SerialPort(115200, SerialPort.Port.kUSB);//= SerialPort.getCommPorts()[0];		serialPort.reset();
		
		CameraServer.getInstance().startAutomaticCapture();
		SmartDashboard.putData("Autonomous Mode", chooser);
        try 
        {
            /* Communicate w/navX MXP via the MXP SPI Bus.                                     */
            /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
            /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */

//    		SmartDashboard.putNumber("Angle", ahrs.getAngle());
//    		SmartDashboard.putNumber("getDisplacementX", ahrs.getDisplacementX());
//    		SmartDashboard.putNumber("getDisplacementY", ahrs.getDisplacementY());
//    		SmartDashboard.putNumber("getDisplacementZ", ahrs.getDisplacementZ());
            LiveWindow.addSensor("IMU"      , "Gyro"                , ahrs                      );

    		LiveWindow.run();

            ahrs.startLiveWindowMode();
        } 
        
        catch (RuntimeException ex ) 
        {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit()
	{

	}

	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit()
	{
		autonomousCommand = (Command)chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
    	RobotMap.backRightMotor.setEncPosition(0);
    	RobotMap.backLeftMotor.setEncPosition(0);
		ahrs.reset();
		ahrs.resetDisplacement();
		//System.out.println("ahrs.reset in Robot.autonomousInit()");
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{
//		SmartDashboard.putNumber("Angle", ahrs.getAngle());
//		SmartDashboard.putNumber("getDisplacementX", ahrs.getDisplacementX());
//		SmartDashboard.putNumber("getDisplacementY", ahrs.getDisplacementY());
//		SmartDashboard.putNumber("getDisplacementZ", ahrs.getDisplacementZ());
//
//		SmartDashboard.putNumber("RightEncoder", drivetrain.getRightEncoderPosition());
//		SmartDashboard.putNumber("LeftEncoder", drivetrain.getLeftEncoderPosition());
		Scheduler.getInstance().run();
	}

	public void teleopInit()
	{
		ahrs.reset();
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
//		   byte[] buffer = {'1',',','4','\n'};
//			if (serialPort.write(buffer, buffer.length) < 0)
//			    System.out.println("failed to write bytes");
//		        try {
//		            Thread.sleep(500);
//		        } catch (InterruptedException e) {
//		            e.printStackTrace();
//		    }
//			buffer[0] = '2';
//			buffer[2] = '3';
//
//			if (serialPort.write(buffer, buffer.length) < 0)
//			    System.out.println("failed to write bytes");
//			buffer[0] = '3';
//			buffer[2] = '2';
//			if (serialPort.write(buffer, buffer.length) < 0)
//			    System.out.println("failed to write bytes");

		//while(isOperatorControl() && isEnabled())
		//{
		//	robotDrive.tankDrive(left, right);
		//}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
//		SmartDashboard.putNumber("Angle", ahrs.getAngle());
//		SmartDashboard.putNumber("getDisplacementX", ahrs.getDisplacementX());
//		SmartDashboard.putNumber("getDisplacementY", ahrs.getDisplacementY());
//		SmartDashboard.putNumber("getDisplacementZ", ahrs.getDisplacementZ());
//		SmartDashboard.putNumber("roll", ahrs.getRoll());
//		SmartDashboard.putNumber("pitch", ahrs.getPitch());
//		SmartDashboard.putNumber("RightEncoder", drivetrain.getRightEncoderPosition());
//		SmartDashboard.putNumber("LeftEncoder", drivetrain.getLeftEncoderPosition());
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{
		SmartDashboard.putNumber("Angle", ahrs.getAngle());
		SmartDashboard.putNumber("RightEncoder", drivetrain.getRightEncoderPosition());
		SmartDashboard.putNumber("LeftEncoder", drivetrain.getLeftEncoderPosition());
        LiveWindow.addSensor("IMU"      , "Gyro"                , ahrs                      );

		LiveWindow.run();
	}
}
