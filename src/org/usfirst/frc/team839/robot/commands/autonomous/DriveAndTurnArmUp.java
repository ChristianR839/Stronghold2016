package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndTurnArmUp extends CommandGroup
{
	{
		addSequential (new DriveCommand(190, 0, 0, 0, false));

//		addSequential (new DriveCommand(190, 0, 0, 0, true));
	}
}