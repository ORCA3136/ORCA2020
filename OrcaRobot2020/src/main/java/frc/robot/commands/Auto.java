/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/*
package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeSoli;

public class Auto extends CommandBase {
  /**
   * Creates a new Auto.
   
  public double startTime;
  public Auto() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   startTime = Timer.getFPGATimestamp();
    IntakeSoli.forward();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   
   
    double time = Timer.getFPGATimestamp();
    if (time - startTime < 1){
      Drivetrain.AutoD(.4,.4);
    }  else {
      Drivetrain.AutoD(0,0);
    }
   
   
   Drivetrain.AutoD(.1,.1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
*/