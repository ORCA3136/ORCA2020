/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  DoubleSolenoid IntakeSoli = new DoubleSolenoid(4, 5);
  // Motor Controllers:
  private TalonSRX m_intake1 = new TalonSRX(Constants.kIntake1);
  private TalonSRX m_intake2 = new TalonSRX(Constants.kIntake2);

  /**
   * Runs the intake motor inward
   */
  public void intakeIn() {
    m_intake1.set(ControlMode.PercentOutput, Constants.kIntakeSpeed * -1);
    m_intake2.set(ControlMode.PercentOutput, Constants.kIntakeSpeed * -1);
  }

  /**
   * Runs the intake motor outward
   */
  public void intakeOut() {
    m_intake1.set(ControlMode.PercentOutput, Constants.kIntakeSpeed);
    m_intake2.set(ControlMode.PercentOutput, Constants.kIntakeSpeed);
  }

  // toggles solenoid for the intake arms
  public void toggle_solenoid() {

  }

  /**
   * Stops the intake motor
   */
  public void intakeStop() {
    m_intake1.set(ControlMode.PercentOutput, 0);
    m_intake2.set(ControlMode.PercentOutput, 0);
  }

  public void initDefaultCommand() {
    reverse();
  }

  public void forward() {
    IntakeSoli.set(DoubleSolenoid.Value.kForward);
  }

  public void reverse(){
    IntakeSoli.set(Value.kReverse);
  }

  public void off(){
    IntakeSoli.set(Value.kOff);
}
}
