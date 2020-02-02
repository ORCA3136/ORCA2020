/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NeoSoli;

public class NeoSoliDefault extends CommandBase {
  /**
   * Creates a new NeoSoliDefault.
 * @param NeoSoli 
   */
  public NeoSoliDefault() {
   addRequirements(new NeoSoli()); 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NeoSoli.forward();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    NeoSoli.forward();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    NeoSoli.forward();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
