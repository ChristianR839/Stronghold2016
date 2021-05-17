/**
 * Rosie Robotics 2016
 */
package org.usfirst.frc.team839.robot;

import org.usfirst.frc.team839.robot.commands.ExtendCommand;
import org.usfirst.frc.team839.robot.commands.FastOuttakeCommand;
import org.usfirst.frc.team839.robot.commands.IntakeCommand;
import org.usfirst.frc.team839.robot.commands.OuttakeCommand;
import org.usfirst.frc.team839.robot.commands.RetractCommand;
import org.usfirst.frc.team839.robot.commands.ToggleFlashlightCommand;
import org.usfirst.frc.team839.robot.commands.autonomous.FastShot;
import org.usfirst.frc.team839.robot.commands.autonomous.SlowShot;
import org.usfirst.frc.team839.robot.commands.IntakeCommand;
import org.usfirst.frc.team839.robot.commands.OuttakeCommand;
import org.usfirst.frc.team839.robot.commands.RetractCommand;
import org.usfirst.frc.team839.robot.UniversalJoystick;
import org.usfirst.frc.team839.robot.subsystems.Accessory;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	public UniversalJoystick joystick1         = new UniversalJoystick(0);
	public UniversalJoystick accessoryController = new UniversalJoystick(1);
    Button intakeButton;
    Button outtakeButton;
    Button fastOuttakeButton;
    Button slowOuttakeButton;
    Button fastOutCommandButton;
    Button scaleExtendButton;
//    Button scaleRetractButton;
    
    Button flashlightToggleButton;
    Button driverFlashlightToggleButton;

    //public UniversalJoystick joystick2 = new UniversalJoystick(2);
    
	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
    public OI()
    {
    	intakeButton        = new JoystickButton(accessoryController, UniversalJoystick.kBtnA);
    	fastOutCommandButton= new JoystickButton(accessoryController, UniversalJoystick.kBtnY);
    	outtakeButton       = new JoystickButton(accessoryController, UniversalJoystick.kBtnRB);
    	fastOuttakeButton   = new JoystickButton(accessoryController, UniversalJoystick.kBtnLB);
    	slowOuttakeButton   = new JoystickButton(accessoryController, UniversalJoystick.kBtnB);
    	scaleExtendButton   = new JoystickButton(accessoryController, UniversalJoystick.kBtnBack);
//    	scaleRetractButton  = new JoystickButton(accessoryController, UniversalJoystick.kBtnStart);

    	flashlightToggleButton = new JoystickButton(accessoryController, UniversalJoystick.kBtnStart);
    	driverFlashlightToggleButton = new JoystickButton(joystick1, UniversalJoystick.kBtnStart);
    	
    	intakeButton         .whileHeld( new IntakeCommand     () );
    	outtakeButton        .whileHeld( new OuttakeCommand    () );
    	slowOuttakeButton    .whileHeld( new SlowShot() );
    	fastOuttakeButton    .whileHeld( new FastOuttakeCommand() );
    	fastOutCommandButton .whenPressed( new FastShot          () );
    	scaleExtendButton    .whileHeld( new ExtendCommand     () );
//    	scaleRetractButton   .whileHeld( new RetractCommand    () );
    	flashlightToggleButton  .whenPressed(new ToggleFlashlightCommand());
    	driverFlashlightToggleButton  .whenPressed(new ToggleFlashlightCommand());
    }
    
    public UniversalJoystick getJoystick1() 
    {
        return joystick1;
    }
    
    public void inwardsIntakeButton()
    {
    	if(joystick1.getBtnX() == true)
    	{
    		Robot.armintake.intake(1);
    	}
    	else
    	{
    		Robot.armintake.stop();
    	}
    }
    
    public void outwardsIntakeButton()
    {
    	if(joystick1.getBtnB() == true)
    	{
    		Robot.armintake.outtake(1);
    	}
    	else
    	{
    		Robot.armintake.stop();
    	}
    }
    
    public void scaleExtendButton()
    {
    	if(joystick1.getBtnBack() == true)
    	{
    		Robot.armscale.extend(1);
    	}
    	else
    	{
    		Robot.armscale.stop();
    	}
    }
    
    public void scaleRetractButton()
    {
    	if(joystick1.getBtnStart() == true)
    	{
    		Robot.armscale.retract(1);
    	}
    	else
    	{
    		Robot.armscale.stop();
    	}
    }
    
    public void mUpButton()
    {
    	if(joystick1.getBtnY() == true)
    	{
    		Accessory.up();
    	}
    	else
    	{
    		Accessory.stop();
    	}
    }
    
    public void armDownButton()
    {
    	if(joystick1.getBtnA() == true)
    	{
    		Accessory.down();
    	}
    	else
    	{
    		Accessory.stop();
    	}
    }
//    public UniversalJoystick getJoystick2() 
//    {
//        return joystick2;
//    }
    
    public double getLeftSpeed()
    {
    	if(joystick1.getBtnLB())
    		return joystick1.leftAxisY() * -0.7;
    	else
    		return joystick1.leftAxisY() * -1.0;
    }
    
    public double getRightSpeed()
    {
    	if(joystick1.getBtnRB())
    		return joystick1.rightAxisY() * -0.7;
    	else        
    		return joystick1.rightAxisY() * -1.0;
    }
}
