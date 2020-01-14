/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;


public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  //private DifferentialDrive drivetrain;
  private CANSparkMax[] motors;
  private SpeedControllerGroup right_motors;
  private SpeedControllerGroup left_motors;

  public Drivetrain() {
   
    motors= new CANSparkMax[] { 
      new CANSparkMax(Constants.rr_motor_id,MotorType.kBrushless),
       new CANSparkMax(Constants.fr_motor_id,MotorType.kBrushless),
       new CANSparkMax(Constants.rl_motor_id,MotorType.kBrushless), 
       new CANSparkMax(Constants.rl_motor_id,MotorType.kBrushless) };
    
     
    right_motors = new SpeedControllerGroup(motors[1], motors[2]);
    left_motors = new SpeedControllerGroup(motors[4], motors[3]);
    //drivetrain = new DifferentialDrive(left_motors, right_motors);
  }

  public void drive(double l, double r){
    


   
    motors[0].set(l);

    right_motors.set(r);
    
    SmartDashboard.putNumber("r", r);
    SmartDashboard.putNumber("l", l);
  }

  public void drive(Joystick joy){
    double l = -joy.getRawAxis(1);
    double r = joy.getRawAxis(5);
   
    drive(l,r);
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
