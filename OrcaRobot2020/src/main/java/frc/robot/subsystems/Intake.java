/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  DoubleSolenoid IntakeSoli = new DoubleSolenoid(Constants.kIntakeForward, Constants.kIntakeReverse);
  // Motor Controllers:
  private CANSparkMax bottomRoller;
  private static CANSparkMax topRoller;
  Conveyor m_convey;
  boolean togglePressed, toggleOn; 
  CANEncoder topEncoder, bottomEncoder;

  public Intake(Conveyor conv){
    bottomRoller = new CANSparkMax(Constants.kIntake1, MotorType.kBrushless);
    topRoller = new CANSparkMax(Constants.kIntake2, MotorType.kBrushless);
    topEncoder= topRoller.getEncoder();
    bottomEncoder = bottomRoller.getEncoder();
    m_convey = conv;
    togglePressed = false;
    toggleOn = false;

  }

  @Override
  public void periodic(){

    SmartDashboard.putNumber(   "Bottom Roller Velocity", bottomEncoder.getVelocity());
    SmartDashboard.putNumber(   "Top Roller Velocity",    topEncoder.getVelocity());
    SmartDashboard.putNumber(   "Bottom Roller Current", bottomRoller.getOutputCurrent());
    SmartDashboard.putNumber(   "Top Roller Current",    topRoller.getOutputCurrent());
    

  }
  /**
   * Runs the intake motor inward
   */
  public void intakeIn() {
    topRoller.set(Constants.kConveyorSpeed);
    bottomRoller.set(Constants.kConveyorSpeed * -1);
    m_convey.raiseConveyor();
  }
  /**
   * Runs the intake motor outward
   */
  public void intakeOut() {
    bottomRoller.set(Constants.kConveyorSpeed/2);
    topRoller.set((Constants.kConveyorSpeed * -1)/2);
    m_convey.lowerConveyor();
  }
 
  /**
   * Stops the intake motor
   */
  
   public void intakeStop() {
    bottomRoller.set(
       0);
    topRoller.set(
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
