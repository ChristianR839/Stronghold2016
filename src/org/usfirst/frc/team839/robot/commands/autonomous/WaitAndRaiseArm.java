package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.AutoRotateArmCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class WaitAndRaiseArm extends CommandGroup
{
	{
		addSequential (new WaitCommand(1.5) );//cross defense half way
		addSequential (new AutoRotateArmCommand(-500));//push down chevalle
	}
}