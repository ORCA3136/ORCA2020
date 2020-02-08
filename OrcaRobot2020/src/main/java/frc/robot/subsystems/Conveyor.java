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


public class Conveyor extends SubsystemBase {
  // Motor Controllers:
  private final static WPI_TalonSRX m_conveyor1 = new WPI_TalonSRX(Constants.kConveyor);
  private final static WPI_TalonSRX m_conveyor2 = new WPI_TalonSRX(Constants.kConveyor);
  static DoubleSolenoid ConveySoli = new DoubleSolenoid(0, 1);
  

  /**
   * Runs conveyor up
   */
  public static void raiseConveyor() {
    m_conveyor1.set(-Constants.kConveyorSpeed);
    m_conveyor2.set(Constants.kConveyorSpeed);
  }

  /**
   * Runs conveyor down
   */
  public static void lowerConveyor() {
    m_conveyor1.set(Constants.kConveyorSpeed);
    m_conveyor2.set(-Constants.kConveyorSpeed);
  }

  /**
   * Stops conveyor
   */
  public static void stopConveyor() {
    m_conveyor1.stopMotor();
    m_conveyor2.stopMotor();
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    reverse();
  }

  public static void toggle() {
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
  public static void forward() {
    ConveySoli.set(DoubleSolenoid.Value.kForward);
}
//retracts
public static void reverse() {
  ConveySoli.set(Value.kReverse);
}

public static void off() {
  ConveySoli.set(Value.kOff);
}
}
