/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.GenericHID;

public class Intake extends SubsystemBase {
  DoubleSolenoid IntakeSoli = new DoubleSolenoid(Constants.kIntakeForward, Constants.kIntakeReverse);
  // Motor Controllers:
  private CANSparkMax m_intake1;
  private static CANSparkMax m_intake2;
  Conveyor m_convey;
  boolean togglePressed, toggleOn; 

  public Intake(Conveyor conv){
    m_intake1 = new CANSparkMax(Constants.kIntake1, MotorType.kBrushless);
    m_intake2 = new CANSparkMax(Constants.kIntake2, MotorType.kBrushless);
    m_convey = conv;
    togglePressed = false;
    toggleOn = false;

  }

  @Override
  public void periodic(){

    

  }
  /**
   * Runs the intake motor inward
   */
  public void intakeIn(XboxController driver) {
    m_intake2.set(driver.getTriggerAxis(GenericHID.Hand.kLeft));
    m_intake1.set(driver.getTriggerAxis(GenericHID.Hand.kLeft) * -1);
    m_convey.raiseConveyor();

   
  }

  public static void spit() {
    m_intake2.set(
       Constants.kIntakeSpeed * -1);   
  }

  /**
   * Runs the intake motor outward
   */
  public void intakeOut(XboxController driver) {
    m_intake1.set(driver.getTriggerAxis(GenericHID.Hand.kRight));
    m_intake2.set(driver.getTriggerAxis(GenericHID.Hand.kRight) * -1);
    m_convey.lowerConveyor();
  }
 
  /**
   * Stops the intake motor
   */
  
   public void intakeStop() {
    m_intake1.set(
       0);
    m_intake2.set(
       0);
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
