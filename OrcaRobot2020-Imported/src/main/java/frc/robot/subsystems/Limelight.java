/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Limelight extends SubsystemBase{

  private final double STEER_K = 0.041;
  private final double DRIVE_K = 0.26;
  private final double MAX_DRIVE = 0.6;
  private final double DESIRED_TARGET_AREA = 13.0;

  private double tv, tx, ta;

  public boolean m_LimelightHasValidTarget;
  
   double changeLedMode;


  public Limelight(){
    
    m_LimelightHasValidTarget = false;
    NetworkTableInstance.getDefault().getTable("limelight-orca").getEntry("ledMode").setNumber(1);


    tv = NetworkTableInstance.getDefault().getTable("limelight-orca").getEntry("tv").getDouble(0);
    tx = NetworkTableInstance.getDefault().getTable("limelight-orca").getEntry("tx").getDouble(0);
    ta = NetworkTableInstance.getDefault().getTable("limelight-orca").getEntry("ta").getDouble(0);


  }
  
  public void periodic(){

    tv = NetworkTableInstance.getDefault().getTable("limelight-orca").getEntry("tv").getDouble(0);
    tx = NetworkTableInstance.getDefault().getTable("limelight-orca").getEntry("tx").getDouble(0);
    ta = NetworkTableInstance.getDefault().getTable("limelight-orca").getEntry("ta").getDouble(0);

    SmartDashboard.putNumber("tv: ", tv);
    SmartDashboard.putNumber("tx: ", tx);
    SmartDashboard.putNumber("ta: ", ta);
    SmartDashboard.putNumber("ledMode:", NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").getDouble(0));
    SmartDashboard.putBoolean("ValidTarget: ", m_LimelightHasValidTarget);

  }


  public boolean findTarget(){
  
    m_LimelightHasValidTarget = false;

    if(tv >= 1){

      m_LimelightHasValidTarget = true;
      return m_LimelightHasValidTarget;

    }else{

      return m_LimelightHasValidTarget;

    }

  }

  public void startTracking(){
    NetworkTableInstance.getDefault().getTable("limelight-orca").getEntry("ledMode").setNumber(3);
  }


  public void stopTracking(){

    m_LimelightHasValidTarget = false;
    NetworkTableInstance.getDefault().getTable("limelight-orca").getEntry("ledMode").setNumber(1);
  }

  public double getDrive(){

    double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;
    
    // don't let the robot drive too fast into the goal
    if (drive_cmd > MAX_DRIVE){ 
     
      drive_cmd = MAX_DRIVE;

    }

    return drive_cmd;

  }


  public double getSteer(){

    double steer_cmd = tx * STEER_K;
    
    return steer_cmd * -1;

  }

}