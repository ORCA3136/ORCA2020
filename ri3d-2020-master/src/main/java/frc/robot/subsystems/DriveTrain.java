/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain extends SubsystemBase {
  // Motor Controllers:
  private CANSparkMax m_Left1 = new CANSparkMax(Constants.kLFDrive,MotorType.kBrushless);
  private CANSparkMax m_Left2 = new CANSparkMax(Constants.kLBDrive, MotorType.kBrushless);
  private CANSparkMax m_Right1 = new CANSparkMax(Constants.kRFDrive,MotorType.kBrushless);
  private CANSparkMax m_Right2 = new CANSparkMax(Constants.kRBDrive,MotorType.kBrushless);

  // SpeedControllerGroups:
  private SpeedControllerGroup m_left = new SpeedControllerGroup(m_Left1, m_Left2);
  private SpeedControllerGroup m_right = new SpeedControllerGroup(m_Right2, m_Right1);


  
  

  /**
   * Runs arcade drive on this drivetrain
   * @param move The speed to move forward
   * @param turn The speed to turn the robot
   */
  public void Drive(double l, double r) {
   m_left.set(-l);
   m_right.set(r);
  }

  /**
   * Reverses the forward direction of the robot
   */

}
