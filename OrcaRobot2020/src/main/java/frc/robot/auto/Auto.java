/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Auto extends CommandBase {
  /**
   * Creates a new Auto.
   */
  private Timer timer = new Timer();
  private Drivetrain m_Drivetrain;
  private boolean finished;

  public Auto(Drivetrain dt) {

    m_Drivetrain = dt;
    addRequirements(m_Drivetrain);
    finished = false;


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   
    double m_time = timer.get();

    SmartDashboard.putNumber("Auto: ", m_time);

    if(m_time < 5){
      m_Drivetrain.AutoD(.25, -.25);
    }
  
      
      finished = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
