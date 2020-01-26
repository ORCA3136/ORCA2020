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

public class Drivetrain extends SubsystemBase {
  // Motor Controllers:
  private static CANSparkMax m_Left1 = new CANSparkMax(Constants.kLFDrive,MotorType.kBrushless);
  private static CANSparkMax m_Left2 = new CANSparkMax(Constants.kLBDrive, MotorType.kBrushless);
  private static CANSparkMax m_Right1 = new CANSparkMax(Constants.kRFDrive,MotorType.kBrushless);
  private static CANSparkMax m_Right2 = new CANSparkMax(Constants.kRBDrive,MotorType.kBrushless);

  // SpeedControllerGroups:
  private static SpeedControllerGroup m_left = new SpeedControllerGroup(m_Left1, m_Left2);
   private static SpeedControllerGroup m_right = new SpeedControllerGroup(m_Right2, m_Right1);

   /**
    * Runs arcade drive on this drivetrain
    * 
    * @param move The speed to move forward
    * @param turn The speed to turn the robot
    */
   public void Drive(double l, double r) {
      m_left.set(TrueLeftX(l));
      m_right.set(TrueRightX(-r));
   }

   public static void AutoD(double l, double r) {
      m_left.set((l));
      m_right.set((r));
  }
  /**
   * Reverses the forward direction of the robot
   */
  public double TrueLeftX(double LY){

    // Used to get the absolute position of our Left control stick Y-axis (removes deadzone)

  
    double stick = LY * Constants.kLeftDriveScaling;

    stick *= Math.abs(stick);

   if (Math.abs(stick) < 0.1) {

       stick = 0;

    }

    return stick;

 }

 public double TrueRightX(double RY){

    // Used to get the absolute position of our Right control stick Y-axis (removes deadzone)

    double stick = RY  * Constants.kLeftDriveScaling;

    stick *= Math.abs(stick);

   if (Math.abs(stick) < 0.1) {

       stick = 0;

    }

 return stick;
}
}
