/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Conveyor extends SubsystemBase {
  // Motor Controllers:
  private final static WPI_TalonSRX m_conveyor = new WPI_TalonSRX(Constants.kConveyor);

  /**
   * Runs conveyor up
   */
  public static void raiseConveyor() {
    m_conveyor.set(-Constants.kConveyorSpeed);
  }

  /**
   * Runs conveyor down
   */
  public void lowerConveyor() {
    m_conveyor.set(Constants.kConveyorSpeed);
  }

  /**
   * Stops conveyor
   */
  public static void stopConveyor() {
    m_conveyor.stopMotor();
  }
}
