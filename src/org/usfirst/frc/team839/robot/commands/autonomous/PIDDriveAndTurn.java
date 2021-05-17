package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.DrivePIDCommand;
import org.usfirst.frc.team839.robot.commands.GyroPIDTurnCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class PIDDriveAndTurn extends CommandGroup
{
	{
//		addSequential(new AutoRotateArmCommand(4600));
		addSequential (new DrivePIDCommand(144) );
		addSequential (new WaitCommand(0.5) );
		addSequential(new GyroPIDTurnCommand(90.0));
//		addSequential (new WaitCommand(0.5) );
//		addSequential (new DrivePIDCommand(12) );
	}
} 