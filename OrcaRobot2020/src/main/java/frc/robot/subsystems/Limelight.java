/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.*;
<<<<<<< Updated upstream
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
=======
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
>>>>>>> Stashed changes

/**
 * Add your docs here.
 */
<<<<<<< Updated upstream
public class Limelight {
=======
public class Limelight extends Subsystem {
>>>>>>> Stashed changes

  private final double STEER_K = 0.03;
  private final double DRIVE_K = 0.26;
  private final double MAX_DRIVE = 0.6;
  private final double DESIRED_TARGET_AREA = 13.0;

<<<<<<< Updated upstream

  private double tv, tx, ta, led;
=======
  private Timer blinkDelay = new Timer();

  private double tv, tx, ty, ta;
>>>>>>> Stashed changes
  private NetworkTableEntry ledMode;

  public boolean m_LimelightHasValidTarget;


  public Limelight(){
    
    // Coding this at 1 A.M. EST
    // Please dont judge my method names
<<<<<<< Updated upstream
    m_LimelightHasValidTarget = false;
    
=======

    blinky();
    m_LimelightHasValidTarget = false;
>>>>>>> Stashed changes


  }
  
  public void periodic(){

    tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
<<<<<<< Updated upstream
    ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    ledMode = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode");

    led = ledMode.getDouble(0);
    SmartDashboard.putNumber("ledMode: ", led);
=======
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0); 
    ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

    ledMode = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode");
>>>>>>> Stashed changes

  
  }

<<<<<<< Updated upstream
=======
  public void blinky(){

    // handles led initialization blinks
    // if they dont blink, you done goofed my friend

    ledMode.setDouble(2);
    blinkDelay.delay(2);
    ledMode.setDouble(1);


  }
>>>>>>> Stashed changes

  public void initDefaultCommand(){
    // IM FORCED TO PUT THIS HERE FOR NOW

    //TODO: Find a way around this.
    
  }


  public boolean findTarget(){
    
    m_LimelightHasValidTarget = false;

<<<<<<< Updated upstream
    if(tv > 1){

      m_LimelightHasValidTarget = true;

=======
    ledMode.setDouble(3);
    
    

    if(tv > 1){

      m_LimelightHasValidTarget = true;
>>>>>>> Stashed changes
      return m_LimelightHasValidTarget;

    }else{

      return m_LimelightHasValidTarget;

    }

    
  }


  public void stopTracking(){

    m_LimelightHasValidTarget = false;
<<<<<<< Updated upstream
=======
    ledMode.setDouble(1);
>>>>>>> Stashed changes
    
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
