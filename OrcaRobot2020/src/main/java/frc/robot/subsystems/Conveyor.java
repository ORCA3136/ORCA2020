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
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Conveyor extends SubsystemBase {
  // Motor Controllers:
  private VictorSPX m_conveyor1;
  private VictorSPX m_conveyor2;
  private DoubleSolenoid ConveySoli;


  public Conveyor(){
    m_conveyor1 = new VictorSPX(Constants.kConveyor1);
    m_conveyor2 = new VictorSPX(Constants.kConveyor2);
    ConveySoli = new DoubleSolenoid(0, 3);
  }


  /**
   * Runs conveyor up
   */
  public void raiseConveyor() {
    m_conveyor1.set(ControlMode.PercentOutput, Constants.kConveyorSpeed);
    m_conveyor2.set(ControlMode.PercentOutput, Constants.kConveyorSpeed *-1);
  }

  /**
   * Runs conveyor down
   */
  public void lowerConveyor() {
    m_conveyor1.set(ControlMode.PercentOutput, Constants.kConveyorSpeed * -1);
    m_conveyor2.set(ControlMode.PercentOutput, Constants.kConveyorSpeed);
  }

  /**
   * Stops conveyor
   */
  public void stopConveyor() {
    m_conveyor1.set(ControlMode.PercentOutput, 0);
    m_conveyor2.set(ControlMode.PercentOutput, 0);
  }

  public void toggle() {
  //Switches between fired a retracted per called
    if (Constants.ToggleSoli = true) {
      forward();
      Constants.ToggleSoli = false;
    } else if (Constants.ToggleSoli = false) {
      reverse();
      Constants.ToggleSoli = true;
    } else {
      off();
    }
  }

//fires 
public void forward() {
  ConveySoli.set(DoubleSolenoid.Value.kForward);
}
//retracts
public void reverse() {
  ConveySoli.set(Value.kReverse);
}

public void off() {
  ConveySoli.set(Value.kOff);
}
}
