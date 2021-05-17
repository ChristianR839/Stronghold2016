package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.AutoRotateArmCommand;
import org.usfirst.frc.team839.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossChevalle extends CommandGroup
{
	{
		CommandGroup command = new CommandGroup();
		command.addParallel(new DriveCommand(48, 0,0,0, false));
		command.addParallel(new WaitAndRaiseArm());
		addSequential (new DriveCommand(44, 0, 0));//to defense
		addSequential (new AutoRotateArmCommand(2000));// 3460 push down chevalle
		addSequential (command);
		addSequential (new DriveCommand(50, 0,0,0, true));//drive straight
	}
}