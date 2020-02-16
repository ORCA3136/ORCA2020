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
<<<<<<< Updated upstream
  //private DifferentialDrive drivetrain;
<<<<<<< Updated upstream
=======
  
>>>>>>> Stashed changes
  private static CANSparkMax[] motors;
  private static SpeedControllerGroup right_motors;
  private static SpeedControllerGroup left_motors;
  static DoubleSolenoid PTOSoli = new DoubleSolenoid(6, 7);
<<<<<<< Updated upstream
=======
  private CANSparkMax[] motors;
  private SpeedControllerGroup right_motors;
  private SpeedControllerGroup left_motors;
  DoubleSolenoid PTOSoli = new DoubleSolenoid(6, 7);
  NetworkTable vision_table = NetworkTableInstance.getDefault().getTable("limelight");

  NetworkTableEntry target_valid = vision_table.getEntry("tv");
  NetworkTableEntry target_offset = vision_table.getEntry("tx");
>>>>>>> Stashed changes

=======
  private DifferentialDrive autoSteer;  
  
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======

  public void vision_align() {
    double output = 0;

    output = target_offset.getDouble(0) * Constants.visionP;
    

    output *= Constants.visionLimit;

    AutoD(-output, output);

}



>>>>>>> Stashed changes
=======

  public void visionAlignment(Limelight m_Limelight){

    boolean m_LimelightHasValidTarget = m_Limelight.findTarget();
   
    if (m_LimelightHasValidTarget){

      double m_LimelightDriveCommand = m_Limelight.getDrive();
      double m_LimelightSteerCommand = m_Limelight.getSteer();

      autoSteer.arcadeDrive(m_LimelightDriveCommand,m_LimelightSteerCommand);

    }else{

      autoSteer.arcadeDrive(0.0,0.0);

    }
>>>>>>> Stashed changes
//code for auto
  public void AutoD(double l, double r) {
    motors[1].set((l));
    motors[2].set((r));
  }
//winches up
  public void WinchUp(XboxController driver) {
    forward();
    motors[1].set(Constants.kWinchSpeed * -1);
    motors[2].set(Constants.kWinchSpeed);
  }
//winches down
  public void WinchDown(XboxController driver) {
    forward();
    
    motors[1].set(Constants.kWinchSpeed);
    motors[2].set(Constants.kWinchSpeed * -1);
    
  }
//manual drive
  public void Drive( XboxController controller) {
   
      left_motors.set(TrueRightX((controller.getY(GenericHID.Hand.kLeft) * Constants.kLeftDriveScaling)));
      right_motors.set(TrueLeftX((controller.getY(GenericHID.Hand.kRight) * -Constants.kLeftDriveScaling)));
    
  }
//fixes deadzone
  public double TrueLeftX(double LY) {
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
  public double TrueRightX(double RY) {
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
  public void _StAAapP() {
    for (CANSparkMax t : motors) {
      t.set(0);
    }
  }

  @Override
  public void periodic() {
  // This method will be called once per scheduler run
  reverse();
}
//fires forward
  public void forward() {
    PTOSoli.set(DoubleSolenoid.Value.kForward);

}
//fires back
public void reverse(){
  PTOSoli.set(Value.kReverse);
}
//holds 
public void off(){
  PTOSoli.set(Value.kOff);
}
public void initDefaultCommand() {
  reverse();
}
}
