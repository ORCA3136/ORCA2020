/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Flywheel;

public class Auto extends SequentialCommandGroup {
  private Drivetrain m_Drivetrain;
  private final Flywheel m_fly;
  private final Conveyor m_Conveyor;

  public Auto(Drivetrain dt,Flywheel fw,Conveyor cv) {

    m_Drivetrain = dt;
    m_Conveyor = cv;
    m_fly = fw;
  
    addCommands(
      new InstantCommand(m_Conveyor::closeHopperToFlywheel, m_Conveyor),
      new InstantCommand(() -> m_fly.runFlywheelWithoutPID()),
      new InstantCommand(m_Conveyor::stopConveyor, m_Conveyor),
      new WaitCommand(2),
      new InstantCommand(m_Conveyor::openHopperToFlyWheel, m_Conveyor)
     .andThen( new InstantCommand(m_Conveyor::raiseConveyor, m_Conveyor)),
     new WaitCommand(3),
     new InstantCommand(m_fly::stop, m_fly),
     new InstantCommand(m_Conveyor::stopConveyor, m_Conveyor),
     new InstantCommand(m_Conveyor::closeHopperToFlywheel, m_Conveyor),
     new WaitCommand(1),
     new InstantCommand(m_Conveyor::closeHopperToFlywheel, m_Conveyor),
     new InstantCommand(()-> m_Drivetrain.autonomousDrive()),
     new WaitCommand(1),
     new InstantCommand(()-> m_Drivetrain.stop())
     
    );
    


  }

  // // Called when the command is initially scheduled.
  // @Override
  // public void initialize() {
  //   timer.start();
  // }

  // // Called every time the scheduler runs while the command is scheduled.
  // @Override
  // public void execute() {
   
  //   double m_time = timer.get();

  //   SmartDashboard.putNumber("Auto: ", m_time);
  //   if( m_time < 5){
  //     new InstantCommand(() -> m_fly.runFlywheelWithoutPID());
  //     new WaitCommand(1);
  //     new InstantCommand(m_Conveyor::openHopperToFlyWheel, m_Conveyor)
  //    .andThen( new InstantCommand(m_Conveyor::raiseConveyor, m_Conveyor));
  //   }
  //   else if(m_time > 13){
  //     m_Drivetrain.autonomousDrive(.25, -.25);
  //   }
  
      
  //     finished = true;
  // }

  // // Called once the command ends or is interrupted.
  // @Override
  // public void end(boolean interrupted) {
  //   timer.stop();
  //   timer.reset();
  // }

  // // Returns true when the command should end.
  // @Override
  // public boolean isFinished() {
  //   new InstantCommand(m_fly::stop, m_fly);
  //   new InstantCommand(m_Conveyor::stopConveyor, m_Conveyor);
  //   new InstantCommand(m_Conveyor::closeHopperToFlywheel, m_Conveyor);
  //   return finished;
  // }
}
