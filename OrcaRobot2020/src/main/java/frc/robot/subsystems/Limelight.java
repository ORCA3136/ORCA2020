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

/**
 * Add your docs here.
 */
public class Limelight extends SubsystemBase{

  private final double STEER_K = 0.03;
  private final double DRIVE_K = 0.26;
  private final double MAX_DRIVE = 0.6;
  private final double DESIRED_TARGET_AREA = 13.0;

  private double tv, tx, ta;
  private NetworkTableEntry ledMode;

  public boolean m_LimelightHasValidTarget;


  public Limelight(){
    
    m_LimelightHasValidTarget = false;


  }
  
  public void periodic(){

    tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

    ledMode = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode");  

    SmartDashboard.putNumber("ledMode: ", ledMode.getDouble(0));
  }


  public boolean findTarget(){
    
    m_LimelightHasValidTarget = false;
    ledMode.setDouble(3);
    
    

    if(tv > 1){

      m_LimelightHasValidTarget = true;
      return m_LimelightHasValidTarget;

    }else{

      return m_LimelightHasValidTarget;

    }

  }


  public void stopTracking(){

    m_LimelightHasValidTarget = false;
    ledMode.setDouble(1);
    
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
    
    return steer_cmd;

  }

}
