package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.AutoRotateArmCommand;
import org.usfirst.frc.team839.robot.commands.DriveCommand;
import org.usfirst.frc.team839.robot.commands.FastOuttakeCommand;
import org.usfirst.frc.team839.robot.commands.IntakeCommand;
import org.usfirst.frc.team839.robot.commands.OuttakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveAndTurnDontShoot extends CommandGroup
{
	{
		CommandGroup group = new CommandGroup();
		group.addParallel (new DriveCommand(190, 0, 0, 0, true));//was 218
		group.addParallel (new IntakeCommand(2));


		addSequential (new AutoRotateArmCommand(3460));//3460
		addSequential (group);
	}
}