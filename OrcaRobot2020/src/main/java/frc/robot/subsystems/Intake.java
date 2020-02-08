/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  static DoubleSolenoid IntakeSoli = new DoubleSolenoid(4, 5);
  // Motor Controllers:
  private final static WPI_TalonSRX m_intake = new WPI_TalonSRX(Constants.kIntake);

  /**
   * Runs the intake motor inward
   */
  public static void intakeIn() {
    m_intake.set(Constants.kIntakeSpeed * -1);
  }

  /**
   * Runs the intake motor outward
   */
  public static void intakeOut() {
    m_intake.set(Constants.kIntakeSpeed);
  }

  // toggles solenoid for the intake arms
  public void toggle_solenoid() {

  }

  /**
   * Stops the intake motor
   */
  public static void intakeStop() {
    m_intake.stopMotor();
  }

  public void initDefaultCommand() {
    reverse();
  }

  public static void forward() {
    IntakeSoli.set(DoubleSolenoid.Value.kForward);
}

public void reverse(){
  IntakeSoli.set(Value.kReverse);
}

public void off(){
  IntakeSoli.set(Value.kOff);
}
}
