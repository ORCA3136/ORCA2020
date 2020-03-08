package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written
 * explicitly for pedagogical purposes. Actual code should inline a command this
 * simple with {@link edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class DriveOnlyAutonomousCommand extends CommandBase {
  // The subsystem the command runs on
  private final Drivetrain driveTrain;
  private boolean complete = false;
  public DriveOnlyAutonomousCommand(Drivetrain subsystem) {
    SmartDashboard.putNumber("Drive Distance", 42);
    driveTrain = subsystem;
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {
    double distance = SmartDashboard.getNumber("Drive Distance", -42);
    complete = driveTrain.specificDrive(distance);
  }

  @Override
  public boolean isFinished() {
    return complete;
  }
}