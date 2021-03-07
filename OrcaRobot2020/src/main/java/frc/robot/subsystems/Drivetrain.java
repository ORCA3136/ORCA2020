/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  private DoubleSolenoid PTOSoli;
  private DifferentialDrive diffDrive;  

  private CANEncoder leftEncoder;
  private CANEncoder rightEncoder;
    
  private CANPIDController leftController;
  private CANPIDController rightController;
  

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
    //setup the L / R Encoders
    
    leftEncoder = motors[1].getEncoder();
    rightEncoder = motors[2].getEncoder();

    //setup the L / R PIDControllers

    leftController = motors[1].getPIDController();  
    rightController = motors[2].getPIDController();

    //setup the L / R controller feedbackDevice
    leftController.setFeedbackDevice(leftEncoder);
    rightController.setFeedbackDevice(rightEncoder);
    
    diffDrive = new DifferentialDrive(left_motors, right_motors);
    PTOSoli = new DoubleSolenoid(Constants.kPTOForward, Constants.kPTOReverse);
    
    engageDrivePTO();
  }
  


  public void visionAlignment(Limelight m_Limelight){

    boolean m_LimelightHasValidTarget = m_Limelight.findTarget();
    
    

    if (m_LimelightHasValidTarget){

      //double m_LimelightDriveCommand = m_Limelight.getDrive();
      double m_LimelightSteerCommand = m_Limelight.getSteer();
      SmartDashboard.putNumber("Steer Command: ", m_LimelightSteerCommand);
      diffDrive.arcadeDrive(0,m_LimelightSteerCommand);

    }else{

     diffDrive.arcadeDrive(0.0,0.0);

    }
  }

  //code for auto
  public void autonomousDrive() {
    engageDrivePTO();
    diffDrive.tankDrive(1, -1,true);
  }

  /**
    * used to set the motors to a specific value....
    */  
  public boolean specificDrive(double distance) {
    boolean complete = false;
    getLeftEncoder().setPosition(0); //set the position to 0
    Double leftPosition = getLeftEncoder().getPosition();
    SmartDashboard.putNumber("Left Enc Pos: ", leftPosition);
    
    //really only need to get this once...
    int perRev =  getLeftEncoder().getCountsPerRevolution();
    double totalRevolutions = distance*perRev;
    double currentRevolutions = 0;
    engageDrivePTO();
    while(currentRevolutions<totalRevolutions)
    {
      //set the motors to running
      diffDrive.tankDrive(Constants.kAutoSpeedL, Constants.kAutoSpeedR,true);
      currentRevolutions = getLeftEncoder().getPosition() * perRev;
      SmartDashboard.putNumber("Current Revs", currentRevolutions);
    }
    complete = true;

    return complete;
  }


//winches up
  public void winchUp(XboxController controller) {
    engageClimbPTO();
    diffDrive.tankDrive(trueRightX((controller.getY(GenericHID.Hand.kLeft) * Constants.kLeftDriveScaling)), trueRightX((controller.getY(GenericHID.Hand.kRight) * Constants.kLeftDriveScaling)), true);
  }


//winches down
  public void winchDown(XboxController driver) {
    if (driver.getStickButton(GenericHID.Hand.kRight) && driver.getYButton()){
      engageClimbPTO();
      motors[1].set(Constants.kWinchSpeed);
      motors[2].set(Constants.kWinchSpeed * -1);
    }
  }
//manual drive
  public void driveSlow( XboxController controller) {
      engageDrivePTO();
      diffDrive.tankDrive(trueRightX((controller.getY(GenericHID.Hand.kLeft) * Constants.kLeftDriveScaling/2)), trueRightX((controller.getY(GenericHID.Hand.kRight) * Constants.kLeftDriveScaling/2)), true);
      //left_motors.set(trueLeftX((controller.getY(GenericHID.Hand.kLeft) * Constants.kLeftDriveScaling)));   
  }

  //slow manual drive
  public void drive( XboxController controller) 
  {
      engageDrivePTO();
      diffDrive.tankDrive(trueRightX((controller.getY(GenericHID.Hand.kLeft)* Constants.kLeftDriveScaling)), trueRightX((controller.getY(GenericHID.Hand.kRight)* Constants.kRightDriveScaling)), true);
      //left_motors.set(trueLeftX((controller.getY(GenericHID.Hand.kLeft) * Constants.kLeftDriveScaling)));
     
  }

//fixes deadzone
  public double trueRightX(double RY) {
    // Used t get the absolute position of our Left control stick Y-axis (removes
    // deadzone)
    double stick = RY;
    stick *= Math.abs(stick);
    if (Math.abs(stick) < 0.1) {
      stick = 0;
    }
    return stick;
  }
//fixes deadzone
  public double trueLeftX(double LY) {
    // Used to get the absolute position of our Right control stick Y-axis (removes
    // deadzone)
    double stick = LY;
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

  }

//fires back
public void engageDrivePTO(){
  PTOSoli.set(Value.kReverse);
}
//holds 
public void engageClimbPTO(){
  PTOSoli.set(Value.kForward);
}

public CANEncoder getLeftEncoder()
{
  return leftEncoder;
}

public CANEncoder getRightEncoder()
{
  return rightEncoder;
}

public CANPIDController getLeftPIDController()
{
  return leftController;
}

public CANPIDController getRightPIDController()
{
  return rightController;
}


public void initDefaultCommand() {
 // engageDrivePTO();
}
}
