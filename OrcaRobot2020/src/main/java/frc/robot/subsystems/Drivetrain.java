/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  //private DifferentialDrive drivetrain;
  private static CANSparkMax[] motors;
  private static SpeedControllerGroup right_motors;
  private static SpeedControllerGroup left_motors;

  public Drivetrain() {

    motors = new CANSparkMax[] { new CANSparkMax(Constants.rr_motor_id, MotorType.kBrushless),
        new CANSparkMax(Constants.fr_motor_id, MotorType.kBrushless),
        new CANSparkMax(Constants.rl_motor_id, MotorType.kBrushless),
        new CANSparkMax(Constants.fl_motor_id, MotorType.kBrushless) };

    right_motors = new SpeedControllerGroup(motors[0], motors[1]);
    left_motors = new SpeedControllerGroup(motors[3], motors[2]);

    motors[0].follow(motors[1]);
    motors[3].follow(motors[2]);
    // drivetrain = new DifferentialDrive(left_motors, right_motors);
  }

  public static void AutoD(double l, double r) {
   // motors[1].set((l));
   // motors[2].set((r));
}

public static void WinchUp(XboxController driver){
  motors[1].set(driver.getTriggerAxis(GenericHID.Hand.kLeft)* -1);
  motors[2].set(driver.getTriggerAxis(GenericHID.Hand.kLeft));
}

public static void WinchDown(XboxController driver){
  motors[1].set(driver.getTriggerAxis(GenericHID.Hand.kRight));   
  motors[2].set(driver.getTriggerAxis(GenericHID.Hand.kRight) * -1);
}
 
public static void Drive(double l, double r, XboxController driver) {
    if (driver.getYButton() == true) {
      WinchDown(driver);
    }
    else{
      left_motors.set(TrueRightX(l));
      right_motors.set(TrueLeftX(-r));
          }

   
       }
    
       public static double TrueLeftX(double LY) {
    
          // Used to get the absolute position of our Left control stick Y-axis (removes
          // deadzone)
    
          double stick = LY ;
    
          stick *= Math.abs(stick);
    
          if (Math.abs(stick) < 0.1) {
    
             stick = 0;
    
          }
    
          return stick;
    
       }
    
       public static double TrueRightX(double RY) {
    
        // Used to get the absolute position of our Right control stick Y-axis (removes deadzone)
    
        double stick = RY;
    
        stick *= Math.abs(stick);
    
       if (Math.abs(stick) < 0.1) {
    
           stick = 0;
    
        }
    
     return stick;
      }

  public void _StAAapP(){
    for (CANSparkMax t : motors) {
      t.set(0);
    } 
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
