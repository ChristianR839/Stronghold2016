package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.AutoRotateArmCommand;
import org.usfirst.frc.team839.robot.commands.DriveCommand;
import org.usfirst.frc.team839.robot.commands.OuttakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveAndTurnChevalle extends CommandGroup
{
	{

		CommandGroup command = new CommandGroup();
		command.addParallel(new DriveCommand(48, 0,0,0, false));
		command.addParallel(new WaitAndRaiseArm());
	
		
		addSequential (new DriveCommand(44, 0, 0));//to defense
		addSequential (new AutoRotateArmCommand(2000));// 3460 push down chevalle
		addSequential (command);
		addSequential (new DriveCommand(125, 0,0,0, true));//drive straight
		addSequential (new DriveCommand(70, 60,40,70, true)); //turn to castle
		addSequential (new WaitCommand(1.5) );
		addSequential (new SlowShot());
		
//		addSequential (new DriveCommand(44, 0, 0, 0,true));//to defense
//		addSequential (new AutoRotateArmCommand(3460));//push down chevalle
//		addSequential (new DriveCommand(48, 0,1,45, false));//cross defense half way
//		addSequential (new DriveCommand(125, 0,0,0, true));//drive straight
//		addSequential (new DriveCommand(70, 60,50,70, true)); //turn to castle
//		addSequential (new WaitCommand(1.5) );
//		addSequential (new OuttakeCommand(2)); //score
	}
}