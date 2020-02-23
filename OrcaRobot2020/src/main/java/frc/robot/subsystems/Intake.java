/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  DoubleSolenoid IntakeSoli = new DoubleSolenoid(1, 2);
  // Motor Controllers:
  private VictorSPX m_intake1, m_intake2;
  Conveyor m_convey;
  boolean togglePressed, toggleOn; 

  public Intake(Conveyor conv){
    m_intake1 = new VictorSPX(Constants.kIntake1);
    m_intake2 = new VictorSPX(Constants.kIntake2);
    m_convey = conv;
    togglePressed = false;
    toggleOn = false;

  }

  @Override
  public void periodic(){

    SmartDashboard.putNumber("Motor One Voltz: ", m_intake1.getMotorOutputVoltage());
    SmartDashboard.putNumber("Motor Two Voltz: ", m_intake2.getMotorOutputVoltage());
    

  }
  /**
   * Runs the intake motor inward
   */
  public void intakeIn() {
    m_intake1.set(ControlMode.PercentOutput, Constants.kIntakeSpeed * -1);
    m_intake2.set(ControlMode.PercentOutput, Constants.kIntakeSpeed);
    m_convey.raiseConveyor();

   
  }

  /**
   * Runs the intake motor outward
   */
  public void intakeOut() {
    m_intake1.set(ControlMode.PercentOutput, Constants.kIntakeSpeed);
    m_intake2.set(ControlMode.PercentOutput, Constants.kIntakeSpeed * -1);
    m_convey.lowerConveyor();
  }
 
  /**
   * Stops the intake motor
   */
  
   public void intakeStop() {
    m_intake1.set(ControlMode.PercentOutput, 0);
    m_intake2.set(ControlMode.PercentOutput, 0);
    m_convey.stopConveyor();
  }


  public void initDefaultCommand() {
    retractIntake();
  }

  public void deployIntake() {
    IntakeSoli.set(DoubleSolenoid.Value.kForward);
  }

  public void retractIntake(){
    IntakeSoli.set(Value.kReverse);
  }

  public void off(){
    IntakeSoli.set(Value.kOff);
}


public void toggle(){
  updateToggle();
  if(toggleOn){
    deployIntake();
  }
  else{
    retractIntake();
  }
 
 
 }
 private void updateToggle()
 {
   if(!togglePressed){
     toggleOn=!toggleOn;
     togglePressed=true;
   }
   else{
     togglePressed = true;
   }
 }
}
