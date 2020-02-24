/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
  */

  //private DifferentialDrive drivetrain;
  private CANSparkMax[] motors;
  private SpeedControllerGroup right_motors;
  private SpeedControllerGroup left_motors;
  DoubleSolenoid PTOSoli = new DoubleSolenoid(6, 4);
  private DifferentialDrive autoSteer;  
  

  public Drivetrain() {
//instantiates motors
    motors = new CANSparkMax[] { new CANSparkMax(Constants.rr_motor_id, MotorType.kBrushless),
        new CANSparkMax(Constants.fr_motor_id, MotorType.kBrushless),
        new CANSparkMax(Constants.rl_motor_id, MotorType.kBrushless),
        new CANSparkMax(Constants.fl_motor_id, MotorType.kBrushless) };

    right_motors = new SpeedControllerGroup(motors[0], motors[1]);
    left_motors = new SpeedControllerGroup(motors[3], motors[2]);
//creates leader-follower relationships
    motors[0].follow(motors[1]);
    motors[3].follow(motors[2]);
    autoSteer = new DifferentialDrive(left_motors, right_motors);
  }
  


  public void visionAlignment(Limelight m_Limelight){

    boolean m_LimelightHasValidTarget = m_Limelight.findTarget();
   
    if (m_LimelightHasValidTarget){

      //double m_LimelightDriveCommand = m_Limelight.getDrive();
      double m_LimelightSteerCommand = m_Limelight.getSteer();

      autoSteer.arcadeDrive(0,m_LimelightSteerCommand);

    }else{

      autoSteer.arcadeDrive(0.0,0.0);

    }
  }
//code for auto
  public void autonomousDrive(double l, double r) {
    motors[1].set((l));
    motors[2].set((r));
  }
//winches up
  public void winchUp(XboxController driver) {
    forward();
    motors[1].set(Constants.kWinchSpeed * -1);
    motors[2].set(Constants.kWinchSpeed);
  }
//winches down
  public void winchDown(XboxController driver) {
    if (driver.getStickButton(GenericHID.Hand.kRight) && driver.getYButton()
    /* && Nuke button on joystick. NUKE button will call this method*/){
    forward();
    
    motors[1].set(Constants.kWinchSpeed);
    motors[2].set(Constants.kWinchSpeed * -1);
    }
  }
//manual drive
  public void drive( XboxController controller) {
 
     left_motors.set(trueRightX((controller.getY(GenericHID.Hand.kLeft) * Constants.kLeftDriveScaling)));
      right_motors.set(trueLeftX((controller.getY(GenericHID.Hand.kRight) * -Constants.kLeftDriveScaling)));
  }
//fixes deadzone
  public double trueLeftX(double LY) {
    // Used t get the absolute position of our Left control stick Y-axis (removes
    // deadzone)
    double stick = LY;
    stick *= Math.abs(stick);
    if (Math.abs(stick) < 0.1) {
      stick = 0;
    }
    return stick;
  }
//fixes deadzone
  public double trueRightX(double RY) {
    // Used to get the absolute position of our Right control stick Y-axis (removes
    // deadzone)
    double stick = RY;
    stick *= Math.abs(stick);
    if (Math.abs(stick) < 0.1) {
      stick = 0;
    }
    return stick;
  }
//stops motorsS
  public void stop() {
    for (CANSparkMax t : motors) {
      t.set(0);
    }
  }

  @Override
  public void periodic() {
  // This method will be called once per scheduler run
  engageDrivePTO();
}
//fires forward
  public void forward() {
    PTOSoli.set(DoubleSolenoid.Value.kForward);

}
//fires back
public void engageDrivePTO(){
  PTOSoli.set(Value.kReverse);
}
//holds 
public void engageClimbPTO(){
  PTOSoli.set(Value.kOff);
}


public void initDefaultCommand() {
  engageDrivePTO();
}
}
