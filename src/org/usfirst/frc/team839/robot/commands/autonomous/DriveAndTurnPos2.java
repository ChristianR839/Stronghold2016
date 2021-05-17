package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.AutoRotateArmCommand;
import org.usfirst.frc.team839.robot.commands.DriveCommand;
import org.usfirst.frc.team839.robot.commands.OuttakeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveAndTurnPos2 extends CommandGroup
{
	{
		addSequential (new DriveCommand(70, 0,0,0, false));//was 235
		addSequential (new DriveCommand(155, 0,0,0, true));//was 235
		addSequential (new AutoRotateArmCommand(2000));//3460
		addSequential (new DriveCommand(70, 60,40,70, true));//35
		addSequential (new WaitCommand(1.5) );
		addSequential (new SlowShot());
		
//		addSequential (new DriveCommand(50, 0,0,0, false));//was 235
//		addSequential (new DriveCommand(175, 0,0,0, true));//was 235
//		addSequential (new AutoRotateArmCommand(3460));//3460
//		addSequential (new DriveCommand(70, 60,50,70, true));//35
//		addSequential (new WaitCommand(1.5) );
//		addSequential (new OuttakeCommand(2));
	}
}