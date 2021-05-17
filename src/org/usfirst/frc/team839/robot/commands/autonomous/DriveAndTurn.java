package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.AutoRotateArmCommand;
import org.usfirst.frc.team839.robot.commands.DriveCommand;
import org.usfirst.frc.team839.robot.commands.FastOuttakeCommand;
import org.usfirst.frc.team839.robot.commands.IntakeCommand;
import org.usfirst.frc.team839.robot.commands.OuttakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveAndTurn extends CommandGroup
{
	{
		CommandGroup group = new CommandGroup();
		group.addParallel (new DriveCommand(182, 0, 0, 0, true));//was 218
		group.addParallel (new IntakeCommand(2));


		addSequential (new AutoRotateArmCommand(2000));//3460
		addSequential (group);
		addSequential (new DriveCommand(103, 60,30, 95, true));//90
		addSequential (new WaitCommand(1.5) );
		addSequential (new SlowShot());
		
//	CommandGroup group = new CommandGroup();
//	group.addParallel (new DriveCommand(190, 0, 0, 0, true));//was 218
//	group.addParallel (new IntakeCommand(2));
//
//		addSequential (new AutoRotateArmCommand(3460));//3460
//		addSequential (group);
//		addSequential (new DriveCommand(103, 60,80, 95, true));//90
//		addSequential (new WaitCommand(1.5) );
//		addSequential (new OuttakeCommand(2));
	}
}