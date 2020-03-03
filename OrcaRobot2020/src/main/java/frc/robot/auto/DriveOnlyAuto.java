package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class DriveOnlyAuto extends SequentialCommandGroup
{

    public DriveOnlyAuto(Drivetrain driveTrain)
    {
        addCommands(
            new RunCommand(()->driveTrain.specificDrive(Constants.kAutoSpeed, Constants.kAutoSpeed)),
            new WaitCommand(1),
            new InstantCommand(driveTrain::stop, driveTrain)
        );
    }

}
