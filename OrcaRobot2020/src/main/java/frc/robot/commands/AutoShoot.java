/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Flywheel;

public class AutoShoot extends CommandBase {
  /**
   * Creates a new AutoShoot.
   */
Conveyor Convey;
Flywheel fly;
  public AutoShoot() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   // flywheel.FlyAcceleration();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
  boolean Done = false;  
  boolean stop = false; 
  double time = Timer.getFPGATimestamp();


  while (Done = false){
    if (stop = false){
       // Flywheel.Run();
        Convey.openHopperToFlyWheel();
       Convey.raiseConveyor();
    }
    
    else{
      fly.stop();
      Convey.stopConveyor();
      Done = true;
      }
    
    
      if (time > 7){
      stop = true;
      }
    }
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
