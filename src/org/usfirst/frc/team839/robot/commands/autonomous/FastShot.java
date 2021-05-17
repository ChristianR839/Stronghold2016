package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.FastOuttakeCommand;
import org.usfirst.frc.team839.robot.commands.IntakeCommand;
import org.usfirst.frc.team839.robot.commands.OuttakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class FastShot extends CommandGroup
{
	{
		CommandGroup group = new CommandGroup();
		group.addSequential (new WaitCommand(1.5));
		group.addSequential (new OuttakeCommand(1));
		
		

		group.addSequential (new IntakeCommand(.25));
		addParallel (new FastOuttakeCommand(2.5));
		addParallel (group);
	}
}
