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


public class Conveyor extends SubsystemBase {
  // Motor Controllers:
  private TalonSRX m_conveyor1 = new TalonSRX(Constants.kConveyor1);
  private TalonSRX m_conveyor2 = new TalonSRX(Constants.kConveyor2);
  DoubleSolenoid ConveySoli = new DoubleSolenoid(0, 1);

  /**
   * Runs conveyor up
   */
  public void raiseConveyor() {
    m_conveyor1.set(ControlMode.PercentOutput, -Constants.kConveyorSpeed);
    m_conveyor2.set(ControlMode.PercentOutput, Constants.kConveyorSpeed);
  }

  /**
   * Runs conveyor down
   */
  public void lowerConveyor() {
    m_conveyor1.set(ControlMode.PercentOutput, Constants.kConveyorSpeed);
    m_conveyor2.set(ControlMode.PercentOutput, -Constants.kConveyorSpeed);
  }

  /**
   * Stops conveyor
   */
  public void stopConveyor() {
    m_conveyor1.set(ControlMode.PercentOutput, 0);
    m_conveyor2.set(ControlMode.PercentOutput, 0);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    reverse();
    stopConveyor();
  }

  public void toggle() {
  //Switches between fired a reteacted per called
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
