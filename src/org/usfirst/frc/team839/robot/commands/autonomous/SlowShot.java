package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.FastOuttakeCommand;
import org.usfirst.frc.team839.robot.commands.IntakeCommand;
import org.usfirst.frc.team839.robot.commands.OuttakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class SlowShot extends CommandGroup
{
	{

		

		addParallel (new FastOuttakeCommand(1,.75));
		addParallel (new OuttakeCommand(1));
	}
}
