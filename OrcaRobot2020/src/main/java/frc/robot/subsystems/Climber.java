/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  DoubleSolenoid climberSol;

  public Climber() {
    climberSol = new DoubleSolenoid(Constants.kClimberForward,Constants.kClimberReverse);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void NukeClimb(XboxController Driver) {
    if(Driver.getStartButton()){
      erectClimber();
    }
    else{
      retractClimber();
    }
  }

  public void erectClimber(){
    climberSol.set(Value.kForward);
  }

  public void retractClimber(){
    climberSol.set(Value.kReverse);
  }

  public void stopSol(){
    climberSol.set(Value.kOff);
  }

  public void initDefaultCommand() {
    retractClimber();
  }
  
}
